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


public class MenuPengelolaController {
    @FXML private TableView<Peminjaman> tableView;
    @FXML private TableColumn<Peminjaman, String> colRuang;
    @FXML private TableColumn<Peminjaman, String> colGedung;
    @FXML private TableColumn<Peminjaman, String> colTanggal;
    @FXML private TableColumn<Peminjaman, String> colJamMasuk;
    @FXML private TableColumn<Peminjaman, String> colJamKeluar;
    @FXML private TableColumn<Peminjaman, String> colPeminjam;
    @FXML private TableColumn<Peminjaman, String> colKeterangan;
    @FXML private TableColumn<Peminjaman, String> colStatus;

    @FXML
    public void initialize(){
        colGedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        colRuang.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJamMasuk.setCellValueFactory(new PropertyValueFactory<>("jamMasuk"));
        colJamKeluar.setCellValueFactory(new PropertyValueFactory<>("jamKeluar"));
        colPeminjam.setCellValueFactory(new PropertyValueFactory<>("namaPeminjam"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(DataPeminjaman.getInstance().getDataRuangList());
    }

    @FXML
    public void handleStatusChange(ActionEvent event, String newStatus) {
        Peminjaman selectedValues = tableView.getSelectionModel().getSelectedItem();
        if (selectedValues != null) {
            selectedValues.setStatus(newStatus);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Pengajuan berhasil diubah ke status: " + newStatus);
            alert.show();
        } else {
            showWarning("Silakan pilih data terlebih dahulu!");
        }
    }

    @FXML
    public void handleAcceptButton(ActionEvent event) {
        handleStatusChange(event, "Disetujui");
    }

    @FXML
    public void handleRejectButton(ActionEvent event) {
        handleStatusChange(event, "Ditolak");
    }

    @FXML
    public void handleLogOutButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/welcome.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void handleInfoGedung(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/infoGedung.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setContentText(message);
        alert.showAndWait();
    }
}