package nyp.m7.odeme;

import java.util.List;

/**
 * M7 - Bir ödeme yönteminin sözleşmesi (contract).
 *
 * <p>Arayüzü uygulayan her sınıf {@link #ad()} ve {@link #komisyon(double)} metotlarını yazmak
 * zorundadır. {@link #toplamTutar(double)} bir {@code default} metottur: tüm uygulayıcılara hazır
 * gelir, istenirse ezilebilir. {@link #enUygun(List, double)} ise arayüze ait bir {@code static}
 * yardımcıdır.
 */
public interface Odeme {

    /** Ödeme yönteminin görünen adı. */
    String ad();

    /** Verilen tutar için alınacak komisyon (TL). */
    double komisyon(double tutar);

    /** Müşterinin ödeyeceği toplam: tutar + komisyon. */
    default double toplamTutar(double tutar) {
        if (tutar <= 0) {
            throw new IllegalArgumentException("Tutar pozitif olmalı: " + tutar);
        }
        return tutar + komisyon(tutar);
    }

    /** Verilen tutar için komisyonu en düşük yöntemi döndürür; liste boşsa null. */
    static Odeme enUygun(List<Odeme> secenekler, double tutar) {
        Odeme enIyi = null;
        for (Odeme o : secenekler) {
            if (enIyi == null || o.komisyon(tutar) < enIyi.komisyon(tutar)) {
                enIyi = o;
            }
        }
        return enIyi;
    }
}
