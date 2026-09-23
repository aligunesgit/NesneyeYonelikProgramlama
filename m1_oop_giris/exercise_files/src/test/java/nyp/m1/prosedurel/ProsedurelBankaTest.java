package nyp.m1.prosedurel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProsedurelBankaTest {

    @Test
    void paraYatirYalnizcaIlgiliHesabiDegistirir() {
        double[] bakiyeler = {0, 0};

        assertTrue(ProsedurelBanka.paraYatir(bakiyeler, 0, 500));
        assertArrayEquals(new double[] {500, 0}, bakiyeler);
    }

    @Test
    void bakiyedenFazlaCekilemez() {
        double[] bakiyeler = {100, 0};

        assertFalse(ProsedurelBanka.paraCek(bakiyeler, 0, 150));
        assertArrayEquals(new double[] {100, 0}, bakiyeler);
    }

    @Test
    void diziyeDogrudanErisimKuraliAtlatabilir() {
        // Prosedürel yaklaşımın zayıf noktası: kural (bakiye negatif olamaz) yalnızca
        // paraCek içinde yazılı. Diziye erişen herhangi bir kod onu atlayabilir.
        double[] bakiyeler = {100, 0};
        bakiyeler[0] = -500;

        assertTrue(bakiyeler[0] < 0);
    }
}
