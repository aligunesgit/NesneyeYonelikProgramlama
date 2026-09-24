package nyp.m12.tekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YapilandirmaTest {

    @AfterEach
    void temizle() {
        // Tekil küresel durum taşır: bir testin değişikliği diğerine sızmasın.
        Yapilandirma.ORNEK.sifirla();
    }

    @Test
    @DisplayName("Her erişim aynı örneği verir")
    void ayniOrnek() {
        Yapilandirma a = Yapilandirma.ORNEK;
        Yapilandirma b = Yapilandirma.valueOf("ORNEK");

        assertSame(a, b);
        assertEquals(1, Yapilandirma.values().length);
    }

    @Test
    @DisplayName("Varsayılan ayarlar okunur, tanımsız anahtar istisna fırlatır")
    void varsayilanAyarlar() {
        assertEquals("TRY", Yapilandirma.ORNEK.oku("para.birimi"));
        assertThrows(IllegalArgumentException.class, () -> Yapilandirma.ORNEK.oku("yok.boyle.ayar"));
    }

    @Test
    @DisplayName("Bir yerde yapılan değişiklik her yerden görünür (küresel durum)")
    void kureselDurum() {
        AyarOkuyucu baskaBirSinifinGordugu = Yapilandirma.ORNEK;

        Yapilandirma.ORNEK.ayarla("kampanya.esik", "750");

        assertEquals("750", baskaBirSinifinGordugu.oku("kampanya.esik"));
    }
}
