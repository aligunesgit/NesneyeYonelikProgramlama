package nyp.m5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - Yonetici: super.metot() ile genişletme, üç katmanlı zincir, final sınıf. */
class YoneticiTest {

    @Test
    @DisplayName("aylikUcret = üst sınıfın maaşı + prim")
    void aylikUcretMaasArtiPrim() {
        Yonetici zeynep = new Yonetici("S-003", "Zeynep", 60000, 15000);

        assertEquals(75000.0, zeynep.aylikUcret(), 1e-9);
    }

    @Test
    @DisplayName("toString iki kat super.toString() zincirinden geçer")
    void toStringZinciri() {
        Yonetici zeynep = new Yonetici("S-003", "Zeynep", 60000, 15000);

        assertEquals("S-003 Zeynep (tam zamanlı) [yönetici]", zeynep.toString());
    }

    @Test
    @DisplayName("Yönetici hem bir TamZamanliCalisan hem de bir Calisan'dır")
    void birTurIliskisi() {
        Yonetici zeynep = new Yonetici("S-003", "Zeynep", 60000, 15000);

        assertTrue(zeynep instanceof TamZamanliCalisan);
        assertTrue(zeynep instanceof Calisan);
        assertEquals(TamZamanliCalisan.class, Yonetici.class.getSuperclass());
    }

    @Test
    @DisplayName("Sıfır prim kabul edilmez")
    void sifirPrimReddedilir() {
        assertThrows(IllegalArgumentException.class,
                () -> new Yonetici("S-003", "Zeynep", 60000, 0));
    }

    @Test
    @DisplayName("Yonetici final, Calisan.equals ve hashCode final")
    void finalBildirimleri() {
        // Derleyicinin koyduğu kuralı yansıma (reflection) ile okuyoruz; ayrıntısı derste değil.
        assertTrue(Modifier.isFinal(Yonetici.class.getModifiers()));
        assertTrue(Modifier.isFinal(calisanMetotBelirtecleri("equals", Object.class)));
        assertTrue(Modifier.isFinal(calisanMetotBelirtecleri("hashCode")));
    }

    private static int calisanMetotBelirtecleri(String ad, Class<?>... parametreler) {
        try {
            return Calisan.class.getDeclaredMethod(ad, parametreler).getModifiers();
        } catch (NoSuchMethodException e) {
            throw new AssertionError(ad + " bulunamadı", e);
        }
    }
}
