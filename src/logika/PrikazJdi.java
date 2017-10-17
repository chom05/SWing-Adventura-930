/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Riha
 * @version    ZS 2016/2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan herniPlan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu\n"
                    + herniPlan.getAktualniProstor().popisVychodu();
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = herniPlan.getAktualniProstor().vratSousedniProstor(smer);
        // if(sousedniProstor == null) return "sousedniProstor je Null = " + herniPlan.getAktualniProstor().getNazev();
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if(smer.equals("tristram") && herniPlan.getBatoh().obsahujeVec("magický_kámen_moci")){
                sousedniProstor.setZamcena(false);
            }
            if(sousedniProstor.isZamcena()){
                return "Tam bych ještě nechodil, tam už je to jiný level kamaráde, jdi radši postupně";
            }
            herniPlan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
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

}
