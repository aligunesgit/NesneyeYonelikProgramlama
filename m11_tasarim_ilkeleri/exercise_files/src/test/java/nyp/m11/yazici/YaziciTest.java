package nyp.m11.yazici;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YaziciTest {

    /** Yalnızca yazdırmaya ihtiyaç duyan istemci kod: Yazdirici dışında hiçbir şey bilmez. */
    private static List<String> hepsiniYazdir(Yazdirici yazdirici, List<String> belgeler) {
        return belgeler.stream().map(yazdirici::yazdir).toList();
    }

    @Test
    @DisplayName("Basit yazıcı yazdırır ve sayar")
    void basitYaziciYazdirir() {
        BasitYazici yazici = new BasitYazici();

        List<String> kayitlar = hepsiniYazdir(yazici, List.of("fatura", "rapor"));

        assertEquals(List.of("Yazdırıldı: fatura", "Yazdırıldı: rapor"), kayitlar);
        assertEquals(2, yazici.getYazdirilanSayisi());
    }

    @Test
    @DisplayName("Basit yazıcı tarama yeteneği iddia etmez")
    void basitYaziciTarayiciDegil() {
        Object yazici = new BasitYazici();

        assertFalse(yazici instanceof Tarayici);
    }

    @Test
    @DisplayName("Çok fonksiyonlu yazıcı iki arayüzü de karşılar")
    void cokFonksiyonluYazici() {
        CokFonksiyonluYazici yazici = new CokFonksiyonluYazici();
        Tarayici tarayici = yazici;

        assertEquals(List.of("Yazdırıldı: sözleşme"), hepsiniYazdir(yazici, List.of("sözleşme")));
        assertEquals("sözleşme.pdf", tarayici.tara("sözleşme"));
    }
}
