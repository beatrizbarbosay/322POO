package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaAdmin {

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Área do Administrador");

        Button btnCadastrarCarro = new Button("Cadastrar Carro");
        btnCadastrarCarro.setPrefWidth(600);
        btnCadastrarCarro.setPrefHeight(100);
        btnCadastrarCarro.setStyle("-fx-font-size: 28px;");
        Button btnCadastrarPiloto = new Button("Cadastrar Piloto");
        btnCadastrarPiloto.setPrefWidth(600);
        btnCadastrarPiloto.setPrefHeight(100);
        btnCadastrarPiloto.setStyle("-fx-font-size: 28px;");
        Button btnCriarCorrida = new Button("Criar Corrida");
        btnCriarCorrida.setPrefWidth(600);
        btnCriarCorrida.setPrefHeight(100);
        btnCriarCorrida.setStyle("-fx-font-size: 28px;");
        // Ações dos botões
        btnCadastrarCarro.setOnAction(e -> {
            TelaCadastroCarro.exibir();
        });

        btnCadastrarPiloto.setOnAction(e -> {
            TelaCadastroPiloto.exibir();
        });

        btnCriarCorrida.setOnAction(e -> {
            // TelaCriarCorrida.exibir();
            System.out.println("Abrir criação de corrida");
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(btnCadastrarCarro, btnCadastrarPiloto, btnCriarCorrida);

        Scene scene = new Scene(layout, 1200, 1000);
        scene.getStylesheets().add(TelaAdmin.class.getResource("/resources/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
