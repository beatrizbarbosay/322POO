package app;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.*;

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

        VBox root = new VBox(20, grid, btnCadastrar);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(TelaCadastroCarro.class.getResource("/resources/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}