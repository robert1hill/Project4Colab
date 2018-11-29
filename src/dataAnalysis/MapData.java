package dataAnalysis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * This class takes in a weather data file. It sorts the air temperature data
 * (1.5m and 9.0m above the ground) and solar radiation data into distinct
 * observations. Additionally, it calculates helpful statistics including
 * maximum, minimum, and average values for each data type. The class will print
 * these values.
 * 
 * @author Robert Hill
 * @version 2018-09-25
 */

public class MapData
{

    /**
     * HashMap of observations with their respective STID.
     */
    private HashMap<String, ArrayList<Observation>> dataCatalog;

    /**
     * EnumMap of the types of statistics with a treeMap of Statistics with their
     * corresponding STID
     */
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(
            StatsType.class);

    /**
     * A TreeMap of the STID code and their position in the map.
     */
    private TreeMap<String, Integer> paramPositions = new TreeMap<String, Integer>();

    /**
     * The minimum number of missing observations required to not calculate
     * statistics.
     */
    final static private int NUMBER_OF_MISSING_OBSERVATIONS = 10;

    /**
     * Number of sample stations in the data file.
     */
    private Integer numberOfStations = null;

    /**
     * A string name of the data collection group.
     */
    final static private String TA9M = "TA9M";

    /**
     * A string name of the data collection group.
     */
    final static private String TAIR = "TAIR";

    /**
     * A string name of the data collection group.
     */
    final static private String SRAD = "SRAD";

    /**
     * A string name of the data collection group.
     */
    final static private String STID = "STID";

    /**
     * A string name of the data collection group.
     */
    final static private String MESONET = "Mesonet";

    /**
     * String that is the name of the file to retrieve data from.
     */
    private String fileName;

    /**
     * the directory of the data files.
     */
    private String directory;

    /**
     * date/time data stored in a GregorianCalendar.
     */
    private GregorianCalendar utcDateTime;

    /**
     * The constructor. Takes in the data, time, and directory of the data file.
     * Calls parseFile() and the three methods that calculate statistics. Finally,
     * it uses the toString() method to print these statistics to the Console
     * 
     * @param year
     *            The year of the data one wants to analyze.
     * @param month
     *            The month of the data one wants to analyze.
     * @param day
     *            The day of the data one wants to analyze.
     * @param hour
     *            The hour of the data one wants to analyze.
     * @param minute
     *            The minute of the data one wants to analyze.
     * @param directory
     *            The directory where the data files are stored.
     * @throws IOException
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory) throws IOException
    {
        // initializing the instance variables with the values of the argument
        fileName = createFileName(year, month, day, hour, minute, directory);
        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        this.directory = directory;
        
        prepareDataCatalog();

        // calls the following method to parse the desired file within the directory
        parseFile();

        calculateAllStatistics();

        // prints the statistics
        // System.out.println(toString());

    }

    /**
     * Retrieves the directory and uses the fileName to open a data file. Then, it
     * parses the data file into 3 ArrayLists of observations based off data type.
     * It also calls the parseParamHeader method to find the locations of the
     * desired data types.
     * 
     * @throws IOException
     */
    public void parseFile() throws IOException
    {
        // opening the file to be read
        BufferedReader br = new BufferedReader(new FileReader(directory + "/" + fileName));

        // cycling through the header
        br.readLine();
        br.readLine();

        parseParamHeader(br.readLine());

        // taking in a string input and parsing it. This primes the read for the
        // upcoming while loop
        String line = br.readLine();
        String[] parsedLine = new String[100];

        // creating arraylists that will be stored eventually in dataCatalog
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();
        ArrayList<Observation> sradData = new ArrayList<Observation>();

        // calling the index location of important stids.
        int tairPosition = getIndexOf(TAIR);
        int ta9mPosition = getIndexOf(TA9M);
        int sradPosition = getIndexOf(SRAD);
        int stidPosition = getIndexOf(STID);
        
        

        // storing parsed data into arrays while cycling through the file
        numberOfStations = 0;
        // this loop stops when the file has ended
        while (line != null && numberOfStations < 1000)
        {
            parsedLine = line.split("\\s+");
            tairData.add(new Observation(Double.parseDouble(parsedLine[tairPosition]), parsedLine[stidPosition]));
            ta9mData.add(new Observation(Double.parseDouble(parsedLine[ta9mPosition]), parsedLine[stidPosition]));
            sradData.add(new Observation(Double.parseDouble(parsedLine[sradPosition]), parsedLine[stidPosition]));
            
            ++numberOfStations;
            line = br.readLine();

        }

        // filling data catalog
        dataCatalog.put(TAIR, tairData);
        dataCatalog.put(TA9M, ta9mData);
        dataCatalog.put(SRAD, sradData);

        br.close();

    }
    
