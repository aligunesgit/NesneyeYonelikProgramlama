package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenericAraclarTest {

    @Test
    @DisplayName("enBuyuk sayılarla, metinlerle ve öğrencilerle çalışır")
    void enBuyukFarkliTurler() {
        assertEquals(9, GenericAraclar.enBuyuk(List.of(3, 9, 4)));
        assertEquals("zeytin", GenericAraclar.enBuyuk(List.of("elma", "zeytin", "armut")));

        Ogrenci can = new Ogrenci("2024003", "Can", 2.8);
        List<Ogrenci> ogrenciler = List.of(new Ogrenci("2024001", "Ece", 3.5), can);
        assertEquals(can, GenericAraclar.enBuyuk(ogrenciler));
    }

    @Test
    @DisplayName("Tek elemanlı listede en büyük o elemandır")
    void tekEleman() {
        assertEquals(-5, GenericAraclar.enBuyuk(List.of(-5)));
    }

    @Test
    @DisplayName("Boş listede enBuyuk NoSuchElementException fırlatır")
    void bosListe() {
        List<Integer> bos = List.of();

        assertThrows(NoSuchElementException.class, () -> GenericAraclar.enBuyuk(bos));
    }

    @Test
    @DisplayName("ilkVeSon bir Cift döndürür")
    void ilkVeSon() {
        Cift<String, String> sonuc = GenericAraclar.ilkVeSon(List.of("pzt", "sal", "car"));

        assertEquals(new Cift<>("pzt", "car"), sonuc);
    }

    @Test
    @DisplayName("toplam, ? extends Number sayesinde Integer ve Double listelerini kabul eder")
    void jokerliToplam() {
        List<Integer> tamSayilar = List.of(1, 2, 3);
        List<Double> ondaliklar = List.of(0.5, 0.25);

        assertEquals(6.0, GenericAraclar.toplam(tamSayilar), 1e-9);
        assertEquals(0.75, GenericAraclar.toplam(ondaliklar), 1e-9);
        assertEquals(0.0, GenericAraclar.toplam(List.of()), 1e-9);
    }
}
