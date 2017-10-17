/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

package logika;
/*******************************************************************************
 * Instance třídy PrikazBojuj implementují pro hru příkaz Mluv.
 * 
 * @author  Michal Chobola  
 */
public class PrikazBojuj implements IPrikaz
{
    private static final String NAZEV = "bojuj";
    private Hra hra;
    private HerniPlan herniPlan;
    private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy PrikazBojuj
     *  
     *  @param  plan  -herní plán, kde dojde k interakci
     */
    public PrikazBojuj(Hra hra)
    {
        this.hra = hra;
        this.herniPlan = hra.getHerniPlan();
        batoh = herniPlan.getBatoh();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro vyvolání souboje s mostrem, pokud jsou správně názvány
     *  a vyskytují se v prostoru.  Zároveň záleží na tom, jestli jsou
     *  splněny potřeby k poražení mostra, pokud nejsou, hráč prohral a
     *  hra zde končí
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  @return odpověď, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Zadej s kým chceš bojovat" + herniPlan.getAktualniProstor().popisMonster();
        }
        String bojRec = "";
        String nazev = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Monstrum monstrum = aktualniProstor.getMonstrum(nazev);
        
        if (monstrum == null) {
            return "Nikdo takový tu není.\n";
        }
        else {
            bojRec += monstrum.getUvodniRec();
            if(splnujePotreby(monstrum)){
                bojRec += " \n " + monstrum.getKoncovaRec();
                monstrum.setZabito(true);
                aktualniProstor.vlozVec(monstrum.getVec());
            }else{
                bojRec += "Nezvládl si zabít dané Monstrum, možná si moc spěchal a zapomněl\n"
                          + "ses dobře vybavit. Zde pro tebe hra končí";
                hra.setKonecHry(true);
            }
            
            return bojRec;
        }
    }
    /**
     * zjišťuje jestli hráč splňuje potřeby k tomu aby zabil monstrum
     * @param montrum - monstrum z kterého zjistím dané potřeby
     * @return boolean - true - splňuje potřeba / false - nesplňuje
     */
    private boolean splnujePotreby (Monstrum monstrum){
        if(batoh.obsahujeVec(monstrum.getPotreba1()) && batoh.obsahujeVec(monstrum.getPotreba2()) ){
            return true;
        }
        return false;
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