    /**
     * Returns the integer value that the given STID is stored.
     * 
     * @param inParamStr String of the STID.
     * @return Integer of the location the STID is stored
     * 
     */
    public Integer getIndexOf(String inParamStr)
    {
        
        return paramPositions.get(inParamStr);
        
    }
    
    /**
     * Gets the dataCatalog ready by instantiating it.
     */
    private void prepareDataCatalog() 
    {
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
    }

    /**
     * Takes in the date and time and returns a created a file name based on this
     * data.
     * 
     * @param year
     *            The year of the data one wants to analyze.
     * @param month
     *            The month of the data one wants to analyze.
     * @param day
     *            The day of the data one wants to analyze.
     * @param hour
     *            The hour of the data one wants to analyze.
     * @param minute
     *            The minute of the data one wants to analyze.
     * @param directory
     *            The directory where the data files are stored.
     * 
     * @return a String file name of the format:
     *         "(year)(month)(day)(hour)(minute).mdf". e.g.: "201806150900.mdf"
     */
    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {

        // these lines of code determine if month, day, hour, or minute need a "0" to be
        // added to the front of
        // their value to make the correct file name
        String monthBuffed;
        String dayBuffed;
        String hourBuffed;
        String minuteBuffed;
        if (month - 10 < 0)
        {
            monthBuffed = "0" + month;
        } else
        {
            monthBuffed = "" + month;
        }
        if (day - 10 < 0)
        {
            dayBuffed = "0" + day;
        } else
        {
            dayBuffed = "" + day;
        }
        if (hour - 10 < 0)
        {
            hourBuffed = "0" + hour;
        } else
        {
            hourBuffed = "" + hour;
        }
        if (minute - 10 < 0)
        {
            minuteBuffed = "0" + minute;
        } else
        {
            minuteBuffed = "" + minute;
        }

        // returns the data file name
        return year + monthBuffed + dayBuffed + hourBuffed + minuteBuffed + ".mdf";
    }

    /**
     * Takes in input String of column headers and parses them, searching and
     * storing the locations of the desired data.
     * 
     * @param inParamStr
     *            A String that includes all the column headers from the data file.
     * 
     * 
     */
    private void parseParamHeader(String inParamStr)
    {

        // creating a string array to put the parsed header into. Arbitrarily
        // setting 250 to be the size.
        int arraySize = 100;
        String[] parsedLine = new String[arraySize];

        // parsing the string based on spaces
        parsedLine = inParamStr.split("\\s+");
        //System.out.println(parsedLine[1]);

        // checking to see if each parsed piece is one of the four desired data types
        // if it is, it is stored in the corresponding position variable
        for (int i = 1; i < parsedLine.length; ++i)
        {
            if (parsedLine[i].equalsIgnoreCase(STID))
            {
                paramPositions.put(STID, i);
            } else if (parsedLine[i].equalsIgnoreCase("tair"))
            {
                paramPositions.put(TAIR, i);
            } else if (parsedLine[i].equalsIgnoreCase("ta9m"))
            {
                paramPositions.put(TA9M, i);
            } else if (parsedLine[i].equalsIgnoreCase("srad"))
            {
                paramPositions.put(SRAD, i);
            }

        }
    }

    /**
     * Straight up calculates statistics for the data read in a file. The program
     * analyzes the observations and creates Statistics of the min, max, and
     * average.
     * 
     */
    private void calculateAllStatistics()
    {

        // counts the occurances when data was missing and tracks
        // the location in the array of the last obervation that was missing data
        TreeMap<String, Statistics> tmMin = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> tmMax = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> tmAve = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> tmTot = new TreeMap<String, Statistics>();

        //System.out.print(dataCatalog.get(TAIR).size());
        int countMissingData = 0;
        for (int i = 0; i < dataCatalog.get(TAIR).size(); ++i)
        {
            if (!dataCatalog.get(TAIR).get(i).isValid())
            {
                ++countMissingData;

            }
        }

        // checks to see if the amount of missing data exceeds a preset limit. If it
        // does, all statistics are assigned -999 values.
        if (countMissingData >= NUMBER_OF_MISSING_OBSERVATIONS)
        {

           // TreeMap<String, Statistics> tm = new TreeMap<String, Statistics>();
            tmMin.put(TAIR, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MINIMUM));
            //statistics.put(StatsType.MINIMUM, tmMin);
           
            tmMax.put(TAIR, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MAXIMUM));
            //statistics.put(StatsType.MAXIMUM, tmMax);
            
