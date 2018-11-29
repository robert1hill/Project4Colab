package dataAnalysis;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A single observation of weather data with relevant statistics. 
 * Stores the data, the sample station ID, whether the data is a valid sample,
 * the date and time the sample was taken, the number of stations that reported
 * values, and the type of statistic.
 * It inherits from Observation and uses the interface DateTimeComparable.
 *
 * 
 * @author Robert Hill
 * @version 2018-10-02
 */
public class Statistics extends Observation implements DateTimeComparable
{
    /**
     * The standard format for the date/time of a string.
     */
    static final protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";

    
    
    protected DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    
    /**
     * The date/time as stored in a GregorianCalendar.
     */
    private GregorianCalendar utcDateTime;

    /**
     * The date/time as stored in a ZonedDateTime.
     */
    private ZonedDateTime zdtDateTime;
    
    /**
     * The number of stations that reported data.
     */
    private int numberOfReportingStations;

    /**
     * The type of statistic: minimum, maximum, average, or total.
     */
    protected StatsType statType;

    /**
     * Constructor for Statistics. Takes in information on the observation and
     * relavent statistical info as arguments. This specific constructor converts a 
     * String with the date/time data into a GregorianCalendar.
     * 
     * @param value
     *            a double of the value of the given Statistics (either Temperature
     *            or Radiation)
     * @param stid
     *            a String of the stid of the given Statistics (showing the
     *            location of the Observation)
     * @param numberOfValidStations
     *            an int that represents the number of stations that reported valid data.
     * @param inStatType
     *            the type of Statistic this data point represents. Can be the average, minimum,
     *            maximum, or total.
     *
     */
    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
        zdtDateTime = dateTime;

    }

    /**
     * Constructor for Statistics. Takes in information on the observation and
     * relavent statistical info as arguments. This specific constructor takes in a GregorianCalendar.
     * 
     * @param value
     *            a double of the value of the given Statistics (either Temperature
     *            or Radiation)
     * @param stid
     *            a String of the stid of the given Statistics (showing the
     *            location of the Observation)
     * @param numberOfValidStations
     *            an int that represents the number of stations that reported valid data.
     * @param inStatType
     *            the type of Statistic this data point represents. Can be the average, minimum,
     *            maximum, or total.
     *
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
        //utcDateTime.setTimeZone(TimeZone.getTimeZone("UTC-05:00"));
        utcDateTime = dateTime;
    }

    /**
     * Takes in a String of date/time data and converts it to a
     * GregorianCalendar.
     * 
     * @param dateTimeStr a string of the date/time
     * 
     * @return a GregorianCalendar with the corresponding date/time
     * data of the input String.
     *      
     */
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {

        //parsing the input string by each of various delimeters
        int arraySize = 5;
        String[] parsedLine1 = new String[arraySize];

        //parsing by "-"
        parsedLine1 = dateTimeStr.split("-");
        int year = Integer.parseInt(parsedLine1[0]);
        int month = Integer.parseInt(parsedLine1[1]);

      //parsing by "'"
        String[] parsedLine2 = new String[arraySize];
        parsedLine2 = parsedLine1[2].split("'");
        int day = Integer.parseInt(parsedLine2[0]);

      //parsing by ":"
        String[] parsedLine3 = new String[arraySize];
        parsedLine3 = parsedLine2[2].split(":");
        int hour = Integer.parseInt(parsedLine3[0]);
        int minute = Integer.parseInt(parsedLine3[1]);

        //setting a new GregorianCalendar with the data from
        //the input string
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, minute, 0);

        return gc;
    }
    
    
    /**
     * Takes in a String of date/time data and converts it to a
     * ZonedDateTime.
     * 
     * @param dateTimeStr a string of the date/time
     * @return a ZonedDateTime with the corresponding date/time
     * data of the input String.
     *      
     */
    public ZonedDateTime createZDateFromString(String dateTimeStr)
    {

      //parsing the input string by each of various delimeters
        int arraySize = 5;
        String[] parsedLine1 = new String[arraySize];

        //parsing by "-"
        parsedLine1 = dateTimeStr.split("-");
        int year = Integer.parseInt(parsedLine1[0]);
        int month = Integer.parseInt(parsedLine1[1]);

      //parsing by "T"
        String[] parsedLine2 = new String[arraySize];
        parsedLine2 = parsedLine1[2].split("T");
        int day = Integer.parseInt(parsedLine2[0]);

      //parsing by ":"
        String[] parsedLine3 = new String[arraySize];
        parsedLine3 = parsedLine2[1].split(":");
        int hour = Integer.parseInt(parsedLine3[0]);
        int minute = Integer.parseInt(parsedLine3[1]);

        //setting a new ZonedDateTime with the data from
        //the input string
        ZoneId zoneID = ZoneId.of("US/Central");
        ZonedDateTime zdt = ZonedDateTime.of(year, month, day, hour, minute, 0, 0, zoneID);

        return zdt;
        
    }

    /**
     * Takes in a GregorianCalendar and converts it to a String of date/time data.
     * 
     * @return a String file of time/date data matching the 
     *            DATE_TIME_FORMAT
     *      
     */
    public String createStringFromDate(GregorianCalendar calendar)
    {
        // This code puts zeros where needed in front of month, day, hour, and minute
        String monthBuffed;
        String dayBuffed;
        String hourBuffed;
        String minuteBuffed;

        if (utcDateTime.get(Calendar.MONTH) - 10 < 0)
        {
            monthBuffed = "0" + utcDateTime.get(Calendar.MONTH);
        } else
        {
            monthBuffed = "" + utcDateTime.get(Calendar.MONTH);
        }
        if (utcDateTime.get(Calendar.DAY_OF_MONTH) - 10 < 0)
        {
            dayBuffed = "0" + utcDateTime.get(Calendar.DAY_OF_MONTH);
        } else
        {
            dayBuffed = "" + utcDateTime.get(Calendar.DAY_OF_MONTH);
        }
        if (utcDateTime.get(Calendar.HOUR_OF_DAY) - 10 < 0)
        {
            hourBuffed = "0" + utcDateTime.get(Calendar.HOUR_OF_DAY);
        } else
        {
            hourBuffed = "" + utcDateTime.get(Calendar.HOUR_OF_DAY);
        }
        if (utcDateTime.get(Calendar.MINUTE) - 10 < 0)
        {
            minuteBuffed = "0" + utcDateTime.get(Calendar.MINUTE);
        } else
        {
            minuteBuffed = "" + utcDateTime.get(Calendar.MINUTE);
        }

        // building the String to return
        String stringBuild = "";
        stringBuild = stringBuild.concat("" + calendar.get(Calendar.YEAR));
        stringBuild = stringBuild.concat("-" + monthBuffed);
        stringBuild = stringBuild.concat("-" + dayBuffed);
        stringBuild = stringBuild.concat("'T'" + hourBuffed);
        stringBuild = stringBuild.concat(":" + minuteBuffed + ":00 z");

        return stringBuild;
    }
    
    /**
     * Takes in a ZonedDateTime and converts it to a String of date/time data.
     * 
     * @return a String file of time/date data matching the 
     *            DATE_TIME_FORMAT
     *      
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        
        //boom. How about that!
        return calendar.format(format);
        
        
    }

    /**
     * Overrides the default toString() method to format the output of MapData in
     * the desired format.
     * 
     * @return a String file of the following format:
     *              "[value, STID, statType]"
     *      
     */
    public String toString()
    {
        return "[" + super.getValue() + ", " + super.getStid() + ", " + statType + "]";
    }

    /**
     * @return the UTCDateTime
     */
    public String getUTCDateTime()
    {
        return createStringFromDate(utcDateTime);
    }

    /**
     * @return the numberOfReportingStations
     */
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is newer than the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is newer than the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean newerThan(GregorianCalendar inDateTimeUTC)
    {

        int result = inDateTimeUTC.compareTo(utcDateTime);
        return (result > 0);
        
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is older than the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is older than the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean olderThan(GregorianCalendar inDateTimeUTC)
    {
        int result = inDateTimeUTC.compareTo(utcDateTime);
        return (result < 0);
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is the same as the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is the same as the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean sameAs(GregorianCalendar inDateTimeUTC)
    {
        int result = inDateTimeUTC.compareTo(utcDateTime);
        return (result == 0);
        
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is newer than the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is newer than the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean newerThan(ZonedDateTime inDateTimeUTC)
    {
        int result = inDateTimeUTC.compareTo(zdtDateTime);
        return (result > 0);
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is older than the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is older than the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean olderThan(ZonedDateTime inDateTimeUTC)
    {
        int result = inDateTimeUTC.compareTo(zdtDateTime);
        return (result < 0);
    }

    /**
     * Overrides the corresponding method from the interface DateTimeComparable,
     * testing to see if the argument is the same as the date/time of the System.
     * 
     * @return a boolean value. If true, the argument is the same as the current
     * date/time of the System.
     *      
     */
    @Override
    public boolean sameAs(ZonedDateTime inDateTimeUTC)
    {
        int result = inDateTimeUTC.compareTo(zdtDateTime);
        return (result == 0);
    }

}
