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
import model.*;

public class TelaUsuario {
    private static Stage stage;
    private static String usuarioAtual;

    public static void exibir(String usuario) {
        usuarioAtual = usuario;
        stage = new Stage();
        stage.setTitle("Área do Usuário - " + usuario);

        try {
            stage.getIcons().add(new Image("/resources/icon.png"));
        } catch (Exception e) {
            System.out.println("Ícone não encontrado, continuando sem ícone...");
        }

        // Título
        Label titulo = new Label("Bem-vindo, " + usuario + "!");
        titulo.getStyleClass().add("titulo-principal");

        // Botões principais
        Button btnCorridas = criarBotaoGrande("Corridas Disponíveis");
        Button btnEstatPilotos = criarBotaoGrande("Estatísticas dos Pilotos");
        Button btnEstatCarros = criarBotaoGrande("Estatísticas dos Carros");
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

    private static void exibirEstatisticasPilotos() {
        Stage estatStage = new Stage();
        estatStage.setTitle("Estatísticas dos Pilotos - " + usuarioAtual);

        try {
            estatStage.getIcons().add(new Image("/resources/icon.png"));
        } catch (Exception e) {
            System.out.println("Ícone não encontrado, continuando sem ícone...");
        }

        // Título
        Label titulo = new Label("ESTATÍSTICAS DOS PILOTOS");
        titulo.getStyleClass().add("titulo-secundario");

        // Tabela
        TableView<PilotoEstatistica> tabela = new TableView<>();
        tabela.getStyleClass().add("tabela-estatisticas");

        // Colunas
        TableColumn<PilotoEstatistica, String> colNome = criarColunaEstilizada("PILOTO", "nome", 250);
        TableColumn<PilotoEstatistica, Integer> colIdade = criarColunaEstilizada("IDADE", "idade", 100);
        TableColumn<PilotoEstatistica, String> colNacionalidade = criarColunaEstilizada("NACIONALIDADE", "nacionalidade", 180);
        TableColumn<PilotoEstatistica, Double> colTempoMedio = criarColunaEstilizada("TEMPO MÉDIO (s)", "tempoMedio", 150);

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
        mostrarAlerta("Estatísticas dos Carros", "Aqui serão exibidas as estatísticas dos carros.");
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