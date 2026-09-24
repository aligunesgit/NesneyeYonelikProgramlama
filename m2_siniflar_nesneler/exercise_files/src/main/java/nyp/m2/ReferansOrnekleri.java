package nyp.m2;

/**
 * M2 - Java'da parametreler her zaman değerle geçirilir (pass by value).
 *
 * <p>İlkel tipte değerin kopyası, nesnede ise referansın kopyası metoda gider. Referansın kopyası
 * aynı nesneyi gösterdiği için nesne değiştirilebilir; ama parametreye yeni bir nesne atamak
 * çağıranın değişkenini etkilemez.
 */
public final class ReferansOrnekleri {

    private ReferansOrnekleri() {
        // Yalnızca static gösterim metotları içerir; nesnesi oluşturulmaz.
    }

    /** Parametrenin kopyasını artırır; çağıranın değişkeni değişmez. */
    public static void artir(int sayi) {
        sayi = sayi + 1;
    }

    /** Referansın kopyası aynı nesneyi gösterir; nesne değişir, çağıran bunu görür. */
    public static void artir(Sayac sayac) {
        sayac.artir();
    }

    /** Parametreye yeni nesne atanır; çağıranın referansı hâlâ eski nesneyi gösterir. */
    public static void yeniSayacAta(Sayac sayac) {
        sayac = new Sayac();
        sayac.artir();
    }

    /** İki referansı takas etmeye çalışır; yalnızca kopyalar takas edilir, çağıran etkilenmez. */
    public static void takasEt(Nokta a, Nokta b) {
        Nokta gecici = a;
        a = b;
        b = gecici;
    }
}
