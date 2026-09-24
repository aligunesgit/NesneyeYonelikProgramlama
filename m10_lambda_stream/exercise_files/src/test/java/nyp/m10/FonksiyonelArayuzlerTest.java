package nyp.m10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FonksiyonelArayuzlerTest {

    private final Siparis kitap = new Siparis("Ayşe", "Kitap", 120, SiparisDurumu.TESLIM_EDILDI);
    private final Siparis telefon = new Siparis("Mehmet", "Elektronik", 2500, SiparisDurumu.IPTAL);

    @Test
    @DisplayName("Predicate: and, negate ile birleştirme")
    void predicateBirlestirme() {
        Predicate<Siparis> pahali = s -> s.tutar() > 1000;
        Predicate<Siparis> iptal = Siparis::iptalMi;

        assertTrue(pahali.and(iptal).test(telefon));
        assertFalse(pahali.test(kitap));
        assertTrue(pahali.negate().test(kitap));
    }

    @Test
    @DisplayName("Function: andThen ile zincirleme")
    void functionZincirleme() {
        Function<Siparis, String> musteri = Siparis::musteri;
        Function<Siparis, Integer> adUzunlugu = musteri.andThen(String::length);

        assertEquals("Ayşe", musteri.apply(kitap));
        assertEquals(4, adUzunlugu.apply(kitap));
    }

    @Test
    @DisplayName("Consumer ve Supplier")
    void consumerVeSupplier() {
        // Hazırla
        Supplier<List<String>> yeniListe = ArrayList::new;
        List<String> kayitlar = yeniListe.get();
        Consumer<Siparis> kaydet = s -> kayitlar.add(s.musteri());
        // Çalıştır
        kaydet.accept(kitap);
        kaydet.accept(telefon);
        // Doğrula
        assertEquals(List.of("Ayşe", "Mehmet"), kayitlar);
    }

    @Test
    @DisplayName("UnaryOperator ve BinaryOperator")
    void unaryVeBinaryOperator() {
        UnaryOperator<Urun> zam = u -> u.zamli(0.10);
        BinaryOperator<Double> topla = Double::sum;

        Urun kalem = new Urun("Kalem", 20);
        Urun zamliKalem = zam.apply(kalem);

        assertEquals(22.0, zamliKalem.fiyat(), 1e-9);
        assertEquals(20.0, kalem.fiyat(), 1e-9); // orijinal değişmedi
        assertEquals(42.0, topla.apply(20.0, 22.0), 1e-9);
    }

    @Test
    @DisplayName("Kurucu referansı: Urun::new")
    void kurucuReferansi() {
        BiFunction<String, Double, Urun> uret = Urun::new;

        assertEquals(new Urun("Defter", 35), uret.apply("Defter", 35.0));
    }

    @Test
    @DisplayName("Lambda etkin final (effectively final) değişkeni yakalar")
    void etkinFinalYakalama() {
        double esik = 500; // hiç yeniden atanmadığı için etkin final
        Predicate<Siparis> esikUstu = s -> s.tutar() > esik;

        assertTrue(esikUstu.test(telefon));
        assertFalse(esikUstu.test(kitap));
    }

    @Test
    @DisplayName("Tembel değerlendirme: limit(1) ilk eşleşmeden sonra durur")
    void tembelDegerlendirme() {
        // Hazırla
        List<Siparis> siparisler = List.of(kitap, telefon, kitap, kitap);
        AtomicInteger kontrolSayisi = new AtomicInteger();
        // Çalıştır
        List<String> sonuc = siparisler.stream()
                .filter(s -> {
                    kontrolSayisi.incrementAndGet();
                    return s.tutar() > 1000;
                })
                .map(Siparis::musteri)
                .limit(1)
                .toList();
        // Doğrula
        assertEquals(List.of("Mehmet"), sonuc);
        assertEquals(2, kontrolSayisi.get()); // 4 elemanın yalnızca ilk 2'si kontrol edildi
    }
}
