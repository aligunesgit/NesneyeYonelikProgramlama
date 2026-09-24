package nyp.m3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SicaklikTest {

    @Test
    @DisplayName("Celsius - Fahrenheit dönüşümü iki yönde de doğrudur")
    void donusumler() {
        assertEquals(212.0, new Sicaklik(100).fahrenheit(), 1e-9);
        assertEquals(0.0, Sicaklik.fahrenheittenOlustur(32).getCelsius(), 1e-9);
        assertEquals(37.0, Sicaklik.fahrenheittenOlustur(98.6).getCelsius(), 1e-9);
    }

    @Test
    @DisplayName("artir yeni nesne döndürür, asıl nesne değişmez")
    void artirYeniNesneDondurur() {
        // Hazırla
        Sicaklik sabah = new Sicaklik(12.5);
        // Çalıştır
        Sicaklik ogle = sabah.artir(8);
        // Doğrula
        assertNotSame(sabah, ogle);
        assertEquals(12.5, sabah.getCelsius(), 1e-9);
        assertEquals(20.5, ogle.getCelsius(), 1e-9);
    }

    @Test
    @DisplayName("Mutlak sıfır geçerlidir, altı geçersizdir (sınır durumu)")
    void mutlakSifirSiniri() {
        assertEquals(Sicaklik.MUTLAK_SIFIR, new Sicaklik(-273.15).getCelsius(), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> new Sicaklik(-273.16));
    }

    @Test
    @DisplayName("artir sonucu mutlak sıfırın altına düşerse reddedilir")
    void artirDogrulamayiAtlayamaz() {
        Sicaklik soguk = new Sicaklik(-270);

        assertThrows(IllegalArgumentException.class, () -> soguk.artir(-10));
        assertEquals(-270.0, soguk.getCelsius(), 1e-9);
    }

    @Test
    @DisplayName("toString birimi gösterir")
    void toStringBirimli() {
        assertEquals("21.5 °C", new Sicaklik(21.5).toString());
    }
}
