package nyp.m7.siparis;

/** M7 - Kullanıcıya gösterilecek bir açıklaması olan türler. */
public interface Aciklanabilir {

    String aciklama();

    /** Açıklamayı köşeli parantez içinde döndürür. */
    default String etiket() {
        return "[" + aciklama() + "]";
    }
}
