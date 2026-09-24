package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KelimeSayaciTest {

    @Test
    @DisplayName("Kelimeler büyük/küçük harf ve noktalamadan bağımsız sayılır")
    void kelimeleriSayar() {
        KelimeSayaci sayac = new KelimeSayaci();

        sayac.ekle("Bir elma, bir armut; iki ELMA.");

        assertEquals(2, sayac.sayi("elma"));
        assertEquals(2, sayac.sayi("Bir"));
        assertEquals(0, sayac.sayi("kiraz"));
        assertEquals(4, sayac.farkliKelimeSayisi());
    }

    @Test
    @DisplayName("TreeMap anahtarları alfabetik sırada tutar")
    void alfabetikSira() {
        KelimeSayaci sayac = new KelimeSayaci();
        sayac.ekle("kedi at balik at");

        Map<String, Integer> sayimlar = sayac.sayimlar();

        assertEquals(List.of("at", "balik", "kedi"), List.copyOf(sayimlar.keySet()));
        assertEquals(Map.of("at", 2, "balik", 1, "kedi", 1), sayimlar);
    }

    @Test
    @DisplayName("Türkçe büyük I harfi ı olarak küçültülür")
    void turkceKucukHarf() {
        KelimeSayaci sayac = new KelimeSayaci();
        sayac.ekle("IŞIK ışık");

        assertEquals(2, sayac.sayi("ışık"));
    }

    @Test
    @DisplayName("En sık kelime; eşitlikte alfabetik önce gelen")
    void enSikKelime() {
        KelimeSayaci sayac = new KelimeSayaci();
        sayac.ekle("mavi yeşil mavi yeşil kırmızı");

        assertEquals("mavi", sayac.enSikKelime());
    }

    @Test
    @DisplayName("Boş sayaçta en sık kelime sorulamaz")
    void bosSayac() {
        assertThrows(IllegalStateException.class, () -> new KelimeSayaci().enSikKelime());
    }

    @Test
    @DisplayName("Dışarıya verilen harita değiştirilemez")
    void haritaDegistirilemez() {
        KelimeSayaci sayac = new KelimeSayaci();
        sayac.ekle("elma");

        assertThrows(UnsupportedOperationException.class, () -> sayac.sayimlar().put("elma", 99));
        assertEquals(1, sayac.sayi("elma"));
    }
}
