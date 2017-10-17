/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
 
package logika;

/*******************************************************************************
 * Instance třídy PrikazZahod představují ...
 *
 * @author    Michal Chobola
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahod";
    
    private HerniPlan herniPlan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param  hPlan - herní plán, kde dojde k interakci
     */
    public PrikazZahod(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = hPlan.getBatoh();
    }
    
    
    /**
     * Tato metoda realizuje zahození věci z batohu
     * @return     zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "Nevím co mám zahodit \n " + "V batohu máš" + batoh.getVeci();
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = batoh.odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        herniPlan.getAktualniProstor().vlozVec(vec);
        
        return "věc '" + nazevVeci + "' jsi zahodil z batohu";
    }

     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
