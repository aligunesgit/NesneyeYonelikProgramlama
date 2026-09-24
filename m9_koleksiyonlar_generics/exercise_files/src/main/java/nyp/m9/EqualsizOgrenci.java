package nyp.m9;

/**
 * M9 - Bilerek hatalı örnek: {@code equals} ve {@code hashCode} ezilmemiş öğrenci.
 *
 * <p>{@link Object}'ten gelen sürümler nesne kimliğine (identity) bakar. Bu yüzden aynı numaralı iki
 * nesne bir {@code HashSet}'te iki ayrı eleman sayılır. Karşılaştırma için {@link Ogrenci}'ye bakın.
 */
public class EqualsizOgrenci {

    private final String numara;
    private final String ad;

    public EqualsizOgrenci(String numara, String ad) {
        this.numara = numara;
        this.ad = ad;
    }

    public String getNumara() {
        return numara;
    }

    public String getAd() {
        return ad;
    }
}
