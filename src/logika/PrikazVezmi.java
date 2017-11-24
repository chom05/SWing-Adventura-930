/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy PrikazVezmi implementuji pro příkaz Vezmi
 *
 * @author    Michal Chobola
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    private HerniPlan herniPlan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  
     *  @param  plan - herní plán, kde dojde k interakci
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
        this.batoh = hPlan.getBatoh();
    }
    /**
     *  Metoda pro sebrání věci z prostoru a vložení do Batohu. Navíc
     *  kontroluje jestli hráč nevzal výherní předmět
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  @return odpověď, kterou vypíše hra hráči
     */
    //== Nesoukromé metody (instancí i třídy) ======================================
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            if(!herniPlan.getAktualniProstor().isVeciVProstoru()){
                return "Zde není co sebrat\n";
            }
            return "Nevím co mám sebrat \n " + herniPlan.getAktualniProstor().popisVeci();
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        if((vec.getNazev()).equals("pohár_vítězství")){
            herniPlan.setVyhra(true);
            return "Blahopřeji ti! Tvé putování je zde u konce. Toto byla první část mé \n"
                   + "hry na motivy Diablo počítačové hry. Další část obsahuje další města\n"
                   + "s dalšími nechutnými monstry a těžkými úkoly, protože, jak řekla Andariel,\n"
                   + "Diablo je na cestě a nejde sám, bere kámoše! Je potřeba je jednoho po druhém\n"
                   + "posundávat :D\n"
                   + "Díky že sis zahrál tuto hru a měj se fajn :) \n";
        }
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            herniPlan.notifyObservers();
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
       
        if (!batoh.jeMisto()) {
               herniPlan.getAktualniProstor().vlozVec(vec);
               return "batoh je plný, něco je potřeba zahodit";
           }

        batoh.vlozVec(vec);
        herniPlan.notifyObservers();
        return "věc '" + nazevVeci + "' jsi uložil do batohu";
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
