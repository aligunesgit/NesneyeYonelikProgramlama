package nyp.m11.siparis;

import java.util.List;

/**
 * M11 - Tek sorumluluk (SRP): yalnızca indirim hesaplar.
 *
 * <p>Hangi kuralların geçerli olduğunu bilmez; kurallar dışarıdan verilir. Toplam indirim sipariş
 * tutarını aşamaz (net tutar hiçbir zaman negatif olmaz).
 */
public class IndirimHesaplayici {

    private final List<IndirimKurali> kurallar;

    public IndirimHesaplayici(List<IndirimKurali> kurallar) {
        this.kurallar = List.copyOf(kurallar);
    }

    /** Tüm kuralların indirimlerinin toplamı; sipariş tutarıyla sınırlıdır. */
    public double toplamIndirim(Siparis siparis) {
        double toplam = kurallar.stream()
                .mapToDouble(kural -> kural.indirim(siparis))
                .sum();
        return Math.min(toplam, siparis.tutar());
    }

    /** İndirimler düşüldükten sonra ödenecek tutar. */
    public double netTutar(Siparis siparis) {
        return siparis.tutar() - toplamIndirim(siparis);
    }
}
