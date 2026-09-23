package nyp.m1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HesapTest {

    @Test
    @DisplayName("Yeni hesabın bakiyesi 0'dır")
    void yeniHesapBakiyesiSifir() {
        Hesap hesap = new Hesap("Ayşe");

        assertEquals(0.0, hesap.getBakiye());
        assertEquals("Ayşe", hesap.getSahip());
    }

    @Test
    @DisplayName("Para yatırınca bakiye artar")
    void paraYatirBakiyeyiArtirir() {
        // Hazırla
        Hesap hesap = new Hesap("Ayşe");
        // Çalıştır
        boolean sonuc = hesap.paraYatir(500);
        // Doğrula
        assertTrue(sonuc);
        assertEquals(500.0, hesap.getBakiye());
    }

    @Test
    @DisplayName("Sıfır veya negatif tutar yatırılamaz")
    void pozitifOlmayanTutarYatirilamaz() {
        Hesap hesap = new Hesap("Ayşe");

        assertFalse(hesap.paraYatir(0));
        assertFalse(hesap.paraYatir(-50));
        assertEquals(0.0, hesap.getBakiye());
    }

    @Test
    @DisplayName("Bakiye yeterliyse para çekilir")
    void yeterliBakiyeIleParaCekilir() {
        Hesap hesap = new Hesap("Ayşe");
        hesap.paraYatir(500);

        assertTrue(hesap.paraCek(120));
        assertEquals(380.0, hesap.getBakiye());
    }

    @Test
    @DisplayName("Bakiyeden fazla para çekilemez, bakiye değişmez")
    void bakiyedenFazlaCekilemez() {
        Hesap hesap = new Hesap("Ayşe");
        hesap.paraYatir(100);

        assertFalse(hesap.paraCek(150));
        assertEquals(100.0, hesap.getBakiye());
    }

    @Test
    @DisplayName("Tüm bakiye çekilebilir (sınır durumu)")
    void tumBakiyeCekilebilir() {
        Hesap hesap = new Hesap("Ayşe");
        hesap.paraYatir(100);

        assertTrue(hesap.paraCek(100));
        assertEquals(0.0, hesap.getBakiye());
    }

    @Test
    @DisplayName("İki hesap birbirinden bağımsızdır")
    void hesaplarBagimsiz() {
        Hesap ayse = new Hesap("Ayşe");
        Hesap mehmet = new Hesap("Mehmet");

        ayse.paraYatir(500);

        assertEquals(500.0, ayse.getBakiye());
        assertEquals(0.0, mehmet.getBakiye());
    }
}
