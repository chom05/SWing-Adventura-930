package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testovací metoda pro třídu Věc
 *
 * @author Michal CHobola
 */
public class VecTest
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
     * Testuje třídu Věc, zjištování názvu, popisu a přenositelnosti
     */
    @Test
    public  void testVec() {
        Vec vec1 = new logika.Vec("a", "popis a", true);
        Vec vec2 = new logika.Vec("b", "popis b", false);
        
        assertSame("a", vec1.getNazev());
        assertSame("b", vec2.getNazev());
        
        assertSame("popis a", vec1.getPopis());
        assertSame("popis b", vec2.getPopis());
        
        assertEquals(true, vec1.isPrenositelna());
        assertEquals(false, vec2.isPrenositelna());
    }
}
