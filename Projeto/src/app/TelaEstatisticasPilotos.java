package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import model.BancoPilotos;
import model.Piloto;
import model.PilotoEstatistica;
import util.Bandeiras;

public class TelaEstatisticasPilotos {
    private static Stage stage;

    public TelaEstatisticasPilotos(Stage stage) {
        stage = new Stage();
    }

    // Exibe estatísticas dos pilotos
    public static void exibir() {
        Stage estatStage = new Stage();
        estatStage.setTitle("Estatísticas dos Pilotos");

        // Título
        Label titulo = new Label("ESTATÍSTICAS DOS PILOTOS");
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela de estatísticas
        TableView<PilotoEstatistica> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela-estatisticas");

        // Colunas

        // Coluna com nome do piloto
        TableColumn<PilotoEstatistica, String> colNome = criarColunaEstilizada("PILOTO", "nome", 250);

        // Coluna com idade do piloto
        TableColumn<PilotoEstatistica, Integer> colIdade = criarColunaEstilizada("IDADE", "idade", 100);

        // Coluna com nacionalidade do piloto + bandeira
        TableColumn<PilotoEstatistica, String> colNacionalidade = new TableColumn<>("NACIONALIDADE");
        colNacionalidade.setPrefWidth(300);
        colNacionalidade.setResizable(false);
        colNacionalidade.getStyleClass().add("coluna-tabela");
        colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));

        // Configura célula personalizada para mostrar bandeira + nome do país
        colNacionalidade.setCellFactory(new Callback<TableColumn<PilotoEstatistica, String>, TableCell<PilotoEstatistica, String>>() {
        @Override
        public TableCell<PilotoEstatistica, String> call(TableColumn<PilotoEstatistica, String> param) {
            return new TableCell<PilotoEstatistica, String>() {
                private final ImageView imageView = new ImageView();
                private final HBox content = new HBox();
                private final Label countryLabel = new Label();
                
                {
                    imageView.setFitWidth(20);
                    imageView.setPreserveRatio(true);
                    content.setAlignment(Pos.CENTER);
                    content.setSpacing(10);
                    countryLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-weight: bold; -fx-font-size: 13.5px");
                }
                
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        try {
                            String countryCode = Bandeiras.getCountryCode(item);
                            Image flagImage = new Image(getClass().getResourceAsStream("/flags/" + countryCode + ".png"));
                            imageView.setImage(flagImage);
                            countryLabel.setText(item);
                            content.getChildren().setAll(imageView, countryLabel);
                            setGraphic(content);
                            setStyle("-fx-alignment: CENTER;"); // Centraliza o HBox na célula
                        } catch (Exception e) {
                            countryLabel.setText(item); // Fallback também estilizado
                            setStyle("-fx-alignment: CENTER;"); // Centraliza também o fallback
                        }
                    }
                }
            };
        }
    });

        // Coluna de nível do piloto
        TableColumn<PilotoEstatistica, Double> colNivel = criarColunaEstilizada("NÍVEL", "nivel", 160);

        // Formatação com 2 casas decimais
        colNivel.setCellFactory(col -> new TableCell<PilotoEstatistica, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });

        // Adiciona colunas
        tabela.getColumns().addAll(colNome, colIdade, colNacionalidade, colNivel);
        
        // Carrega dados dos pilotos
        ObservableList<PilotoEstatistica> dados = FXCollections.observableArrayList();
        for (Piloto piloto : BancoPilotos.getTodosPilotos()) {
            dados.add(new PilotoEstatistica(
                piloto.getNome(),
                piloto.getIdade(),
                piloto.getNacionalidade(),
                piloto.getNivel()
            ));
        }
        tabela.setItems(dados);

        // Rodapé com data de atualização
        Label rodape = new Label("Atualizado em " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        rodape.getStyleClass().add("rodape");

        // Layout
        VBox layout = new VBox(15, titulo, tabela, rodape);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("painel-secundario");
        VBox.setVgrow(tabela, Priority.ALWAYS);

        // Configura e exibe janela
        Scene scene = new Scene(layout, 900, 650);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        estatStage.setScene(scene);
        estatStage.centerOnScreen();
        estatStage.show();
        estatStage.setResizable(false);
    }

    // Cria coluna estilizada para tabela de pilotos
    private static <T> TableColumn<PilotoEstatistica, T> criarColunaEstilizada(String titulo, String propriedade, double largura) {
        TableColumn<PilotoEstatistica, T> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        coluna.setPrefWidth(largura);
        coluna.setResizable(false);
        coluna.getStyleClass().add("coluna-tabela");
        return coluna;
    }
}