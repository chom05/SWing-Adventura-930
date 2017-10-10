package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test pro třídu postava
 *
 * @author  Michal Chobola
 */
public class PostavaTest
{
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Testuje, zda postava, pokud je oslovena před splněním úkol, po něm a kdykoliv potom oslovena, vrací pokaždé
     * jinou řeč
     */
    @Test
    public void testGetTriReci()
    {
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Monstrum monstrum1 = new Monstrum("skret","uvodniRec-blabla", "koncovaRec - blabla",
                                          "meč","vesta",vec1);
        Postava postava3 = new Postava("postava1", "ahoj", "čau", "nazdar", monstrum1);                                  
        assertEquals("ahoj", postava3.getRec());
        monstrum1.setZabito(true);
        assertEquals(true, postava3.isUkolSplnen());
        assertEquals("čau", postava3.getRec());
        assertEquals("nazdar", postava3.getRec());
        assertEquals("nazdar", postava3.getRec());
    }
    
    /**
     * Testuje zda správně funguje vkládání věcí do postavy
     */
    @Test
    public void testVlozVecPostave()
    {
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Postava postava1 = new Postava ("postava1", "", "", "", null);
        postava1.vlozVec(vec1);
        assertEquals(vec1, postava1.getVec());
    }
}
