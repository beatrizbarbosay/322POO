package app;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BancoCorridas;
import model.Corrida;   

public class TelaCorridasDisponiveis {
    private static Stage stage;

    public TelaCorridasDisponiveis(Stage stage) {
        stage = new Stage();
    }

    // Exibe a tela de corridas disponíveis
    public static void exibir() {
        Stage corridasStage = new Stage();
        corridasStage.setTitle("Corridas Disponíveis");

        // Título
        Label titulo = new Label("CORRIDAS DISPONÍVEIS");
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela para exibir as corridas
        TableView<Corrida> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela-corrida");

        // Cria e configura as colunas da tabela
        TableColumn<Corrida, String> colNome = criarColunaTabela("NOME", "nome", 638);
        TableColumn<Corrida, String> colLocal = criarColunaTabela("LOCAL", "local", 250);
        colLocal.setStyle("-fx-font-size: 20px;");
        TableColumn<Corrida, Double> colDistancia = criarColunaTabela("DISTÂNCIA (km)", "distancia", 265);
        colDistancia.setStyle("-fx-font-size: 20px;");

        // Adiciona colunas e dados à tabela
        tabela.getColumns().addAll(colNome, colLocal, colDistancia);
        tabela.setItems(FXCollections.observableArrayList(BancoCorridas.getTodasCorridas()));

        // Botão para ver detalhes da corrida selecionada
        Button btnDetalhes = new Button("Ver Detalhes");
        btnDetalhes.getStyleClass().add("botao-grande");
        btnDetalhes.setOnAction(e -> {
            Corrida selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                TelaDetalhesCorrida.exibir(selecionada); //Exibe os detalhes de uma corrida específica
            } else {
                mostrarAlerta("Aviso", "Selecione uma corrida para ver os detalhes");
            }
        });

        // Layout
        VBox layout = new VBox(15, titulo, tabela, btnDetalhes);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("painel-secundario");

        VBox.setVgrow(tabela, Priority.ALWAYS);

        // Configura e exibe a janela
        Scene scene = new Scene(layout, 1200, 800);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        corridasStage.setScene(scene);
        corridasStage.centerOnScreen();
        corridasStage.show();
        corridasStage.setResizable(false);
    }
    
    // Método genérico para criar colunas de tabela estilizadas
    private static <S, T> TableColumn<S, T> criarColunaTabela(String titulo, String propriedade, double largura) {
        TableColumn<S, T> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        coluna.setPrefWidth(largura);
        coluna.setResizable(false);
        coluna.getStyleClass().add("coluna-tabela");
        coluna.setStyle("-fx-font-size: 25px;");
        return coluna;
    }

    // Exibe um alerta/mensagem ao usuário
    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}