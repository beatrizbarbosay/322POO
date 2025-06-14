package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaUsuario {
    private static Stage stage;
    private static String usuarioAtual;

    // Método que exibe a tela do usuário
    public static void exibir(String usuario) {
        usuarioAtual = usuario;
        stage = new Stage();
        stage.setTitle("Área do Usuário - " + usuario);

        // Título
        Label titulo = new Label("Bem-vindo, " + usuario + "!");
        titulo.getStyleClass().add("titulo-principal");

        // Botões principais
        Button btnCorridas = criarBotaoGrande("Corridas Disponíveis");
        Button btnEstatPilotos = criarBotaoGrande("Estatísticas dos Pilotos");
        Button btnEstatCarros = criarBotaoGrande("Dados dos Carros");
        Button btnHistApostas = criarBotaoGrande("Histórico de Apostas");

        // Ações dos botões
        btnCorridas.setOnAction(e -> TelaCorridasDisponiveis.exibir()); //Exibe a tela de corridas disponíveis
        btnEstatPilotos.setOnAction(e -> TelaEstatisticasPilotos.exibir());
        btnEstatCarros.setOnAction(e -> TelaEstatisticasCarros.exibir());
        btnHistApostas.setOnAction(e -> TelaHistoricoApostas.exibir());

        // Layout principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("painel-principal");
        layout.getChildren().addAll(titulo, btnCorridas, btnEstatPilotos, btnEstatCarros, btnHistApostas);

        // Cena principal
        Scene scene = new Scene(layout, 1200, 800);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
    }

    // Cria um botão grande com estilo padronizado
    private static Button criarBotaoGrande(String texto) {
        Button btn = new Button(texto);
        btn.getStyleClass().add("botao-grande");
        btn.setPrefWidth(600);
        btn.setPrefHeight(100);
        return btn;
    }
}