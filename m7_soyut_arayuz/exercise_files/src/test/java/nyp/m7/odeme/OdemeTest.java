package nyp.m7.odeme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OdemeTest {

    @Test
    @DisplayName("Kredi kartı tek çekim: %2,5 komisyon")
    void krediKartiTekCekim() {
        Odeme kart = new KrediKarti(1);

        assertEquals(25.0, kart.komisyon(1000), 1e-9);
        assertEquals(1025.0, kart.toplamTutar(1000), 1e-9);
    }

    @Test
    @DisplayName("Kredi kartı 6 taksit: %2,5 + 5 x %1 = %7,5")
    void krediKartiTaksitli() {
        KrediKarti kart = new KrediKarti(6);

        assertEquals(75.0, kart.komisyon(1000), 1e-9);
        assertEquals(1075.0 / 6, kart.aylikTaksit(1000), 1e-9);
    }

    @Test
    @DisplayName("Geçersiz taksit sayısı reddedilir")
    void gecersizTaksit() {
        assertThrows(IllegalArgumentException.class, () -> new KrediKarti(0));
        assertThrows(IllegalArgumentException.class, () -> new KrediKarti(13));
        assertEquals(12, new KrediKarti(12).getTaksitSayisi());
    }

    @Test
    @DisplayName("Havale: 1000 TL altı 5 TL, 1000 TL ve üzeri ücretsiz")
    void havaleEsik() {
        Odeme havale = new Havale();

        assertEquals(5.0, havale.komisyon(999.99), 1e-9);
        assertEquals(0.0, havale.komisyon(1000), 1e-9);
        assertEquals(1000.0, havale.toplamTutar(1000), 1e-9);
    }

    @Test
    @DisplayName("Dijital cüzdan: %1,5, en az 1 TL")
    void dijitalCuzdanAsgari() {
        Odeme cuzdan = new DijitalCuzdan();

        assertEquals(1.0, cuzdan.komisyon(50), 1e-9);
        assertEquals(15.0, cuzdan.komisyon(1000), 1e-9);
    }

    @Test
    @DisplayName("default metot: sıfır ya da negatif tutar reddedilir")
    void toplamTutarGecersiz() {
        Odeme havale = new Havale();

        assertThrows(IllegalArgumentException.class, () -> havale.toplamTutar(0));
        assertThrows(IllegalArgumentException.class, () -> havale.toplamTutar(-10));
    }

    @Test
    @DisplayName("static arayüz metodu en düşük komisyonlu yöntemi bulur")
    void enUygun() {
        Odeme kart = new KrediKarti(1);
        Odeme havale = new Havale();
        Odeme cuzdan = new DijitalCuzdan();
        List<Odeme> secenekler = List.of(kart, havale, cuzdan);

        assertSame(cuzdan, Odeme.enUygun(secenekler, 200));   // 5 / 5 / 3
        assertSame(havale, Odeme.enUygun(secenekler, 2000));  // 50 / 0 / 30
        assertNull(Odeme.enUygun(List.of(), 100));
    }

    @Test
    @DisplayName("Birden çok arayüz: iade kuralları türe göre değişir")
    void iade() {
        Iade kart = new KrediKarti(3);
        Iade cuzdan = new DijitalCuzdan();

        assertEquals(1045.0, kart.iadeTutari(1045), 1e-9);
        assertEquals(1014.0, cuzdan.iadeTutari(1015), 1e-9);
        assertEquals(0.0, cuzdan.iadeTutari(0.5), 1e-9);
        assertTrue(kart instanceof Odeme);
    }
}
