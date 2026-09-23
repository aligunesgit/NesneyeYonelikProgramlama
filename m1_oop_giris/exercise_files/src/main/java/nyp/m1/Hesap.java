package nyp.m1;

/**
 * M1 - Nesne yönelimli yaklaşım: bir banka hesabı.
 *
 * <p>Hesabın verisi (durum: sahip, bakiye) ve bu veri üzerindeki işlemler (davranış: para yatırma,
 * çekme) aynı sınıfta durur. Bakiye {@code private} olduğu için yalnızca bu sınıfın metotları
 * değiştirebilir (ayrıntısı M3'te).
 */
public class Hesap {

    private final String sahip;
    private double bakiye;

    /** Verilen sahip adına bakiyesi 0 olan yeni bir hesap oluşturur. */
    public Hesap(String sahip) {
        this.sahip = sahip;
        this.bakiye = 0;
    }

    /** Tutar pozitifse bakiyeye ekler ve true döndürür; aksi hâlde false döndürür. */
    public boolean paraYatir(double tutar) {
        if (tutar <= 0) {
            return false;
        }
        bakiye = bakiye + tutar;
        return true;
    }

    /** Tutar pozitif ve bakiye yeterliyse çeker ve true döndürür; aksi hâlde bakiye değişmez. */
    public boolean paraCek(double tutar) {
        if (tutar <= 0 || tutar > bakiye) {
            return false;
        }
        bakiye = bakiye - tutar;
        return true;
    }

    public double getBakiye() {
        return bakiye;
    }

    public String getSahip() {
        return sahip;
    }
}
