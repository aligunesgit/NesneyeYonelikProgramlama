package nyp.m7.hayvan;

/** M7 - Yüzebilen canlılar. {@link Ucabilen} ile aynı imzalı bir {@code default} metodu vardır. */
public interface Yuzebilen {

    default String hareket() {
        return "yüzer";
    }
}
