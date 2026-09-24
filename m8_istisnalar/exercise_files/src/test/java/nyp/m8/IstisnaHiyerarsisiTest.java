package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IstisnaHiyerarsisiTest {

    @Test
    @DisplayName("Banka istisnaları kontrollüdür: Exception'dan türer, RuntimeException'dan değil")
    void bankaIstisnalariKontrollu() {
        Exception hata = new YetersizBakiyeException("TR01", 10, 20);

        assertTrue(hata instanceof BankaException);
        assertFalse(RuntimeException.class.isAssignableFrom(YetersizBakiyeException.class));
        assertFalse(RuntimeException.class.isAssignableFrom(HesapBulunamadiException.class));
    }

    @Test
    @DisplayName("IllegalArgumentException denetlenmeyen bir istisnadır")
    void illegalArgumentDenetlenmeyen() {
        assertTrue(RuntimeException.class.isAssignableFrom(IllegalArgumentException.class));
    }

    @Test
    @DisplayName("Neden (cause) zinciri kurucuya verilen istisnayı saklar")
    void nedenZinciri() {
        NumberFormatException asil = new NumberFormatException("For input string: \"x\"");
        BankaException sarici = new BankaException("Tutar okunamadı", asil);

        assertSame(asil, sarici.getCause());
        assertEquals("Tutar okunamadı", sarici.getMessage());
    }

    @Test
    @DisplayName("YetersizBakiyeException alanları doğru hesaplar")
    void yetersizBakiyeAlanlari() {
        YetersizBakiyeException hata = new YetersizBakiyeException("TR07", 40, 100);

        assertEquals(40.0, hata.getBakiye(), 1e-9);
        assertEquals(100.0, hata.getIstenen(), 1e-9);
        assertEquals(60.0, hata.getEksik(), 1e-9);
    }
}
