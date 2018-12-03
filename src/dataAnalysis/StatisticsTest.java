package dataAnalysis;
import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest
{

    protected Statistics test;
    protected Statistics test1;
    protected Statistics test2;
    protected Statistics ztest;
    protected Statistics ztest1;
    protected Statistics ztest2;
    protected ZoneId zoneID = ZoneId.of("US/Central");
    protected GregorianCalendar gc = new GregorianCalendar(2018, 8, 1, 7, 0);
    protected GregorianCalendar gc2 = new GregorianCalendar(2018, 11, 10, 23, 30);
    protected String calendar = "2018-08-01'T'07:00:00 z";
    protected ZonedDateTime z1 = ZonedDateTime.of(2018, 8, 1, 7, 0, 0, 0, zoneID);
    protected ZonedDateTime z2 = ZonedDateTime.of(2018, 11, 10, 23, 30, 0, 0, zoneID);
    protected String zCalendar = "2018-08-01T07:00:00-05:00[US/Central]";

    @Before
    public void setUp() throws Exception
    {
        
        //gc.set(2018, 8, 1, 7, 0);
        //gc2.set(2018, 11, 10, 23, 30);
        

        test = new Statistics(55.5, "TAIR", gc, -1, StatsType.MINIMUM, test.getStid());
        test1 = new Statistics(-999.0, "NONE", gc, -1, StatsType.AVERAGE, test1.getStid());
        test2 = new Statistics(55.5, "TAIR", gc2, -1, StatsType.MINIMUM, test2.getStid());
        
        ztest = new Statistics(55.5, "TAIR", z1, -1, StatsType.MINIMUM, ztest.getStid());
        ztest1 = new Statistics(-999.0, "NONE", z1, -1, StatsType.AVERAGE, ztest1.getStid());
        ztest2 = new Statistics(55.5, "TAIR", z2, -1, StatsType.MINIMUM, ztest2.getStid());
        

    }

    @Test
    public void testToString()
    {
        assertEquals(test1.toString(), "[-999.0, NONE, AVERAGE]");
    }

    @Test
    public void testCreateDateFromString()
    {
        assertEquals(test1.createDateFromString(calendar), gc);
    }
    
    @Test
    public void testCreateZDateFromString()
    {
        assertEquals(ztest.createZDateFromString(zCalendar), z1);
    }

    @Test
    public void testCreateStringFromDate()
    {
        assertEquals(test.createStringFromDate(gc), "2018-08-01'T'07:00:00 z");
    }
    
    @Test
    public void testCreateStringFromZDate()
    {
        assertEquals(ztest.createStringFromDate(z1), "2018-08-01T07:00:00 CDT");
    }

    @Test
    public void testCreateStringFromDateNoBuff()
    {
        assertEquals(test2.createStringFromDate(gc2), "2018-11-10'T'23:30:00 z");
    }

    @Test
    public void testGetUTCDateTime()
    {
        assertEquals(test.getUTCDateTime(), calendar);
    }

    @Test
    public void testGetNumberOfReportingStations()
    {

        assertEquals(test1.getNumberOfReportingStations(), -1);
    }

    @Test
    public void testNewerThan()
    {
        assertTrue(test.newerThan(gc2));
    }

    @Test
    public void testOlderThan()
    {
        assertTrue(test2.olderThan(gc));
    }

    @Test
    public void testSameAs()
    {
        assertTrue(test2.sameAs(gc2));
    }

    @Test
    public void testNewerThanFalse()
    {
        assertFalse(test2.newerThan(gc));
    }

    @Test
    public void testOlderThanFalse()
    {
        assertFalse(test.olderThan(gc2));
    }

    @Test
    public void testSameAsFalse()
    {
        assertFalse(test.sameAs(gc2));
    }
    
    @Test
    public void testZNewerThan()
    {
        assertTrue(ztest1.newerThan(z2));
    }

    @Test
    public void testZOlderThan()
    {
        assertTrue(ztest2.olderThan(z1));
    }

    @Test
    public void testZSameAs()
    {
        assertTrue(ztest2.sameAs(z2));
    }

    @Test
    public void testZNewerThanFalse()
    {
        assertFalse(ztest2.newerThan(z1));
    }

    @Test
    public void testZOlderThanFalse()
    {
        assertFalse(ztest1.olderThan(z2));
    }

    @Test
    public void testZSameAsFalse()
    {
        assertFalse(ztest.sameAs(z2));
    }

}
