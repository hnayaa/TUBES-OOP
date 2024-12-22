package kelompok.tiga;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Peminjaman {
    private StringProperty ruangan;
    private StringProperty gedung;
    private ObjectProperty<LocalDate> tanggal;
    private StringProperty jamMasuk;
    private StringProperty jamKeluar;
    private StringProperty namaPeminjam;
    private StringProperty keterangan;
    private StringProperty status;
    private StringProperty jenis;

    public Peminjaman(String ruangan, String gedung, LocalDate tanggal, String jamMasuk, String jamKeluar, String namaPeminjam, String status, String keterangan, String jenis) {
        this.ruangan = new SimpleStringProperty(ruangan);
        this.gedung = new SimpleStringProperty(gedung);
        this.tanggal = new SimpleObjectProperty<>(tanggal); 
        this.jamMasuk = new SimpleStringProperty(jamMasuk);
        this.jamKeluar = new SimpleStringProperty(jamKeluar);
        this.namaPeminjam = new SimpleStringProperty(namaPeminjam);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.status = new SimpleStringProperty(status);
        this.jenis = new SimpleStringProperty(jenis);
    }

    // Getter dan Setter menggunakan StringProperty
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getRuangan() {
        return ruangan.get();
    }

    public String getGedung() {
        return gedung.get();
    }

    public LocalDate getTanggal() {
        return tanggal.get();
    }


    public String getJamMasuk() {
        return jamMasuk.get();
    }

    public String getJamKeluar() {
        return jamKeluar.get();
    }

    public String getNamaPeminjam() {
        return namaPeminjam.get();
    }


    public String getKeterangan() {
        return keterangan.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    
}

