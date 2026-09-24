package nyp.m12;

import nyp.m12.fabrika.Bildirim;
import nyp.m12.fabrika.BildirimFabrikasi;
import nyp.m12.fabrika.Kanal;
import nyp.m12.gozlemci.LogGozlemcisi;
import nyp.m12.gozlemci.Siparis;
import nyp.m12.gozlemci.SiparisDurumu;
import nyp.m12.insaci.Pizza;
import nyp.m12.strateji.AgirligaGoreKargo;
import nyp.m12.strateji.SabitUcretliKargo;
import nyp.m12.strateji.Sepet;
import nyp.m12.tekil.KargoKampanyasi;
import nyp.m12.tekil.Yapilandirma;

/** M12 - Beş kalıbı bir arada gösteren küçük bir sipariş senaryosu. */
public class TasarimKaliplariUygulamasi {

    public static void main(String[] args) {
        // Builder
        Pizza pizza = Pizza.insaci()
                .boyut(Pizza.Boyut.ORTA)
                .malzeme("mantar")
                .malzeme("zeytin")
                .ekstraPeynir()
                .olustur();
        System.out.println("[Builder]   " + pizza + " = " + pizza.fiyat() + " TL");

        // Strategy
        Sepet sepet = new Sepet(new SabitUcretliKargo(30));
        sepet.urunEkle("Pizza", pizza.fiyat(), 0.5);
        sepet.urunEkle("Ayran (6 adet)", 90, 2.0);
        System.out.println("[Strategy]  Sabit kargo: " + sepet.kargoUcreti() + " TL");
        sepet.setKargoStratejisi(new AgirligaGoreKargo(20, 10));
        System.out.println("[Strategy]  Ağırlığa göre: " + sepet.kargoUcreti() + " TL");

        // Singleton
        KargoKampanyasi kampanya = new KargoKampanyasi(Yapilandirma.ORNEK);
        System.out.println("[Singleton] Ücretsiz kargo mu? " + kampanya.ucretsizKargoMu(sepet.toplamTutar()));

        // Factory + Observer
        Bildirim sms = BildirimFabrikasi.olustur(Kanal.SMS, "05551234567");
        LogGozlemcisi log = new LogGozlemcisi();
        Siparis siparis = new Siparis("S-1001");
        siparis.aboneEkle(log);
        siparis.aboneEkle((s, eski, yeni) ->
                System.out.println("[Observer]  " + sms.gonder(s.getSiparisNo() + " artık " + yeni)));
        siparis.durumGuncelle(SiparisDurumu.HAZIRLANIYOR);
        siparis.durumGuncelle(SiparisDurumu.KARGODA);
        System.out.println("[Observer]  Log: " + log.getKayitlar());
    }
}
