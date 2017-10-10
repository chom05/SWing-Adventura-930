package logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "*****************************************************\n" +
               " _____    _____              ____    _         ____  \n" +
               "|  __ \\  |_   _|     /\\     |  _ \\  | |       / __ \\ \n" +
               "| |  | |   | |      /  \\    | |_) | | |      | |  | |\n" +
               "| |  | |   | |     / /\\ \\   |  _ <  | |      | |  | |\n" +
               "| |__| |  _| |_   / ____ \\  | |_) | | |____  | |__| |\n" +
               "|_____/  |_____| /_/    \\_\\ |____/  |______|  \\____/ \n" + 
               "*****************************************************\n" +
               "Vítej Cizinče!\n" +
               "Je zlá doba, na zemský povrch se dostalo ZLO a předpovědi\n" +
               "o nadcházející apokalypse se zdají čím dal tím více pravdivé.\n" +
               "Tvým hrdinským úkolem Cizinče, je zabít Andariel, vládkyni,\n" +
               "která se ukryla v katakombách a odtamtud řidí a vysílá démony\n" +
               "na zem. Přeji ti hodně zdaru a štěstí na tvém putování\n" +
               "Napiš 'napoveda', pokud si nevíš rady, jak hrát dál.\n" +
               "Pokud nevíš kudy se vydat, neváhej se někoho zeptat :)\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Rychle od démonů, zpátky do reality :)  Ahoj.";
    }
    
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);

            if (herniPlan.hracVyhral()) {
                konecHry = true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám.\nPlatné příkazy jsou tyto: " + platnePrikazy.vratNazvyPrikazu();
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}
