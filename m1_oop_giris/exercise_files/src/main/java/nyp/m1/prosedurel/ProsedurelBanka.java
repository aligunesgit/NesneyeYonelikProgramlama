package nyp.m1.prosedurel;

/**
 * M1 - Prosedürel yaklaşım.
 *
 * <p>Hesap bilgileri paralel dizilerde (sahipler[i], bakiyeler[i]) durur; bu veriyi işleyen
 * fonksiyonlar ise ayrı bir yerdedir. Diziye erişebilen her kod, bakiyeyi doğrudan değiştirebilir.
 */
public final class ProsedurelBanka {

    private ProsedurelBanka() {
        // Yalnızca static metotlar içerir; nesnesi oluşturulmaz.
    }

    /** Tutar pozitifse {@code hesapNo} numaralı hesaba yatırır ve true döndürür. */
    public static boolean paraYatir(double[] bakiyeler, int hesapNo, double tutar) {
        if (tutar <= 0) {
            return false;
        }
        bakiyeler[hesapNo] = bakiyeler[hesapNo] + tutar;
        return true;
    }

    /** Tutar pozitif ve bakiye yeterliyse çeker ve true döndürür; aksi hâlde hiçbir şeyi değiştirmez. */
    public static boolean paraCek(double[] bakiyeler, int hesapNo, double tutar) {
        if (tutar <= 0 || tutar > bakiyeler[hesapNo]) {
            return false;
        }
        bakiyeler[hesapNo] = bakiyeler[hesapNo] - tutar;
        return true;
    }
}