            tmAve.put(TAIR, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.AVERAGE));
            //statistics.put(StatsType.AVERAGE, tmAve);

        }
        // calculating the min, max, and average
        else
        {
            
            int minLocation = 0;
            int maxLocation = 0;
            double sum = 0;
            for (int i = 0; i < dataCatalog.get(TAIR).size(); ++i)
            {
                // checking to see if a value is valid and the current minimum
                if (dataCatalog.get(TAIR).get(i).getValue() < dataCatalog.get(TAIR).get(minLocation).getValue()
                        && dataCatalog.get(TAIR).get(i).getValue() != -999)
                {
                    minLocation = i;
                }
                // checking to see if a value is valid and the current maximum
                if (dataCatalog.get(TAIR).get(i).getValue() > dataCatalog.get(TAIR).get(maxLocation).getValue())
                {
                    maxLocation = i;
                }
                // checking to see if a value is valid. If so, it is summed
                if (dataCatalog.get(TAIR).get(i).getValue() != -999)
                {
                    sum = sum + dataCatalog.get(TAIR).get(i).getValue();
                }
            }

           
            // setting the min and max based on the data analysis
            
            tmMin.put(TAIR,
                    new Statistics(dataCatalog.get(TAIR).get(minLocation).getValue(),
                            dataCatalog.get(TAIR).get(minLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MINIMUM));
            
            //tm.clear();
            
            tmMax.put(TAIR,
                    new Statistics(dataCatalog.get(TAIR).get(maxLocation).getValue(),
                            dataCatalog.get(TAIR).get(maxLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MAXIMUM));
            
            //tm.clear();

            // finding the average and storing it in a BigDecimal for rounding
            BigDecimal bd = new BigDecimal((sum / (dataCatalog.get(TAIR).size() - countMissingData)));
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            // setting the average
            
            tmAve.put(TAIR, new Statistics(bd.doubleValue(), MESONET, utcDateTime, (numberOfStations - countMissingData),
                    StatsType.AVERAGE));

        }

        countMissingData = 0;
        for (int i = 0; i < dataCatalog.get(TA9M).size(); ++i)
        {
            if (!dataCatalog.get(TA9M).get(i).isValid())
            {
                ++countMissingData;

            }
        }

        // checks to see if the amount of missing data exceeds a preset limit. If it
        // does, all statistics are assigned -999 values.
        if (countMissingData >= NUMBER_OF_MISSING_OBSERVATIONS)
        {

            //TreeMap<String, Statistics> tm2 = new TreeMap<String, Statistics>();
            tmMin.put(TA9M, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MINIMUM));
            //statistics.put(StatsType.MINIMUM, tmMin);
            
            tmMax.put(TA9M, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MAXIMUM));
           // statistics.put(StatsType.MAXIMUM, tmMax);
            
            tmAve.put(TA9M, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.AVERAGE));
            //statistics.put(StatsType.AVERAGE, tmAve);

        }
        // calculating the min, max, and average
        else
        {
            int minLocation = 0;
            int maxLocation = 0;
            double sum = 0;
            for (int i = 0; i < dataCatalog.get(TA9M).size(); ++i)
            {
                // checking to see if a value is valid and the current minimum
                if (dataCatalog.get(TA9M).get(i).getValue() < dataCatalog.get(TA9M).get(minLocation).getValue()
                        && dataCatalog.get(TA9M).get(i).getValue() != -999)
                {
                    minLocation = i;
                }
                // checking to see if a value is valid and the current maximum
                if (dataCatalog.get(TA9M).get(i).getValue() > dataCatalog.get(TA9M).get(maxLocation).getValue())
                {
                    maxLocation = i;
                }
                // checking to see if a value is valid. If so, it is summed
                if (dataCatalog.get(TA9M).get(i).getValue() != -999)
                {
                    sum = sum + dataCatalog.get(TA9M).get(i).getValue();
                }
            }

            // setting the min and max based on the data analysis
            //TreeMap<String, Statistics> tm = new TreeMap<String, Statistics>();
            tmMin.put(TA9M,
                    new Statistics(dataCatalog.get(TA9M).get(minLocation).getValue(),
                            dataCatalog.get(TA9M).get(minLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MINIMUM));
            
           
            tmMax.put(TA9M,
                    new Statistics(dataCatalog.get(TA9M).get(maxLocation).getValue(),
                            dataCatalog.get(TA9M).get(maxLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MAXIMUM));
            
            

            // finding the average and storing it in a BigDecimal for rounding
            BigDecimal bd = new BigDecimal((sum / (dataCatalog.get(TA9M).size() - countMissingData)));
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            // setting the average
            tmAve.put(TA9M, new Statistics(bd.doubleValue(), MESONET, utcDateTime, (numberOfStations - countMissingData),
                    StatsType.AVERAGE));
        }

        countMissingData = 0;

        for (int i = 0; i < dataCatalog.get(SRAD).size(); ++i)
        {
            if (!dataCatalog.get(SRAD).get(i).isValid())
            {
                ++countMissingData;

            }
        }

        // checks to see if the amount of missing data exceeds a preset limit. If it
        // does, all statistics are assigned -999 values.
        if (countMissingData >= NUMBER_OF_MISSING_OBSERVATIONS)
        {

            //TreeMap<String, Statistics> tm2 = new TreeMap<String, Statistics>();
            tmMin.put(SRAD, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MINIMUM));
            //statistics.put(StatsType.MINIMUM, tmMin);
            
            tmMax.put(SRAD, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.MAXIMUM));
            //statistics.put(StatsType.MAXIMUM, tmMax);
           
            tmAve.put(SRAD, new Statistics(-999, "NONE", utcDateTime, -1, StatsType.AVERAGE));
            //statistics.put(StatsType.AVERAGE, tmAve);

        }
        // calculating the min, max, and average
        else
        {
            int minLocation = 0;
            int maxLocation = 0;
            double sum = 0;
            for (int i = 0; i < dataCatalog.get(SRAD).size(); ++i)
            {
                // checking to see if a value is valid and the current minimum
                if (dataCatalog.get(SRAD).get(i).getValue() < dataCatalog.get(SRAD).get(minLocation).getValue()
                        && dataCatalog.get(SRAD).get(i).getValue() != -999)
                {
                    minLocation = i;
                }
                // checking to see if a value is valid and the current maximum
                if (dataCatalog.get(SRAD).get(i).getValue() > dataCatalog.get(SRAD).get(maxLocation).getValue())
                {
                    maxLocation = i;
                }
                // checking to see if a value is valid. If so, it is summed
                if (dataCatalog.get(SRAD).get(i).getValue() != -999)
                {
                    sum = sum + dataCatalog.get(SRAD).get(i).getValue();
                }
            }

            // setting the min and max based on the data analysis
            //TreeMap<String, Statistics> tm = new TreeMap<String, Statistics>();
            tmMin.put(SRAD,
                    new Statistics(dataCatalog.get(SRAD).get(minLocation).getValue(),
                            dataCatalog.get(SRAD).get(minLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MINIMUM));
            
            
            tmMax.put(SRAD,
                    new Statistics(dataCatalog.get(SRAD).get(maxLocation).getValue(),
                            dataCatalog.get(SRAD).get(maxLocation).getStid(), utcDateTime,
                            (numberOfStations - countMissingData), StatsType.MAXIMUM));
            
            

            // finding the average and storing it in a BigDecimal for rounding
            BigDecimal bd = new BigDecimal((sum / (dataCatalog.get(SRAD).size() - countMissingData)));
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            // setting the average
            tmAve.put(SRAD, new Statistics(bd.doubleValue(), MESONET, utcDateTime, (numberOfStations - countMissingData),
                    StatsType.AVERAGE));
            
           
            tmTot.put(SRAD,
                    new Statistics(sum, MESONET, utcDateTime, (numberOfStations - countMissingData), StatsType.TOTAL));
            

        }
        
        statistics.put(StatsType.MINIMUM, tmMin);
        statistics.put(StatsType.MAXIMUM, tmMax);
        statistics.put(StatsType.AVERAGE, tmAve);
        statistics.put(StatsType.TOTAL, tmTot);

    }

    /**
     * gets the Statistic that is desired.
     * @param type StatsType that you want to get.
     * @param paramId One of the three STID types.
     * @return The Statistics that you want!
     */
    public Statistics getStatistics(StatsType type, String paramId)
    {
        TreeMap<String, Statistics> tm = statistics.get(type);
        Statistics result = tm.get(paramId);
        return result;

    }
    
    /**
     * Overrides the default toString() method to format the output of MapData in
     * the desired format.
     * 
     * @return a String file name of the format:
     *         "========================================================" "===
     *         2018-08-30 17:45 ==="
     *         "========================================================" "Maximum
     *         Air Temperature[1.5m] = 36.5 C at HOOK" "Minimum Air
     *         Temperature[1.5m] = 20.8 C at MIAM" "Average Air Temperature[1.5m] =
     *         32.4 C at Mesonet"
     *         "========================================================"
     *         "========================================================" "Maximum
     *         Air Temperature[9.0m] = 34.9 C at HOOK" "Minimum Air
     *         Temperature[9.0m] = 20.7 C at MIAM" "Average Air Temperature[9.0m] =
     *         31.6 C at Mesonet"
     *         "========================================================"
     *         "========================================================" "Maximum
     *         Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP" "Minimum Solar
     *         Radiation[1.5m] = 163.0 W/m^2 at MIAM" "Average Solar Radiation[1.5m]
     *         = 828.1 W/m^2 at Mesonet"
     *         "========================================================"
     */
    public String toString()
    {
        // these lines of code determine if month, day, hour, or minute need a "0" to be
        // added to the front of
        // their value to make the correct file name

        // FIXME change this

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

        // building the string to return. For ease, StringBuffer was used and was
        // converted to a String later.
        
        StringBuffer testString = new StringBuffer();
        testString.append("========================================================" + '\n');
        testString.append("=== " + utcDateTime.get(Calendar.YEAR) + "-" + monthBuffed + "-" + dayBuffed);
        testString.append(" " + hourBuffed + ":" + minuteBuffed + " ===" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[1.5m] = " + this.getStatistics(StatsType.MAXIMUM, TAIR).getValue() + " C at "
                + this.getStatistics(StatsType.MAXIMUM, TAIR).getStid() + '\n');
        testString.append("Minimum Air Temperature[1.5m] = " + this.getStatistics(StatsType.MINIMUM, TAIR).getValue() + " C at "
                + this.getStatistics(StatsType.MINIMUM, TAIR).getStid() + '\n');
        testString.append("Average Air Temperature[1.5m] = " + this.getStatistics(StatsType.AVERAGE, TAIR).getValue() + " C at "
                + this.getStatistics(StatsType.AVERAGE, TAIR).getStid() + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Air Temperature[9.0m] = " + this.getStatistics(StatsType.MAXIMUM, TA9M).getValue() + " C at "
                + this.getStatistics(StatsType.MAXIMUM, TA9M).getStid() + '\n');
        testString.append("Minimum Air Temperature[9.0m] = " + this.getStatistics(StatsType.MINIMUM, TA9M).getValue() + " C at "
                + this.getStatistics(StatsType.MINIMUM, TA9M).getStid() + '\n');
        testString.append("Average Air Temperature[9.0m] = " + this.getStatistics(StatsType.AVERAGE, TA9M).getValue() + " C at "
                + this.getStatistics(StatsType.AVERAGE, TA9M).getStid() + '\n');
        testString.append("========================================================" + '\n');
        testString.append("========================================================" + '\n');
        testString.append("Maximum Solar Radiation[1.5m] = " + this.getStatistics(StatsType.MAXIMUM, SRAD).getValue() + " W/m^2 at "
                + this.getStatistics(StatsType.MAXIMUM, SRAD).getStid() + '\n');
        testString.append("Minimum Solar Radiation[1.5m] = " + this.getStatistics(StatsType.MINIMUM, SRAD).getValue() + " W/m^2 at "
                + this.getStatistics(StatsType.MINIMUM, SRAD).getStid() + '\n');
        testString.append("Average Solar Radiation[1.5m] = " + this.getStatistics(StatsType.AVERAGE, SRAD).getValue() + " W/m^2 at "
                + this.getStatistics(StatsType.AVERAGE, SRAD).getStid() + '\n');
        testString.append("========================================================");

        return testString.toString();
    }

}