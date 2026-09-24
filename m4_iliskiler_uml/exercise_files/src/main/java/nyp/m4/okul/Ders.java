package nyp.m4.okul;

import java.util.ArrayList;

/**
 * M4 - Öğrencilerin kaydolduğu bir ders: toplama (aggregation) ve tek yönlü ilişkilendirme örneği.
 *
 * <p>Ders öğrencilerini bilir, öğrenci derslerini bilmez. Öğrenciler dışarıda yaratılır ve
 * {@link #kaydet} ile derse eklenir; aynı öğrenci birden çok derse kayıtlı olabilir.
 */
public class Ders {

    private final String kod;
    private final int kontenjan;
    private final ArrayList<Ogrenci> ogrenciler = new ArrayList<>();

    public Ders(String kod, int kontenjan) {
        if (kod == null || kod.isBlank()) {
            throw new IllegalArgumentException("Ders kodu boş olamaz");
        }
        if (kontenjan < 1) {
            throw new IllegalArgumentException("Kontenjan en az 1 olmalı");
        }
        this.kod = kod;
        this.kontenjan = kontenjan;
    }

    /** Öğrenciyi derse kaydeder. Kontenjan doluysa ya da öğrenci zaten kayıtlıysa {@code false}. */
    public boolean kaydet(Ogrenci ogrenci) {
        if (ogrenci == null) {
            throw new IllegalArgumentException("Öğrenci null olamaz");
        }
        if (ogrenciler.size() >= kontenjan || kayitliMi(ogrenci)) {
            return false;
        }
        ogrenciler.add(ogrenci);
        return true;
    }

    public boolean kayitliMi(Ogrenci ogrenci) {
        for (Ogrenci o : ogrenciler) {
            if (o == ogrenci) {
                return true;
            }
        }
        return false;
    }

    public int ogrenciSayisi() {
        return ogrenciler.size();
    }

    public String getKod() {
        return kod;
    }

    public int getKontenjan() {
        return kontenjan;
    }
}
