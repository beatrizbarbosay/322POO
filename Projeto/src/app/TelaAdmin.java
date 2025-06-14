package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Tela principal do administrador com opções de cadastro e gerenciamento.
public class TelaAdmin {

    // Exibe a interface do administrador com botões para diferentes funcionalidades.
    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Área do Administrador");

        // Componentes da tela
        Label titulo = new Label("Menu do Administrador");
        titulo.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

        // Botões para cadastros
        Button btnCadastrarCarro = criarBotaoGrande("Cadastrar Carro");
        Button btnCadastrarPiloto = criarBotaoGrande("Cadastrar Piloto");
        Button btnCriarCorrida = criarBotaoGrande("Criar Corrida");

        // Ações dos botões
        btnCadastrarCarro.setOnAction(e -> TelaCadastroCarro.exibir());
        btnCadastrarPiloto.setOnAction(e -> TelaCadastroPiloto.exibir());
        btnCriarCorrida.setOnAction(e -> TelaCriarCorrida.exibir());

        // Layout
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("painel-principal");
        layout.getChildren().addAll(titulo, btnCadastrarCarro, btnCadastrarPiloto, btnCriarCorrida);

        // Configuração da cena
        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add(TelaAdmin.class.getResource("/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
    }

    // Cria um botão com estilo padronizado para a interface.
    private static Button criarBotaoGrande(String texto) {
        Button btn = new Button(texto);
        btn.setPrefWidth(300);
        btn.setPrefHeight(80);
        btn.setStyle("-fx-font-size: 20px;");
        return btn;
    }
}