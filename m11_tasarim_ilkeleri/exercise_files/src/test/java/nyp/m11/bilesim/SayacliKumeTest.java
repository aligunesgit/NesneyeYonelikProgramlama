package nyp.m11.bilesim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SayacliKumeTest {

    @Test
    @DisplayName("Bileşimle yazılan küme addAll'da doğru sayar")
    void bilesimDogruSayar() {
        // Hazırla
        SayacliKume<String> kume = new SayacliKume<>();
        // Çalıştır
        kume.addAll(List.of("elma", "armut", "kiraz"));
        // Doğrula
        assertEquals(3, kume.getEklemeDenemesi());
        assertEquals(3, kume.size());
    }

    @Test
    @DisplayName("Kalıtımla yazılan küme addAll'da her elemanı iki kez sayar (kırılgan üst sınıf)")
    void kalitimIkiKezSayar() {
        KalitimliSayacliKume<String> kume = new KalitimliSayacliKume<>();

        kume.addAll(List.of("elma", "armut", "kiraz"));

        // Beklenen 3 olmalıydı: HashSet.addAll içeride add'i çağırıyor, add de sayıyor.
        assertEquals(6, kume.getEklemeDenemesi());
    }

    @Test
    @DisplayName("Tekrarlanan eleman denemeyi sayar ama kümeyi büyütmez")
    void tekrarlananEleman() {
        SayacliKume<String> kume = new SayacliKume<>();

        kume.add("elma");
        kume.add("elma");

        assertEquals(2, kume.getEklemeDenemesi());
        assertEquals(1, kume.size());
        assertTrue(kume.contains("elma"));
    }

    @Test
    @DisplayName("İçerideki küme gerçeklemesi dışarıdan seçilebilir")
    void icKumeSecilebilir() {
        SayacliKume<Integer> kume = new SayacliKume<>(new TreeSet<>());

        kume.addAll(List.of(3, 1, 2));

        assertEquals(3, kume.getEklemeDenemesi());
        assertTrue(kume.contains(2));
    }
}
