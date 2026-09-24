package nyp.m9;

/**
 * M9 - İki farklı türden değeri bir arada tutan generic kayıt (record, M3).
 *
 * @param <A> ilk değerin türü
 * @param <B> ikinci değerin türü
 */
public record Cift<A, B>(A ilk, B ikinci) {

    /** Değerlerin yeri değişmiş yeni bir çift döndürür: {@code Cift<B, A>}. */
    public Cift<B, A> yerDegistir() {
        return new Cift<>(ikinci, ilk);
    }
}
