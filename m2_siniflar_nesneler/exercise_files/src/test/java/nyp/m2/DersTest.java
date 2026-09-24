package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DersTest {

    @Test
    @DisplayName("İki parametreli kurucu 3 kredi ve 40 kontenjan kullanır")
    void varsayilanKrediVeKontenjan() {
        Ders ders = new Ders("BLM102", "Nesneye Yönelik Programlama");

        assertEquals(3, ders.getKredi());
        assertEquals(40, ders.getKontenjan());
        assertEquals(0, ders.getKayitliSayisi());
        assertEquals("BLM102 Nesneye Yönelik Programlama (3 kredi, 0/40)", ders.toString());
    }

    @Test
    @DisplayName("Dersteki öğrenci, dışarıdaki öğrenciyle aynı nesnedir")
    void derstekiOgrenciAyniNesne() {
        Ders ders = new Ders("BLM102", "Nesneye Yönelik Programlama");
        Ogrenci ayse = new Ogrenci("2024001", "Ayşe Yılmaz");

        ders.kayitEt(ayse);
        ayse.sinifAtla();

        assertSame(ayse, ders.getOgrenci(0));
        assertEquals(2, ders.getOgrenci(0).getSinif());
    }

    @Test
    @DisplayName("Aynı öğrenci iki kez kaydedilemez, null kaydedilemez")
    void tekrarVeNullKayitReddedilir() {
        Ders ders = new Ders("BLM102", "Nesneye Yönelik Programlama");
        Ogrenci ayse = new Ogrenci("2024001", "Ayşe Yılmaz");

        assertTrue(ders.kayitEt(ayse));
        assertFalse(ders.kayitEt(ayse));
        assertFalse(ders.kayitEt(null));
        assertEquals(1, ders.getKayitliSayisi());
    }

    @Test
    @DisplayName("Kontenjan dolunca kayıt yapılmaz (sınır durumu)")
    void kontenjanDolunca() {
        Ders ders = new Ders("BLM499", "Bitirme Projesi", 6, 2);

        assertTrue(ders.kayitEt(new Ogrenci("1", "A")));
        assertTrue(ders.kayitEt(new Ogrenci("2", "B")));
        assertFalse(ders.kayitEt(new Ogrenci("3", "C")));
        assertEquals(2, ders.getKayitliSayisi());
    }

    @Test
    @DisplayName("Geçersiz sıra numarasında getOgrenci null döndürür")
    void gecersizSiradaNull() {
        Ders ders = new Ders("BLM102", "Nesneye Yönelik Programlama");
        ders.kayitEt(new Ogrenci("2024001", "Ayşe Yılmaz"));

        assertNull(ders.getOgrenci(1));
        assertNull(ders.getOgrenci(-1));
    }
}
