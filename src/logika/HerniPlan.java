package logika;

import utils.Subject;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject {
    private Prostor aktualniProstor;
    private Batoh batoh;
    private boolean vyhra;
    private static final String mecHrdinuFinal = "meč_hrdinů";
    private static final String vestaFinal = "vesta";

    private List<Observer> listObserveru = new ArrayList<Observer>();
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

        this.batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory s popisy
        Prostor taborTulaku = new Prostor("tabor_tulaku",
                "Nacházíš se ve smutném a poloprázdném táboře bojovnic a tuláků.\n"
                        + "Po příchodu zákeřné Andariel se tento kraj proměnil v ohěň, smrt \n"
                        + "a pláč. Lidé co stihli utéct nemilosrdnému zlu stojí kolem tebe.\n"
                        + "Vítej v místě poslední naděje. \n", false, 20, 20);
        Prostor doupeZla = new Prostor("doupe_zla",
                "Jsi v nechutném a smradlavém doupěti zla \n"
                        + "Zvenku se jeví jako klasická jeskyně, avšak uvnitř se \n"
                        + "skrývá obrovské zlo! Horda nemrtvých a démonů", false, 20,157);
        Prostor chladnePlaniny = new Prostor("chladne_planiny",
                "Jsi na prastarých pláninách, kde si mnohé místní děti jako malé hrály na \n "
                        + "schovávanou, nyní se zde potulují obři a prokleté bojovnice\n"
                        + "z katedrály, měj se zde na velkém pozoru", true, 20, 293);
        Prostor pohrebiste = new Prostor("pohrebiste",
                "Jsi mezi kupou kostí a zničených náhrobků\n"
                        + "Kdysi klidné místo odpočinku pozůstalých, nyní znovu povstali jako netvorové,\n"
                        + "kterým zde vládne Blood Raven.Kdysi velmi dobrá bojovnice, která však nakonec \n"
                        + "zradila a připojila se (po zaslíbení velké moci) k temné síle", true, 77, 404);
        Prostor krypta = new Prostor("krypta",
                "Vstoupil si do krypty \n"
                        + "Zde pozůstalé v hrobech už nenajdeš, spíš na tebe zaútočí a budou se tě snažit sežrat. Byla to krypta králů,\n"
                        + "nyní to je krypta hrůzy a smrti.", true, 105, 258);
        Prostor kamennePole = new Prostor("kamenne_pole",
                "na poli plném kamennů, kde uprostřed se tyčí 6 obřích obelisků s tajemnými modře svítícími nápisy \n"
                        + "Zde pozůstalé v hrobech už nenajdeš, spíš na tebe zaútočí a budou se tě snažit sežrat. Byla to krypta králů,\n"
                        + "nyní to je krypta hrůzy a smrti.", true, 107, 147);
        Prostor tristram = new Prostor("tristram",
                "Ještě nedávno bylo považováno za jedno z nejznámnějších obchodních měst. Nyní je poseto mrtvolami \n "
                        + "a celé v plamenech. Zde najdeš jedině smrt a zlo, avšak měj odvahu bojovníku(z mrtvoly padá zlato, což je fajn :)", true, 131, 0 );
        Prostor katedrala = new Prostor("katedrala",
                "Vstupuješ do Velké Katedrály. Zde sídlili králové a nejlepší kouzelníci celého kraje. Je to magické místo,\n"
                        + "což se také pojí s tím, že zde nenajdeš typické zombie a démony ale o hodně podivuhodnější stvoření. \n "
                        + "Buď připraven na nejhorší, očekávej to nejlepší. Hodně štěstí bojovníku.", true, 252, 54);
        Prostor katakomby = new Prostor("katakomby",
                "Dostal si se přímo do komnat nádherné královny, která kdysi byla velmi oblíbená a milována.\n"
                        + "Do těchto míst se podíval malokterý muž :P Nyní tady avšak nejsi za radostí, nýbrž za pomstou, za zlobou. \n"
                        + "Zde už všechna sranda končí bojovníku\n"
                        + "Zabij tu proklatou Andariel!!", true, 252, 230);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        taborTulaku.setVychod(doupeZla);
        taborTulaku.setVychod(chladnePlaniny);
        taborTulaku.setVychod(pohrebiste);
        taborTulaku.setVychod(krypta);
        taborTulaku.setVychod(kamennePole);
        taborTulaku.setVychod(tristram);
        taborTulaku.setVychod(katedrala);
        taborTulaku.setVychod(katakomby);
        
        // z každého je možné se vrátit do tábora tuláků
        doupeZla.setVychod(taborTulaku);
        chladnePlaniny.setVychod(taborTulaku);
        pohrebiste.setVychod(taborTulaku);
        krypta.setVychod(taborTulaku);
        kamennePole.setVychod(taborTulaku);
        kamennePole.setVychod(tristram);
        tristram.setVychod(taborTulaku);
        katedrala.setVychod(taborTulaku);
        katakomby.setVychod(taborTulaku);
        
        //závislé prostory, tedy ty, které se otevřou po návštěve daného prostoru
        taborTulaku.setZavisly(doupeZla);
        doupeZla.setZavisly(chladnePlaniny);
        chladnePlaniny.setZavisly(pohrebiste);
        pohrebiste.setZavisly(krypta);
        krypta.setZavisly(kamennePole);
        kamennePole.setZavisly(tristram);
        tristram.setZavisly(katedrala);
        katedrala.setZavisly(katakomby);
        katakomby.setZavisly(taborTulaku);
        
        aktualniProstor = taborTulaku;  // hra začíná v táboře tuláků

        // vytvoříme několik věcí - PŘENOSITELNÉ
        Vec mec = new Vec("meč", "Základní meč, který se dostává po dokončení tréninku", true, "mec.jpg");
        Vec mecHrdinu = new Vec(mecHrdinuFinal, "Hrdinský meč kdysi ukovaným nejlepším kouzelníkem z Katedrály.\n"
        + "\tBuď pyšný na to, že ho zrovna ty můžeš mít. Tu starou trosku zahoď ze svého inventáře, \n"
        + "\taby si mohl používat tento meč hrdinů", true, "mec.jpg");
        Vec stitHrdinu = new Vec("štít_hrdinů",
        "Hrdinský magický štít s velkou mocí, jen opravdový hrdina může nosit tento štít, jsi opravdový hrdina?", true, "mec.jpg");
        Vec vesta = new Vec(vestaFinal, "Kožená vesnická vesta", true, "mec.jpg");
        Vec brneni = new Vec("brnění", "Karbonové brnění nejvyšší kvality, stvořené z nejlepších \n"
        + "\tmateriálů západních bájných krajů, kde jsou lidé pracovitější a vzrůstem menší", true, "mec.jpg");
        Vec zlato = new Vec("zlato", "třpitivé penízky",true, "mec.jpg");
        Vec amuletBloodRaven = new Vec("amulet_přesnosti",
        "Blyštivý amulet ze zlata s magickou schopností přidávání přesnosti do tvých ran", true, "mec.jpg");
        Vec roztrhanaVesta = new Vec("roztrhana_vesta","Pravá kůže a hodně děr, tohle by ti určitě slušelo :P", true, "mec.jpg");
        Vec magickyKamenMoci = new Vec("magický_kámen_moci", "Tento magický, pradávný kámen ti umožní vstup do starého a\n"
        + "zapomenutého města Tristram",true, "mec.jpg");
        Vec poharVitezstvi = new Vec("pohár_vítězství","Zasloužené Vítězství statečný bojovníku!\n",true, "mec.jpg");
        // vytvoříme několiv věcí - NEPŘENOSITELNÉ
        Vec zidle = new Vec("židle", "klasická židle", false, "mec.jpg");
        Vec lavicka = new Vec("lavička", "posaď se, ale neodnášej!", false, "mec.jpg");
        Vec kamen = new Vec("šutr", "zakrvácený klasický šutr, kterých se tady válí spousty, tenhle ale je obří!", false, "mec.jpg");
        Vec kosti = new Vec("kosti", "fujky! Starý smradlavý kosti", false, "mec.jpg");
        Vec knihovna = new Vec("knihovna", "Hmmm..tolik vědomostí a leží tady tak zbytečně!", false, "mec.jpg");
        Vec postel = new Vec("postel", "Tady muselo být uděláno tolik dětí!", false, "mec.jpg");
        // Nepřenositelné věci vložím přímo do prostorů
        taborTulaku.vlozVec(zidle);
        chladnePlaniny.vlozVec(lavicka);
        doupeZla.vlozVec(kamen);
        pohrebiste.vlozVec(kosti);
        kamennePole.vlozVec(kamen);
        katedrala.vlozVec(knihovna);
        katakomby.vlozVec(postel);        
                
        //Vytvořím monstra
        Monstrum skret = new Monstrum("skřet",
          "Další hloupě odvážný bojovník, který si myslí, že může zachránit tento umírající\n"
        + "svět. Zemřeš jako všichni před tebou!\n"
        + "*Drsný souboj na život a na smrt! Ani jeden z vás se nedá jen tak snadno!*", 
          "Au! Ty svině! Vezmi si tohle zlato a vypadni odsud!","meč",vestaFinal,zlato);
        Monstrum lucisnice = new Monstrum("temná_lučištnice",
          "Umřeš!\n" + "*Snaží se po tobě střílet, ale ty svou vytrénovanou obratností uhýbáš\n"
          + "šípům jako profík*", "*ugh*\n",mecHrdinuFinal,vestaFinal,zlato);
        Monstrum bloodRaven = new Monstrum("bloodRaven",
          "Celý tento svět jde do pekel! A já budu moct být u toho a budu se dívat \n"
        + "Jak se všichni smažíte! Zatímco já budu velet armádám! Jsi pro mě nic \n"
        + "zbabělče, tak pojď blíž ať můžu ukončit tvé trápení!\n"
        + "*Raven nejdřív vytáhne luk a zkouší po tobě střílet, ty ale uhýbáš (seš profík)\n"
        + "po chvilce její nepozornosti se přiřítíš a..*",
          "Prosím nezabíjej mě!...*fňuk*...chci žít! *zvuk kovu padajícího o zem*\n",mecHrdinuFinal,vestaFinal,brneni);
        Monstrum zombie = new Monstrum("zombie",
          "egggrrrhhhh\n", "*zvuk spadlých kostí*\n",mecHrdinuFinal,"amulet_přesnosti",roztrhanaVesta);
        Monstrum padlyDemonChampion = new Monstrum("padly_demon_champion",
          "Už mi o tobě vyprávěli, že tady chodíš okolo a zabíjíš co se ti zachce, tohle je teď náš rajon!\n"
        + "*Neotálíš, tasíš meč a jdeš se s ním utkat tváří v tvář (ikdyž jeho tvář jako tvář moc nevypadá..hh)*", 
          "Fááájn! Vítězství je tvoje! Tady máš magický kámen moci s kterým se dostaneš do Tristramu pro\n"
        + "toho dědulu, tam už ti snad Griswold domluví.\n",mecHrdinuFinal,"amulet_přesnosti",magickyKamenMoci);
        Monstrum griswold = new Monstrum("griswold",
          "Ghrrrr\n", "eghhhhhh\n",mecHrdinuFinal,"brnění",zlato);
        Monstrum andariel = new Monstrum("Andariel",
          "Tak se konečně setkáváme! Už se mi doslechlo o tvé výpravě za záchranu tohoto ohavného světa\n"
        + "a jsem ráda, že jsi dorazil abych se mohla tvé výpravě z plných úst vysmát *HAHAHA*\n"
        + "Tento svět je už dávno pěkně v prdeli, diablo už míří na tento svět daleko odsud\n"
        + "a ty s tím nic neuděláš! Můžeš mě zabít, ale jsem jen jedna z mnoha postaviček\n"
        + "této apokalypsy, ale nemysli si že se nechám snadno!\n"
        + "Tak pojď, ukážu ti co jsem zač!\n"
        + "\n"
        + "Následoval opravdu drsný a dlouhý boj...\n",
          "Této apokalypse nezabráníš *kuck* Zemřeš, jako všichni na této zemi!\n"
        + "Slyšíš mě?! Umřééééééš!!! *blééé* \n"
        + "*cink* \n",mecHrdinuFinal,"brnění",poharVitezstvi);
        
        //přiřadím je do daných prostorů
        doupeZla.vlozMonstrum(skret);
        chladnePlaniny.vlozMonstrum(lucisnice);
        kamennePole.vlozMonstrum(padlyDemonChampion);
        tristram.vlozMonstrum(griswold);
        pohrebiste.vlozMonstrum(bloodRaven);
        krypta.vlozMonstrum(zombie);
        katakomby.vlozMonstrum(andariel);
        
        
        // vytvoříme několik postav
        Postava akara = new Postava("akara",
          "Vítej cizinče, jsem Akara, vrchní kněžka sester Nevidomého oka, slyšela \n"
        + "jsem, že jsem že jsi velmi statečný bojovník a jdeš nás zachránit od zla \n"
        + "Hned ze začátku ti musím říct, že jsem ohledně toho dooost skeptická\n"
        + "Pár už takových tady bylo a od prvního setkání té doby jsem je neviděla\n"
        + "Avšak jestli si svým záměrem stále tak přesvědčen, tak tvůj první\n"
        + "úkol bude vyčistit doupě zla, z kterého se na nás hrnou hordy potvor\n"
        + "Jestli se ti tento úkol podaří splnit, obdaruji tě magickým mečem :)\n"
        + "Měj se fajn! A nezapomeň je od nás pozdravovat (až je budeš masakrovat :))",
        "Jsi náš hrdina! Všichni zde jsme ti neskonale zavázáni, zde máš svou odměnu \n"
        + "Hrdinský magický meč po padlém králi. Je to nejmocnější meč široko daleko! \n",
        "Ještě jednou ti moc děkujeme hrdino :) Snad ti meč slouží dobře :) \n", skret);
        Postava flavie = new Postava("flavie",
          "Dávej pozor cizinče, tady už to začíná být nebezpečné\n",
          "Dávej pozor cizinče, tady už to začíná být nebezpečné\n",
          "Dávej pozor cizinče, tady už to začíná být nebezpečné\n",null);
        Postava deckardCain = new Postava("deckard_cain",
          "Greetings! Krásný to den, že ano? Děkuji ti za záchranu. Zajmul mě ten obr \n"
        + "co k nám blíží. Griswold je velmi silný, dávej si na něj pozor!\n",
        "Ještě jednou ti mnohokrát děkuji za záchranu, zde máš ode mě jako dar\n"
        + "magický štít. Teď jdi dále statečný bojovníku, jdi a zabij Andariel! \n"
        + "Schovává se oslabená v bývalých Katakombách královny! \n",
        "Děkuji ti za záchranu, snad ti štít poslouží dobře. \n", null);
        Postava kashya = new Postava("kashya",
          "Ahoj Cizinče, jsem velitelkou zbylých lučištnic, kterým\n"
        + "se podařilo utéci z kláštera a vyhnout se moci Andariel, která \n"
        + "zasáhla a zkorumpovala mnohé z nich. Bohužel i jednu z mých nejbližších\n"
        + "kamarádek, která teď ovládá chladné planiny a okolí. Velí všemu zlu z \n"
        + "pohřebiště, kde má své útočiště, vydej se tím směrem a zabij ji! \n"
        + "Jestli tě bude žadonit o život, nevěř jí! Zlo ji prostoupilo celou! \n",
          "Ach, konečně je její bolest u konce, děkuji ti Cizinče, jsi vážně hrdina! \n"
        + "Zde máš na oplátku její náhrdelník, darovala jsem jí ho když mi byla ještě kamarádkou\n",
          "Děkuji Cizinče, jsem tvým dlužníkem :)\n", bloodRaven);
        Postava charsi = new Postava("charsie",
         "Ahoj Cizinče, prosímtě, pospěš a udělej dobrý skutek! Deckarde Cain, náš \n"
        + "milovaný přítel a zastánce našeho světa se ztratil. Je to velký čaroděj, \n"
        + "kterého nejspíš unesli při jeho bádání. On jako jediný předvídal tuto apokalypsu\n"
        + "a my hlupáci jsme mu nevěřili :( . Podle toho, co jsem se doslechl, tak ho \n"
        + "uvěznili do starého Tristramu, kam se bohužel nedostane, ale jen tak někdo. \n"
        + "Potřebuješ magický kámen moci aby ses dostal skrz obelisky portálem do Tristramu\n"
        + "Buď opatrný, Tristram není zrovna přátelské místo.\n",
          "Děkujeme ti mnohokrát! Rád slyším že starý dobrý Caine je v pořádku, děkujeme \n"
        + "ti za jeho záchranu! :) Zde máš od nás jako odměnu kupu zlata :) \n",
          "Ještě jednou ti mockrát děkujeme za Cainovu záchranu :)", griswold);
          
        // rozdáme věci daným postavám
        akara.vlozVec(mecHrdinu);
        charsi.vlozVec(zlato);
        deckardCain.vlozVec(stitHrdinu);
        kashya.vlozVec(amuletBloodRaven);
        
        //umístíme postavy do prostor
        taborTulaku.vlozPostavu(akara);
        taborTulaku.vlozPostavu(charsi);
        taborTulaku.vlozPostavu(kashya);
        chladnePlaniny.vlozPostavu(flavie);
        tristram.vlozPostavu(deckardCain);
        
        // umístíme věci do prostorů
        taborTulaku.vlozVec(mec);
        taborTulaku.vlozVec(vesta);
        
    }
    
    /**
     *  Metoda vrací batoh, který je během hry používán
     * 
     *  @return    batoh    batoh používaný ve hře
     */
    public Batoh getBatoh() {
        return this.batoh;
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *  @return     Prostor      aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *  @param      prostor     nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyObservers();
    }
    /**
     *  Metoda vrací hodnotu o tom, jestli hráč hru vyhrál nebo prohrál
     *
     *  @return boolean 
     */
    public boolean hracVyhral() {
        if(vyhra){
            return true;
        }
        return false;
    }
    
    /**
     * Nastavuje vítězství po sebrání předmětu pohár_vítězství, což se zjišťuje v příkazu Vezmi
     *
     * @return vyhra boolean
     */
    public void setVyhra(boolean hodnota){
        vyhra = hodnota;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
