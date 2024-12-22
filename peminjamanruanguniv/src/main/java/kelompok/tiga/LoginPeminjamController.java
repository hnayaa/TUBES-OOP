package kelompok.tiga;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPeminjamController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private void handleLoginButton(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("bintang11") && password.equals("050511")) { // set username & password
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resource/mainMenu.fxml")); // load path file fxml
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR); // menampilkan error username/password salah
            alert.setContentText("Username atau password anda salah!");
            alert.show();
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/welcome.fxml")); // load path file fxml
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}

