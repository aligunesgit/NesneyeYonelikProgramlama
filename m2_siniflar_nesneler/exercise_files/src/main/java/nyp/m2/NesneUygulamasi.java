package nyp.m2;

/** M2 - Nesne oluşturma, toString, aliasing ve değerle geçirmeyi konsolda gösterir. */
public class NesneUygulamasi {

    public static void main(String[] args) {
        Ogrenci ayse = new Ogrenci("2024001", "Ayşe Yılmaz");
        Ogrenci mehmet = new Ogrenci("2023017", "Mehmet Kaya", 2);
        System.out.println(ayse);             // toString() otomatik çağrılır

        Ders nyp = new Ders("BLM102", "Nesneye Yönelik Programlama");
        nyp.kayitEt(ayse);
        nyp.kayitEt(mehmet);
        System.out.println(nyp);

        // Aliasing: iki referans, tek nesne.
        Ogrenci kopya = ayse;
        kopya.sinifAtla();
        System.out.println("ayse.getSinif() = " + ayse.getSinif());
        System.out.println("ayse == kopya : " + (ayse == kopya));

        // Değerle geçirme.
        int sayi = 5;
        ReferansOrnekleri.artir(sayi);
        Sayac sayac = new Sayac();
        ReferansOrnekleri.artir(sayac);
        System.out.println("sayi = " + sayi + ", " + sayac);

        // Paylaşılan nokta.
        Nokta kose = new Nokta(1, 1);
        Dikdortgen d1 = new Dikdortgen(kose, 2, 3);
        Dikdortgen d2 = new Dikdortgen(kose, 4, 5);
        d1.tasi(10, 0);
        System.out.println(d2);
    }
}
