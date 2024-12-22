package kelompok.tiga;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuPembatalanController {
    @FXML
    private TableView<Peminjaman> tableView;
    @FXML
    private TableColumn<Peminjaman, String> colRuang;
    @FXML
    private TableColumn<Peminjaman, String> colGedung;
    @FXML
    private TableColumn<Peminjaman, String> colTanggal;
    @FXML
    private TableColumn<Peminjaman, String> colJamMasuk;
    @FXML
    private TableColumn<Peminjaman, String> colJamKeluar;
    @FXML
    private TableColumn<Peminjaman, String> colPeminjam;
    @FXML
    private TableColumn<Peminjaman, String> colStatus;

    @FXML
    public void initialize(){
        colGedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        colRuang.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJamMasuk.setCellValueFactory(new PropertyValueFactory<>("jamMasuk"));
        colJamKeluar.setCellValueFactory(new PropertyValueFactory<>("jamKeluar"));
        colPeminjam.setCellValueFactory(new PropertyValueFactory<>("namaPeminjam"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(DataPeminjaman.getInstance().getDataRuangList());
    }

    @FXML
    public void handleDeleteButton(){
        Peminjaman selectedValues = tableView.getSelectionModel().getSelectedItem();
        if (selectedValues != null){
            DataPeminjaman.getInstance().hapusData(selectedValues);
            //Menampilkan pesan pemberitahuan
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Peminjaman berhasil dihapus!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Pilih Peminjaman terlebih dahulu!");
            alert.show();
        }
    } 
    @FXML
    public void handleBackButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/mainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
