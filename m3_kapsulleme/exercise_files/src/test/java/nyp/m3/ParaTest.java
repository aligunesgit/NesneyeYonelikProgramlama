package nyp.m3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParaTest {

    @Test
    @DisplayName("Record erişimcileri ve otomatik toString")
    void erisimcilerVeToString() {
        Para para = new Para(1250, "TRY");

        assertEquals(1250L, para.kurus());
        assertEquals("TRY", para.paraBirimi());
        assertEquals("Para[kurus=1250, paraBirimi=TRY]", para.toString());
    }

    @Test
    @DisplayName("Record equals içeriğe bakar; farklı nesneler eşit olabilir")
    void equalsIcerigeBakar() {
        Para a = Para.tl(12, 50);
        Para b = new Para(1250, "TRY");

        assertNotSame(a, b);
        assertEquals(a, b);
    }

    @Test
    @DisplayName("Kompakt kurucu para birimini büyük harfe çevirir")
    void kompaktKurucuNormallestirir() {
        assertEquals("EUR", new Para(100, "eur").paraBirimi());
    }

    @Test
    @DisplayName("Kompakt kurucu geçersiz değerleri reddeder")
    void kompaktKurucuDogrular() {
        assertThrows(IllegalArgumentException.class, () -> new Para(-1, "TRY"));
        assertThrows(IllegalArgumentException.class, () -> new Para(100, "TL"));
        assertThrows(IllegalArgumentException.class, () -> new Para(100, null));
        assertThrows(IllegalArgumentException.class, () -> Para.tl(5, 100));
    }

    @Test
    @DisplayName("topla ve cikar yeni nesne döndürür, asıl nesne değişmez")
    void toplaVeCikar() {
        Para fiyat = Para.tl(12, 50);

        Para toplam = fiyat.topla(Para.tl(7, 75));
        Para kalan = toplam.cikar(Para.tl(20, 25));

        assertEquals(Para.tl(20, 25), toplam);
        assertEquals(new Para(0, "TRY"), kalan);
        assertEquals(1250L, fiyat.kurus());
        assertEquals("20,25 TRY", toplam.bicimli());
    }

    @Test
    @DisplayName("Farklı para birimleri toplanamaz, sonuç negatif olamaz")
    void gecersizIslemler() {
        Para tl = Para.tl(10, 0);
        Para euro = new Para(1000, "EUR");

        assertThrows(IllegalArgumentException.class, () -> tl.topla(euro));
        assertThrows(IllegalArgumentException.class, () -> tl.cikar(Para.tl(10, 1)));
    }
}
