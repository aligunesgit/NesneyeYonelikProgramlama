package nyp.m9;

import java.util.Comparator;

/** M9 - Öğrencileri ada göre alfabetik sıralayan, ayrı bir sınıf olarak yazılmış karşılaştırıcı. */
public class AdaGoreKarsilastirici implements Comparator<Ogrenci> {

    @Override
    public int compare(Ogrenci a, Ogrenci b) {
        return a.getAd().compareTo(b.getAd());
    }
}
