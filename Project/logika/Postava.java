/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * instance této třídy představuje jednotlivé postavy se kterými se můžete v
 * průběhu hraní potkat a komunikovat
 *
 * @author    Michal Chobola
 */
public class Postava
{
    private String jmeno;
    private String uvodniRec;
    private String koncovaRec;
    private String poUkoluRec;
    private int mluveno;    // Udává, kolikrát se s postavou mluvilo.
    private Vec vecPostavy; // Věc, kterou má postava u sebe.
    private Monstrum monstrumUkolu;
        
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     * 
     * @param   jmeno           Jméno postavy
     * @param   uvodniRec        Co postava řekne poprvé, co je na ní promluveno
     * @param   koncovaRec        Co postava řekne po úkolu
     * @param   poUkoluRec        Co postava řekne kdykoliv potom, co je s ní mluveno
     * @param   ukol              Monstrum které je potřeba zabít pro splnění úkolu
     */
    public Postava (String jmeno, String uvodniRec, String koncovaRec, String poUkoluRec, Monstrum ukol) {
        this.jmeno = jmeno;
        this.uvodniRec = uvodniRec;
        this.koncovaRec = koncovaRec;
        this.poUkoluRec = poUkoluRec;
        this.mluveno = 0;
        monstrumUkolu = ukol;
    }

    /**
     * Metoda vrátí jméno postavy.
     * 
     * @return  jmeno - Jméno postavy
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Metoda vrací dialog vedený s postavou. Dialog se liší, pokud
     * už bylo s postavou hovořeno před nebo po splnění úkolu.
     * 
     * @return  String - dialog s postavou
     */
    public String getRec() {
        if(isUkolSplnen()){
            if(mluveno == 0){
                mluveno++;
                return koncovaRec;
            }else{
                return poUkoluRec;
            }
            
        }else{
            return uvodniRec;
        }
        
    }
    
    /**
     * Zjistí jestli hráč splnil úkol, který mu postava zadala
     *
     * @return boolean - jestli je monstrum zabito
     */
    public boolean isUkolSplnen () {
        if(monstrumUkolu == null){
            return true;
        }else{
            return monstrumUkolu.isZabito();
        }
    }
    
    /**
     * Metoda vloží věc postavě. Každá postava může mít maximálně
     * jednu věc u sebe
     *
     * @param vecPostavy - věc, kterou chceme vložit
     */
    public void vlozVec (Vec vecPostavy) {
        this.vecPostavy = vecPostavy;
    }
    
    /**
     * Metoda vrací věc postavy. Každá postava může mít maximálně
     * jednu věc u sebe.
     *
     * @return vrátí vybranou věc
     */
    public Vec getVec() {
        return this.vecPostavy;
    }
    
    /**
     * Metoda odstraní věc, kterou má postava u sebe
     * 
     */
    public void odstranVec() {
        this.vecPostavy = null;
    }
    
    /**
     * Metoda vrací číslo, kolikrát se s postavou mluvilo.
     * 
     * @return mluveno - počet rozhovorů
     */
    public int getMluveno() {
        return this.mluveno;
    }

}
