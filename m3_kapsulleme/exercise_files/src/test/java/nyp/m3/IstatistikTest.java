package nyp.m3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IstatistikTest {

    @Test
    @DisplayName("Static metotlar nesne oluşturmadan sınıf adıyla çağrılır")
    void staticMetotlar() {
        int[] dizi = {4, -2, 9, 1};

        assertEquals(3.0, Istatistik.ortalama(dizi), 1e-9);
        assertEquals(9, Istatistik.enBuyuk(dizi));
        assertEquals(-2, Istatistik.enKucuk(dizi));
    }

    @Test
    @DisplayName("Tek elemanlı dizide ortalama, en büyük ve en küçük aynıdır")
    void tekElemanliDizi() {
        int[] dizi = {42};

        assertEquals(42.0, Istatistik.ortalama(dizi), 1e-9);
        assertEquals(42, Istatistik.enBuyuk(dizi));
        assertEquals(42, Istatistik.enKucuk(dizi));
    }

    @Test
    @DisplayName("Boş dizi reddedilir")
    void bosDiziReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> Istatistik.ortalama(new int[0]));
        assertThrows(IllegalArgumentException.class, () -> Istatistik.enBuyuk(new int[0]));
    }
}
