package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CiftTest {

    @Test
    @DisplayName("Cift iki farklı türü tutar")
    void ikiFarkliTur() {
        Cift<String, Integer> cift = new Cift<>("NYP101", 3);

        assertEquals("NYP101", cift.ilk());
        assertEquals(3, cift.ikinci());
    }

    @Test
    @DisplayName("yerDegistir türleri de değiştirir")
    void yerDegistir() {
        Cift<String, Integer> cift = new Cift<>("NYP101", 3);

        Cift<Integer, String> ters = cift.yerDegistir();

        assertEquals(new Cift<>(3, "NYP101"), ters);
    }
}
