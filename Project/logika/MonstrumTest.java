package logika;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MonstrumTest.
 *
 * @author  Michal Chobola
 */
public class MonstrumTest
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
     * Testuje zda správně funguje vkládání věcí do monstra
     */
    @Test
    public void testVlozVecMonstrum()
    {
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Monstrum monstrum1 = new Monstrum("skret","uvodniRec-blabla", "koncovaRec - blabla",
                                          "meč","vesta",vec1);
        monstrum1.vlozVec(vec1);
        assertEquals(vec1, monstrum1.getVec());
    }
    
    /**
     * Testuje zda správně funguje odstaňování věcí z monstra
     */
    @Test
    public void testOdstranVecMonstrum()
    {
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Monstrum monstrum1 = new Monstrum("skret","uvodniRec-blabla", "koncovaRec - blabla",
                                          "meč","vesta",vec1);
        monstrum1.vlozVec(vec1);
        assertEquals(vec1, monstrum1.getVec());
        
        monstrum1.odstranVec();
        assertNull(monstrum1.getVec());
    }
}
