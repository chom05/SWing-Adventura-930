package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author   Jarmila Pavlíčková
 * @version  ZS 2016/2017
 */
public class HraTest {
    private Hra hra;
    private HerniPlan herniPlan;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
        herniPlan = hra.getHerniPlan();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání několika příkazů testuje, zda hra končí
     * a v po každém jdi testuje jestli je hráč v  dané místnosti.
     * 
     */
    @Test
    public void testPrubehHryVyhra() {
        hra.zpracujPrikaz("vezmi meč");
        hra.zpracujPrikaz("vezmi vesta");
        hra.zpracujPrikaz("jdi doupe_zla");
        assertSame("doupe_zla", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj skřet");
        hra.zpracujPrikaz("vezmi zlato");
        
        assertEquals(false, hra.konecHry());
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("mluv akara");
        hra.zpracujPrikaz("vezmi meč_hrdinů");
        hra.zpracujPrikaz("jdi chladne_planiny");
        assertSame("chladne_planiny", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("mluv flavie");
        
        assertEquals(false, hra.konecHry());
        hra.zpracujPrikaz("bojuj temná_lučišnice");
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi pohrebiste");
        assertSame("pohrebiste", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj bloodRaven");
        hra.zpracujPrikaz("zahod meč");
        hra.zpracujPrikaz("zahod vesta");
        hra.zpracujPrikaz("vezmi brnění");
        
        assertEquals(false, hra.konecHry());
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("mluv kashya");
        hra.zpracujPrikaz("vezmi amulet_přesnosti");
        hra.zpracujPrikaz("batoh");
        hra.zpracujPrikaz("jdi krypta");
        assertSame("krypta", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj zombie");
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi kamenne_pole");
        assertSame("kamenne_pole", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj");
        hra.zpracujPrikaz("bojuj padly_demon_champion");
        hra.zpracujPrikaz("vezmi magický_kámen_moci");
        hra.zpracujPrikaz("jdi tristram");
        
        assertEquals(false, hra.konecHry());
        assertSame("tristram", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj griswold");
        hra.zpracujPrikaz("mluv deckard_cain");
        hra.zpracujPrikaz("batoh");
        hra.zpracujPrikaz("vezmi štít_hrdinů");
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi katedrala");
        
        assertEquals(false, hra.konecHry());
        assertSame("katedrala", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi tabor_tulaku");
        assertSame("tabor_tulaku", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("jdi katakomby");
        assertSame("katakomby", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj Andariel");
        hra.zpracujPrikaz("zahod zlato");
        assertEquals(false, hra.konecHry());
        hra.zpracujPrikaz("vezmi pohár_vítězství");
        assertEquals(true, hra.konecHry());

    }
    /***************************************************************************
     * Testuje průběh hry, po zavolání několika příkazů testuje, zda hra končí
     * a v po každém jdi testuje jestli je hráč v  dané místnosti.
     * 
     */
    @Test
    public void testPrubehHryProhra() {
        hra.zpracujPrikaz("vezmi meč");
        //hráč si nevezme vestu
        hra.zpracujPrikaz("jdi doupe_zla");
        assertEquals(false, hra.konecHry());
        assertSame("doupe_zla", herniPlan.getAktualniProstor().getNazev());
        hra.zpracujPrikaz("bojuj skřet");
        assertEquals(true, hra.konecHry());
    }
}
