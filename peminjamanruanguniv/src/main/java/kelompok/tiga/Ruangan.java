package kelompok.tiga;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ruangan extends JenisRuangan {
    private String idRuangan;
    private String namaRuangan; 
    private Gedung gedung;
    private static List<Ruangan> dataRuangan = new ArrayList<>(); // List untuk menyimpan daftar ruangan


    public Ruangan(String idRuangan, String namaRuangan, String idJenisRuangan, String namaJenis, Gedung gedung) {
        super(idJenisRuangan, namaJenis);
        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.gedung = gedung;
    }

    // Method Getter & Setter

    public static List<Ruangan> getAllDataRuangan() {
        return dataRuangan;
    }

    // Method untuk mengambil info ruangan berdasarkan gedung
    public static List<Ruangan> getDataRuanganByGedung(String idGedung) {
        return dataRuangan.stream()
                .filter(ruangan -> ruangan.getIdGedung().equals(idGedung))
                .toList();
    }

    // Method untuk mengambil info ruangan berdasarkan jenis ruangan
    public static List<Ruangan> getDataRuanganByJenis(JenisRuangan filterRuanganByJenis) {
        if (filterRuanganByJenis == null){
            return dataRuangan;
        }else{
            return dataRuangan.stream()
                    .filter(ruangan -> ruangan.getJenisRuangan().equals(filterRuanganByJenis))
                    .collect(Collectors.toList());
        }
    }

    // method untuk menambahkan ruangan ke List dataRuangan
    public static void tambahRuangan(Ruangan ruangan) {
        dataRuangan.add(ruangan);
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    // mengambil id gedung dari class Gedung
    public String getIdGedung(){
        return gedung.getIdGedung(); 
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    // mengambil id jenis ruangan
    public String getIdJenisRuangan() {
        return super.getIdJenisRuangan();
    }

    // mengambil jenis raungan (namanya)
    public String getJenisRuangan() {
        return super.getIdJenisRuangan();
    }

    public Gedung getGedung(){
        return gedung;
    }

    @Override
    public String toString() {
        return namaRuangan + " ("+ gedung.getNamaGedung() + ")";
    }
}

