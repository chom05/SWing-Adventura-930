/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
 
package logika;

/*******************************************************************************
 * Instance třídy Vec představují věci s kterými s může hráč v průběhu hry setkat
 *
 * @author    Michal Chobola
 */
public class Vec {
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private String nazevObrazku;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *
     *  @param nazev - název věci
     *  @param popis - popis věci
     *  @param prenositelna - určuje přenositelnost věci
     */
    public Vec(String nazev, String popis, boolean prenositelna, String nazevObrazku) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.nazevObrazku = nazevObrazku;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda vrátí název věci.
     *
     * @return nazev - název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda vrátí popis věci
     *
     * @return popis - popis dané věci
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Zjistí jestli je věc přenositelná a může se dát do batohu
     *
     * @return boolean prenositelna
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * Vrátí název obrázku pro jeho zobrazení
     *
     * @return String nazevObrazku
     */
    public String getNazevObrazku() {
        return nazevObrazku;

    }
}
