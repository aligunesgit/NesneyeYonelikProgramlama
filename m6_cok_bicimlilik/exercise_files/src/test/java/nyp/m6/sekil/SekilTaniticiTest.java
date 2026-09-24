package nyp.m6.sekil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SekilTaniticiTest {

    @Test
    @DisplayName("Aşırı yükleme statik türe göre seçilir")
    void statikTureGoreSecim() {
        Sekil s = new Daire(2);

        assertEquals("Bir şekil", SekilTanitici.tanit(s));
    }

    @Test
    @DisplayName("Statik tür Daire ise Daire sürümü seçilir")
    void daireSurumu() {
        Daire d = new Daire(2);

        assertEquals("Bir daire", SekilTanitici.tanit(d));
        assertEquals("Bir daire", SekilTanitici.tanit((Daire) (Sekil) d));
    }

    @Test
    @DisplayName("Ezilmiş metot ise gerçek türe göre seçilir")
    void ezmeGercekTureGore() {
        Sekil s = new Daire(2);

        assertEquals("Daire", s.ad());
    }
}
