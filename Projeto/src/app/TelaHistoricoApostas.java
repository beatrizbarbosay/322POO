package app;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class TelaHistoricoApostas {
    private static Stage stage;

    public TelaHistoricoApostas(Stage stage) {
        stage = new Stage();
    }
    
    // Exibe histórico de apostas
    public static void exibir() {
        mostrarAlerta("Histórico de Apostas", "Aqui será exibido o histórico de apostas do usuário");
    }
    private static void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}