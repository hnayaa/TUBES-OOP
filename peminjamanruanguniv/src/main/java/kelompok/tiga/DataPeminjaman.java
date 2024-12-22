package kelompok.tiga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataPeminjaman {
    private static DataPeminjaman instance; // Singleton instance
    private ObservableList<Peminjaman> dataRuangList;

    // Constructor private untuk singleton
    private DataPeminjaman() {
        dataRuangList = FXCollections.observableArrayList();
    }

    // Method untuk mendapatkan instance tunggal
    public static DataPeminjaman getInstance() {
        if (instance == null) {
            instance = new DataPeminjaman();
        }
        return instance;
    }

    // Method untuk mendapatkan ObservableList
    public ObservableList<Peminjaman> getDataRuangList() {
        return dataRuangList;
    }

    // Contoh method untuk menambahkan data
    public void tambahData(Peminjaman data) {
        dataRuangList.add(data);
    }

    // Contoh method untuk menghapus data
    public void hapusData(Peminjaman data) {
        dataRuangList.remove(data);
    }

    public void updateStatusPeminjaman(Peminjaman peminjaman) {
        for (Peminjaman p : dataRuangList) {
            if (p.equals(peminjaman)) {
                p.setStatus(peminjaman.getStatus());
                break;
            }
        }
    }
}
