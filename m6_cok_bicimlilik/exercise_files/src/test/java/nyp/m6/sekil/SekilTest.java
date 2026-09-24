package nyp.m6.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SekilTest {

    @Test
    @DisplayName("Dairenin alanı ve çevresi")
    void daireAlanVeCevre() {
        Sekil s = new Daire(2);

        assertEquals(4 * Math.PI, s.alan(), 1e-9);
        assertEquals(4 * Math.PI, s.cevre(), 1e-9);
    }

    @Test
    @DisplayName("Dikdörtgenin alanı ve çevresi")
    void dikdortgenAlanVeCevre() {
        Sekil s = new Dikdortgen(2, 3);

        assertEquals(6.0, s.alan(), 1e-9);
        assertEquals(10.0, s.cevre(), 1e-9);
    }

    @Test
    @DisplayName("3-4-5 üçgeninin alanı 6'dır (Heron)")
    void ucgenHeron() {
        Sekil s = new Ucgen(3, 4, 5);

        assertEquals(6.0, s.alan(), 1e-9);
        assertEquals(12.0, s.cevre(), 1e-9);
    }

    @Test
    @DisplayName("Sıfır yarıçaplı dairenin alanı 0'dır")
    void sifirYaricap() {
        assertEquals(0.0, new Daire(0).alan(), 1e-9);
    }

    @Test
    @DisplayName("Geçersiz ölçüler reddedilir")
    void gecersizOlculer() {
        assertThrows(IllegalArgumentException.class, () -> new Daire(-1));
        assertThrows(IllegalArgumentException.class, () -> new Dikdortgen(-2, 3));
        assertThrows(IllegalArgumentException.class, () -> new Ucgen(1, 2, 3));
    }

    @Test
    @DisplayName("Dinamik bağlama: ad() nesnenin gerçek türünden gelir")
    void dinamikBaglama() {
        Sekil s = new Daire(1);
        assertEquals("Daire", s.ad());

        s = new Ucgen(3, 4, 5);
        assertEquals("Üçgen", s.ad());
    }

    @Test
    @DisplayName("toString içindeki alan() çağrısı da çok biçimlidir")
    void toStringCokBicimli() {
        Sekil s = new Dikdortgen(2, 3);

        assertEquals("Dikdörtgen (alan = 6.0)", s.toString());
    }

    @Test
    @DisplayName("Yanlış aşağı dönüşüm ClassCastException fırlatır")
    void yanlisAsagiDonusum() {
        Sekil s = new Daire(1);

        assertThrows(ClassCastException.class, () -> {
            Dikdortgen r = (Dikdortgen) s;
            r.kareMi();
        });
    }
}
