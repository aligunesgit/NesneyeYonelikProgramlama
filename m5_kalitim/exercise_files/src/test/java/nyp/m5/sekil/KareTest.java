package nyp.m5.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - Kare: "Kare bir dikdörtgendir" sezgisinin bozduğu varsayım. */
class KareTest {

    /** Dikdortgen bekleyen, "eni değiştirmek boyu değiştirmez" varsayan bir istemci metot. */
    private static double eniBesYapVeAlanHesapla(Dikdortgen d) {
        d.setEn(5);
        return d.alan();   // istemcinin beklentisi: 5 x eski boy
    }

    @Test
    @DisplayName("Karede setEn boyu da değiştirir: kare kalır")
    void kareKenarlariEsitKalir() {
        Kare k = new Kare(2);

        k.setBoy(7);

        assertEquals(7.0, k.getEn(), 1e-9);
        assertEquals(49.0, k.alan(), 1e-9);
    }

    @Test
    @DisplayName("Dikdörtgen bekleyen kod kareyle beklenmedik sonuç alır")
    void kareDikdortgenVarsayiminiBozar() {
        double dikdortgenIle = eniBesYapVeAlanHesapla(new Dikdortgen(2, 2));
        double kareIle = eniBesYapVeAlanHesapla(new Kare(2));

        assertEquals(10.0, dikdortgenIle, 1e-9);   // 5 x 2: beklenen
        assertEquals(25.0, kareIle, 1e-9);         // 5 x 5: istemci şaşırır
    }

    @Test
    @DisplayName("Sıfır ya da negatif kenar kabul edilmez")
    void pozitifOlmayanKenarReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new Kare(0));
        assertThrows(IllegalArgumentException.class, () -> new Dikdortgen(2, -1));
        Kare k = new Kare(3);
        assertThrows(IllegalArgumentException.class, () -> k.setEn(-2));
        assertEquals(3.0, k.getBoy(), 1e-9);
    }
}
