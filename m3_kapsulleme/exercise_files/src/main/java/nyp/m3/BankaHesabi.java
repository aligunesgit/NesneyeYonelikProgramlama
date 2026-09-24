package nyp.m3;

/**
 * M3 - Kapsüllenmiş banka hesabı (M1'deki {@code Hesap} sınıfının geliştirilmiş sürümü).
 *
 * <p>Sınıf değişmezleri (class invariants): sahip boş olamaz; bakiye hiçbir zaman negatif olamaz;
 * işlem limiti 0'dan büyük ve {@link #UST_ISLEM_LIMITI} değerinden küçük ya da eşittir. Kurucu ve
 * tüm metotlar bu kuralları korur; geçersiz argümanda {@link IllegalArgumentException} fırlatılır
 * (istisnaların ayrıntısı M8'de).
 */
public class BankaHesabi {

    /** Bir işlemde çekilebilecek en yüksek limit. Tüm hesaplar için ortak bir sabit. */
    public static final double UST_ISLEM_LIMITI = 100_000;

    /** Yeni hesap limitinin varsayılan değeri. */
    public static final double VARSAYILAN_ISLEM_LIMITI = 10_000;

    /** Şimdiye kadar verilen son hesap numarası. static: nesneye değil sınıfa aittir. */
    private static int sonNumara = 0;

    private final int numara;
    private final String sahip;
    private double bakiye;
    private double islemLimiti;

    /** Sahibi ve işlem limiti verilen, bakiyesi 0 olan hesap açar. */
    public BankaHesabi(String sahip, double islemLimiti) {
        if (sahip == null || sahip.isBlank()) {
            throw new IllegalArgumentException("Hesap sahibi boş olamaz");
        }
        this.sahip = sahip;
        this.islemLimiti = gecerliLimit(islemLimiti);
        sonNumara = sonNumara + 1;
        this.numara = sonNumara;
    }

    /** Varsayılan işlem limitiyle hesap açar. */
    public BankaHesabi(String sahip) {
        this(sahip, VARSAYILAN_ISLEM_LIMITI);
    }

    /** Pozitif tutarı bakiyeye ekler. Tutar pozitif değilse IllegalArgumentException fırlatır. */
    public void paraYatir(double tutar) {
        if (tutar <= 0) {
            throw new IllegalArgumentException("Tutar pozitif olmalı: " + tutar);
        }
        bakiye = bakiye + tutar;
    }

    /**
     * Para çeker. Tutar pozitif değilse bu bir programlama hatasıdır: IllegalArgumentException.
     * Bakiye ya da işlem limiti yetmiyorsa bu olağan bir durumdur: false döner, bakiye değişmez.
     */
    public boolean paraCek(double tutar) {
        if (tutar <= 0) {
            throw new IllegalArgumentException("Tutar pozitif olmalı: " + tutar);
        }
        if (tutar > bakiye || tutar > islemLimiti) {
            return false;
        }
        bakiye = bakiye - tutar;
        return true;
    }

    /**
     * Bu hesaptan alıcıya para gönderir ("Tell, Don't Ask": çağıran bakiyeleri kendisi hesaplamaz).
     * Çekim yapılamazsa iki hesap da değişmez ve false döner.
     */
    public boolean havaleYap(BankaHesabi alici, double tutar) {
        if (alici == null || alici == this) {
            throw new IllegalArgumentException("Geçersiz alıcı hesap");
        }
        if (!paraCek(tutar)) {
            return false;
        }
        alici.paraYatir(tutar);
        return true;
    }

    /** İşlem limitini değiştirir; geçersiz değerde IllegalArgumentException fırlatır, limit değişmez. */
    public void setIslemLimiti(double islemLimiti) {
        this.islemLimiti = gecerliLimit(islemLimiti);
    }

    /** Kurucu ve setter'ın ortak doğrulaması: değişmez tek yerde tanımlanır. */
    private static double gecerliLimit(double limit) {
        if (limit <= 0 || limit > UST_ISLEM_LIMITI) {
            throw new IllegalArgumentException(
                    "İşlem limiti 0 ile " + UST_ISLEM_LIMITI + " arasında olmalı: " + limit);
        }
        return limit;
    }

    /** Şimdiye kadar açılan hesap sayısı. Bir nesne gerektirmez: {@code BankaHesabi.acilanHesapSayisi()}. */
    public static int acilanHesapSayisi() {
        return sonNumara;
    }

    public int getNumara() {
        return numara;
    }

    public String getSahip() {
        return sahip;
    }

    public double getBakiye() {
        return bakiye;
    }

    public double getIslemLimiti() {
        return islemLimiti;
    }

    @Override
    public String toString() {
        return "Hesap #" + numara + " (" + sahip + "): " + bakiye + " TL";
    }
}
