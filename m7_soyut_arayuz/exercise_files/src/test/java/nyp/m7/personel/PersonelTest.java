package nyp.m7.personel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonelTest {

    @Test
    @DisplayName("Tam zamanlı: net = brüt - %15")
    void tamZamanliNet() {
        Personel p = new TamZamanliPersonel("Ayşe", 40_000);

        assertEquals(40_000.0, p.brutMaas(), 1e-9);
        assertEquals(34_000.0, p.netMaas(), 1e-9);
    }

    @Test
    @DisplayName("Saatlik: 160 saati aşan saatler 1,5 kat ödenir")
    void saatlikFazlaMesai() {
        Personel p = new SaatlikPersonel("Mehmet", 100, 170);

        assertEquals(160 * 100 + 10 * 150.0, p.brutMaas(), 1e-9);
        assertEquals(17_500 * 0.85, p.netMaas(), 1e-9);
    }

    @Test
    @DisplayName("Saatlik: brüt 10.000 TL altındaysa kesinti yok (kanca metot)")
    void saatlikKesintiYok() {
        Personel p = new SaatlikPersonel("Zeynep", 50, 100);

        assertEquals(5_000.0, p.netMaas(), 1e-9);
    }

    @Test
    @DisplayName("Tam 10.000 TL brütte kesinti uygulanır (sınır)")
    void saatlikSinir() {
        Personel p = new SaatlikPersonel("Can", 62.5, 160);

        assertEquals(8_500.0, p.netMaas(), 1e-9);
    }

    @Test
    @DisplayName("Soyut tür üzerinden toplam bordro")
    void toplamBordro() {
        List<Personel> personeller = List.of(
                new TamZamanliPersonel("Ayşe", 40_000), new SaatlikPersonel("Zeynep", 50, 100));

        double toplam = 0;
        for (Personel p : personeller) {
            toplam += p.netMaas();
        }

        assertEquals(39_000.0, toplam, 1e-9);
    }
}
