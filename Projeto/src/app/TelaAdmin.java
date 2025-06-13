package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaAdmin {

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Área do Administrador");

        // Criar componentes
        Label titulo = new Label("Menu do Administrador");
        titulo.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        Button btnCadastrarCarro = criarBotaoGrande("Cadastrar Carro");
        Button btnCadastrarPiloto = criarBotaoGrande("Cadastrar Piloto");
        Button btnCriarCorrida = criarBotaoGrande("Criar Corrida");

        // Configurar ações dos botões
        btnCadastrarCarro.setOnAction(e -> TelaCadastroCarro.exibir());
        btnCadastrarPiloto.setOnAction(e -> TelaCadastroPiloto.exibir());
        btnCriarCorrida.setOnAction(e -> TelaCriarCorrida.exibir());

        // Configurar layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("painel-principal");
        layout.getChildren().addAll(titulo, btnCadastrarCarro, btnCadastrarPiloto, btnCriarCorrida);

        // Configurar cena
        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(TelaAdmin.class.getResource("/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
    }

    private static Button criarBotaoGrande(String texto) {
        Button btn = new Button(texto);
        btn.setPrefWidth(300);
        btn.setPrefHeight(80);
        btn.setStyle("-fx-font-size: 20px;");
        return btn;
    }
}