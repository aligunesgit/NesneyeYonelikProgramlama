package nyp.m7.hayvan;

/** M7 - Uçabilen canlılar. {@code default} metot içerir. */
public interface Ucabilen {

    default String hareket() {
        return "uçar";
    }
}
