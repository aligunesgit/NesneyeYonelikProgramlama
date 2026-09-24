package nyp.m8;

/**
 * M8 - İstisnalarla kurallarını koruyan banka hesabı.
 *
 * <p>Geçersiz argüman (sıfır/negatif tutar, boş hesap numarası) bir programlama hatasıdır ve
 * denetlenmeyen {@link IllegalArgumentException} ile bildirilir. Yetersiz bakiye ise normal işleyişte
 * beklenen, kurtarılabilir bir durumdur ve kontrollü {@link YetersizBakiyeException} ile bildirilir.
 */
public class Hesap {

    private final String hesapNo;
    private final String sahip;
    private double bakiye;

    public Hesap(String hesapNo, String sahip) {
        if (hesapNo == null || hesapNo.isBlank()) {
            throw new IllegalArgumentException("Hesap numarası boş olamaz");
        }
        this.hesapNo = hesapNo;
        this.sahip = sahip;
        this.bakiye = 0;
    }

    /** Tutarı bakiyeye ekler. Tutar pozitif değilse {@link IllegalArgumentException} fırlatır. */
    public void paraYatir(double tutar) {
        tutariDenetle(tutar);
        bakiye = bakiye + tutar;
    }

    /**
     * Tutarı bakiyeden düşer.
     *
     * @throws IllegalArgumentException tutar pozitif değilse
     * @throws YetersizBakiyeException tutar bakiyeden büyükse (bakiye değişmez)
     */
    public void paraCek(double tutar) throws YetersizBakiyeException {
        tutariDenetle(tutar);
        if (tutar > bakiye) {
            throw new YetersizBakiyeException(hesapNo, bakiye, tutar);
        }
        bakiye = bakiye - tutar;
    }

    private static void tutariDenetle(double tutar) {
        if (tutar <= 0) {
            throw new IllegalArgumentException("Tutar pozitif olmalı: " + tutar);
        }
    }

    public String getHesapNo() {
        return hesapNo;
    }

    public String getSahip() {
        return sahip;
    }

    public double getBakiye() {
        return bakiye;
    }
}
