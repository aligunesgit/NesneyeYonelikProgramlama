package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KutuTest {

    @Test
    @DisplayName("Kutu<String> içindeki değer cast olmadan alınır")
    void castGerekmez() {
        Kutu<String> kutu = new Kutu<>("kalem");

        String icerik = kutu.al();

        assertEquals("kalem", icerik);
        assertFalse(kutu.bosMu());
    }

    @Test
    @DisplayName("Aynı generic sınıf farklı türlerle kullanılabilir")
    void farkliTurler() {
        Kutu<Integer> sayiKutusu = new Kutu<>(42);
        Kutu<Ogrenci> ogrenciKutusu = new Kutu<>(new Ogrenci("2024001", "Ece", 3.5));

        assertEquals(43, sayiKutusu.al() + 1);
        assertEquals("Ece", ogrenciKutusu.al().getAd());
    }

    @Test
    @DisplayName("Boş kutudan nesne alınamaz; koy ile doldurulur")
    void bosKutu() {
        Kutu<String> kutu = new Kutu<>();

        assertTrue(kutu.bosMu());
        assertThrows(IllegalStateException.class, kutu::al);
        kutu.koy("silgi");
        assertEquals("silgi", kutu.al());
    }
}
