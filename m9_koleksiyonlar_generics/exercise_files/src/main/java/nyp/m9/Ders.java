package nyp.m9;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * M9 - Kontenjanlı bir ders ve ona kayıtlı öğrenciler.
 *
 * <p>Öğrenciler bir {@link Set}'te tutulur: aynı öğrenci iki kez kaydolamaz. {@link LinkedHashSet}
 * seçildiği için kayıt sırası da korunur.
 */
public class Ders {

    private final String kod;
    private final int kontenjan;
    private final Set<Ogrenci> ogrenciler = new LinkedHashSet<>();

    public Ders(String kod, int kontenjan) {
        if (kontenjan <= 0) {
            throw new IllegalArgumentException("Kontenjan pozitif olmalı: " + kontenjan);
        }
        this.kod = kod;
        this.kontenjan = kontenjan;
    }

    /**
     * Öğrenciyi derse kaydeder.
     *
     * @return öğrenci yeni kaydolduysa true, zaten kayıtlıysa false
     * @throws IllegalStateException kontenjan doluysa
     */
    public boolean kaydet(Ogrenci ogrenci) {
        if (ogrenciler.contains(ogrenci)) {
            return false;
        }
        if (ogrenciler.size() >= kontenjan) {
            throw new IllegalStateException("Kontenjan dolu: " + kod);
        }
        return ogrenciler.add(ogrenci);
    }

    public boolean kayitliMi(Ogrenci ogrenci) {
        return ogrenciler.contains(ogrenci);
    }

    public int ogrenciSayisi() {
        return ogrenciler.size();
    }

    /** Kayıt sırasıyla öğrencilerin değiştirilemez bir kopyası. */
    public List<Ogrenci> ogrenciListesi() {
        return List.copyOf(ogrenciler);
    }

    /**
     * Ortalaması {@code enAzOrtalama}'nın altında kalan öğrencilerin kaydını siler. Dolaşırken
     * güvenle silmek için {@link Iterator#remove()} kullanır.
     *
     * @return silinen kayıt sayısı
     */
    public int ortalamasiYetmeyenleriCikar(double enAzOrtalama) {
        int silinen = 0;
        Iterator<Ogrenci> it = ogrenciler.iterator();
        while (it.hasNext()) {
            Ogrenci ogrenci = it.next();
            if (ogrenci.getOrtalama() < enAzOrtalama) {
                it.remove();
                silinen++;
            }
        }
        return silinen;
    }

    public String getKod() {
        return kod;
    }

    public int getKontenjan() {
        return kontenjan;
    }
}
