package app;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import java.util.Map;
import java.io.InputStream;
import model.*;

public class TelaUsuario {
    private static Stage stage;
    private static String usuarioAtual;

    public static void exibir(String usuario) {
        usuarioAtual = usuario;
        stage = new Stage();
        stage.setTitle("Área do Usuário - " + usuario);

        // Título
        Label titulo = new Label("Bem-vindo, " + usuario + "!");
        titulo.getStyleClass().add("titulo-principal");

        // Botões principais
        Button btnCorridas = criarBotaoGrande("Corridas Disponíveis");
        Button btnEstatPilotos = criarBotaoGrande("Estatísticas dos Pilotos");
        Button btnEstatCarros = criarBotaoGrande("Dados dos Carros");
        Button btnHistApostas = criarBotaoGrande("Histórico de Apostas");

        // Ações dos botões
        btnCorridas.setOnAction(e -> exibirCorridasDisponiveis());
        btnEstatPilotos.setOnAction(e -> exibirEstatisticasPilotos());
        btnEstatCarros.setOnAction(e -> exibirEstatisticasCarros());
        btnHistApostas.setOnAction(e -> exibirHistoricoApostas());

        // Layout principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("painel-principal");
        layout.getChildren().addAll(titulo, btnCorridas, btnEstatPilotos, btnEstatCarros, btnHistApostas);

        Scene scene = new Scene(layout, 1200, 1000);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
    }

    private static Button criarBotaoGrande(String texto) {
        Button btn = new Button(texto);
        btn.getStyleClass().add("botao-grande");
        btn.setPrefWidth(600);
        btn.setPrefHeight(100);
        return btn;
    }

