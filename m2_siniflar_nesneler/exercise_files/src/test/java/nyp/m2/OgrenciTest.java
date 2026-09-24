package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OgrenciTest {

    @Test
    @DisplayName("İki parametreli kurucu, sınıfı 1 olarak ayarlar (this(...) zinciri)")
    void ikiParametreliKurucuSinifiBirYapar() {
        Ogrenci ogrenci = new Ogrenci("2024001", "Ayşe Yılmaz");

        assertEquals("2024001", ogrenci.getNumara());
        assertEquals("Ayşe Yılmaz", ogrenci.getAd());
        assertEquals(1, ogrenci.getSinif());
    }

    @Test
    @DisplayName("Atanmamış alanlar varsayılan değerlerini alır: double 0.0, String null")
    void atanmamisAlanlarVarsayilanDegerde() {
        Ogrenci ogrenci = new Ogrenci("2024001", "Ayşe Yılmaz");

        assertEquals(0.0, ogrenci.getGano(), 1e-9);
        assertNull(ogrenci.getDanisman());
        assertFalse(ogrenci.danismaniVarMi());
    }

    @Test
    @DisplayName("Sınıf atlama 4. sınıfta durur (sınır durumu)")
    void sinifAtlamaDorttenSonraDurur() {
        Ogrenci ogrenci = new Ogrenci("2021005", "Zeynep Demir", 3);

        assertTrue(ogrenci.sinifAtla());
        assertEquals(4, ogrenci.getSinif());
        assertFalse(ogrenci.sinifAtla());
        assertEquals(4, ogrenci.getSinif());
    }

    @Test
    @DisplayName("toString okunaklı bir metin üretir")
    void toStringOkunakli() {
        Ogrenci ogrenci = new Ogrenci("2023017", "Mehmet Kaya", 2);

        assertEquals("2023017 Mehmet Kaya (2. sınıf)", ogrenci.toString());
        assertEquals("Öğrenci: 2023017 Mehmet Kaya (2. sınıf)", "Öğrenci: " + ogrenci);
    }

    @Test
    @DisplayName("Atama nesneyi kopyalamaz: iki referans aynı nesneyi gösterir")
    void atamaAliasingOlusturur() {
        // Hazırla
        Ogrenci ayse = new Ogrenci("2024001", "Ayşe Yılmaz");
        Ogrenci kopya = ayse;
        // Çalıştır
        kopya.sinifAtla();
        // Doğrula
        assertSame(ayse, kopya);
        assertEquals(2, ayse.getSinif());
    }

    @Test
    @DisplayName("Aynı bilgilerle oluşturulan iki öğrenci iki ayrı nesnedir")
    void ayniBilgilerIkiAyriNesne() {
        Ogrenci a = new Ogrenci("2024001", "Ayşe Yılmaz");
        Ogrenci b = new Ogrenci("2024001", "Ayşe Yılmaz");

        assertNotSame(a, b);
        assertFalse(a == b);
    }

    @Test
    @DisplayName("Danışmanı null olan öğrencide danismanBasHarfi NullPointerException fırlatır")
    void danismanYoksaNullPointerException() {
        Ogrenci ogrenci = new Ogrenci("2024001", "Ayşe Yılmaz");

        assertThrows(NullPointerException.class, () -> ogrenci.danismanBasHarfi());
    }

    @Test
    @DisplayName("Danışman atanınca baş harfi okunabilir")
    void danismanAtanincaBasHarfOkunur() {
        Ogrenci ogrenci = new Ogrenci("2024001", "Ayşe Yılmaz");
        ogrenci.setDanisman("Dr. Elif Şahin");

        assertTrue(ogrenci.danismaniVarMi());
        assertEquals('D', ogrenci.danismanBasHarfi());
    }
}
