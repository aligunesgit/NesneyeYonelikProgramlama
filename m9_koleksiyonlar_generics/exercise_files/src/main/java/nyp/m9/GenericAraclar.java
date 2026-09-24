package nyp.m9;

import java.util.List;
import java.util.NoSuchElementException;

/** M9 - Generic metot örnekleri. */
public final class GenericAraclar {

    private GenericAraclar() {
    }

    /**
     * Listedeki en büyük elemanı doğal sıralamaya göre bulur. {@code T}, kendi türüyle
     * karşılaştırılabilir (Comparable) olmak zorundadır: sınırlı tür parametresi (bounded type).
     *
     * @throws NoSuchElementException liste boşsa
     */
    public static <T extends Comparable<T>> T enBuyuk(List<T> liste) {
        if (liste.isEmpty()) {
            throw new NoSuchElementException("Boş listenin en büyük elemanı yok");
        }
        T enBuyuk = liste.get(0);
        for (T eleman : liste) {
            if (eleman.compareTo(enBuyuk) > 0) {
                enBuyuk = eleman;
            }
        }
        return enBuyuk;
    }

    /**
     * Listenin ilk ve son elemanını bir çift olarak döndürür.
     *
     * @throws NoSuchElementException liste boşsa
     */
    public static <T> Cift<T, T> ilkVeSon(List<T> liste) {
        if (liste.isEmpty()) {
            throw new NoSuchElementException("Boş listenin ilk ve son elemanı yok");
        }
        return new Cift<>(liste.get(0), liste.get(liste.size() - 1));
    }

    /** Her tür sayı listesinin toplamı: {@code List<Integer>}, {@code List<Double>}... */
    public static double toplam(List<? extends Number> sayilar) {
        double toplam = 0;
        for (Number sayi : sayilar) {
            toplam += sayi.doubleValue();
        }
        return toplam;
    }
}
