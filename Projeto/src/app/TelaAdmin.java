package app;

import javafx.scene.control.Alert;

public class TelaAdmin {
    public static void exibir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Admin");
        alert.setHeaderText("Bem-vindo, Administrador!");
        alert.setContentText("Aqui será implementado o menu de gerenciamento.");
        alert.showAndWait();
    }
}
