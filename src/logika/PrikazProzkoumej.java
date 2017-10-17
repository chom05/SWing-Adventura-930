/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazProzkoumej implementují pro hru příkaz Prozkoumej.
 * Tento příkaz prozkoumá a tedy vypíše popis pro jednotlivé věci ať už v Batohu nebo v prostoru
 *
 * @author    Michal Chobola
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    
    private HerniPlan hPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy PrikazProzkoumej
     *  
     *   @param hra       hra, která řídí celkový průběh 
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "prozkoumej". Prohledá určitou věc v daném prostoru nebo batohu hráče.
     *  Pokud věc najde, tak vypíše její popis. Pokud nenajde, vypíše chybovou hlášku
     *  
     *  @param parametry    -jako  parametr obsahuje jméno prohledávané věci
     *  @return             zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám prozkoumat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazevVeci);
        Vec vecBatoh = hPlan.getBatoh().getVec(nazevVeci);
        if (vec == null && vecBatoh == null) {
            return "Věc " + nazevVeci + " nevlastníš ani zde není";
        }
        hPlan.getAktualniProstor().vlozVec(vec);
        if(vec == null){
            return nazevVeci + ": " + vecBatoh.getPopis();
        }else{
            return nazevVeci + ": " + vec.getPopis();
        }
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

    //== Soukromé metody (instancí i třídy) ========================================

}
