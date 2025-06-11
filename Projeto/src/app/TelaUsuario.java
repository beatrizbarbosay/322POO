package app;

import javafx.scene.control.Alert;

public class TelaUsuario {
    public static void exibir() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuário");
        alert.setHeaderText("Bem-vindo, Usuário!");
        alert.setContentText("Aqui será implementado o menu de apostas e simulação.");
        alert.showAndWait();
    }
}
