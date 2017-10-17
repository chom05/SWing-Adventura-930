/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;

/**
 * Batoh umožňuje uchovávat posbírané věci v průběhu hraní
 * 
 * @author (Michal Chobola) 
 */
public class Batoh
{
    private static final int MAX_VECI = 6;
    private List<Vec> batoh;

    /**
     * Konstruktor
     */
    public Batoh(){
        batoh = new ArrayList<Vec>(); 
    }

    /**
     * Metoda pro vložení věci.
     * 
     * @param   Vec    vkládaná věc
     * @return  Vec    vrátí tu samou věc, pokud se ji podaří přidat do batohu.
     *                 Null, pokud ne.
     */
    public Vec vlozVec(Vec pridavana) {
        if (jeMisto() && !obsahujeVec(pridavana.getNazev())) {
            batoh.add(pridavana);
            return pridavana;
        }
        return null;
    }

    /**
     * Zjišťuje, zda je v batohu ještě místo.
     * 
     * @return  true   pokud místo je.
     *          false  pokud místo už není
     */
    public boolean jeMisto() {
        if (batoh.size() < MAX_VECI) {
            return true;   
        }        
        return false;
    }

    /**
     * Zjišťuje, zda je věc v batohu.
     *  
     * @param   hledana    hledaná vec
     * @return  true       pokud se věc v batohu nachází, jinak false
     */
    public boolean obsahujeVec(String hledana) {
        for (Vec aktualni: batoh) {
            if (aktualni.getNazev().equals(hledana)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vrací seznam věcí v batohu
     * 
     * @return   seznam     grafický výpis věcí v batohu
     */
    public String getVeci() {
        String seznam = "";
        for (Vec aktualni: batoh) {
            if (!seznam.equals("")) {
                //pro větší přehlednost dáme čárku
                seznam += ",";
            }
            seznam += " " + aktualni.getNazev();
        }
        return seznam;
    }

    /**
     * Vrací dlouhý seznam věcí v batohu
     * 
     * @return   seznam     grafický výpis věcí v batohu
     */
    public String getVeciDlouze() {
        String seznam = "";
        for (Vec aktualni: batoh) {
            if (!seznam.equals("")) {
                //pro větší přehlednost dáme čárku
                seznam += "\n";
            }
            seznam += "  " + aktualni.getNazev() + " - " + aktualni.getPopis();
        }
        return seznam;
    }

    /**
     * Metoda najde věc, na kterou chceme odkázat
     * 
     * @param   nazev      název věci, kterou chceme najít
     * @return  hledana    odkaz na nalezenou věc. Je null, pokud věc nebyla nalezena
     */
    public Vec getVec(String nazev) {
        Vec hledana = null;
        for (Vec aktualni: batoh) {
            if(aktualni.getNazev().equals(nazev)) {
                hledana = aktualni;
                break;
            }
        }
        return hledana;
    }

    /**
     * Odstrani veci z inventare
     * 
     * @param   mazana      odstraňovaná věc
     * @return  smazana     odstraněná věc. Je null, pokud věc nebyla v batohu nalezena
     */
    public Vec odeberVec (String mazana) {
        Vec smazana = null;
        for(Vec aktualni: batoh) {
            if(aktualni.getNazev().equals(mazana)) {
                smazana = aktualni;
                batoh.remove(aktualni);
                break;
            }
        }
        return smazana;
    }

    /**
     * Metoda vrací maximální počet věcí, které lze přidat do batohu.
     * 
     * @return  počet věcí
     */
    public int getMaxVeci() {
        return MAX_VECI;
    }

}
