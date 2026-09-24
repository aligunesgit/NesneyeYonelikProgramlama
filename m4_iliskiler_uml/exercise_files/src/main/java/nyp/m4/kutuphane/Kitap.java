package nyp.m4.kutuphane;

/**
 * M4 - Kütüphanedeki bir kitap.
 *
 * <p>{@link Uye} ile çift yönlü ilişkilendirmenin (association) bir ucudur: kitap, kendisini ödünç
 * alan üyeyi (0..1) bilir. Bu bağlantıyı yalnızca {@link Uye} sınıfı kurup koparır; bu yüzden
 * {@link #oduncAlaniAta(Uye)} paket içi (package-private) tanımlanmıştır.
 */
public class Kitap {

    private final String isbn;
    private final String baslik;
    private Uye oduncAlan;

    /** Verilen ISBN ve başlıkla rafta duran (ödünçte olmayan) bir kitap oluşturur. */
    public Kitap(String isbn, String baslik) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN boş olamaz");
        }
        if (baslik == null || baslik.isBlank()) {
            throw new IllegalArgumentException("Başlık boş olamaz");
        }
        this.isbn = isbn;
        this.baslik = baslik;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBaslik() {
        return baslik;
    }

    /** Kitabı ödünç alan üyeyi döndürür; kitap raftaysa {@code null}. */
    public Uye getOduncAlan() {
        return oduncAlan;
    }

    public boolean oduncteMi() {
        return oduncAlan != null;
    }

    /** Paket içi: yalnızca {@link Uye#oduncAl} ve {@link Uye#iadeEt} çağırır. */
    void oduncAlaniAta(Uye uye) {
        this.oduncAlan = uye;
    }
}
