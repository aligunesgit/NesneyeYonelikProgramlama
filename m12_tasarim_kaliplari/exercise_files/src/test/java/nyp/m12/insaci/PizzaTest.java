package nyp.m12.insaci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PizzaTest {

    @Test
    @DisplayName("İnşacı tüm seçimleri pizzaya aktarır ve fiyatı hesaplar")
    void tamPizza() {
        // Hazırla + Çalıştır
        Pizza pizza = Pizza.insaci()
                .boyut(Pizza.Boyut.ORTA)
                .hamur("kalın")
                .malzeme("mantar")
                .malzeme("zeytin")
                .ekstraPeynir()
                .olustur();
        // Doğrula: 200 + 2 * 15 + 20 = 250
        assertEquals(Pizza.Boyut.ORTA, pizza.getBoyut());
        assertEquals("kalın", pizza.getHamur());
        assertEquals(List.of("mantar", "zeytin"), pizza.getMalzemeler());
        assertTrue(pizza.isEkstraPeynir());
        assertEquals(250.0, pizza.fiyat(), 1e-9);
    }

    @Test
    @DisplayName("İsteğe bağlı alanlar verilmezse varsayılanlar kullanılır")
    void varsayilanlar() {
        Pizza pizza = Pizza.insaci().boyut(Pizza.Boyut.KUCUK).olustur();

        assertEquals("ince", pizza.getHamur());
        assertTrue(pizza.getMalzemeler().isEmpty());
        assertFalse(pizza.isEkstraPeynir());
        assertEquals(150.0, pizza.fiyat(), 1e-9);
    }

    @Test
    @DisplayName("Zorunlu alan (boyut) eksikse istisna fırlatılır")
    void boyutZorunlu() {
        Pizza.Insaci insaci = Pizza.insaci().malzeme("sucuk");

        assertThrows(IllegalStateException.class, insaci::olustur);
    }

    @Test
    @DisplayName("Beş malzeme kabul edilir, altıncısı reddedilir")
    void malzemeSiniri() {
        Pizza.Insaci insaci = Pizza.insaci().boyut(Pizza.Boyut.BUYUK);
        for (int i = 1; i <= 5; i++) {
            insaci.malzeme("m" + i);
        }
        assertEquals(335.0, insaci.olustur().fiyat(), 1e-9);   // 260 + 5 * 15

        insaci.malzeme("m6");
        assertThrows(IllegalStateException.class, insaci::olustur);
    }

    @Test
    @DisplayName("Oluşan pizza değişmezdir; inşacıdaki sonraki değişiklik ona yansımaz")
    void pizzaDegismez() {
        Pizza.Insaci insaci = Pizza.insaci().boyut(Pizza.Boyut.ORTA).malzeme("mısır");
        Pizza pizza = insaci.olustur();

        insaci.malzeme("biber");

        assertEquals(List.of("mısır"), pizza.getMalzemeler());
        assertThrows(UnsupportedOperationException.class, () -> pizza.getMalzemeler().add("ton"));
    }

    @Test
    @DisplayName("Boş malzeme ve boş hamur reddedilir")
    void bosDegerlerReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> Pizza.insaci().malzeme(""));
        assertThrows(IllegalArgumentException.class, () -> Pizza.insaci().hamur(null));
    }
}
