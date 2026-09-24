package nyp.m11.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SekilTest {

    @Test
    @DisplayName("Dikdörtgenin genişliği değişince alan beklendiği gibi değişir")
    void dikdortgenGenislikDegisimi() {
        // Hazırla
        Dikdortgen d = new Dikdortgen(2, 4);
        // Çalıştır
        Dikdortgen genis = d.genislikIle(5);
        // Doğrula: LSP ihlalindeki beklenti (5 x 4 = 20) burada her zaman tutar
        assertEquals(20.0, genis.alan(), 1e-9);
        assertEquals(8.0, d.alan(), 1e-9); // eski nesne değişmedi
    }

    @Test
    @DisplayName("Kare Dikdortgen'in alt türü değildir, ikisi de Sekil'dir")
    void kareDikdortgenDegil() {
        Sekil kare = new Kare(3);

        assertFalse(kare instanceof Dikdortgen);
        assertEquals(9.0, kare.alan(), 1e-9);
    }

    @Test
    @DisplayName("Sekil listesinde her şekil kendi alanını hesaplar")
    void sekilListesiToplamAlan() {
        List<Sekil> sekiller = List.of(new Dikdortgen(2, 3), new Kare(4));

        double toplam = sekiller.stream().mapToDouble(Sekil::alan).sum();

        assertEquals(22.0, toplam, 1e-9);
    }

    @Test
    @DisplayName("Kare açıkça dikdörtgene dönüştürülebilir")
    void kareyiDonustur() {
        assertEquals(new Dikdortgen(3, 3), new Kare(3).dikdortgeneDonustur());
    }

    @Test
    @DisplayName("Sıfır ya da negatif kenar reddedilir")
    void gecersizKenar() {
        assertThrows(IllegalArgumentException.class, () -> new Kare(0));
        assertThrows(IllegalArgumentException.class, () -> new Dikdortgen(2, -1));
    }
}
