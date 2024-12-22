package kelompok.tiga;

import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InfoGedungController {
    @FXML
    private TableView<Gedung> tableView;
    @FXML 
    private TableColumn<Gedung, String> colIdGedung;
    @FXML 
    private TableColumn<Gedung, String> colNama;
    @FXML 
    private TableColumn<Gedung, String> colPengelola;
    @FXML 
    private TableColumn<Gedung, String> colKontak;

    @FXML
    public void initialize(){
        colIdGedung.setCellValueFactory(new PropertyValueFactory<>("idGedung"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaGedung"));
        colPengelola.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNamPengelola().getNamaPengelola()));
        colKontak.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKontak().getKontak()));
        loadGedungData();
    }

    private void loadGedungData(){ 
        List<Gedung> gedungList = Gedung.getDataGedung();//mengambil data gedung dari class Gedung
        if (gedungList == null) { // cek apakah data gedung ditemukan
            System.out.println("Data gedung tidak ditemukan atau null.");
            return;
        }
        ObservableList<Gedung> gedungObservableList = FXCollections.observableArrayList(gedungList);
        tableView.setItems(gedungObservableList); 

    }

    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/dashboardPengelola.fxml")); // load file fxml
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void handleInfoRuangan(ActionEvent event) {
        Gedung selectedValues = tableView.getSelectionModel().getSelectedItem(); // mengambil values dari data yang dipilih
        try{
            
            if (selectedValues == null) {
                throw new MissingGedungException("Pilih gedung terlebih dahulu!!!"); //cek apakah data sudah di select
            }

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/InfoRuangan.fxml")); // load file fxml
                Parent root = loader.load();

                // Mengakses controller InfoRuanganController dan mengirimkan data gedung
                InfoRuanganController controller = loader.getController();
                controller.setGedung(selectedValues);

                // Berpindah ke scene baru
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                System.err.println("Gagal memuat file FXML: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        catch (MissingGedungException e){
            showError(e.getMessage());
        }

    }

    public class MissingGedungException extends Exception {
        public MissingGedungException(String Message){
            super(Message);
        }
        
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
