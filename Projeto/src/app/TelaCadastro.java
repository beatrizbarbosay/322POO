package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BancoUsuarios;
import model.Usuario;

// Tela de cadastro de novos usuários (admin ou comum).
public class TelaCadastro {
    public static void exibir() {
        Stage stage = new Stage();

        // Campos do formulário
        Label lblNome = new Label("Usuário:");
        TextField tfNome = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField pfSenha = new PasswordField();

        CheckBox cbAdmin = new CheckBox("Administrador");

        Button btnCadastrar = new Button("Cadastrar");

        // Layout
        VBox root = new VBox(10, lblNome, tfNome, lblSenha, pfSenha, cbAdmin, btnCadastrar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Validação e cadastro
        btnCadastrar.setOnAction(e -> {
            String nome = tfNome.getText();
            String senha = pfSenha.getText();
            boolean admin = cbAdmin.isSelected();

            if (nome.isEmpty() || senha.isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos.");
            } else if (BancoUsuarios.existe(nome)) {
                mostrarAlerta("Erro", "Usuário já existe.");
            } else {
                BancoUsuarios.cadastrar(new Usuario(nome, senha, admin));
                mostrarAlerta("Sucesso", "Usuário cadastrado!");
                stage.close();
            }
        });

        //Configuração da cena.
        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(TelaCadastro.class.getResource("/resources/estilo.css").toExternalForm());
        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    // Exibe um alerta estilizado com mensagem de feedback.
    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Aplica seu CSS ao diálogo do Alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(TelaCadastro.class.getResource("/resources/estilo.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");
        alert.showAndWait();
    }
}
