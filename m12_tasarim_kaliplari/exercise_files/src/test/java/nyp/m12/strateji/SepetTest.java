package nyp.m12.strateji;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SepetTest {

    private Sepet sepet;

    @BeforeEach
    void hazirla() {
        // Toplam: 200 TL, 2.0 kg
        sepet = new Sepet(new SabitUcretliKargo(30));
        sepet.urunEkle("Kitap", 120, 0.5);
        sepet.urunEkle("Kupa", 80, 1.5);
    }

    @Test
    @DisplayName("Sabit ücretli stratejide kargo ağırlıktan bağımsızdır")
    void sabitUcretliKargo() {
        assertEquals(30.0, sepet.kargoUcreti(), 1e-9);
        assertEquals(230.0, sepet.odenecekTutar(), 1e-9);
    }

    @Test
    @DisplayName("Strateji değişince kargo ücreti de değişir")
    void stratejiDegisinceUcretDegisir() {
        // Çalıştır: 20 + 10 * 2.0 = 40
        sepet.setKargoStratejisi(new AgirligaGoreKargo(20, 10));
        // Doğrula
        assertEquals(40.0, sepet.kargoUcreti(), 1e-9);
        assertEquals(240.0, sepet.odenecekTutar(), 1e-9);
    }

    @Test
    @DisplayName("Strateji lambda ile de verilebilir")
    void lambdaIleStrateji() {
        sepet.setKargoStratejisi((kg, tutar) -> tutar >= 150 ? 0.0 : 40.0);

        assertEquals(0.0, sepet.kargoUcreti(), 1e-9);
    }

    @Test
    @DisplayName("Boş sepette ağırlığa göre kargo yalnızca taban ücrettir")
    void bosSepetTabanUcret() {
        Sepet bos = new Sepet(new AgirligaGoreKargo(20, 10));

        assertEquals(0.0, bos.toplamTutar(), 1e-9);
        assertEquals(20.0, bos.kargoUcreti(), 1e-9);
    }

    @Test
    @DisplayName("Null strateji ve negatif ücret reddedilir")
    void gecersizStratejiReddedilir() {
        assertThrows(NullPointerException.class, () -> sepet.setKargoStratejisi(null));
        assertThrows(IllegalArgumentException.class, () -> new SabitUcretliKargo(-1));
        assertThrows(IllegalArgumentException.class, () -> new AgirligaGoreKargo(10, -2));
    }
}
