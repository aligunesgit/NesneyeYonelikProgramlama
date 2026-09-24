package nyp.m10;

import java.util.List;

/** M10 - Örnek siparişler üzerinde lambda, metot referansı ve stream raporlarını çalıştırır. */
public class SiparisUygulamasi {

    public static void main(String[] args) {
        List<Siparis> siparisler = List.of(
                new Siparis("Ayşe", "Kitap", 120, SiparisDurumu.TESLIM_EDILDI),
                new Siparis("Mehmet", "Elektronik", 2500, SiparisDurumu.KARGODA),
                new Siparis("Ayşe", "Elektronik", 800, SiparisDurumu.TESLIM_EDILDI),
                new Siparis("Zeynep", "Giyim", 350, SiparisDurumu.BEKLIYOR),
                new Siparis("Mehmet", "Kitap", 90, SiparisDurumu.IPTAL),
                new Siparis("Can", "Giyim", 600, SiparisDurumu.TESLIM_EDILDI),
                new Siparis("Zeynep", "Kitap", 45, SiparisDurumu.TESLIM_EDILDI));

        // Davranışı parametre olarak geçmek: lambda ile filtre
        List<Siparis> kitaplar = SiparisSecici.filtrele(siparisler, s -> s.kategori().equals("Kitap"));
        System.out.println("Kitap siparişi sayısı: " + kitaplar.size());

        SiparisRaporu rapor = new SiparisRaporu(siparisler);
        System.out.println("Toplam ciro: " + rapor.toplamCiro());
        System.out.println("Kategoriye göre: " + rapor.kategoriyeGoreToplam());
        System.out.println("Müşteriler (" + rapor.musteriSayisi() + "): " + rapor.musteriListesi());
        rapor.enPahaliSiparis().ifPresent(s -> System.out.println("En pahalı: " + s));
        System.out.println("Durumlar: " + rapor.durumaGoreSayilar());
    }
}
