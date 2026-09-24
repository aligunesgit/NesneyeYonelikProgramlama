package nyp.m5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - TamZamanliCalisan: kurucu zinciri ve ezilmiş metotlar. */
class TamZamanliCalisanTest {

    @Test
    @DisplayName("super(...) üst sınıfın alanlarını kurar")
    void kurucuZinciriUstAlanlariKurar() {
        TamZamanliCalisan ayse = new TamZamanliCalisan("S-001", "Ayşe", 45000);

        assertEquals("S-001", ayse.getSicilNo());   // Calisan'dan miras
        assertEquals("Ayşe", ayse.getAd());
    }

    @Test
    @DisplayName("Ezilmiş aylikUcret sabit maaşı döndürür")
    void aylikUcretMaastir() {
        Calisan ayse = new TamZamanliCalisan("S-001", "Ayşe", 45000);

        assertEquals(45000.0, ayse.aylikUcret(), 1e-9);
    }

    @Test
    @DisplayName("toString üst sınıfın metnini super.toString() ile genişletir")
    void toStringGenisletir() {
        TamZamanliCalisan ayse = new TamZamanliCalisan("S-001", "Ayşe", 45000);

        assertEquals("S-001 Ayşe (tam zamanlı)", ayse.toString());
    }

    @Test
    @DisplayName("Sıfır ya da negatif maaş kabul edilmez")
    void pozitifOlmayanMaasReddedilir() {
        assertThrows(IllegalArgumentException.class,
                () -> new TamZamanliCalisan("S-001", "Ayşe", 0));
        assertThrows(IllegalArgumentException.class,
                () -> new TamZamanliCalisan("S-001", "Ayşe", -100));
    }

    @Test
    @DisplayName("Üst sınıfın doğrulaması alt sınıf kurucusunda da çalışır")
    void ustDogrulamaAltKurucudaCalisir() {
        IllegalArgumentException hata = assertThrows(IllegalArgumentException.class,
                () -> new TamZamanliCalisan("", "Ayşe", 45000));

        assertTrue(hata.getMessage().contains("Sicil"));
    }
}
