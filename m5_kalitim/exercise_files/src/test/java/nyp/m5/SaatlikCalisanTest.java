package nyp.m5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - SaatlikCalisan: fazla mesai sınırı ve toString. */
class SaatlikCalisanTest {

    @Test
    @DisplayName("Hiç saat girilmemişse ücret 0'dır")
    void saatYoksaUcretSifir() {
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);

        assertEquals(0.0, mehmet.aylikUcret(), 1e-9);
    }

    @Test
    @DisplayName("Tam 160 saat: fazla mesai yok (sınır durumu)")
    void tamSinirdaFazlaMesaiYok() {
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);
        mehmet.saatEkle(160);

        assertEquals(32000.0, mehmet.aylikUcret(), 1e-9);   // 160 x 200
    }

    @Test
    @DisplayName("160 saatin üstü 1,5 katından ödenir")
    void fazlaMesaiBucukKat() {
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);
        mehmet.saatEkle(100);
        mehmet.saatEkle(70);                                 // toplam 170

        // 160 x 200 + 10 x 200 x 1,5 = 32000 + 3000
        assertEquals(35000.0, mehmet.aylikUcret(), 1e-9);
        assertEquals(170.0, mehmet.getCalisilanSaat(), 1e-9);
    }

    @Test
    @DisplayName("Sıfır ya da negatif saat eklenemez, toplam değişmez")
    void pozitifOlmayanSaatEklenemez() {
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);
        mehmet.saatEkle(10);

        assertThrows(IllegalArgumentException.class, () -> mehmet.saatEkle(0));
        assertThrows(IllegalArgumentException.class, () -> mehmet.saatEkle(-5));
        assertEquals(10.0, mehmet.getCalisilanSaat(), 1e-9);
    }

    @Test
    @DisplayName("toString saat bilgisini ekler")
    void toStringSaatiGosterir() {
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);
        mehmet.saatEkle(170);

        assertEquals("S-002 Mehmet (saatlik, 170.0 saat)", mehmet.toString());
    }
}
