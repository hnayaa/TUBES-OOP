package kelompok.tiga;

import java.util.ArrayList;
import java.util.List;

public class Gedung {
    private String idGedung;
    private String namaGedung;
    Pengelola namPengelola;
    Pengelola kontak;
    private static List<Gedung> dataGedung = new ArrayList<>(); // list untuk menyimpan daftar gedung
    

    // Constructor
    public Gedung(String idGedung, String namaGedung, Pengelola namaPengelola, Pengelola kontak) {
        this.idGedung = idGedung;
        this.namaGedung = namaGedung;
        this.namPengelola = namaPengelola;
        this.kontak = kontak;
        dataGedung.add(this); // Menambahkan data saat objek dibuat
    }


    // Method Getter & Setter
    public static List<Gedung> getDataGedung() {
        return dataGedung;
    }

    public String getIdGedung() {
        return idGedung;
    }

    public String getNamaGedung() {
        return namaGedung;
    }

    public Pengelola getNamPengelola() {  // Tambahkan getter untuk 'namPengelola'
        return namPengelola;
    }

    public Pengelola getKontak() {  // Tambahkan getter untuk 'kontak'
        return kontak;
    }

    @Override
    public String toString() {
    return this.namaGedung; // Untuk kelas Gedung
}

    
}
