package dataAnalysis;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapDataTest
{

    protected MapData mdTest;
    protected MapData mdTestSec;

    

    @Before
    public void setUp() throws Exception
    {
        // This unit test checks MapData for the 08/01 data file
        mdTest = new MapData(2018, 8, 1, 7, 0, "data1");
        mdTestSec = new MapData(2018, 8, 30, 17, 45, "data1");

    }

    

    @Test
    public void testCreateFileName()
    {
        assertEquals("Fail", "201808010700.mdf", mdTest.createFileName(2018, 8, 1, 7, 0, "data"));
    }

    @Test
    public void testCreateFileNameNoBuff()
    {
        assertEquals("Fail", "201780107030.mdf", mdTest.createFileName(2017, 80, 10, 70, 30, "data"));
    }
    
    @Test
    public void testGetIndexOf()
    {
        Integer i = new Integer(5);
        assertEquals("Fail", i, mdTest.getIndexOf("TAIR"));
    }

    @Test
    public void testToString()
    {

        // creating the String output to test against
        StringBuffer testString = new StringBuffer(500);

        testString.append("========================================================" + '\n');
        testString.append("=== " + "2018" + "-08" + "-01");
        testString.append(" 07:00" + " ===" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[1.5m] = 21.7 C at MEDI" + '\n');
        testString.append("Minimum Air Temperature[1.5m] = 13.8 C at EVAX" + '\n');
        testString.append("Average Air Temperature[1.5m] = 18.0 C at Mesonet" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[9.0m] = 23.3 C at MARE" + '\n');
        testString.append("Minimum Air Temperature[9.0m] = 15.8 C at COOK" + '\n');
        testString.append("Average Air Temperature[9.0m] = 19.7 C at Mesonet" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Solar Radiation[1.5m] = 0.0 W/m^2 at ACME" + '\n');
        testString.append("Minimum Solar Radiation[1.5m] = 0.0 W/m^2 at ACME" + '\n');
        testString.append("Average Solar Radiation[1.5m] = 0.0 W/m^2 at Mesonet" + '\n');
        testString.append("========================================================");
        // testing the string output
        assertEquals("Fail", testString.toString(), mdTest.toString());
    }

    @Test
    public void testToStringNoBuff()
    {

        // creating the String output to test against
        StringBuffer testString = new StringBuffer(500);
        testString.append("========================================================" + '\n');
        testString.append("=== " + "2018" + "-08" + "-30");
        testString.append(" 17:45" + " ===" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[1.5m] = 36.5 C at HOOK" + '\n');
        testString.append("Minimum Air Temperature[1.5m] = 20.8 C at MIAM" + '\n');
        testString.append("Average Air Temperature[1.5m] = 32.4 C at Mesonet" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[9.0m] = 34.9 C at HOOK" + '\n');
        testString.append("Minimum Air Temperature[9.0m] = 20.7 C at MIAM" + '\n');
        testString.append("Average Air Temperature[9.0m] = 31.6 C at Mesonet" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP" + '\n');
        testString.append("Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM" + '\n');
        testString.append("Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet" + '\n');
        testString.append("========================================================");
        // testing the string output
        assertEquals("Fail", testString.toString(), mdTestSec.toString());
    }

    @Test
    public void testGetStatistics()
    {

        assertEquals("Fail", 13.8, mdTest.getStatistics(StatsType.MINIMUM, "TAIR").getValue(), 1.0);
        assertEquals("Fail", 21.7, mdTest.getStatistics(StatsType.MAXIMUM, "TAIR").getValue(), 1.0);
        assertEquals("Fail", 19.7, mdTest.getStatistics(StatsType.AVERAGE, "TA9M").getValue(), 1.0);
        assertEquals("Fail", 0.0, mdTest.getStatistics(StatsType.TOTAL, "SRAD").getValue(), 1.0);
    }


}
