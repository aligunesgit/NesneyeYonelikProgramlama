package nyp.m11.sekil;

/**
 * M11 - Liskov yerine geçme ilkesi (LSP) düzeltmesi: {@link Kare} ve {@link Dikdortgen} birbirinden
 * türemez; ikisi de alanı olan birer {@code Sekil}dir.
 */
public sealed interface Sekil permits Dikdortgen, Kare {

    double alan();
}
