package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testovací třída pro test třídy Batoh
 *
 * @author  Michal Chobola
 */
public class BatohTest
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
     * Testuje, zda do batohu nelze přidat víc jak maximální počet předmětů.
     */
    @Test
    public void testPridatVicJakMaximumPredmetu()
    {
        Batoh batoh2 = new Batoh();
        Vec vec0 = new Vec("vec1", "popis veci", true);
        Vec vec1 = new Vec("vec1", "popis veci", true);
        Vec vec2 = new Vec("vec2", "popis veci", true);
        Vec vec3 = new Vec("vec3", "popis veci", true);
        Vec vec4 = new Vec("vec4", "popis veci", true);
        Vec vec5 = new Vec("vec5", "popis veci", true);
        Vec vec6 = new Vec("vec6", "popis veci", true);
        Vec vec7 = batoh2.vlozVec(vec1);
        assertEquals(vec7, vec1);
        Vec vec8 = batoh2.vlozVec(vec2);
        assertEquals(vec8, vec2);
        Vec vec9 = batoh2.vlozVec(vec3);
        assertEquals(vec9, vec3);
        Vec vec10 = batoh2.vlozVec(vec4);
        assertEquals(vec10, vec4);
        Vec vec11 = batoh2.vlozVec(vec5);
        assertEquals(vec11, vec5);
        Vec vec12 = batoh2.vlozVec(vec6);
        assertEquals(vec12, vec6);
        assertNull(batoh2.vlozVec(vec0));
    }

    /**
     * Testuje úspěšné vložení věci do batohu
     */
    @Test
    public void testPridejVec()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("vec1", "popis veci", true);
        assertEquals(vec1, batoh1.vlozVec(vec1));
        assertEquals(true, batoh1.obsahujeVec("vec1"));
    }

    /**
     * Testuje úspěšné smazání věci z batohu
     */
    @Test
    public void testZahodVec()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("vec1", "popis veci", true);
        batoh1.vlozVec(vec1);
        assertEquals(vec1, batoh1.odeberVec("vec1"));
        assertEquals("", batoh1.getVeci());
    }
}
