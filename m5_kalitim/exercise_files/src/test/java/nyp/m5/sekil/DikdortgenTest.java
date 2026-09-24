package nyp.m5.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - Dikdortgen: kenarlar bağımsız değişir. */
class DikdortgenTest {

    @Test
    @DisplayName("Dikdörtgende eni değiştirmek boyu değiştirmez")
    void dikdortgenKenarlariBagimsiz() {
        Dikdortgen d = new Dikdortgen(2, 3);

        d.setEn(5);

        assertEquals(5.0, d.getEn(), 1e-9);
        assertEquals(3.0, d.getBoy(), 1e-9);
        assertEquals(15.0, d.alan(), 1e-9);
    }

    @Test
    @DisplayName("Geçersiz setBoy çağrısı nesneyi değiştirmez")
    void gecersizSetBoyDegistirmez() {
        Dikdortgen d = new Dikdortgen(2, 3);

        assertThrows(IllegalArgumentException.class, () -> d.setBoy(0));
        assertEquals(6.0, d.alan(), 1e-9);
    }
}
