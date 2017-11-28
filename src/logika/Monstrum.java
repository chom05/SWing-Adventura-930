/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
 package logika;

/*******************************************************************************
 * instance této třídy představuje jednotlivé monstra se kterými se můžete v
 * průběhu hraní potkat a bojovat
 *
 * @author    Michal Chobola
 */
public class Monstrum
{
    private String nazev;
    private String nazevObrazku;
    private String uvodniRec;
    private String koncovaRec;
    private String potreba1;
    private String potreba2;
    private Vec vecMonstra; // Věc, kterou má postava u sebe.
    private boolean zabito;
        
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     * 
     * @param   nazev   Název monstra
     * @param   uvodniRec   Prvotní reč monstra před bitvou
     * @param   koncovaRec   koncová řeč monstra po bitvě
     * @param   potreba1   Vec kterou musí hráč vlastnit v batohu pro poražení monstra 
     * @param   potreba2   Vec kterou musí hráč vlastnit v batohu pro poražení monstra
     * @param   vecMonstra   Věc kterou monstrum po umření zahodí
     */
    public Monstrum (String nazev, String nazevObrazku, String uvodniRec, String koncovaRec, String potreba1,
                     String potreba2, Vec vecMonstra) {
        this.nazev = nazev;
        this.uvodniRec = uvodniRec;
        this.koncovaRec = koncovaRec;
        this.potreba1 = potreba1;
        this.potreba2 = potreba2;
        this.vecMonstra = vecMonstra;
        this.nazevObrazku = nazevObrazku;
        zabito = false;
    }

    /**
     * Metoda vrátí jméno monstra.
     * 
     * @return  nazev   Jméno monstra
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Metoda vrátí název věci kterou je nutné vlastnit pro zabití monstra
     * 
     * @return  String  Název věcí
     */
    public String getPotreba1() {
        return potreba1;
    }
    
    /**
     * Metoda vrátí název věci kterou je nutné vlastnit pro zabití monstra
     * 
     * @return String - Název věci
     */
    public String getPotreba2() {
        return potreba2;
    }

    
    /**
     * getter pro prvotní reč monstra před bitvou
     * 
     * @return String - úvodní reč monstra
     */
    public String getUvodniRec() {
        return uvodniRec;
    }
    
    /**
     * getter pro řeč monstra po bitvě
     * 
     * @return String - reč po bitvě
     */
    public String getKoncovaRec() {
        return koncovaRec;
    }
    
    /**
     * Metoda vloží věc kterou monstrum po smrtí vyhodí
     *
     * @param vecMonstra - daná věc kterou pak vyhodí
     */
    public void vlozVec (Vec vecMonstra) {
        this.vecMonstra = vecMonstra;
    }
    
    /**
     * getter pro věc kterou monstrum po smrti vyhodí
     *
     * @return Vec - vrátí vybranou věc
     */
    public Vec getVec() {
        return this.vecMonstra;
    }
    
    /**
     * Metoda odstraní věc, kterou má monstrum u sebe
     * 
     */
    public void odstranVec() {
        this.vecMonstra = null;
    }
    /**
     * Metoda zjistí jestli je monstrum mrtvé, tedy jestli bylo zabito
     * 
     * @return boolean
     */
    public boolean isZabito(){
        return zabito;
    }
    
    /**
     * Metoda nastaví smrt Monstra (po bitvě)
     * 
     * @param zabit
     */
    public void setZabito(boolean zabit){
        zabito = zabit;
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
