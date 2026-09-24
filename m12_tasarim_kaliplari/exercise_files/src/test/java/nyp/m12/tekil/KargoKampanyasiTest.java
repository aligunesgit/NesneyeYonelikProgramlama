package nyp.m12.tekil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KargoKampanyasiTest {

    @Test
    @DisplayName("Tekile dokunmadan, lambda ile verilen ayarla test edilir")
    void lambdaAyarIleTest() {
        KargoKampanyasi kampanya = new KargoKampanyasi(anahtar -> "300");

        assertTrue(kampanya.ucretsizKargoMu(300));   // sınır: eşiğe eşit
        assertFalse(kampanya.ucretsizKargoMu(299.99));
    }

    @Test
    @DisplayName("Üretim yapılandırmasıyla varsayılan eşik 500 TL'dir")
    void varsayilanEsik() {
        KargoKampanyasi kampanya = new KargoKampanyasi(Yapilandirma.ORNEK);

        assertTrue(kampanya.ucretsizKargoMu(500));
        assertFalse(kampanya.ucretsizKargoMu(499));
    }
}
