package app;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.DialogPane; 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.BancoCarros;
import model.BancoCorridas;
import model.BancoPilotos;
import model.Carro;
import model.Corrida;
import model.Piloto;

import java.util.ArrayList;
import java.util.List;

// Tela para criação de corridas com seleção de pilotos e carros.
public class TelaCriarCorrida {
    // Listas para armazenar os comboboxes de seleção
    private static List<ComboBox<Piloto>> comboPilotos = new ArrayList<>();
    private static List<ComboBox<Carro>> comboCarros = new ArrayList<>();

    //Exibe a janela de criação de corrida e Configura todos os elementos visuais e lógica de interação.
    public static void exibir() {
        Stage stage = new Stage();
        stage.setTitle("Criar Nova Corrida");

        // Layout principal
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setStyle("-fx-background-color: linear-gradient(to bottom, #2c3e50, #4ca1af);");

        // Componentes (título, formulário, tabela de vagas)
        Label titulo = new Label("CRIAR NOVA CORRIDA");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);");

        //Formulário de Dados Básicos
        GridPane formulario = new GridPane();
        formulario.setHgap(15);
        formulario.setVgap(15);
        formulario.setPadding(new Insets(25));
        formulario.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9);" +
                          "-fx-background-radius: 10;" +
                          "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        // Campos de entrada
        TextField txtNome = criarCampoTexto();
        TextField txtLocal = criarCampoTexto();
        TextField txtDistancia = criarCampoTexto();

        // Adiciona campos ao formulário
        formulario.add(new Label("Nome da Corrida:"), 0, 0);
        formulario.add(txtNome, 1, 0);
        formulario.add(new Label("Local:"), 0, 1);
        formulario.add(txtLocal, 1, 1);
        formulario.add(new Label("Distância (km):"), 0, 2);
        formulario.add(txtDistancia, 1, 2);

        // Aplica estilo uniforme aos labels do formulário
        String labelStyle = "-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";
        for (javafx.scene.Node node : formulario.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setStyle(labelStyle);
            }
        }

        // Seleção de participantes
        GridPane vagasPane = new GridPane();
        vagasPane.setHgap(15);
        vagasPane.setVgap(10);
        vagasPane.setPadding(new Insets(15));

        // Cria 15 vagas (pares de piloto+carro)
        for (int i = 0; i < 15; i++) {
            Label lblVaga = new Label("Vaga " + (i + 1) + ":");
            lblVaga.setStyle(labelStyle);

            // Comboboxes para seleção
            ComboBox<Piloto> cbPiloto = criarComboPilotos();
            ComboBox<Carro> cbCarro = criarComboCarros();

            // Armazena referências para validação posterior
            comboPilotos.add(cbPiloto);
            comboCarros.add(cbCarro);

            // Adiciona ao layout
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
        
        // Validações antes de criar a corrida
        btnCriar.setOnAction(e -> {
            try {
                // VALIDAÇÃO 1: Os campos Nome, local e distância foram preenchidos
                if (txtNome.getText().isEmpty() || txtLocal.getText().isEmpty() || txtDistancia.getText().isEmpty()) {
                    throw new Exception("Preencha todos os campos da corrida");
                }

                 // VALIDAÇÃO 2: O campo distância foi preenchido com um número
                double distancia = Double.parseDouble(txtDistancia.getText());
                if (distancia <= 0) {
                    throw new Exception("Distância deve ser maior que zero");
                }

                // VALIDAÇÃO 3: Vagas obrigatórias (8 primeiras)
                for (int i = 0; i < 8; i++) {
                    if (comboPilotos.get(i).getValue() == null || comboCarros.get(i).getValue() == null) {
                        throw new Exception("Preencha todas as vagas obrigatórias (1-8)");
                    }
                }

                // Validação 4: Todo piloto possue um carro e vice-versa
                for (int i = 0; i < 15; i++) {
                    boolean temPiloto = comboPilotos.get(i).getValue() != null;
                    boolean temCarro = comboCarros.get(i).getValue() != null;
                    
                    if (temPiloto && !temCarro) {
                        throw new Exception("Vaga " + (i + 1) + ": Piloto sem carro selecionado");
                    }
                    if (!temPiloto && temCarro) {
                        throw new Exception("Vaga " + (i + 1) + ": Carro sem piloto selecionado");
                    }
                }

                // VALIDAÇÃO 5: Cada piloto e carro foi selecionado apenas uma vez
                if (temDuplicatas(comboPilotos) || temDuplicatas(comboCarros)) {
                    throw new Exception("Não é permitido selecionar o mesmo piloto ou carro mais de uma vez");
                }

                // Criar a corrida
                Corrida corrida = new Corrida(txtNome.getText(), txtLocal.getText(), distancia);

                // Adiciona participantes (considera até 15 vagas)
                for (int i = 0; i < 15; i++) {
                    corrida.adicionarParticipante(
                        comboPilotos.get(i).getValue(),
                        comboCarros.get(i).getValue()
                    );
                }

                // Salva no banco de dados
                BancoCorridas.adicionarCorrida(corrida);
                stage.close();
                
                // Exibe mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Corrida criada com sucesso!");

                // Estilização do alerta
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

        // Configuração da cena
        Scene scene = new Scene(mainContainer, 800, 700);
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // Cria um ComboBox para seleção de pilotos
    private static ComboBox<Piloto> criarComboPilotos() {
        ComboBox<Piloto> combo = new ComboBox<>();
    
        // Obtém a lista de pilotos e ordena por nome
        List<Piloto> pilotosOrdenados = new ArrayList<>(BancoPilotos.getTodosPilotos());
        pilotosOrdenados.sort((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()));
        
        combo.setItems(FXCollections.observableArrayList(pilotosOrdenados));
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

    // Cria um ComboBox para seleção de carros
    private static ComboBox<Carro> criarComboCarros() {
        ComboBox<Carro> combo = new ComboBox<>();
        
        // Obtém a lista de carros e ordena por modelo
        List<Carro> carrosOrdenados = new ArrayList<>(BancoCarros.getTodosCarros());
        carrosOrdenados.sort((c1, c2) -> c1.getModelo().compareToIgnoreCase(c2.getModelo()));
        
        combo.setItems(FXCollections.observableArrayList(carrosOrdenados));
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

    //Verifica se há valores duplicados nos ComboBoxes
    private static <T> boolean temDuplicatas(List<ComboBox<T>> combos) {
        List<T> selecionados = new ArrayList<>();
        for (ComboBox<T> combo : combos) {
            T valor = combo.getValue();
            if (valor != null) {
                if (selecionados.contains(valor)) {
                    return true; // Encontrou duplicata
                }
                selecionados.add(valor);
            }
        }
        return false; // Não encontrou duplicata
    }

    // Cria um campo de texto padronizado
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

    // Exibe uma mensagem de erro estilizada
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