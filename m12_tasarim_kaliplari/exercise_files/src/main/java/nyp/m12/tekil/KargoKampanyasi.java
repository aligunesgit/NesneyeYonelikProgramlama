package nyp.m12.tekil;

import java.util.Objects;

/**
 * M12 - Singleton'ı gizlemek: kampanya kuralı ayarları kurucudan alır (bağımlılık enjeksiyonu, M11).
 *
 * <p>Üretim kodunda {@code new KargoKampanyasi(Yapilandirma.ORNEK)} yazılır; testte ise
 * {@code new KargoKampanyasi(anahtar -> "300")} yeterlidir.
 */
public class KargoKampanyasi {

    private final AyarOkuyucu ayarlar;

    public KargoKampanyasi(AyarOkuyucu ayarlar) {
        this.ayarlar = Objects.requireNonNull(ayarlar);
    }

    /** Sepet tutarı kampanya eşiğine ulaşmışsa kargo ücretsizdir. */
    public boolean ucretsizKargoMu(double sepetTutari) {
        double esik = Double.parseDouble(ayarlar.oku("kampanya.esik"));
        return sepetTutari >= esik;
    }
}
