/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/**
 * Příkaz Batoh bude implementovat práci s Batohem v naší Adventuře
 * 
 * @author Michal Chobola
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan herniPlan;
    
     /***************************************************************************
     *  Konstruktor
     */
    public PrikazBatoh(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }
    
    /**
     *  Provádí příkaz "batoh". Vypíše všechny věci, které jsou v batohu i s popisem věcí.
     *
     *  @return     zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (herniPlan.getBatoh().getVeci().isEmpty()) {
            // pokud je batoh prázdný
            return "\nZatím jsem nic nesebrala.";
        }
        else {
            // pokud je v batohu jedna a více věcí.
            return "\nV batohu máš: \n" + herniPlan.getBatoh().getVeciDlouze();
        }
    }

    /**
     *  Metoda vrací název příkazu
     *  
     *  @ return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
