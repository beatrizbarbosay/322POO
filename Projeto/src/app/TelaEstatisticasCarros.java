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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.InputStream;
import model.BancoCarros;
import model.Carro;
import model.CarroEstatistica;

public class TelaEstatisticasCarros {
    private static Stage stage;

    public TelaEstatisticasCarros(Stage stage) {
        stage = new Stage();
    }

    // Exibe estatísticas dos carros
    public static void exibir() {
        Stage estatStage = new Stage();
        estatStage.setTitle("Dados dos Carros");

        // Título
        Label titulo = new Label("DADOS DOS CARROS");
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela
        TableView<CarroEstatistica> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela-estatisticas");




        // Colunas

        // Coluna da Imagem do Carro
        TableColumn<CarroEstatistica, String> colImagem = new TableColumn<>("CARRO");
        colImagem.setPrefWidth(150);
        colImagem.setCellFactory(new Callback<TableColumn<CarroEstatistica, String>, TableCell<CarroEstatistica, String>>() {
            @Override
            public TableCell<CarroEstatistica, String> call(TableColumn<CarroEstatistica, String> param) {
                return new TableCell<CarroEstatistica, String>() {
                    private final ImageView imageView = new ImageView();
                    {
                        imageView.setFitWidth(150);
                        imageView.setPreserveRatio(true);
                        imageView.setSmooth(true);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            try {
                                String modelo = getTableView().getItems().get(getIndex()).getModelo();
                                // Assumindo que as imagens estão em /resources/cars/ com o nome do modelo + extensão
                                InputStream is = TelaEstatisticasCarros.class.getResourceAsStream("/cars/" + modelo.toLowerCase().replace(" ", "_") + ".png");
                                if (is != null) {
                                    imageView.setImage(new Image(is));
                                } else {
                                    // Imagem padrão se não encontrar
                                    is = TelaEstatisticasCarros.class.getResourceAsStream("/cars/default.png");
                                    if (is != null) {
                                        imageView.setImage(new Image(is));
                                    }
                                }
                                setGraphic(imageView);
                            } catch (Exception e) {
                                setGraphic(null);
                            }
                        }
                    }
                };
            }
        });

        TableColumn<CarroEstatistica, String> colModelo = criarColunaEstilizada("MODELO", "modelo", 250);
        TableColumn<CarroEstatistica, String> colTipo = criarColunaEstilizada("TIPO", "tipo", 180);
        TableColumn<CarroEstatistica, Double> colVelocidadeMax = criarColunaEstilizada("VELOCIDADE MÁX (km/h)", "velocidadeMax", 250);

        tabela.getColumns().addAll(colImagem, colModelo, colTipo, colVelocidadeMax);
        
        // Carrega dados dos carros
        ObservableList<CarroEstatistica> dados = FXCollections.observableArrayList();
        for (Carro carro : BancoCarros.getTodosCarros()) {
            dados.add(new CarroEstatistica(
                carro.getModelo(),       
                carro.getTipo(),         
                carro.getvelocidadeMax() 
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
    
    // Cria coluna estilizada para tabela de carros
    private static <T> TableColumn<CarroEstatistica, T> criarColunaEstilizada(String titulo, String propriedade, double largura) {
        TableColumn<CarroEstatistica, T> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        coluna.setPrefWidth(largura);
        coluna.setResizable(false);
        coluna.getStyleClass().add("coluna-tabela");
        return coluna;
    }
}