package dataAnalysis;

/**
 * A single observation of weather data. Stores the data, the sample station ID,
 * and whether the data is a valid sample (using AbstractObservation).
 * It inherits form AbstractObservation
 * 
 * @author Robert Hill
 * @version 2018-10-02
 */

public class Observation extends AbstractObservation
{

    /**
     * The value of the Observation in either degrees C or in W/m^2.
     */
    private double value;


    /**
     * The stid of the Observation (a four letter code expressing the sample station
     * location).
     */
    private String stid;

    /**
     * Constructor for Observation. Takes in information on the observation as
     * arguments and stores them in the value and stid instance variables. It also
     * checks to see whether the value supplied as an argument is valid and saves
     * that as true or false.
     * 
     * @param value
     *            a double of the value of the given Observation (either Temperature
     *            or Radiation)
     * @param stid
     *            a String of the stid of the given Observation (showing the
     *            location of the Observation)
     */
    public Observation(double value, String stid)
    {
        
        this.value = value;
        this.stid = stid;     

    }

    /**
     * Return the Value of the given Observation
     * 
     * @return double Value of the Observation object
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Return whether the Value of the given Observation is valid
     * 
     * @return boolean validity of the Observation Value data
     */
    public boolean isValid()
    {
     // checking value's validity
        if (value == -999)
        {
            this.valid = false;
        }
        else
        {
            this.valid = true;
        }
        
        return valid;
    }

    /**
     * Return the Stid of the given Observation
     * 
     * @return String Stid of the Observation object
     */
    public String getStid()
    {
        return stid;
    }

    /**
     * toString Override. Returns the Value and stid of the given Observation
     * 
     * @return String Value and the stid of the Observation object in the format
     *         "Value at Stid"
     */
    public String toString()
    {
        return value + " at " + stid;
    }

}
