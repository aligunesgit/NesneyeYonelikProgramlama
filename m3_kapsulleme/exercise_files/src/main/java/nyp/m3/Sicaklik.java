package nyp.m3;

/**
 * M3 - Değişmez (immutable) sınıf: oluşturulduktan sonra durumu hiç değişmez.
 *
 * <p>Sınıf {@code final} (alt sınıf türetilemez), alan {@code private final}, setter yok. "Değiştiren"
 * metotlar ({@link #artir(double)}) mevcut nesneyi değil, yeni bir nesne döndürür; tıpkı
 * {@code String.toUpperCase()} gibi.
 */
public final class Sicaklik {

    /** Mutlak sıfır, Celsius cinsinden. */
    public static final double MUTLAK_SIFIR = -273.15;

    private final double celsius;

    /** Celsius cinsinden sıcaklık. Mutlak sıfırın altındaki değerlerde IllegalArgumentException. */
    public Sicaklik(double celsius) {
        if (celsius < MUTLAK_SIFIR) {
            throw new IllegalArgumentException("Mutlak sıfırın altında sıcaklık olamaz: " + celsius);
        }
        this.celsius = celsius;
    }

    /** Fahrenheit değerinden sıcaklık oluşturan static fabrika metodu. */
    public static Sicaklik fahrenheittenOlustur(double fahrenheit) {
        return new Sicaklik((fahrenheit - 32) * 5 / 9);
    }

    /** Bu sıcaklığı değiştirmez; {@code derece} kadar farklı yeni bir Sicaklik döndürür. */
    public Sicaklik artir(double derece) {
        return new Sicaklik(celsius + derece);
    }

    public double getCelsius() {
        return celsius;
    }

    public double fahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    @Override
    public String toString() {
        return celsius + " °C";
    }
}
