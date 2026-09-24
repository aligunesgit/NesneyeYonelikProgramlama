package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DikdortgenTest {

    @Test
    @DisplayName("Alan ve çevre doğru hesaplanır")
    void alanVeCevre() {
        Dikdortgen d = new Dikdortgen(4, 2.5);

        assertEquals(10.0, d.alan(), 1e-9);
        assertEquals(13.0, d.cevre(), 1e-9);
    }

    @Test
    @DisplayName("Kenar üzerindeki nokta içeride sayılır, dışarıdaki sayılmaz")
    void icerirSinirDurumlari() {
        Dikdortgen d = new Dikdortgen(new Nokta(1, 1), 2, 3);

        assertTrue(d.icerir(new Nokta(3, 4)));       // sağ üst köşe
        assertTrue(d.icerir(new Nokta(2, 2)));
        assertFalse(d.icerir(new Nokta(3.01, 2)));
    }

    @Test
    @DisplayName("Aynı Nokta ile kurulan iki dikdörtgen köşeyi paylaşır (aliasing)")
    void ayniNoktaPaylasilir() {
        // Hazırla
        Nokta kose = new Nokta(1, 1);
        Dikdortgen d1 = new Dikdortgen(kose, 2, 3);
        Dikdortgen d2 = new Dikdortgen(kose, 4, 5);
        // Çalıştır
        d1.tasi(10, 0);
        // Doğrula: yalnızca d1'i taşıdık ama d2 de taşındı
        assertSame(d1.getSolAlt(), d2.getSolAlt());
        assertEquals(11.0, d2.getSolAlt().getX(), 1e-9);
        assertEquals(11.0, kose.getX(), 1e-9);
    }

    @Test
    @DisplayName("Kopya nokta ile kurulan dikdörtgenler birbirinden bağımsızdır")
    void kopyaNoktaIleBagimsiz() {
        Nokta kose = new Nokta(1, 1);
        Dikdortgen d1 = new Dikdortgen(new Nokta(kose), 2, 3);
        Dikdortgen d2 = new Dikdortgen(new Nokta(kose), 4, 5);

        d1.tasi(10, 0);

        assertNotSame(d1.getSolAlt(), d2.getSolAlt());
        assertEquals(1.0, d2.getSolAlt().getX(), 1e-9);
        assertEquals(1.0, kose.getX(), 1e-9);
    }

    @Test
    @DisplayName("getSolAlt ile alınan referans üzerinden dikdörtgen değiştirilebilir")
    void getterReferansDondurur() {
        Dikdortgen d = new Dikdortgen(2, 2);

        d.getSolAlt().tasi(0, 7);

        assertEquals(7.0, d.getSolAlt().getY(), 1e-9);
    }
}
