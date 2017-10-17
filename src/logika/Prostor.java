/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version ZS 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private boolean bylJsemTady;
    private boolean zamcena;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Postava> seznamPostav;
    private List<Monstrum> seznamMonster;
    private Map<String, Vec> veci;
    private Prostor zavisly;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, boolean zamcena) {
        this.nazev = nazev;
        this.popis = popis;
        this.zamcena = zamcena;
        seznamPostav = new ArrayList<>();
        seznamMonster = new ArrayList<>();
        vychody = new HashSet<>();
        veci = new HashMap<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     *  Nastaví závislý Prostor, tedy prostor který se otevře po navštívení tohoto
     *  prostoru
     *
     * @param zavisly - Prostor který se otevře po navšívení tohoto
     *
     */
    public void setZavisly(Prostor zavisly) {
        this.zavisly = zavisly;
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object object) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == object) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(object instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) object;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací dlouhý popis prostoru
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        bylJsemTady = true;
        zavisly.setZamcena(false);

        return  popis + "\n"
        + popisVychodu() + "\n"
        + popisVeci() + "\n"
        + popisPostav() + "\n"
        + popisMonster() + "\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "VÝCHODY: hala ", ale pouze pokud seznam není prázdný.
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String popisVychodu() {
        String popis = "";
        int pocetVychodu = 0;
        if(!vychody.isEmpty()) {
            popis = "VÝCHODY:";
            for (Prostor aktualni: vychody) {
                if (!popis.equals("VÝCHODY:") && pocetVychodu == 5) {
                    popis += "\n \t";
                    pocetVychodu = 0;
                }
                if(!aktualni.isZamcena()){
                    popis += " " + aktualni.getNazev();
                    pocetVychodu++;
                    if(pocetVychodu >= 1){
                        //přidání čárky na konec slova
                        popis += ",";
                    }
                }
            }
        }

        return popis;
    }

    /**
     * Vrací textový řetězec, který popisuje viditelné předměty v místnosti, například:
     * "OBJEKTY: stul, vaza", ale pouze pokud seznam není prázdný.
     *
     * @return Popis předmětů - názvů
     */
    public String popisVeci() {
        String popis = "";
        if(!veci.isEmpty()) {
            popis = "OBJEKTY:";
            for (String aktualni: veci.keySet()) {
                if (!popis.equals("OBJEKTY:")) {
                    //pro větší přehlednost dáme čárku
                    popis += ",";
                }
                popis += " " + aktualni;
            }
        }
        return popis;
    }

    /**
     * Metoda zjistí jestli jsou nějaké věci v prostoru
     * 
     * @return boolean 
     */
    public boolean isVeciVProstoru(){
        return !veci.isEmpty();
    }

    /**
     * Vrací textový řetězec, který popisuje postavy v místnosti, například:
     * "POSTAVY: karel", ale pouze pokud seznam není prázdný.
     *
     * @return Popis postav - jmen
     */
    public String popisPostav() {
        String popis = "";
        if(!seznamPostav.isEmpty()) {
            popis = "POSTAVY:";
            for (Postava aktualni: seznamPostav) {
                if (!popis.equals("POSTAVY:")) {
                    //pro větší přehlednost dáme čárku
                    popis += ",";
                }
                popis += " " + aktualni.getJmeno();
            }
        }
        return popis;
    }

    /**
     * Metoda zjistí jestli jsou nějaké postavy v prostoru
     * @return boolean 
     */
    public boolean isVProstoruNekdo(){
        return !seznamPostav.isEmpty();
    }

    /**
     * Vrací textový řetězec, který popisuje monstra v místnosti, například:
     * "MONSTRA: skřet", ale pouze pokud seznam není prázdný.
     *
     * @return Popis monster
     */
    public String popisMonster() {
        int pocetZivych = 0;
        String popis = "";
        String monstra = "";
        if(!seznamMonster.isEmpty()) {
            for (Monstrum aktualni: seznamMonster) {
                if(!aktualni.isZabito()){
                    pocetZivych++;
                }
                popis += " " + aktualni.getNazev();
            }
            if(pocetZivych > 0){
                monstra = "MONSTRA:";
                monstra += popis;
                return monstra;
            }else{
                return monstra;
            }
        }
        return popis;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     *  Metoda vloží věc do prostoru
     *  @param vec která se vloží do prostoru
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     *  Metoda vloží věc do prostoru
     *  @param vec která se vloží do prostoru
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }

    /**
     * Vrací informaci o tom, je-li je prostor zamčen.
     *
     * @return true  -je zamčen
     */
    public boolean isZamcena() {
        return zamcena;
    }

    /**
     * Vloží postavu do prostoru
     *
     * @param vkladana -jméno postavy kterou chceme vložit
     * @return true    pokud se podaží vložit
     */
    public boolean vlozPostavu(Postava vkladana) {
        seznamPostav.add(vkladana);
        return true;
    }

    /**
     * Vrací postavu pokud se najde v místnosti podle jména.
     *
     * @param jmeno     -jméno postavy hledané v místnosti
     * @return hledana  -nalezená postava, příp. null, pokud postavu nenajde
     */
    public Postava getPostava(String jmeno) {
        Postava hledana = null;
        for (Postava aktualni: seznamPostav) {
            if (aktualni.getJmeno().equals(jmeno)) {
                hledana = aktualni;
                break;
            }
        }
        return hledana;
    }

    /**
     * Metoda vloží do Prostoru monstrum
     *
     * @param vkladane - monstrum
     */
    public void vlozMonstrum(Monstrum vkladane) {
        seznamMonster.add(vkladane);
    }

    /**
     * Najde hledáné monstrum v Prostoru a pošle ho zpátky
     * @param nazev - název monstra
     * @return hledane monstrum
     */
    public Monstrum getMonstrum(String nazev) {
        Monstrum hledane = null;
        for (Monstrum aktualni: seznamMonster) {
            if (aktualni.getNazev().equals(nazev)) {
                hledane = aktualni;
                break;
            }
        }
        return hledane;
    }

    /**
     * Nastavuje prostor na zamčený či odemčený
     *
     * @param zamcena - true pokud má být zamčen
     */
    public void setZamcena(boolean zamcena) {
        this.zamcena = zamcena;        
    }

    /**
     * Metoda vrací informaci o tom, zda hráč už v prostoru byl nebo ne
     * 
     * @return bylJsemTady    - true, pokud už tu byl jednou a víckrát,
     *                      false, pokud tu ještě nebyl
     */
    public boolean getBylJsemTady() {
        return this.bylJsemTady;
    }

    /**
     * Metoda nastavuje informaci o tom, zda hráč už v prostoru byl nebo ne
     * 
     * @return bylJsemTady  -  true, pokud už tu byl jednou a víckrát,
     *                      false, pokud tu ještě nebyl
     */
    public void setBylJsemTady(boolean hodnota) {
        bylJsemTady = hodnota;
    }

}
