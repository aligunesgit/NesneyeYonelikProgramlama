package nyp.m12.insaci;

import java.util.ArrayList;
import java.util.List;

/**
 * M12 - Builder: çok sayıda isteğe bağlı parametresi olan, değişmez (immutable) bir pizza.
 *
 * <p>Nesne yalnızca {@link Insaci} üzerinden oluşturulur: {@code Pizza.insaci().boyut(...)...olustur()}.
 * Kurucu {@code private} olduğu için yarım kurulmuş bir pizza dışarıya hiç sızmaz.
 */
public final class Pizza {

    /** Pizza boyutu ve taban fiyatı (TL). */
    public enum Boyut {
        KUCUK(150),
        ORTA(200),
        BUYUK(260);

        private final double tabanFiyat;

        Boyut(double tabanFiyat) {
            this.tabanFiyat = tabanFiyat;
        }

        public double getTabanFiyat() {
            return tabanFiyat;
        }
    }

    public static final int AZAMI_MALZEME = 5;
    public static final double MALZEME_FIYATI = 15;
    public static final double EKSTRA_PEYNIR_FIYATI = 20;

    private final Boyut boyut;
    private final String hamur;
    private final List<String> malzemeler;
    private final boolean ekstraPeynir;

    private Pizza(Insaci insaci) {
        this.boyut = insaci.boyut;
        this.hamur = insaci.hamur;
        this.malzemeler = List.copyOf(insaci.malzemeler);
        this.ekstraPeynir = insaci.ekstraPeynir;
    }

    public static Insaci insaci() {
        return new Insaci();
    }

    public double fiyat() {
        return boyut.getTabanFiyat()
                + MALZEME_FIYATI * malzemeler.size()
                + (ekstraPeynir ? EKSTRA_PEYNIR_FIYATI : 0);
    }

    public Boyut getBoyut() {
        return boyut;
    }

    public String getHamur() {
        return hamur;
    }

    /** Değiştirilemez liste döndürür. */
    public List<String> getMalzemeler() {
        return malzemeler;
    }

    public boolean isEkstraPeynir() {
        return ekstraPeynir;
    }

    @Override
    public String toString() {
        return boyut + " " + hamur + " hamur " + malzemeler + (ekstraPeynir ? " + ekstra peynir" : "");
    }

    /** Pizza için inşacı (builder). Her metot {@code this} döndürür; çağrılar zincirlenebilir. */
    public static final class Insaci {

        private Boyut boyut;                      // zorunlu
        private String hamur = "ince";            // isteğe bağlı, varsayılanı var
        private final List<String> malzemeler = new ArrayList<>();
        private boolean ekstraPeynir;

        private Insaci() {
        }

        public Insaci boyut(Boyut boyut) {
            this.boyut = boyut;
            return this;
        }

        public Insaci hamur(String hamur) {
            if (hamur == null || hamur.isBlank()) {
                throw new IllegalArgumentException("Hamur boş olamaz");
            }
            this.hamur = hamur;
            return this;
        }

        public Insaci malzeme(String malzeme) {
            if (malzeme == null || malzeme.isBlank()) {
                throw new IllegalArgumentException("Malzeme boş olamaz");
            }
            malzemeler.add(malzeme);
            return this;
        }

        public Insaci ekstraPeynir() {
            this.ekstraPeynir = true;
            return this;
        }

        /**
         * Kuralları kontrol eder ve pizzayı oluşturur.
         *
         * @throws IllegalStateException boyut seçilmemişse ya da malzeme sayısı sınırı aşılmışsa
         */
        public Pizza olustur() {
            if (boyut == null) {
                throw new IllegalStateException("Boyut seçilmeden pizza oluşturulamaz");
            }
            if (malzemeler.size() > AZAMI_MALZEME) {
                throw new IllegalStateException("En fazla " + AZAMI_MALZEME + " malzeme seçilebilir");
            }
            return new Pizza(this);
        }
    }
}
