package nyp.m4.ev;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EvTest {

    @Test
    @DisplayName("Yeni evin odası yoktur, alanı 0'dır")
    void yeniEvBos() {
        Ev ev = new Ev("Kadıköy, İstanbul");

        assertEquals(0, ev.odaSayisi());
        assertEquals(0.0, ev.toplamAlan(), 1e-9);
    }

    @Test
    @DisplayName("Toplam alan, odaların alanları toplamıdır")
    void toplamAlanHesaplanir() {
        Ev ev = new Ev("Kadıköy, İstanbul");
        ev.odaEkle("Salon", 5, 4);        // 20 m²
        ev.odaEkle("Yatak odası", 3.5, 4); // 14 m²

        assertEquals(2, ev.odaSayisi());
        assertEquals(34.0, ev.toplamAlan(), 1e-9);
        assertEquals(14.0, ev.odaAlani("Yatak odası"), 1e-9);
    }

    @Test
    @DisplayName("Aynı adlı ikinci oda eklenmez")
    void ayniAdliOdaEklenmez() {
        Ev ev = new Ev("Kadıköy, İstanbul");

        assertTrue(ev.odaEkle("Salon", 5, 4));
        assertFalse(ev.odaEkle("Salon", 6, 6));
        assertEquals(1, ev.odaSayisi());
    }

    @Test
    @DisplayName("Boyutu sıfır olan oda yaratılamaz (sınır durumu)")
    void sifirBoyutReddedilir() {
        Ev ev = new Ev("Kadıköy, İstanbul");

        assertThrows(IllegalArgumentException.class, () -> ev.odaEkle("Kiler", 0, 2));
        assertEquals(0, ev.odaSayisi());
    }

    @Test
    @DisplayName("Olmayan odanın alanı sorulamaz")
    void olmayanOda() {
        Ev ev = new Ev("Kadıköy, İstanbul");

        assertThrows(IllegalArgumentException.class, () -> ev.odaAlani("Balkon"));
    }
}
