/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

package logika;
/*******************************************************************************
 * Instance třídy PrikazMluv implementují pro hru příkaz Mluv.
 * 
 * @author  Michal Chobola  
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    private HerniPlan herniPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy PrikazMluv
     *  
     *  @param  plan - herní plán, kde dojde k interakci
     */
    public PrikazMluv(HerniPlan herniPlan){
        this.herniPlan = herniPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda pro vyvolání odpovědí jednotlivých postav, pokud jsou správně
     *  osloveny a jsou přítomny. Zároveň odkládá věci do prostor pokud jsou
     *  splněny úkoly dané od postavy
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  @return odpověď, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            if(!herniPlan.getAktualniProstor().isVProstoruNekdo()){
                return "Zde si nemáš s kým pokecat :( #alone \n";
            }
            return "Zadej s kým chceš mluvit\n" + herniPlan.getAktualniProstor().popisPostav();
        }

        String jmeno = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Postava postava = aktualniProstor.getPostava(jmeno);

        if (postava == null) {
            return "Nikdo takový tu není.\n";
        }
        else {
            if (postava.getMluveno() == 0 && postava.isUkolSplnen() && postava.getVec() != null) {
                aktualniProstor.vlozVec(postava.getVec());
            }
            return postava.getRec();
        }
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
