package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.DialogPane; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BancoUsuarios;
import model.Usuario;

/**
 * Tela de Login - Ponto de entrada principal do sistema.
 * Responsável por:
 * - Autenticar usuários
 * - Redirecionar para a tela adequada (Admin/Usuário)
 * - Permitir cadastro de novos usuários
 */
public class TelaLogin extends Application {
    @Override
    public void start(Stage primaryStage) {
        // COMPONENTES DA INTERFACE

        // Rótulo e campo para o nome de usuário
        Label lblUsuario = new Label("Usuário:");
        TextField tfUsuario = new TextField();

        // Rótulo e campo para a senha (com máscara de caracteres)
        Label lblSenha = new Label("Senha:");
        PasswordField pfSenha = new PasswordField();

        // Botões de ação
        Button btnEntrar = new Button("Entrar");
        btnEntrar.setDefaultButton(true);  // Permite acionar com a tecla Enter
        
        Button btnCadastrar = new Button("Cadastrar");

        // Layout
        VBox root = new VBox(10, lblUsuario, tfUsuario, lblSenha, pfSenha, btnEntrar, btnCadastrar);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Autenticação
        btnEntrar.setOnAction(e -> {
            // Obtém as credenciais informadas
            String nome = tfUsuario.getText();
            String senha = pfSenha.getText();

            // Verifica as credenciais no banco de usuários
            Usuario u = BancoUsuarios.autenticar(nome, senha);

            if (u != null) {
                if (u.isAdmin()) { // Autenticação bem-sucedida
                    TelaAdmin.exibir(); // Abre a interface de administrador
                } else {
                    TelaUsuario.exibir(nome); // Abre a interface de usuário comum
                }
                primaryStage.close(); // Fecha a tela de login
            } else {
                // Exibe mensagem de erro
                mostrarAlerta("Erro", "Usuário ou senha incorretos.");
                // Limpa o campo de senha para nova tentativa
                pfSenha.clear();
                // Retorna o foco para o campo de usuário
                tfUsuario.requestFocus();
            }
        });

        // Cadastro
        btnCadastrar.setOnAction(e -> {
            TelaCadastro.exibir(); // Abre a tela de cadastro
        });

        // Configuração da cena principal
        Scene scene = new Scene(root, 600, 500);

        // Carrega o arquivo CSS para estilização
        scene.getStylesheets().add(getClass().getResource("/resources/estilo.css").toExternalForm());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Foca automaticamente no campo de usuário ao abrir
        tfUsuario.requestFocus();
    }

    // Exibe uma mensagem de alerta estilizada
    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Aplica o estilo CSS personalizado ao alerta
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(TelaLogin.class.getResource("/resources/estilo.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");  // Opcional: adiciona uma classe CSS para alertas

        alert.showAndWait();
    }
    
    // Ponto de entrada principal da aplicação.
    public static void main(String[] args) {
        launch(args);
    }
}
