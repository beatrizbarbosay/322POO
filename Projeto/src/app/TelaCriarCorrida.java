package app;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.BancoCarros;
import model.BancoPilotos;
import model.BancoCorridas;
import model.Carro;
import model.Piloto;
import model.Corrida;

import java.util.ArrayList;
import java.util.List;

public class TelaCriarCorrida {
    private static List<ComboBox<Piloto>> comboPilotos = new ArrayList<>();
    private static List<ComboBox<Carro>> comboCarros = new ArrayList<>();

    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Criar Nova Corrida");

        // Container principal com fundo degradê
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #4ca1af);");

        // Título estilizado
        Label titulo = new Label("CRIAR NOVA CORRIDA");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);");

        // Painel do formulário com sombra
        GridPane formulario = new GridPane();
        formulario.setHgap(15);
        formulario.setVgap(15);
        formulario.setPadding(new Insets(25));
        formulario.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);" +
                          "-fx-background-radius: 10;" +
                          "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        // Campos da corrida
        TextField txtNome = criarCampoTexto();
        TextField txtLocal = criarCampoTexto();
        TextField txtDistancia = criarCampoTexto();

        formulario.add(new Label("Nome da Corrida:"), 0, 0);
        formulario.add(txtNome, 1, 0);
        formulario.add(new Label("Local:"), 0, 1);
        formulario.add(txtLocal, 1, 1);
        formulario.add(new Label("Distância (km):"), 0, 2);
        formulario.add(txtDistancia, 1, 2);

        // Estilo para labels
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";
        for (javafx.scene.Node node : formulario.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle(labelStyle);
            }
        }

        // Painel para as vagas
        GridPane vagasPane = new GridPane();
        vagasPane.setHgap(15);
        vagasPane.setVgap(10);
        vagasPane.setPadding(new Insets(15));

        // Adicionar comboboxes para cada vaga
        for (int i = 0; i < 8; i++) {
            Label lblVaga = new Label("Vaga " + (i + 1) + ":");
            lblVaga.setStyle(labelStyle);

            ComboBox<Piloto> cbPiloto = criarComboPilotos();
            ComboBox<Carro> cbCarro = criarComboCarros();

            comboPilotos.add(cbPiloto);
            comboCarros.add(cbCarro);

            vagasPane.add(lblVaga, 0, i);
            vagasPane.add(new Label("Piloto:"), 1, i);
            vagasPane.add(cbPiloto, 2, i);
            vagasPane.add(new Label("Carro:"), 3, i);
            vagasPane.add(cbCarro, 4, i);
        }

        // Adicionar scroll caso tenha muitas vagas
        ScrollPane scrollVagas = new ScrollPane(vagasPane);
        scrollVagas.setFitToWidth(true);
        scrollVagas.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Botão de criar estilizado
        Button btnCriar = new Button("CRIAR CORRIDA");
        btnCriar.setStyle("-fx-background-color: #27ae60;" +
                         "-fx-text-fill: white;" +
                         "-fx-font-weight: bold;" +
                         "-fx-font-size: 16px;" +
                         "-fx-padding: 10 20;" +
                         "-fx-background-radius: 5;" +
                         "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1);");
        btnCriar.setOnMouseEntered(e -> btnCriar.setStyle("-fx-background-color: #2ecc71;"));
        btnCriar.setOnMouseExited(e -> btnCriar.setStyle("-fx-background-color: #27ae60;"));

        btnCriar.setOnAction(e -> {
            try {
                // Validar entrada
                if (txtNome.getText().isEmpty() || txtLocal.getText().isEmpty() || txtDistancia.getText().isEmpty()) {
                    throw new Exception("Preencha todos os campos da corrida");
                }

                double distancia = Double.parseDouble(txtDistancia.getText());
                if (distancia <= 0) {
                    throw new Exception("Distância deve ser maior que zero");
                }

                // Verificar se todas as vagas foram preenchidas
                for (int i = 0; i < 8; i++) {
                    if (comboPilotos.get(i).getValue() == null || comboCarros.get(i).getValue() == null) {
                        throw new Exception("Preencha todos os pilotos e carros para as vagas");
                    }
                }

                // Verificar duplicatas
                if (temDuplicatas(comboPilotos) || temDuplicatas(comboCarros)) {
                    throw new Exception("Não é permitido selecionar o mesmo piloto ou carro mais de uma vez");
                }

                // Criar a corrida
                Corrida corrida = new Corrida(txtNome.getText(), txtLocal.getText(), distancia);
                for (int i = 0; i < 8; i++) {
                    corrida.adicionarParticipante(
                        comboPilotos.get(i).getValue(),
                        comboCarros.get(i).getValue()
                    );
                }

                BancoCorridas.adicionarCorrida(corrida);
                stage.close();
                
                // Alert estilizado
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Corrida criada com sucesso!");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-background-color: #f8f8f8;");
                dialogPane.setHeader(null);
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                mostrarErro("Distância inválida", "A distância deve ser um número válido");
            } catch (Exception ex) {
                mostrarErro("Erro ao criar corrida", ex.getMessage());
            }
        });

        // Layout principal
        mainContainer.getChildren().addAll(titulo, formulario, scrollVagas, btnCriar);

        Scene scene = new Scene(mainContainer, 800, 700);
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private static ComboBox<Piloto> criarComboPilotos() {
        ComboBox<Piloto> combo = new ComboBox<>();
        combo.setItems(FXCollections.observableArrayList(BancoPilotos.getTodosPilotos()));
        combo.setConverter(new StringConverter<Piloto>() {
            @Override
            public String toString(Piloto piloto) {
                return piloto != null ? piloto.getNome() : "";
            }

            @Override
            public Piloto fromString(String string) {
                return null;
            }
        });
        combo.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #bdc3c7; -fx-border-radius: 4;");
        combo.setPrefWidth(200);
        return combo;
    }

    private static ComboBox<Carro> criarComboCarros() {
        ComboBox<Carro> combo = new ComboBox<>();
        combo.setItems(FXCollections.observableArrayList(BancoCarros.getTodosCarros()));
        combo.setConverter(new StringConverter<Carro>() {
            @Override
            public String toString(Carro carro) {
                return carro != null ? carro.getModelo() : "";
            }

            @Override
            public Carro fromString(String string) {
                return null;
            }
        });
        combo.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #bdc3c7; -fx-border-radius: 4;");
        combo.setPrefWidth(200);
        return combo;
    }

    private static <T> boolean temDuplicatas(List<ComboBox<T>> combos) {
        List<T> selecionados = new ArrayList<>();
        for (ComboBox<T> combo : combos) {
            T valor = combo.getValue();
            if (valor != null) {
                if (selecionados.contains(valor)) {
                    return true;
                }
                selecionados.add(valor);
            }
        }
        return false;
    }

    private static TextField criarCampoTexto() {
        TextField field = new TextField();
        field.setStyle("-fx-background-color: #f8f8f8;" +
                      "-fx-border-color: #bdc3c7;" +
                      "-fx-border-radius: 4;" +
                      "-fx-padding: 8;" +
                      "-fx-font-size: 14px;");
        field.setPrefHeight(35);
        field.setPrefWidth(550);
        return field;
    }

    private static void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #f8f8f8;" +
                          "-fx-font-size: 14px;");
        
        alert.showAndWait();
    }
}