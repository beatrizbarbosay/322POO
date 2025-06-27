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
import model.BancoCarros;
import model.Carro;
import model.CarroEsportivo;
import model.CarroPopular;

// Tela para cadastro de carros (esportivos ou populares).
public class TelaCadastroCarro {
    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Carro");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Campos do formulário
        ComboBox<String> cbTipo = new ComboBox<>();
        cbTipo.getItems().addAll("Esportivo", "Popular");
        cbTipo.setValue("Esportivo");
        
        TextField tfModelo = new TextField();
        TextField tfVelocidade = new TextField();

        grid.add(new Label("Tipo:"), 0, 0);
        grid.add(cbTipo, 1, 0);
        grid.add(new Label("Modelo:"), 0, 1);
        grid.add(tfModelo, 1, 1);
        grid.add(new Label("Velocidade Máxima (km/h):"), 0, 2);
        grid.add(tfVelocidade, 1, 2);

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setPrefWidth(200);
        
        // Validação e cadastro
        btnCadastrar.setOnAction(e -> {
            try {
                String tipo = cbTipo.getValue();
                String modelo = tfModelo.getText();
                double velocidade = Double.parseDouble(tfVelocidade.getText());

                if (modelo.isEmpty()) {
                    mostrarAlerta("Erro", "Preencha todos os campos.");
                    return;
                }

                Carro novoCarro;
                if (tipo.equals("Esportivo")) {
                    novoCarro = new CarroEsportivo(modelo, velocidade);
                } else {
                    novoCarro = new CarroPopular(modelo, velocidade);
                }
                
                BancoCarros.adicionarCarro(novoCarro);
                mostrarAlerta("Sucesso", "Carro cadastrado com sucesso!");
                stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "Velocidade deve ser um número válido.");
            }
        });

        //Layout
        VBox root = new VBox(20, grid, btnCadastrar);
        root.setAlignment(Pos.CENTER);

        //Configuração da cena
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(TelaCadastroCarro.class.getResource("/resources/estilo.css").toExternalForm());
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