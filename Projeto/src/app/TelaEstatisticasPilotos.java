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

public class TelaEstatisticasPilotos {
    private static Stage stage;

    public TelaEstatisticasPilotos(Stage stage) {
        stage = new Stage();
    }

    // Mapa de códigos de países para bandeiras
    private static final Map<String, String> COUNTRY_CODES = new HashMap<>();

    // Bloco de inicialização estática - carrega os códigos de países
    static {
        //Preenche o mapa com códigos de países (ISO 3166-1 alpha-2), pois assim estão salvas as bandeiras em /resources/flags
        COUNTRY_CODES.put("Afeganistão", "af");
        COUNTRY_CODES.put("África do Sul", "za");
        COUNTRY_CODES.put("Albânia", "al");
        COUNTRY_CODES.put("Alemanha", "de");
        COUNTRY_CODES.put("Andorra", "ad");
        COUNTRY_CODES.put("Angola", "ao");
        COUNTRY_CODES.put("Antígua e Barbuda", "ag");
        COUNTRY_CODES.put("Arábia Saudita", "sa");
        COUNTRY_CODES.put("Argélia", "dz");
        COUNTRY_CODES.put("Argentina", "ar");
        COUNTRY_CODES.put("Armênia", "am");
        COUNTRY_CODES.put("Austrália", "au");
        COUNTRY_CODES.put("Áustria", "at");
        COUNTRY_CODES.put("Azerbaijão", "az");
        COUNTRY_CODES.put("Bahamas", "bs");
        COUNTRY_CODES.put("Bahrein", "bh");
        COUNTRY_CODES.put("Bangladesh", "bd");
        COUNTRY_CODES.put("Barbados", "bb");
        COUNTRY_CODES.put("Bélgica", "be");
        COUNTRY_CODES.put("Belize", "bz");
        COUNTRY_CODES.put("Benim", "bj");
        COUNTRY_CODES.put("Bielorrússia", "by");
        COUNTRY_CODES.put("Bolívia", "bo");
        COUNTRY_CODES.put("Bósnia e Herzegovina", "ba");
        COUNTRY_CODES.put("Botsuana", "bw");
        COUNTRY_CODES.put("Brasil", "br");
        COUNTRY_CODES.put("Brunei", "bn");
        COUNTRY_CODES.put("Bulgária", "bg");
        COUNTRY_CODES.put("Burkina Faso", "bf");
        COUNTRY_CODES.put("Burundi", "bi");
        COUNTRY_CODES.put("Butão", "bt");
        COUNTRY_CODES.put("Cabo Verde", "cv");
        COUNTRY_CODES.put("Camarões", "cm");
        COUNTRY_CODES.put("Camboja", "kh");
        COUNTRY_CODES.put("Canadá", "ca");
        COUNTRY_CODES.put("Catar", "qa");
        COUNTRY_CODES.put("Cazaquistão", "kz");
        COUNTRY_CODES.put("Chade", "td");
        COUNTRY_CODES.put("Chile", "cl");
        COUNTRY_CODES.put("China", "cn");
        COUNTRY_CODES.put("Chipre", "cy");
        COUNTRY_CODES.put("Colômbia", "co");
        COUNTRY_CODES.put("Comores", "km");
        COUNTRY_CODES.put("Congo", "cg");
        COUNTRY_CODES.put("Coreia do Norte", "kp");
        COUNTRY_CODES.put("Coreia do Sul", "kr");
        COUNTRY_CODES.put("Costa do Marfim", "ci");
        COUNTRY_CODES.put("Costa Rica", "cr");
        COUNTRY_CODES.put("Croácia", "hr");
        COUNTRY_CODES.put("Cuba", "cu");
        COUNTRY_CODES.put("Dinamarca", "dk");
        COUNTRY_CODES.put("Djibouti", "dj");
        COUNTRY_CODES.put("Dominica", "dm");
        COUNTRY_CODES.put("Egito", "eg");
        COUNTRY_CODES.put("El Salvador", "sv");
        COUNTRY_CODES.put("Emirados Árabes Unidos", "ae");
        COUNTRY_CODES.put("Equador", "ec");
        COUNTRY_CODES.put("Eritreia", "er");
        COUNTRY_CODES.put("Eslováquia", "sk");
        COUNTRY_CODES.put("Eslovênia", "si");
        COUNTRY_CODES.put("Espanha", "es");
        COUNTRY_CODES.put("Estados Unidos", "us");
        COUNTRY_CODES.put("Estônia", "ee");
        COUNTRY_CODES.put("Eswatini", "sz");
        COUNTRY_CODES.put("Etiópia", "et");
        COUNTRY_CODES.put("Fiji", "fj");
        COUNTRY_CODES.put("Filipinas", "ph");
        COUNTRY_CODES.put("Finlândia", "fi");
        COUNTRY_CODES.put("França", "fr");
        COUNTRY_CODES.put("Gabão", "ga");
        COUNTRY_CODES.put("Gâmbia", "gm");
        COUNTRY_CODES.put("Gana", "gh");
        COUNTRY_CODES.put("Geórgia", "ge");
        COUNTRY_CODES.put("Granada", "gd");
        COUNTRY_CODES.put("Grécia", "gr");
        COUNTRY_CODES.put("Guatemala", "gt");
        COUNTRY_CODES.put("Guiana", "gy");
        COUNTRY_CODES.put("Guiné", "gn");
        COUNTRY_CODES.put("Guiné Equatorial", "gq");
        COUNTRY_CODES.put("Guiné-Bissau", "gw");
        COUNTRY_CODES.put("Haiti", "ht");
        COUNTRY_CODES.put("Honduras", "hn");
        COUNTRY_CODES.put("Hungria", "hu");
        COUNTRY_CODES.put("Iêmen", "ye");
        COUNTRY_CODES.put("Ilhas Marshall", "mh");
        COUNTRY_CODES.put("Ilhas Salomão", "sb");
        COUNTRY_CODES.put("Índia", "in");
        COUNTRY_CODES.put("Indonésia", "id");
        COUNTRY_CODES.put("Irã", "ir");
        COUNTRY_CODES.put("Iraque", "iq");
        COUNTRY_CODES.put("Irlanda", "ie");
        COUNTRY_CODES.put("Islândia", "is");
        COUNTRY_CODES.put("Israel", "il");
        COUNTRY_CODES.put("Itália", "it");
        COUNTRY_CODES.put("Jamaica", "jm");
        COUNTRY_CODES.put("Japão", "jp");
        COUNTRY_CODES.put("Jordânia", "jo");
        COUNTRY_CODES.put("Kiribati", "ki");
        COUNTRY_CODES.put("Kuwait", "kw");
        COUNTRY_CODES.put("Laos", "la");
        COUNTRY_CODES.put("Lesoto", "ls");
        COUNTRY_CODES.put("Letônia", "lv");
        COUNTRY_CODES.put("Líbano", "lb");
        COUNTRY_CODES.put("Libéria", "lr");
        COUNTRY_CODES.put("Líbia", "ly");
        COUNTRY_CODES.put("Liechtenstein", "li");
        COUNTRY_CODES.put("Lituânia", "lt");
        COUNTRY_CODES.put("Luxemburgo", "lu");
        COUNTRY_CODES.put("Macedônia do Norte", "mk");
        COUNTRY_CODES.put("Madagascar", "mg");
        COUNTRY_CODES.put("Malásia", "my");
        COUNTRY_CODES.put("Malawi", "mw");
        COUNTRY_CODES.put("Maldivas", "mv");
        COUNTRY_CODES.put("Mali", "ml");
        COUNTRY_CODES.put("Malta", "mt");
        COUNTRY_CODES.put("Marrocos", "ma");
        COUNTRY_CODES.put("Maurícia", "mu");
        COUNTRY_CODES.put("Mauritânia", "mr");
        COUNTRY_CODES.put("México", "mx");
        COUNTRY_CODES.put("Micronésia", "fm");
        COUNTRY_CODES.put("Moçambique", "mz");
        COUNTRY_CODES.put("Moldávia", "md");
        COUNTRY_CODES.put("Mônaco", "mc");
        COUNTRY_CODES.put("Mongólia", "mn");
        COUNTRY_CODES.put("Montenegro", "me");
        COUNTRY_CODES.put("Myanmar", "mm");
        COUNTRY_CODES.put("Namíbia", "na");
        COUNTRY_CODES.put("Nauru", "nr");
        COUNTRY_CODES.put("Nepal", "np");
        COUNTRY_CODES.put("Nicarágua", "ni");
        COUNTRY_CODES.put("Níger", "ne");
        COUNTRY_CODES.put("Nigéria", "ng");
        COUNTRY_CODES.put("Noruega", "no");
        COUNTRY_CODES.put("Nova Zelândia", "nz");
        COUNTRY_CODES.put("Omã", "om");
        COUNTRY_CODES.put("Países Baixos", "nl");
        COUNTRY_CODES.put("Palau", "pw");
        COUNTRY_CODES.put("Panamá", "pa");
        COUNTRY_CODES.put("Papua-Nova Guiné", "pg");
        COUNTRY_CODES.put("Paquistão", "pk");
        COUNTRY_CODES.put("Paraguai", "py");
        COUNTRY_CODES.put("Peru", "pe");
        COUNTRY_CODES.put("Polônia", "pl");
        COUNTRY_CODES.put("Portugal", "pt");
        COUNTRY_CODES.put("Quênia", "ke");
        COUNTRY_CODES.put("Quirguistão", "kg");
        COUNTRY_CODES.put("Reino Unido", "gb");
        COUNTRY_CODES.put("República Centro-Africana", "cf");
        COUNTRY_CODES.put("República Democrática do Congo", "cd");
        COUNTRY_CODES.put("República Dominicana", "do");
        COUNTRY_CODES.put("República Tcheca", "cz");
        COUNTRY_CODES.put("Romênia", "ro");
        COUNTRY_CODES.put("Ruanda", "rw");
        COUNTRY_CODES.put("Rússia", "ru");
        COUNTRY_CODES.put("Samoa", "ws");
        COUNTRY_CODES.put("San Marino", "sm");
        COUNTRY_CODES.put("Santa Lúcia", "lc");
        COUNTRY_CODES.put("São Cristóvão e Nevis", "kn");
        COUNTRY_CODES.put("São Tomé e Príncipe", "st");
        COUNTRY_CODES.put("São Vicente e Granadinas", "vc");
        COUNTRY_CODES.put("Seicheles", "sc");
        COUNTRY_CODES.put("Senegal", "sn");
        COUNTRY_CODES.put("Serra Leoa", "sl");
        COUNTRY_CODES.put("Sérvia", "rs");
        COUNTRY_CODES.put("Singapura", "sg");
        COUNTRY_CODES.put("Síria", "sy");
        COUNTRY_CODES.put("Somália", "so");
        COUNTRY_CODES.put("Sri Lanka", "lk");
        COUNTRY_CODES.put("Sudão", "sd");
        COUNTRY_CODES.put("Sudão do Sul", "ss");
        COUNTRY_CODES.put("Suécia", "se");
        COUNTRY_CODES.put("Suíça", "ch");
        COUNTRY_CODES.put("Suriname", "sr");
        COUNTRY_CODES.put("Tailândia", "th");
        COUNTRY_CODES.put("Taiwan", "tw");
        COUNTRY_CODES.put("Tajiquistão", "tj");
        COUNTRY_CODES.put("Tanzânia", "tz");
        COUNTRY_CODES.put("Timor-Leste", "tl");
        COUNTRY_CODES.put("Togo", "tg");
        COUNTRY_CODES.put("Tonga", "to");
        COUNTRY_CODES.put("Trinidad e Tobago", "tt");
        COUNTRY_CODES.put("Tunísia", "tn");
        COUNTRY_CODES.put("Turcomenistão", "tm");
        COUNTRY_CODES.put("Turquia", "tr");
        COUNTRY_CODES.put("Tuvalu", "tv");
        COUNTRY_CODES.put("Ucrânia", "ua");
        COUNTRY_CODES.put("Uganda", "ug");
        COUNTRY_CODES.put("Uruguai", "uy");
        COUNTRY_CODES.put("Uzbequistão", "uz");
        COUNTRY_CODES.put("Vanuatu", "vu");
        COUNTRY_CODES.put("Vaticano", "va");
        COUNTRY_CODES.put("Venezuela", "ve");
        COUNTRY_CODES.put("Vietnã", "vn");
        COUNTRY_CODES.put("Zâmbia", "zm");
        COUNTRY_CODES.put("Zimbábue", "zw");
    }

    // Obtém o código de país para exibição de bandeira
    private static String getCountryCode(String countryName) {
        return COUNTRY_CODES.getOrDefault(countryName, "unknown"); // Consulta rápida (O(1))
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

        // Coluna de tempo médio
        TableColumn<PilotoEstatistica, Double> colTempoMedio = criarColunaEstilizada("TEMPO MÉDIO (s)", "tempoMedio", 160);

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

        // Adiciona colunas
        tabela.getColumns().addAll(colNome, colIdade, colNacionalidade, colTempoMedio);
        
        // Carrega dados dos pilotos
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