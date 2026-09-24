package nyp.m8;

import java.util.ArrayList;
import java.util.List;

/**
 * M8 - Hesapları numarayla bulan ve aralarında havale yapan banka.
 *
 * <p>Hesaplar bir listede tutulur (koleksiyonların ayrıntısı M9'da).
 */
public class Banka {

    private final List<Hesap> hesaplar = new ArrayList<>();

    /**
     * Yeni hesap açar.
     *
     * @throws IllegalArgumentException bu numarada zaten bir hesap varsa
     */
    public Hesap hesapAc(String hesapNo, String sahip) {
        for (Hesap hesap : hesaplar) {
            if (hesap.getHesapNo().equals(hesapNo)) {
                throw new IllegalArgumentException("Bu numarada hesap zaten var: " + hesapNo);
            }
        }
        Hesap yeni = new Hesap(hesapNo, sahip);
        hesaplar.add(yeni);
        return yeni;
    }

    /**
     * Numarası verilen hesabı döndürür.
     *
     * @throws HesapBulunamadiException bu numarada hesap yoksa
     */
    public Hesap hesapBul(String hesapNo) throws HesapBulunamadiException {
        for (Hesap hesap : hesaplar) {
            if (hesap.getHesapNo().equals(hesapNo)) {
                return hesap;
            }
        }
        throw new HesapBulunamadiException(hesapNo);
    }

    /**
     * Kaynak hesaptan hedef hesaba para aktarır. Herhangi bir adımda hata olursa iki hesap da
     * değişmeden kalır: önce iki hesap da bulunur, sonra para çekilir, en son yatırılır.
     */
    public void havale(String kaynakNo, String hedefNo, double tutar)
            throws HesapBulunamadiException, YetersizBakiyeException {
        Hesap kaynak = hesapBul(kaynakNo);
        Hesap hedef = hesapBul(hedefNo);
        kaynak.paraCek(tutar);
        hedef.paraYatir(tutar);
    }

    public int hesapSayisi() {
        return hesaplar.size();
    }
}
