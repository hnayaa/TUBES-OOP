package kelompok.tiga;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private void handlePengelolaButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/loginPengelola.fxml")); //load path file fxml
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void handlePeminjamButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/loginPeminjam.fxml")); //load path file fxml
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
