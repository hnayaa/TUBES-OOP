package kelompok.tiga;

import java.util.ArrayList;
import java.util.List;

public class Pengelola {
    private String id;
    private String namaPengelola;
    private String kontak;
    private static List<Pengelola> dataPengelola = new ArrayList<>(); // list untuk menyimpan daftar pengelola

    // Constructor
    public Pengelola(String id, String namaPengelola, String kontak) {
        this.id = id;
        this.namaPengelola = namaPengelola;
        this.kontak = kontak;
        dataPengelola.add(this);
    }

    // Method Setter & Getter
    public static List<Pengelola> getDataPengelola() {
        return dataPengelola;
    }

    public String getId() {
        return id;
    }
    public String getNamaPengelola() {
        return namaPengelola;
    }
    public String getKontak() {
        return kontak;
    }

    @Override
    public String toString() {
    return this.namaPengelola; // Untuk kelas Gedung
    }
}
