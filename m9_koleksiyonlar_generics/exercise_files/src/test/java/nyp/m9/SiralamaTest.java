package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiralamaTest {

    private final Ogrenci can = new Ogrenci("2024003", "Can", 2.8);
    private final Ogrenci ece = new Ogrenci("2024001", "Ece", 3.5);
    private final Ogrenci ali = new Ogrenci("2024002", "Ali", 3.5);
    private List<Ogrenci> liste;

    @BeforeEach
    void hazirla() {
        liste = new ArrayList<>(List.of(can, ece, ali));
    }

    @Test
    @DisplayName("compareTo numaraya göre karşılaştırır")
    void compareToNumarayaGore() {
        assertTrue(ece.compareTo(ali) < 0);
        assertTrue(can.compareTo(ali) > 0);
        assertEquals(0, ece.compareTo(new Ogrenci("2024001", "Başka", 1.0)));
    }

    @Test
    @DisplayName("Collections.sort doğal sıralamayı (numara) kullanır")
    void dogalSiralama() {
        Collections.sort(liste);

        assertEquals(List.of(ece, ali, can), liste);
    }

    @Test
    @DisplayName("Ayrı sınıf olarak yazılmış Comparator ada göre sıralar")
    void adaGoreSiralama() {
        liste.sort(new AdaGoreKarsilastirici());

        assertEquals(List.of(ali, can, ece), liste);
    }

    @Test
    @DisplayName("Anonim sınıf Comparator ortalamaya göre azalan sıralar")
    void ortalamayaGoreAzalan() {
        liste.sort(Ogrenci.ORTALAMAYA_GORE_AZALAN);

        assertEquals(can, liste.get(2));
        assertEquals(3.5, liste.get(0).getOrtalama(), 1e-9);
    }

    @Test
    @DisplayName("thenComparing eşit ortalamada ada göre sıralar")
    void thenComparing() {
        liste.sort(Ogrenci.ORTALAMAYA_GORE_AZALAN.thenComparing(new AdaGoreKarsilastirici()));

        assertEquals(List.of(ali, ece, can), liste);
    }

    @Test
    @DisplayName("reversed sıralamayı tersine çevirir")
    void reversed() {
        liste.sort(new AdaGoreKarsilastirici().reversed());

        assertEquals(List.of(ece, can, ali), liste);
    }

    @Test
    @DisplayName("TreeSet elemanları doğal sıralamayla tutar")
    void treeSetSirali() {
        TreeSet<Ogrenci> kume = new TreeSet<>(liste);

        assertEquals(ece, kume.first());
        assertEquals(can, kume.last());
    }

    @Test
    @DisplayName("Lambda ile yazılan karşılaştırıcı aynı sonucu verir (M10 önizleme)")
    void lambdaIleAyniSonuc() {
        Comparator<Ogrenci> lambdaIle = Comparator.comparingDouble(Ogrenci::getOrtalama)
                .reversed()
                .thenComparing(Ogrenci::getAd);

        liste.sort(lambdaIle);

        assertEquals(List.of(ali, ece, can), liste);
    }
}
