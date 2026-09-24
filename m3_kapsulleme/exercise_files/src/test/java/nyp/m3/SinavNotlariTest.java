package nyp.m3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SinavNotlariTest {

    @Test
    @DisplayName("Ortalama ve en yüksek not hesaplanır")
    void ortalamaVeEnYuksek() {
        SinavNotlari notlar = new SinavNotlari("BLM102", new int[] {70, 85, 90});

        assertEquals(81.666666666, notlar.ortalama(), 1e-6);
        assertEquals(90, notlar.enYuksek());
        assertEquals(3, notlar.notSayisi());
    }

    @Test
    @DisplayName("Kurucuya verilen dizi sonradan değiştirilse de nesne etkilenmez")
    void kurucuKopyaAlir() {
        // Hazırla
        int[] dizi = {70, 85, 90};
        SinavNotlari notlar = new SinavNotlari("BLM102", dizi);
        // Çalıştır: değişmezi dışarıdan bozmaya çalış
        dizi[0] = -500;
        // Doğrula
        assertArrayEquals(new int[] {70, 85, 90}, notlar.getNotlar());
    }

    @Test
    @DisplayName("getNotlar kopya döndürür; dönen diziyi değiştirmek nesneyi etkilemez")
    void getterKopyaDondurur() {
        SinavNotlari notlar = new SinavNotlari("BLM102", new int[] {70, 85, 90});

        int[] alinan = notlar.getNotlar();
        alinan[2] = 0;

        assertNotSame(alinan, notlar.getNotlar());
        assertEquals(90, notlar.enYuksek());
    }

    @Test
    @DisplayName("0-100 dışındaki not ve boş liste reddedilir; 0 ve 100 geçerlidir")
    void gecersizNotlarReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new SinavNotlari("BLM102", new int[] {50, 101}));
        assertThrows(IllegalArgumentException.class, () -> new SinavNotlari("BLM102", new int[] {-1}));
        assertThrows(IllegalArgumentException.class, () -> new SinavNotlari("BLM102", new int[0]));
        assertEquals(50.0, new SinavNotlari("BLM102", new int[] {0, 100}).ortalama(), 1e-9);
    }
}
