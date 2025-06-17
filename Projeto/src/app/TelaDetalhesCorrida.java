package app;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableRow;
import model.Corrida;
import model.ParticipanteCorrida;
import model.Piloto;
import util.Bandeiras;
import java.io.InputStream;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

public class TelaDetalhesCorrida {
    private static Stage stage;

    public TelaDetalhesCorrida(Stage stage) {
        TelaDetalhesCorrida.stage = stage;
    }

    public static void exibir(Corrida corrida) {
        Stage detalhesStage = new Stage();
        detalhesStage.setTitle("Detalhes da Corrida - " + corrida.getNome());

        // Título
        Label titulo = new Label(corrida.getNome());
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Informações básicas
        Label info = new Label(String.format("Local: %s | Distância: %.2f km", 
            corrida.getLocal(), corrida.getDistancia()));
        info.setStyle("-fx-font-size: 18px; -fx-text-fill: #34495e;");

        // Tabela de participantes
        TableView<ParticipanteCorrida> tabela = criarTabelaParticipantes();
        tabela.setItems(FXCollections.observableArrayList(corrida.getParticipantes()));

        // Botão para simular corrida
        Button btnSimular = new Button("SIMULAR CORRIDA");
        btnSimular.setStyle("-fx-font-size: 20px; -fx-padding: 15 40; -fx-background-color: #27ae60; -fx-text-fill: white;");


        // Layout
        final VBox layout = new VBox(20, titulo, info, tabela, btnSimular);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f5f5f5;");

        btnSimular.setOnAction(e -> {
            corrida.simularCorrida();
            atualizarTabelaResultados(tabela, corrida);
            layout.getChildren().remove(btnSimular);
        });

        // Configura e exibe a janela
        Scene scene = new Scene(layout, 1200, 800);
        detalhesStage.setResizable(false);
        detalhesStage.setScene(scene);
        detalhesStage.centerOnScreen();
        detalhesStage.show();
    }

