package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BancoPilotos;
import model.Piloto;
import model.Sexo;

// Tela para cadastro de pilotos com informações pessoais.
public class TelaCadastroPiloto {
    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Piloto");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Campos do formulário
        TextField tfNome = new TextField();
        TextField tfIdade = new TextField();
        TextField tfNacionalidade = new TextField();
        ComboBox<Sexo> cbSexo = new ComboBox<>();
        cbSexo.getItems().setAll(Sexo.values());
        cbSexo.setValue(Sexo.MASCULINO);

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(tfNome, 1, 0);
        grid.add(new Label("Idade:"), 0, 1);
        grid.add(tfIdade, 1, 1);
        grid.add(new Label("Nacionalidade:"), 0, 2);
        grid.add(tfNacionalidade, 1, 2);
        grid.add(new Label("Sexo:"), 0, 3);
        grid.add(cbSexo, 1, 3);

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setPrefWidth(200);
        
         // Validação e cadastro
        btnCadastrar.setOnAction(e -> {
            try {
                String nome = tfNome.getText();
                int idade = Integer.parseInt(tfIdade.getText());
                String nacionalidade = tfNacionalidade.getText();
                Sexo sexo = cbSexo.getValue();

                if (nome.isEmpty() || nacionalidade.isEmpty()) {
                    mostrarAlerta("Erro", "Preencha todos os campos.");
                    return;
                }

                Piloto novoPiloto = new Piloto(nome, idade, nacionalidade, sexo);
                BancoPilotos.adicionarPiloto(novoPiloto);
                mostrarAlerta("Sucesso", "Piloto cadastrado com sucesso!");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "Idade deve ser um número válido.");
            }
        });

        //Layout.
        VBox root = new VBox(20, grid, btnCadastrar);
        root.setAlignment(Pos.CENTER);

        //Configuração da cena.
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(TelaCadastroPiloto.class.getResource("/resources/estilo.css").toExternalForm());
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
        alert.showAndWait();
    }
}