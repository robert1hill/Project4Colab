package dataAnalysis;
import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractObservationTest
{

    @Test
    public void testIsValid()
    {
        Observation ob1 = new Observation(50.0, "STAY");
        assertTrue(ob1.isValid());
    }

}
