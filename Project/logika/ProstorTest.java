package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Michal Chobola
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", false);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    /**
     * Testuje, zda jsou správně nastaveny věci v prostoru, jejich vkládání a odebírání
     */
    @Test
    public void testVeci()
    {
        Prostor prostor1 = new logika.Prostor(null, null, false);
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Vec vec2 = new logika.Vec("b", "popis b", false);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertSame(vec1, prostor1.odeberVec("a"));
        assertNull(prostor1.odeberVec("c"));
    }
    /**
     * Testuje, zda jsou správně nastaveny postavy v prostoru, jejich přidávání a zjištování
     * jestli v prostoru někdo je
     */
    @Test
    public void testPostavy(){
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", false);
        Postava postava1 = new Postava("lenka","uvodniRec-blabla", "koncovaRec - blabla", 
                                       "poUkoluRec-blabla", null);
        prostor1.vlozPostavu(postava1);
        assertEquals(true, prostor1.isVProstoruNekdo());
        assertSame(postava1, prostor1.getPostava("lenka"));
                                    
    }
    /**
     * Testuje, zda jsou správně nastaveny monstra a jejich přidávání
     */
    @Test
    public void testMonstrum(){
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", false);
        Monstrum monstrum1 = new Monstrum("skret","uvodniRec-blabla", "koncovaRec - blabla",
                                          "meč","vesta",vec1);
        prostor1.vlozMonstrum(monstrum1);
        assertSame(monstrum1, prostor1.getMonstrum("skret"));
                                    
    }

}
