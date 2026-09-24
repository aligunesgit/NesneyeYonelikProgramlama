package nyp.m2;

/**
 * M2 - Sınıf anatomisi: alanlar, aşırı yüklenmiş kurucular, {@code this} ve {@code toString()}.
 *
 * <p>Geçerlilik kontrolleri (ör. GANO 0-4 aralığında mı?) bilerek eklenmedi; M3'te kapsüllemeyle
 * birlikte ekleyeceğiz.
 */
public class Ogrenci {

    private String numara;
    private String ad;
    private int sinif;
    private double gano;       // atanmazsa varsayılan değer: 0.0
    private String danisman;   // atanmazsa varsayılan değer: null

    /** Numarası, adı ve sınıfı verilen öğrenciyi oluşturur. */
    public Ogrenci(String numara, String ad, int sinif) {
        this.numara = numara;
        this.ad = ad;
        this.sinif = sinif;
    }

    /** Yeni kayıt olan öğrenci: sınıfı 1 kabul edilir. Asıl işi üç parametreli kurucu yapar. */
    public Ogrenci(String numara, String ad) {
        this(numara, ad, 1);
    }

    /** Öğrenciyi bir üst sınıfa geçirir; 4. sınıftaysa değişiklik yapmaz ve false döndürür. */
    public boolean sinifAtla() {
        if (sinif >= 4) {
            return false;
        }
        sinif = sinif + 1;
        return true;
    }

    /**
     * Danışmanın adının ilk harfini döndürür. Danışman atanmamışsa (null) bu metot
     * {@link NullPointerException} fırlatır; bu davranış M2'de bilerek gösterilmektedir.
     */
    public char danismanBasHarfi() {
        return danisman.charAt(0);
    }

    /** Danışman atanmışsa true döndürür. NPE'den kaçınmanın en basit yolu: önce kontrol et. */
    public boolean danismaniVarMi() {
        return danisman != null;
    }

    public String getNumara() {
        return numara;
    }

    public String getAd() {
        return ad;
    }

    public int getSinif() {
        return sinif;
    }

    public double getGano() {
        return gano;
    }

    public void setGano(double gano) {
        this.gano = gano;
    }

    public String getDanisman() {
        return danisman;
    }

    public void setDanisman(String danisman) {
        this.danisman = danisman;
    }

    @Override
    public String toString() {
        return numara + " " + ad + " (" + sinif + ". sınıf)";
    }
}
