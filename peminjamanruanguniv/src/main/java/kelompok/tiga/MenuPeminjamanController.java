package kelompok.tiga;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuPeminjamanController {
    @FXML
    private TextField namaPeminjamField, jamMasukField, jamKeluarField, keteranganField;
    @FXML
    private ComboBox<Gedung> pilihGedungComboBox;
    @FXML
    private ComboBox<JenisRuangan> jenisRuanganComboBox;
    @FXML
    private ComboBox<Ruangan> pilihRuanganComboBox;
    @FXML
    private DatePicker tanggalPeminjamanPicker;
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
        loadGedungData();
        loadJenisRuanganData();
        jenisRuanganComboBox.setOnAction(this::filterRuanganByJenis);
        colGedung.setCellValueFactory(new PropertyValueFactory<>("gedung"));
        colRuang.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJamMasuk.setCellValueFactory(new PropertyValueFactory<>("jamMasuk"));
        colJamKeluar.setCellValueFactory(new PropertyValueFactory<>("jamKeluar"));
        colPeminjam.setCellValueFactory(new PropertyValueFactory<>("namaPeminjam"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(DataPeminjaman.getInstance().getDataRuangList());
    }

    private void loadGedungData() {
        List<Gedung> gedungList = Gedung.getDataGedung(); // Ambil data dari kelas Gedung
        ObservableList<Gedung> gedungObservableList = FXCollections.observableArrayList(gedungList);
        pilihGedungComboBox.setItems(gedungObservableList);
    }

    private void loadJenisRuanganData() {
        List<JenisRuangan> jenisRuanganList = JenisRuangan.getDataJenisRuangan(); // Ambil data dari kelas JenisRuangan
        ObservableList<JenisRuangan> jenisObservableList = FXCollections.observableArrayList(jenisRuanganList);
        jenisRuanganComboBox.setItems(jenisObservableList);
    }

    private void filterRuanganByJenis(ActionEvent event) {
        JenisRuangan selectedJenis = jenisRuanganComboBox.getValue();
        Gedung selectedGedung = pilihGedungComboBox.getValue();
        
        if (selectedJenis != null) {
            List<Ruangan> ruanganFiltered = Ruangan.getDataRuanganByJenis(null).stream()
                    .filter(ruangan -> ruangan.getIdJenisRuangan() == selectedJenis.getIdJenisRuangan())
                    .collect(Collectors.toList());

            List<Ruangan> ruanganFilteredByGedung = ruanganFiltered.stream()
                    .filter(ruangan -> ruangan.getIdGedung() == selectedGedung.getIdGedung())
                    .collect(Collectors.toList());

            
    
            ObservableList<Ruangan> ruanganObservableList = FXCollections.observableArrayList(ruanganFilteredByGedung);
            pilihRuanganComboBox.setItems(ruanganObservableList);
        } else {
            pilihRuanganComboBox.getItems().clear(); // Kosongkan jika jenis ruangan tidak dipilih
        }
    }
    

    @FXML
    public void handleSubmitButton(ActionEvent event){
        String namaPeminjam = namaPeminjamField.getText();
        Gedung gedung = pilihGedungComboBox.getValue();
        JenisRuangan jenisRuangan = jenisRuanganComboBox.getValue();
        Ruangan ruangan = pilihRuanganComboBox.getValue();
        LocalDate tanggal = tanggalPeminjamanPicker.getValue();
        String jamMasuk = jamMasukField.getText();
        String jamKeluar = jamKeluarField.getText();
        String keterangan = keteranganField.getText();
        try {

            if (namaPeminjam.isEmpty() || gedung== null || ruangan == null || tanggal == null || jamMasuk.isEmpty() || jamKeluar.isEmpty() || keterangan.isEmpty() ){
                throw new MissingFieldException("Tolong isi semua field!!!");
            }
            
            if(tanggalPeminjamanPicker.getValue().isBefore(LocalDate.now())) {
                throw new InvalidDateException("jangan pilih tanggal yang sudah lewat!!!");
            }

            if (!jamMasuk.matches("\\d{2}:\\d{2}") || !jamKeluar.matches("\\d{2}:\\d{2}")) {
                throw new InvalidTimeException("Format jam harus dalam format HH:mm");
            }

            if (TimeConflict(ruangan.getNamaRuangan(), tanggal, jamMasuk, jamKeluar)) {
                throw new TimeConflictException("Ruangan ini sudah di pesan pada waktu tersebut. Silahkan pilih ruangan atau waktu lain!!!");
            }

            Peminjaman peminjaman = new Peminjaman(
                ruangan.getNamaRuangan(), 
                gedung.getNamaGedung(),
                tanggal, 
                jamMasuk, 
                jamKeluar, 
                namaPeminjam,
                "belum", 
                keterangan,
                jenisRuangan.getNamaJenis());

            DataPeminjaman.getInstance().tambahData(peminjaman);
            clearData();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Peminjaman ruangan berhasil diajukan, cek secara berkala 1x24jam untuk mengetahui status persetujuan!!!");
            alert.show();
        }
        catch (MissingFieldException | InvalidDateException | InvalidTimeException | TimeConflictException e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleCancelButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resource/menuPembatalan.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException ex){
            ex.printStackTrace();
        }
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

    private boolean TimeConflict(String ruangan, LocalDate tanggal, String jamMasukBaru, String jamKeluarBaru) {
        List<Peminjaman> existingPeminjamanList = DataPeminjaman.getInstance().getDataRuangList();
        for (Peminjaman peminjaman : existingPeminjamanList) {
            if(peminjaman.getRuangan().equals(ruangan) && peminjaman.getTanggal().equals(tanggal)) {
                String jamMasukExist = peminjaman.getJamMasuk();
                String jamKeluarExist = peminjaman.getJamKeluar();
                    if (TimeOverlap(jamMasukBaru, jamKeluarBaru, jamMasukExist, jamKeluarExist)) {
                        return true; // Konflik ditemukan
                }
            }
        }
        return false; //tidak ada konflik
    }

    private boolean TimeOverlap(String jamMasukBaru, String jamKeluarBaru, String jamMasukExist, String jamKeluarExist) {
        int jamMasukBaruInt = Integer.parseInt(jamMasukBaru.replace(":", ""));
        int jamKeluarBaruInt = Integer.parseInt(jamKeluarBaru.replace(":", ""));
        int jamMasukExistInt = Integer.parseInt(jamMasukExist.replace(":", ""));
        int jamKeluarExistInt = Integer.parseInt(jamKeluarExist.replace(":", ""));

        return (jamMasukBaruInt < jamKeluarExistInt) && (jamKeluarBaruInt > jamMasukExistInt);
    }

    private void clearData() {
        namaPeminjamField.clear();
        pilihGedungComboBox.setValue(null); // Mengosongkan nilai terpilih
        jenisRuanganComboBox.setValue(null); // Mengosongkan nilai terpilih
        pilihRuanganComboBox.setValue(null); // Mengosongkan nilai terpilih
        tanggalPeminjamanPicker.setValue(null); // Bersihkan tanggal yang dipilih
        jamMasukField.clear();
        jamKeluarField.clear();
        keteranganField.clear();
    }

    public class InvalidDateException extends Exception {
        public InvalidDateException(String Message){
            super(Message);
        }
        
    }

    public class MissingFieldException extends Exception {
        public MissingFieldException(String Message){
            super(Message);
        }
        
    }
    public class InvalidTimeException extends Exception {
        public InvalidTimeException(String Message){
            super(Message);
        }
        
    }
    public class TimeConflictException extends Exception {
        public TimeConflictException(String Message){
            super(Message);
        }
        
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

}
