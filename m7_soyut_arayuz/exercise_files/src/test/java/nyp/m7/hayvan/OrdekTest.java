package nyp.m7.hayvan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdekTest {

    /** Yalnızca Ucabilen'i uygulayan, hiçbir metodu ezmeyen yardımcı sınıf. */
    static class Kartal implements Ucabilen {
    }

    @Test
    @DisplayName("Elmas çakışması X.super.m() ile çözülür")
    void elmasCozumu() {
        assertEquals("uçar ve yüzer", new Ordek().hareket());
    }

    @Test
    @DisplayName("Arayüz türünden referans da ezilmiş metodu çağırır")
    void arayuzReferansi() {
        Ucabilen u = new Ordek();
        Yuzebilen y = new Ordek();

        assertEquals("uçar ve yüzer", u.hareket());
        assertEquals("uçar ve yüzer", y.hareket());
    }

    @Test
    @DisplayName("Tek arayüzü uygulayan sınıf default metodu olduğu gibi alır")
    void tekArayuz() {
        Ucabilen kartal = new Kartal();

        assertEquals("uçar", kartal.hareket());
    }
}
