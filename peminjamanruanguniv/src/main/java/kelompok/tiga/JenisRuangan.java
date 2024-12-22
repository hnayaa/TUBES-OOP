package kelompok.tiga;

import java.util.ArrayList;
import java.util.List;

public class JenisRuangan {
    protected String idJenisRuangan;
    protected String namaJenis;
    private static List<JenisRuangan> dataJenisRuangan = new ArrayList<>(); // list untuk menyimpan daftar jenis ruangan

    public JenisRuangan(String idJenisRuangan, String namaJenis) {
        this.idJenisRuangan = idJenisRuangan;
        this.namaJenis = namaJenis;
    }

    // Method Getter & Setter
    public static List<JenisRuangan> getDataJenisRuangan() {
        return dataJenisRuangan;
    }
    public String getIdJenisRuangan() {
        return idJenisRuangan;
    }

    public String getNamaJenis() {
        return namaJenis;
    }

    public static void tambahJenisRuangan(JenisRuangan jenis){
        dataJenisRuangan.add(jenis);
    }

    @Override
    public String toString() {
        return namaJenis;
    }
}
