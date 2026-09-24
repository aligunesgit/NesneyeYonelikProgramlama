package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HesapTest {

    @Test
    @DisplayName("Yeterli bakiyeyle para çekmek istisna fırlatmaz")
    void yeterliBakiyeIstisnaFirlatmaz() {
        Hesap hesap = new Hesap("TR01", "Ayşe");
        hesap.paraYatir(500);

        assertDoesNotThrow(() -> hesap.paraCek(120));
        assertEquals(380.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Yetersiz bakiye: istisna alan verisini taşır, bakiye değişmez")
    void yetersizBakiyeIstisnasi() {
        // Hazırla
        Hesap hesap = new Hesap("TR01", "Ayşe");
        hesap.paraYatir(100);
        // Çalıştır
        YetersizBakiyeException hata =
                assertThrows(YetersizBakiyeException.class, () -> hesap.paraCek(150));
        // Doğrula
        assertEquals("Yetersiz bakiye: hesap TR01, bakiye 100.0, istenen 150.0", hata.getMessage());
        assertEquals("TR01", hata.getHesapNo());
        assertEquals(50.0, hata.getEksik(), 1e-9);
        assertEquals(100.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Tüm bakiye çekilebilir (sınır durumu)")
    void tumBakiyeCekilebilir() {
        Hesap hesap = new Hesap("TR01", "Ayşe");
        hesap.paraYatir(100);

        assertDoesNotThrow(() -> hesap.paraCek(100));
        assertEquals(0.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Sıfır ya da negatif tutar IllegalArgumentException fırlatır")
    void gecersizTutar() {
        Hesap hesap = new Hesap("TR01", "Ayşe");

        IllegalArgumentException hata =
                assertThrows(IllegalArgumentException.class, () -> hesap.paraYatir(0));
        assertEquals("Tutar pozitif olmalı: 0.0", hata.getMessage());
        assertThrows(IllegalArgumentException.class, () -> hesap.paraCek(-5));
        assertEquals(0.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Boş hesap numarasıyla hesap oluşturulamaz")
    void bosHesapNumarasi() {
        assertThrows(IllegalArgumentException.class, () -> new Hesap(" ", "Ayşe"));
        assertThrows(IllegalArgumentException.class, () -> new Hesap(null, "Ayşe"));
    }
}