    private static TableView<ParticipanteCorrida> criarTabelaParticipantes() {
        TableView<ParticipanteCorrida> tabela = new TableView<>();
        tabela.setStyle("-fx-font-size: 18px;");
        tabela.setFixedCellSize(70);
        tabela.setPrefHeight(630);

        // Coluna de pilotos
        TableColumn<ParticipanteCorrida, String> colPiloto = new TableColumn<>();
        colPiloto.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getPiloto().getNome()));
        colPiloto.setPrefWidth(340);
        colPiloto.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");

        // Coluna de Bandeiras
        TableColumn<ParticipanteCorrida, String> colBandeira = new TableColumn<>();
        colBandeira.setPrefWidth(90);
        colBandeira.setStyle("-fx-alignment: CENTER;");
        colBandeira.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitWidth(30);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    try {
                        String pais = getTableView().getItems().get(getIndex()).getPiloto().getNacionalidade();
                        String code = Bandeiras.getCountryCode(pais);
                        InputStream is = getClass().getResourceAsStream("/flags/" + code + ".png");
                        if (is != null) {
                            imageView.setImage(new Image(is));
                            setGraphic(imageView);
                        } else {
                            setGraphic(null);
                        }
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        // Coluna de carros
        TableColumn<ParticipanteCorrida, String> colCarro = new TableColumn<>();
        colCarro.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarro().getModelo()));
        colCarro.setPrefWidth(480);
        colCarro.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");

        // Coluna de Imagem do Carro
        TableColumn<ParticipanteCorrida, String> colImagemCarro = new TableColumn<>();
        colImagemCarro.setPrefWidth(200);
        colImagemCarro.setStyle("-fx-alignment: CENTER;");
        colImagemCarro.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitWidth(140);
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
                        String modelo = getTableView().getItems().get(getIndex()).getCarro().getModelo();
                        InputStream is = TelaDetalhesCorrida.class.getResourceAsStream("/cars/" + modelo.toLowerCase().replace(" ", "_") + ".png");
                        if (is != null) {
                            imageView.setImage(new Image(is));
                            setGraphic(imageView);
                        }
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });

        // Adiciona colunas e configura cabeçalhos
        tabela.getColumns().addAll(colPiloto, colBandeira, colCarro, colImagemCarro);
        colPiloto.setGraphic(createHeader("PILOTO"));
        colBandeira.setGraphic(createHeader("PAÍS"));
        colCarro.setGraphic(createHeader("MODELO"));
        colImagemCarro.setGraphic(createHeader("CARRO"));

        return tabela;
    }

    private static void atualizarTabelaResultados(TableView<ParticipanteCorrida> tabela, Corrida corrida) {
        if (!corrida.isSimulada()) return;

        // Ordena participantes pelo tempo efetivo
        List<ParticipanteCorrida> participantesOrdenados = corrida.getParticipantes().stream()
            .sorted(Comparator.comparing(p -> corrida.getResultados().get(p.getPiloto())))
            .collect(Collectors.toList());
        
        tabela.setItems(FXCollections.observableArrayList(participantesOrdenados));
        
        // Remove todas as colunas existentes para reconstruir a tabela
        tabela.getColumns().clear();

        // Coluna de Posição
        TableColumn<ParticipanteCorrida, String> colPosicao = new TableColumn<>();
        colPosicao.setPrefWidth(120);
        colPosicao.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");
        colPosicao.setCellValueFactory(cd -> {
            int index = tabela.getItems().indexOf(cd.getValue()) + 1;
            String posicao = index + "°";
            return new SimpleStringProperty(posicao);
        });
        colPosicao.setGraphic(createHeader("POSIÇÃO"));

        // Coluna de Pilotos
        TableColumn<ParticipanteCorrida, String> colPiloto = new TableColumn<>();
        colPiloto.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getPiloto().getNome()));
        colPiloto.setPrefWidth(352);
        colPiloto.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");
        colPiloto.setGraphic(createHeader("PILOTO"));

        // Coluna de Bandeiras (mantida do código original)
        TableColumn<ParticipanteCorrida, String> colBandeira = new TableColumn<>();
        colBandeira.setPrefWidth(90);
        colBandeira.setStyle("-fx-alignment: CENTER;");
        colBandeira.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitWidth(30);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    try {
                        String pais = getTableView().getItems().get(getIndex()).getPiloto().getNacionalidade();
                        String code = Bandeiras.getCountryCode(pais);
                        InputStream is = getClass().getResourceAsStream("/flags/" + code + ".png");
                        if (is != null) {
                            imageView.setImage(new Image(is));
                            setGraphic(imageView);
                        } else {
                            setGraphic(null);
                        }
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });
        colBandeira.setGraphic(createHeader("PAÍS"));

        // Coluna de Carros
        TableColumn<ParticipanteCorrida, String> colCarro = new TableColumn<>();
        colCarro.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarro().getModelo()));
        colCarro.setPrefWidth(380);
        colCarro.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");
        colCarro.setGraphic(createHeader("MODELO"));

        // Coluna de Resultado
        TableColumn<ParticipanteCorrida, String> colResultado = new TableColumn<>();
        colResultado.setPrefWidth(200);
        colResultado.setStyle("-fx-alignment: CENTER; -fx-font-size: 18px;");
        colResultado.setCellValueFactory(cd -> {
            Piloto p = cd.getValue().getPiloto();
            double tempo = corrida.getResultados().get(p);
            return new SimpleStringProperty(String.format("%.2f segundos", tempo * 3600));
        });
        colResultado.setGraphic(createHeader("TEMPO"));

        // Adiciona todas as colunas à tabela
        tabela.getColumns().addAll(colPosicao, colPiloto, colBandeira, colCarro, colResultado);

        // Configura o row factory para colorir as linhas
        tabela.setRowFactory(tv -> new TableRow<ParticipanteCorrida>() {
            @Override
            protected void updateItem(ParticipanteCorrida item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    int index = getIndex();
                    if (index == 0) {
                        // Ouro para o primeiro lugar
                        setStyle("-fx-background-color: linear-gradient(to bottom, #FFD700, #FFEC8B); " +
                                "-fx-font-weight: bold; -fx-font-size: 18px;");
                    } else if (index == 1) {
                        // Prata para o segundo lugar
                        setStyle("-fx-background-color: linear-gradient(to bottom, #C0C0C0, #E6E6E6); " +
                                "-fx-font-weight: bold; -fx-font-size: 18px;");
                    } else if (index == 2) {
                        // Bronze para o terceiro lugar
                        setStyle("-fx-background-color: linear-gradient(to bottom, #CD7F32, #E6C8A5); " +
                                "-fx-font-weight: bold; -fx-font-size: 18px;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // Força a atualização da tabela
        tabela.refresh();
    }


    private static Label createHeader(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        label.setPadding(new Insets(10));
        return label;
    }
}