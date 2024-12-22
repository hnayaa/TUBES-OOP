package kelompok.tiga;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/resource/welcome.fxml");
        if (fxmlLocation == null) {
        System.out.println("File FXML tidak ditemukan! Periksa jalur file.");
        }
        Parent root = FXMLLoader.load(fxmlLocation);
        if (root == null) {
            System.out.println("File FXML tidak ditemukan!");
        }else{

            primaryStage.setTitle("Peminjaman Ruang Telko University");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        // Menambahkan data ke kelas masing-masing

        // Membuat Pengelola
        Pengelola pengelola1 = new Pengelola("PG0101", "Muhammad Karim", "0812345678");
        Pengelola pengelola2 = new Pengelola("PG0202", "Reja", "0887654321");

        // Membuat Jenis Ruangan
        JenisRuangan lab = new JenisRuangan("1", "Lab");
        JenisRuangan kelas = new JenisRuangan("2", "Kelas");
        JenisRuangan auditorium = new JenisRuangan("3", "Auditorium");
        JenisRuangan.tambahJenisRuangan(lab);
        JenisRuangan.tambahJenisRuangan(kelas);
        JenisRuangan.tambahJenisRuangan(auditorium);

        // Membuat Gedung
        Gedung gedungA = new Gedung("GD0001", "TULT", pengelola1, pengelola1);
        Gedung gedungB = new Gedung("GD0002", "CACUK", pengelola2, pengelola2);

        // Membuat Ruangan dan menambahkannya ke koleksi
        Ruangan ruangLab = new Ruangan("101", "INTEGRA 05", "1", "Lab" ,gedungA);
        Ruangan ruangLab2 = new Ruangan("102", "L5", "1", "Lab" ,gedungB);
        Ruangan ruangKelas = new Ruangan("201", "TULT.15.08", "2", "Kelas", gedungA);
        Ruangan ruangKelas2 = new Ruangan("202", "KU2.03.11", "2", "Kelas", gedungB);
        Ruangan ruangAuditorium = new Ruangan("301", "AuditoriumLT16 Utama", "3", "Auditorium", gedungA);
        Ruangan ruangAuditorium2 = new Ruangan("302", "Auditorium Cacuk", "3", "Auditorium", gedungB);
        Ruangan.tambahRuangan(ruangLab);
        Ruangan.tambahRuangan(ruangLab2);
        Ruangan.tambahRuangan(ruangKelas);
        Ruangan.tambahRuangan(ruangKelas2);
        Ruangan.tambahRuangan(ruangAuditorium);
        Ruangan.tambahRuangan(ruangAuditorium2);


        // Menampilkan data yang telah ditambahkan ke koleksi
        System.out.println("Data Pengelola:");
        for (Pengelola p : Pengelola.getDataPengelola()) {
            System.out.println(p);
        }
        System.out.println("Data Jenis Ruangan:");
        for (JenisRuangan j : JenisRuangan.getDataJenisRuangan()) {
            System.out.println(j);
        }

        System.out.println("\nData Ruangan:");
        for (Ruangan r : Ruangan.getAllDataRuangan()) {
            System.out.println(r);
        }
        launch(args);
    }
    
}
