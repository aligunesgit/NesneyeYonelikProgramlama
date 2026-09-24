package nyp.m12.strateji;

/**
 * M12 - Strategy kalıbı: kargo ücreti hesaplama stratejisi.
 *
 * <p>Tek soyut metodu olduğu için işlevsel arayüzdür (functional interface); yeni bir strateji ayrı
 * bir sınıf yerine lambda ile de yazılabilir (M10).
 */
@FunctionalInterface
public interface KargoStratejisi {

    /** Verilen toplam ağırlık (kg) ve sepet tutarı (TL) için kargo ücretini (TL) hesaplar. */
    double ucretHesapla(double agirlikKg, double sepetTutari);
}
