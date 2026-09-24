package nyp.m7.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SekilIslemleriTest {

    @Test
    @DisplayName("Eksiksiz switch: üç türün alanı")
    void alan() {
        assertEquals(Math.PI * 4, SekilIslemleri.alan(new Daire(2)), 1e-9);
        assertEquals(6.0, SekilIslemleri.alan(new Dikdortgen(2, 3)), 1e-9);
        assertEquals(6.0, SekilIslemleri.alan(new Ucgen(4, 3)), 1e-9);
    }

    @Test
    @DisplayName("Kayıt desenleri ve koruma koşulları")
    void tarif() {
        assertEquals("nokta", SekilIslemleri.tarif(new Daire(0)));
        assertEquals("daire, r = 1.5", SekilIslemleri.tarif(new Daire(1.5)));
        assertEquals("kare, kenar = 2.0", SekilIslemleri.tarif(new Dikdortgen(2, 2)));
        assertEquals("dikdörtgen, 2.0 x 3.0", SekilIslemleri.tarif(new Dikdortgen(2, 3)));
        assertEquals("üçgen", SekilIslemleri.tarif(new Ucgen(1, 1)));
    }

    @Test
    @DisplayName("Sealed arayüz yalnızca izin verilen alt türleri tanır")
    void izinliAltTurler() {
        Class<?>[] izinliler = Sekil.class.getPermittedSubclasses();

        assertTrue(Sekil.class.isSealed());
        assertEquals(3, izinliler.length);
    }

    @Test
    @DisplayName("record: equals ve toString kendiliğinden gelir")
    void recordEsitlik() {
        assertEquals(new Daire(1), new Daire(1));
        assertEquals("Dikdortgen[en=2.0, boy=3.0]", new Dikdortgen(2, 3).toString());
    }
}
