package nyp.m5.zincir;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - Kurucu çağrı sırası: üstten alta. */
class KurucuZinciriTest {

    @Test
    @DisplayName("new Kedi(): önce Canli, sonra Hayvan, en son Kedi kurucusu çalışır")
    void kurucularUsttenAltaCalisir() {
        // Hazırla
        StringBuilder gunluk = new StringBuilder();
        // Çalıştır
        new KurucuZinciri.Kedi(gunluk);
        // Doğrula
        assertEquals("Canli kurucusu\nHayvan kurucusu\nKedi kurucusu\n", gunluk.toString());
    }

    @Test
    @DisplayName("Ortadaki sınıftan nesne üretmek zinciri orada bitirir")
    void ortadanBaslayanZincir() {
        StringBuilder gunluk = new StringBuilder();

        new KurucuZinciri.Hayvan(gunluk);

        assertEquals("Canli kurucusu\nHayvan kurucusu\n", gunluk.toString());
    }
}
