package dataAnalysis;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObservationTest
{

    protected Observation obTest;

    @Before
    public void init()
    {
        obTest = new Observation(55.0, "hello");

    }

    @Test
    public void testIsValid()
    {
        assertTrue("Fail", obTest.isValid());

    }

    @Test
    public void testIsValidFalse()
    {
        Observation obTest2 = new Observation(-999, "hello");
        assertFalse("Fail", obTest2.isValid());

    }

    @Test
    public void testGetValue()
    {
        assertEquals(55.0, obTest.getValue(), 0.01);

    }

    @Test
    public void testGetStid()
    {
        assertEquals("Fail", "hello", obTest.getStid());

    }

    @Test
    public void testToString()
    {
        assertEquals("Fail", "55.0 at hello", obTest.toString());
    }

}
