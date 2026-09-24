package nyp.m2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SayacTest {

    @Test
    @DisplayName("Kurucu yazılmasa da new Sayac() çalışır ve sayaç 0'dan başlar")
    void varsayilanKurucuVeSifirBaslangic() {
        Sayac sayac = new Sayac();

        assertEquals(0, sayac.getDeger());
    }

    @Test
    @DisplayName("artir ve sifirla")
    void artirVeSifirla() {
        Sayac sayac = new Sayac();

        sayac.artir();
        sayac.artir();
        sayac.artir();
        assertEquals(3, sayac.getDeger());
        assertEquals("Sayac[3]", sayac.toString());

        sayac.sifirla();
        assertEquals(0, sayac.getDeger());
    }
}
