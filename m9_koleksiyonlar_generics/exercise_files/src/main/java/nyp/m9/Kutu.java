package nyp.m9;

/**
 * M9 - Tek bir nesne tutan generic kutu.
 *
 * <p>{@code T} tür parametresidir (type parameter): {@code Kutu<String>} yalnızca String,
 * {@code Kutu<Ogrenci>} yalnızca Ogrenci tutar. Derleyici bunu denetler, çıkarırken cast gerekmez.
 *
 * @param <T> kutudaki nesnenin türü
 */
public class Kutu<T> {

    private T icerik;

    /** Boş bir kutu oluşturur. */
    public Kutu() {
    }

    public Kutu(T icerik) {
        this.icerik = icerik;
    }

    public void koy(T yeni) {
        this.icerik = yeni;
    }

    /**
     * Kutudaki nesneyi döndürür.
     *
     * @throws IllegalStateException kutu boşsa
     */
    public T al() {
        if (icerik == null) {
            throw new IllegalStateException("Kutu boş");
        }
        return icerik;
    }

    public boolean bosMu() {
        return icerik == null;
    }
}
