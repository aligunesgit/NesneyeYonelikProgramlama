package nyp.m7.sekil;

/**
 * M7 - Mühürlü (sealed) şekil hiyerarşisi (JEP 409).
 *
 * <p>Yalnızca {@code permits} listesindeki türler bu arayüzü uygulayabilir. Derleyici tüm alt türleri
 * bildiği için {@code switch} ifadesinde {@code default} gerekmez (eksiksizlik, exhaustiveness).
 */
public sealed interface Sekil permits Daire, Dikdortgen, Ucgen {
}
