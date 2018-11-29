package dataAnalysis;
/**
 * This abstract class is used in conjunction with the
 * Observation class.
 * 
 * @author Robert Hill
 * @version 2018-10-02
 */
public abstract class AbstractObservation
{
    /**
     * Boolean expression of whether or not data is valid.
     */
    protected boolean valid;
    
    /**
     * Constructor for an abstract class won't be used
     */
    public AbstractObservation()
    {
        // this constructor is empty because the 
        //class is abstract and can't be instantiated
    }
    
    /**
     * An abstract method that is defined in Observation
     */
    public abstract boolean isValid();
    
    
}
