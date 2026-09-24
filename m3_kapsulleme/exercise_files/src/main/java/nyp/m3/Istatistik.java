package nyp.m3;

/**
 * M3 - {@code Math} benzeri yardımcı sınıf (utility class): yalnızca static metotlar.
 *
 * <p>Sınıf {@code final} ve kurucusu {@code private}: nesnesi oluşturulamaz, alt sınıfı türetilemez.
 */
public final class Istatistik {

    private Istatistik() {
        // Nesne oluşturulmasın.
    }

    /** Dizinin aritmetik ortalaması. Boş dizide IllegalArgumentException. */
    public static double ortalama(int[] dizi) {
        bosOlmamali(dizi);
        long toplam = 0;
        for (int deger : dizi) {
            toplam = toplam + deger;
        }
        return (double) toplam / dizi.length;
    }

    /** Dizideki en büyük değer. Boş dizide IllegalArgumentException. */
    public static int enBuyuk(int[] dizi) {
        bosOlmamali(dizi);
        int enBuyuk = dizi[0];
        for (int deger : dizi) {
            if (deger > enBuyuk) {
                enBuyuk = deger;
            }
        }
        return enBuyuk;
    }

    /** Dizideki en küçük değer. Boş dizide IllegalArgumentException. */
    public static int enKucuk(int[] dizi) {
        bosOlmamali(dizi);
        int enKucuk = dizi[0];
        for (int deger : dizi) {
            if (deger < enKucuk) {
                enKucuk = deger;
            }
        }
        return enKucuk;
    }

    private static void bosOlmamali(int[] dizi) {
        if (dizi.length == 0) {
            throw new IllegalArgumentException("Dizi boş olamaz");
        }
    }
}
