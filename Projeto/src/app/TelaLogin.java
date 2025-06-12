package app;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class TelaLogin extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label lblUsuario = new Label("Usuário:");
        TextField tfUsuario = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField pfSenha = new PasswordField();

        Button btnEntrar = new Button("Entrar");
        Button btnCadastrar = new Button("Cadastrar");

        VBox root = new VBox(10, lblUsuario, tfUsuario, lblSenha, pfSenha, btnEntrar, btnCadastrar);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        btnEntrar.setOnAction(e -> {
            String nome = tfUsuario.getText();
            String senha = pfSenha.getText();
            Usuario u = BancoUsuarios.autenticar(nome, senha);
            if (u != null) {
                if (u.isAdmin()) {
                    TelaAdmin.exibir();
                } else {
                    TelaUsuario.exibir(nome);
                }
                primaryStage.close();
            } else {
                mostrarAlerta("Erro", "Usuário ou senha incorretos.");
            }
        });

        btnCadastrar.setOnAction(e -> {
            TelaCadastro.exibir();
        });

        Scene scene = new Scene(root, 600, 500);

        scene.getStylesheets().add(getClass().getResource("/resources/estilo.css").toExternalForm());


        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Aplica seu CSS ao diálogo do Alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(TelaLogin.class.getResource("/resources/estilo.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");  // Opcional: adiciona uma classe CSS para alertas

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