    private static void exibirCorridasDisponiveis() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Corridas Disponíveis");
        alert.setHeaderText(null);
        alert.setContentText("Ainda não há corridas disponíveis.");
        alert.showAndWait();
    }

    private static String getCountryCode(String countryName) {
        // Mapeamento de nomes de países para códigos de bandeira
        Map<String, String> countryCodes = Map.of(
            "Brasil", "br",
            "Estados Unidos", "us",
            "Reino Unido", "gb",
            "Espanha", "es",
            "França", "fr",
            "Alemanha", "de",
            "Itália", "it",
            "Japão", "jp"
            // Adicione mais países conforme necessário
        );
        return countryCodes.getOrDefault(countryName, "unknown"); // "unknown" pode ser uma imagem padrão
    }

    private static void exibirEstatisticasPilotos() {
        Stage estatStage = new Stage();
        estatStage.setTitle("Estatísticas dos Pilotos - " + usuarioAtual);


        // Título
        Label titulo = new Label("ESTATÍSTICAS DOS PILOTOS");
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela
        TableView<PilotoEstatistica> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela-estatisticas");

        // Colunas
        TableColumn<PilotoEstatistica, String> colNome = criarColunaEstilizada("PILOTO", "nome", 250);
        TableColumn<PilotoEstatistica, Integer> colIdade = criarColunaEstilizada("IDADE", "idade", 100);
        TableColumn<PilotoEstatistica, String> colNacionalidade = new TableColumn<>("NACIONALIDADE");
        colNacionalidade.setPrefWidth(200);
        colNacionalidade.setResizable(false);
        colNacionalidade.getStyleClass().add("coluna-tabela");

        colNacionalidade.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));

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
                    content.setAlignment(Pos.CENTER); // Alterado para CENTER
                    content.setSpacing(10); // Espaço entre bandeira e texto
                    countryLabel.setStyle("-fx-text-fill: #2c3e50; -fx-font-weight: bold; -fx-font-size: 13.5px");
                }
                
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        try {
                            String countryCode = getCountryCode(item);
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
        TableColumn<PilotoEstatistica, Double> colTempoMedio = criarColunaEstilizada("TEMPO MÉDIO (s)", "tempoMedio", 200);

        // Formatação do tempo médio
        colTempoMedio.setCellFactory(tc -> new TableCell<PilotoEstatistica, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.3f", item));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        tabela.getColumns().addAll(colNome, colIdade, colNacionalidade, colTempoMedio);
        
        // Dados
        ObservableList<PilotoEstatistica> dados = FXCollections.observableArrayList();
        for (Piloto piloto : BancoPilotos.getTodosPilotos()) {
            double tempoMedio = 0;
            dados.add(new PilotoEstatistica(
                piloto.getNome(),
                piloto.getIdade(),
                piloto.getNacionalidade(),
                tempoMedio
            ));
        }
        tabela.setItems(dados);
        
        // Ordenação
        colTempoMedio.setSortType(TableColumn.SortType.DESCENDING);
        tabela.getSortOrder().add(colTempoMedio);
        tabela.sort();

        // Rodapé
        Label rodape = new Label("Atualizado em " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        rodape.getStyleClass().add("rodape");

        // Layout
        VBox layout = new VBox(15, titulo, tabela, rodape);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("painel-secundario");

        // Faz a tabela crescer verticalmente para ocupar espaço disponível
        VBox.setVgrow(tabela, Priority.ALWAYS);

        Scene scene = new Scene(layout, 900, 650);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        estatStage.setScene(scene);
        estatStage.centerOnScreen();
        estatStage.show();
        estatStage.setResizable(false);
    }

    private static <T> TableColumn<PilotoEstatistica, T> criarColunaEstilizada(String titulo, String propriedade, double largura) {
        TableColumn<PilotoEstatistica, T> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        coluna.setPrefWidth(largura);
        coluna.setResizable(false);
        coluna.getStyleClass().add("coluna-tabela");
        return coluna;
    }


    private static void exibirEstatisticasCarros() {
        Stage estatStage = new Stage();
        estatStage.setTitle("Dados dos Carros - " + usuarioAtual);

        // Título
        Label titulo = new Label("DADOS DOS CARROS");  // Corrigido de "PILOTOS" para "CARROS"
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela
        TableView<CarroEstatistica> tabela = new TableView<>();  // Corrigido o tipo genérico
        tabela.getStyleClass().add("tabela-estatisticas");

        // Colunas
        TableColumn<CarroEstatistica, String> colModelo = criarColunaEstilizada2("MODELO", "modelo", 250);
        TableColumn<CarroEstatistica, String> colTipo = criarColunaEstilizada2("TIPO", "tipo", 250);
        TableColumn<CarroEstatistica, Double> colVelocidadeMax = criarColunaEstilizada2("VELOCIDADE MÁX (km/h)", "velocidadeMax", 250);

        tabela.getColumns().addAll(colModelo, colTipo, colVelocidadeMax);
        
        // Dados
        ObservableList<CarroEstatistica> dados = FXCollections.observableArrayList();
        for (Carro carro : BancoCarros.getTodosCarros()) {
            dados.add(new CarroEstatistica(
                carro.getModelo(),       // Corrigido: usando carro em vez de piloto
                carro.getTipo(),         // Corrigido: usando carro em vez de piloto
                carro.getvelocidadeMax() // Corrigido: usando carro em vez de piloto
            ));                         // Removida a vírgula extra
        }
        tabela.setItems(dados);
        
        // Ordenação (removida a linha com colTempoMedio que não existe)
        colVelocidadeMax.setSortType(TableColumn.SortType.DESCENDING);  // Ordena por velocidade
        tabela.getSortOrder().add(colVelocidadeMax);
        tabela.sort();

        // Rodapé
        Label rodape = new Label("Atualizado em " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        rodape.getStyleClass().add("rodape");

        // Layout
        VBox layout = new VBox(15, titulo, tabela, rodape);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getStyleClass().add("painel-secundario");

        // Faz a tabela crescer verticalmente para ocupar espaço disponível
        VBox.setVgrow(tabela, Priority.ALWAYS);

        Scene scene = new Scene(layout, 900, 650);
        scene.getStylesheets().add(TelaUsuario.class.getResource("/estilo.css").toExternalForm());
        estatStage.setScene(scene);
        estatStage.centerOnScreen();
        estatStage.show();
        estatStage.setResizable(false);
    }

    private static <T> TableColumn<CarroEstatistica, T> criarColunaEstilizada2(String titulo, String propriedade, double largura) {
        TableColumn<CarroEstatistica, T> coluna = new TableColumn<>(titulo);
        coluna.setCellValueFactory(new PropertyValueFactory<>(propriedade));
        coluna.setPrefWidth(largura);
        coluna.setResizable(false);
        coluna.getStyleClass().add("coluna-tabela");
        return coluna;
    }

    private static void exibirHistoricoApostas() {
        mostrarAlerta("Histórico de Apostas", "Aqui será exibido o histórico de apostas do usuário " + usuarioAtual);
    }

    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}