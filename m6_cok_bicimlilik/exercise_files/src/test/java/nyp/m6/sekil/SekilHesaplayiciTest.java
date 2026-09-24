package nyp.m6.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SekilHesaplayiciTest {

    private final Sekil daire = new Daire(1);
    private final Sekil dikdortgen = new Dikdortgen(2, 3);
    private final Sekil ucgen = new Ucgen(3, 4, 5);
    private final List<Sekil> sekiller = List.of(daire, dikdortgen, ucgen);

    @Test
    @DisplayName("Çok biçimli toplam alan tüm türleri kapsar")
    void toplamAlanCokBicimli() {
        assertEquals(Math.PI + 6 + 6, SekilHesaplayici.toplamAlan(sekiller), 1e-9);
    }

    @Test
    @DisplayName("instanceof zinciri üçgeni unuttuğu için yanlış sonuç verir")
    void instanceofZinciriEksik() {
        double kotu = SekilHesaplayici.toplamAlanInstanceofIle(sekiller);

        assertEquals(Math.PI + 6, kotu, 1e-9);
        assertNotEquals(SekilHesaplayici.toplamAlan(sekiller), kotu, 1e-9);
    }

    @Test
    @DisplayName("Boş listenin toplam alanı 0'dır")
    void bosListe() {
        assertEquals(0.0, SekilHesaplayici.toplamAlan(List.of()), 1e-9);
        assertNull(SekilHesaplayici.enBuyuk(List.of()));
    }

    @Test
    @DisplayName("Alanı en büyük şekil; eşitlikte ilk gelen kalır")
    void enBuyuk() {
        assertSame(dikdortgen, SekilHesaplayici.enBuyuk(sekiller));
    }

    @Test
    @DisplayName("Kare sayısı instanceof desen eşlemeyle bulunur")
    void kareSayisi() {
        List<Sekil> liste = List.of(new Dikdortgen(2, 2), new Dikdortgen(2, 3), new Daire(2), new Dikdortgen(5, 5));

        assertEquals(2, SekilHesaplayici.kareSayisi(liste));
    }

    @Test
    @DisplayName("switch desen eşleme: her tür ve koruma (when) koşulu")
    void tarif() {
        assertEquals("yarıçapı 1.0 olan daire", SekilHesaplayici.tarif(daire));
        assertEquals("kenarı 4.0 olan kare", SekilHesaplayici.tarif(new Dikdortgen(4, 4)));
        assertEquals("2.0 x 3.0 dikdörtgen", SekilHesaplayici.tarif(dikdortgen));
        assertEquals("Üçgen", SekilHesaplayici.tarif(ucgen));
        assertEquals("şekil yok", SekilHesaplayici.tarif(null));
    }
}
