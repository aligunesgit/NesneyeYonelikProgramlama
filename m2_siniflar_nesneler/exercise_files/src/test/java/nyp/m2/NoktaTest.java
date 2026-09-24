package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoktaTest {

    @Test
    @DisplayName("Parametresiz kurucu başlangıç noktasını (0, 0) oluşturur")
    void parametresizKurucuBaslangicNoktasi() {
        Nokta p = new Nokta();

        assertEquals(0.0, p.getX(), 1e-9);
        assertEquals(0.0, p.getY(), 1e-9);
    }

    @Test
    @DisplayName("3-4-5 üçgeni: (0,0) ile (3,4) arasındaki uzaklık 5'tir")
    void uzaklikPisagor() {
        Nokta a = new Nokta();
        Nokta b = new Nokta(3, 4);

        assertEquals(5.0, a.uzaklik(b), 1e-9);
        assertEquals(5.0, b.uzaklik(a), 1e-9);
    }

    @Test
    @DisplayName("tasi koordinatları değiştirir, toString yeni değerleri gösterir")
    void tasiKoordinatlariDegistirir() {
        Nokta p = new Nokta(1, 2);

        p.tasi(-1, 0.5);

        assertEquals(0.0, p.getX(), 1e-9);
        assertEquals(2.5, p.getY(), 1e-9);
        assertEquals("(0.0, 2.5)", p.toString());
    }

    @Test
    @DisplayName("Kopya kurucu bağımsız yeni bir nesne üretir")
    void kopyaKurucuBagimsizNesne() {
        Nokta asil = new Nokta(1, 1);
        Nokta kopya = new Nokta(asil);

        asil.tasi(5, 5);

        assertNotSame(asil, kopya);
        assertEquals(1.0, kopya.getX(), 1e-9);
    }

    @Test
    @DisplayName("equals tanımlanmadığı için aynı koordinatlı iki nokta eşit sayılmaz (M5)")
    void equalsTanimsizkenKimligeBakar() {
        Nokta a = new Nokta(2, 3);
        Nokta b = new Nokta(2, 3);
        Nokta c = a;

        assertFalse(a.equals(b));
        assertTrue(a.equals(c));
    }
}
