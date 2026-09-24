package nyp.m4.kutuphane;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * M4 - Kitapları ve üyeleri bir arada tutan kütüphane.
 *
 * <p>Kitaplarla ilişkisi bir <b>toplamadır</b> (aggregation): kitaplar kütüphanenin dışında
 * yaratılır ve {@link #kitapEkle} ile içeri alınır; bir kitap kütüphaneden çıkarılıp başka bir
 * kütüphaneye verilebilir. {@link Odunc} ile ilişkisi ise bir <b>bağımlılıktır</b> (dependency):
 * kütüphane ödünç kayıtlarını üretir ve parametre olarak alır, ama saklamaz.
 */
public class Kutuphane {

    /** Geciken her gün için kesilen ceza (TL). */
    public static final double GUNLUK_CEZA = 2.5;

    private final String ad;
    private final ArrayList<Kitap> kitaplar = new ArrayList<>();
    private final ArrayList<Uye> uyeler = new ArrayList<>();

    public Kutuphane(String ad) {
        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Kütüphane adı boş olamaz");
        }
        this.ad = ad;
    }

    /** Kitabı rafa ekler. Aynı ISBN'li bir kitap zaten varsa eklemez ve {@code false} döndürür. */
    public boolean kitapEkle(Kitap kitap) {
        if (kitap == null) {
            throw new IllegalArgumentException("Kitap null olamaz");
        }
        if (kitapBul(kitap.getIsbn()) != null) {
            return false;
        }
        kitaplar.add(kitap);
        return true;
    }

    /** Kitabı kütüphaneden çıkarır. Ödünçteki ya da burada olmayan kitap çıkarılamaz. */
    public boolean kitapCikar(Kitap kitap) {
        if (kitap == null || kitap.oduncteMi()) {
            return false;
        }
        return kitaplar.remove(kitap);
    }

    /** ISBN'i verilen kitabı döndürür; bulunamazsa {@code null}. */
    public Kitap kitapBul(String isbn) {
        for (Kitap k : kitaplar) {
            if (k.getIsbn().equals(isbn)) {
                return k;
            }
        }
        return null;
    }

    /** Üyeyi kaydeder. Aynı üye ikinci kez eklenmez. */
    public boolean uyeEkle(Uye uye) {
        if (uye == null) {
            throw new IllegalArgumentException("Üye null olamaz");
        }
        if (uyeler.contains(uye)) {
            return false;
        }
        uyeler.add(uye);
        return true;
    }

    /**
     * Kitabı üyeye ödünç verir ve işlemin kaydını döndürür.
     *
     * @throws IllegalArgumentException üye kayıtlı değilse, kitap bu kütüphanede değilse ya da
     *     kitap ödünç verilemiyorsa (zaten ödünçte veya üyenin limiti dolu)
     */
    public Odunc oduncVer(Uye uye, Kitap kitap, LocalDate tarih) {
        if (!uyeler.contains(uye)) {
            throw new IllegalArgumentException("Üye bu kütüphaneye kayıtlı değil");
        }
        if (!kitaplar.contains(kitap)) {
            throw new IllegalArgumentException("Kitap bu kütüphanede değil");
        }
        if (!uye.oduncAl(kitap)) {
            throw new IllegalArgumentException("Kitap ödünç verilemez: " + kitap.getBaslik());
        }
        return new Odunc(uye, kitap, tarih);
    }

    /** Ödünç kaydındaki kitabı iade alır ve gecikme cezasını (TL) döndürür. */
    public double iadeAl(Odunc odunc, LocalDate iadeTarihi) {
        odunc.uye().iadeEt(odunc.kitap());
        return odunc.gecikmeGunu(iadeTarihi) * GUNLUK_CEZA;
    }

    public int kitapSayisi() {
        return kitaplar.size();
    }

    public int uyeSayisi() {
        return uyeler.size();
    }

    public String getAd() {
        return ad;
    }
}
