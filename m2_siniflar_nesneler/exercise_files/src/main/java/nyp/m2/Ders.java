package nyp.m2;

/**
 * M2 - Öğrenci nesnelerine referans tutan bir ders.
 *
 * <p>Kayıtlı öğrenciler bir dizide saklanır. Dizi {@code Ogrenci} nesnelerinin kendisini değil,
 * onlara referansları tutar: dersin içindeki öğrenci ile dışarıdaki öğrenci aynı nesnedir.
 */
public class Ders {

    private String kod;
    private String ad;
    private int kredi;
    private Ogrenci[] kayitlilar;
    private int kayitliSayisi;   // atanmadı: varsayılan değer 0

    /** Kodu, adı, kredisi ve kontenjanı verilen dersi oluşturur. */
    public Ders(String kod, String ad, int kredi, int kontenjan) {
        this.kod = kod;
        this.ad = ad;
        this.kredi = kredi;
        this.kayitlilar = new Ogrenci[kontenjan];
    }

    /** Varsayılan değerlerle ders: 3 kredi, 40 kişilik kontenjan. */
    public Ders(String kod, String ad) {
        this(kod, ad, 3, 40);
    }

    /**
     * Öğrenciyi derse kaydeder. Öğrenci null ise, kontenjan doluysa ya da öğrenci zaten kayıtlıysa
     * kayıt yapılmaz ve false döner.
     */
    public boolean kayitEt(Ogrenci ogrenci) {
        if (ogrenci == null || kayitliSayisi == kayitlilar.length || kayitliMi(ogrenci)) {
            return false;
        }
        kayitlilar[kayitliSayisi] = ogrenci;
        kayitliSayisi = kayitliSayisi + 1;
        return true;
    }

    /** Aynı öğrenci nesnesi kayıtlıysa true döndürür. Karşılaştırma {@code ==} ile, yani kimliğe göre. */
    public boolean kayitliMi(Ogrenci ogrenci) {
        for (int i = 0; i < kayitliSayisi; i++) {
            if (kayitlilar[i] == ogrenci) {
                return true;
            }
        }
        return false;
    }

    /** {@code sira} numaralı kayıtlı öğrenciyi döndürür; geçersiz sırada null döner. */
    public Ogrenci getOgrenci(int sira) {
        if (sira < 0 || sira >= kayitliSayisi) {
            return null;
        }
        return kayitlilar[sira];
    }

    public int getKayitliSayisi() {
        return kayitliSayisi;
    }

    public int getKontenjan() {
        return kayitlilar.length;
    }

    public String getKod() {
        return kod;
    }

    public String getAd() {
        return ad;
    }

    public int getKredi() {
        return kredi;
    }

    @Override
    public String toString() {
        return kod + " " + ad + " (" + kredi + " kredi, " + kayitliSayisi + "/" + kayitlilar.length + ")";
    }
}
