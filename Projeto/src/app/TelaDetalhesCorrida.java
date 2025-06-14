package app;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import model.Corrida;
import model.ParticipanteCorrida;

public class TelaDetalhesCorrida{
    private static Stage stage;

    public TelaDetalhesCorrida(Stage stage) {
        stage = new Stage();
    }

    // Exibe os detalhes de uma corrida específica
    public static void exibir(Corrida corrida) {
        Stage detalhesStage = new Stage();
        detalhesStage.setTitle("Detalhes da Corrida - " + corrida.getNome());

        // Título
        Label titulo = new Label(corrida.getNome());
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Informações básicas
        Label info = new Label(String.format("Local: %s | Distância: %.2f km", 
            corrida.getLocal(), corrida.getDistancia()));
        info.setStyle("-fx-font-size: 18px; -fx-text-fill: #34495e;");

        // Tabela de participantes
        TableView<ParticipanteCorrida> tabela = new TableView<>();
        tabela.setStyle("-fx-font-size: 18px;");
        tabela.setFixedCellSize(50);
        tabela.setPrefHeight(450);

        // Coluna de pilotos
        TableColumn<ParticipanteCorrida, String> colPiloto = new TableColumn<>();
        colPiloto.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getPiloto().getNome()));
        colPiloto.setPrefWidth(480);
        colPiloto.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");
        
        // Coluna de carros
        TableColumn<ParticipanteCorrida, String> colCarro = new TableColumn<>();
        colCarro.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarro().getModelo()));
        colCarro.setPrefWidth(480);
        colCarro.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");

        // Adiciona colunas e dados
        tabela.getColumns().addAll(colPiloto, colCarro);
        tabela.setItems(FXCollections.observableArrayList(corrida.getParticipantes()));

        // Configurar cabeçalhos
        colPiloto.setGraphic(createHeader("PILOTO"));
        colCarro.setGraphic(createHeader("CARRO"));

        // Botão para simular corrida
        Button btnSimular = new Button("SIMULAR CORRIDA");
        btnSimular.setStyle("-fx-font-size: 20px; -fx-padding: 15 40; -fx-background-color: #27ae60; -fx-text-fill: white;");

        // Layout
        VBox layout = new VBox(20, titulo, info, tabela, btnSimular);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f5f5f5;");

        //Configura e exibe a janela
        Scene scene = new Scene(layout, 1200, 800); // Ajuste de tamanho
        detalhesStage.setResizable(false);
        detalhesStage.setScene(scene);
        detalhesStage.centerOnScreen();
        detalhesStage.show();
    }

    // Método auxiliar para criar cabeçalhos estilizados
    private static Label createHeader(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        label.setPadding(new Insets(10));
        return label;
    }
}