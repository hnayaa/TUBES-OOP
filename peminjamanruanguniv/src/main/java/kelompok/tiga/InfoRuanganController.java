package kelompok.tiga;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InfoRuanganController {
    @FXML
    private TableView<Ruangan> tableView;
    @FXML
    private TableColumn<Ruangan, String> colIdRuangan;
    @FXML
    private TableColumn<Ruangan, String> colIdGedung;
    @FXML
    private TableColumn<Ruangan, String> colRuangan;
    @FXML
    private TableColumn<Ruangan, String> colJenisRuangan;

    private Gedung gedungTerpilih;

    @FXML
    public void initialize(){
        colIdRuangan.setCellValueFactory(new PropertyValueFactory<>("idRuangan"));
        colIdGedung.setCellValueFactory(new PropertyValueFactory<>("idGedung"));
        colRuangan.setCellValueFactory(new PropertyValueFactory<>("namaRuangan"));
        colJenisRuangan.setCellValueFactory(new PropertyValueFactory<>("namaJenis"));
    }

    public void setGedung(Gedung gedung) {
        if (gedung == null) {
            System.out.println("Gedung yang dipilih null.");
            return;
        }
        this.gedungTerpilih = gedung;
        System.out.println("Gedung Terpilih: " + gedungTerpilih.getIdGedung()); // Debugging, lihat apakah idGedung valid
        loadRuanganData();
    }

    private void loadRuanganData(){
        if (gedungTerpilih == null || gedungTerpilih.getIdGedung() == null) {
            System.out.println("Gedung tidak ditemukan atau idGedung null.");
            return;
        }
        System.out.println("Memuat data ruangan untuk idGedung: " + gedungTerpilih.getIdGedung()); // Debugging
    
        // Ambil data ruangan berdasarkan idGedung dari metode Ruangan.getInfoRuangan
        List<Ruangan> ruanganList = Ruangan.getDataRuanganByGedung(gedungTerpilih.getIdGedung());
        if (ruanganList == null || ruanganList.isEmpty()) {
            System.out.println("Data ruangan tidak ditemukan atau kosong.");
            return;
        }
    
        // Konversi list ke ObservableList untuk menampilkannya di TableView
        ObservableList<Ruangan> ruanganObservableList = FXCollections.observableArrayList(ruanganList);
        tableView.setItems(ruanganObservableList);

    }

    @FXML
    public void handleBackButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/infoGedung.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
