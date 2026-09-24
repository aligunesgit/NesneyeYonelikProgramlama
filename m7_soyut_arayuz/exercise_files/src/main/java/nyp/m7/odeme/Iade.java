package nyp.m7.odeme;

/** M7 - İade desteği sunan ödeme yöntemleri için ikinci bir sözleşme. */
public interface Iade {

    /** Ödenen toplam tutardan müşteriye geri verilecek miktar. */
    double iadeTutari(double odenenToplam);
}
