package nyp.m4.ev;

import java.util.ArrayList;

/**
 * M4 - Odalardan oluşan bir ev: bileşim (composition) örneği.
 *
 * <p>Ev, odalarını kendi içinde yaratır ({@link #odaEkle}) ve oda listesini dışarı vermez. Ev
 * nesnesine erişim kalmadığında odalarına da erişim kalmaz; parçalar bütünle birlikte yaşar ve
 * ölür.
 */
public class Ev {

    private final String adres;
    private final ArrayList<Oda> odalar = new ArrayList<>();

    public Ev(String adres) {
        if (adres == null || adres.isBlank()) {
            throw new IllegalArgumentException("Adres boş olamaz");
        }
        this.adres = adres;
    }

    /** Yeni bir oda yaratıp eve ekler. Aynı adlı bir oda zaten varsa eklemez. */
    public boolean odaEkle(String ad, double en, double boy) {
        if (odaBul(ad) != null) {
            return false;
        }
        odalar.add(new Oda(ad, en, boy));   // parçayı bütün yaratıyor
        return true;
    }

    /** Adı verilen odanın alanını (m²) döndürür. */
    public double odaAlani(String ad) {
        Oda oda = odaBul(ad);
        if (oda == null) {
            throw new IllegalArgumentException("Böyle bir oda yok: " + ad);
        }
        return oda.alan();
    }

    /** Tüm odaların alanları toplamı (m²). */
    public double toplamAlan() {
        double toplam = 0;
        for (Oda oda : odalar) {
            toplam += oda.alan();
        }
        return toplam;
    }

    public int odaSayisi() {
        return odalar.size();
    }

    public String getAdres() {
        return adres;
    }

    private Oda odaBul(String ad) {
        for (Oda oda : odalar) {
            if (oda.getAd().equals(ad)) {
                return oda;
            }
        }
        return null;
    }
}
