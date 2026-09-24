package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReferansOrnekleriTest {

    @Test
    @DisplayName("İlkel tip: metot kopyayı değiştirir, çağıranın değişkeni aynı kalır")
    void ilkelTipDegismez() {
        int sayi = 5;

        ReferansOrnekleri.artir(sayi);

        assertEquals(5, sayi);
    }

    @Test
    @DisplayName("Nesne: referansın kopyası aynı nesneyi gösterir, nesne değişir")
    void nesneDegisir() {
        Sayac sayac = new Sayac();

        ReferansOrnekleri.artir(sayac);
        ReferansOrnekleri.artir(sayac);

        assertEquals(2, sayac.getDeger());
    }

    @Test
    @DisplayName("Parametreye yeni nesne atamak çağıranın referansını değiştirmez")
    void yeniNesneAtamakEtkisiz() {
        Sayac sayac = new Sayac();
        Sayac onceki = sayac;

        ReferansOrnekleri.yeniSayacAta(sayac);

        assertSame(onceki, sayac);
        assertEquals(0, sayac.getDeger());
    }

    @Test
    @DisplayName("Java'da takas metodu referansları takas edemez")
    void takasCalismaz() {
        Nokta a = new Nokta(1, 1);
        Nokta b = new Nokta(2, 2);

        ReferansOrnekleri.takasEt(a, b);

        assertEquals(1.0, a.getX(), 1e-9);
        assertEquals(2.0, b.getX(), 1e-9);
    }
}
