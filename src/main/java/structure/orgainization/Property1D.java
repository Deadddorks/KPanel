package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.util.function.Function;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Allows a Node to have a fast and easy way to define what sort of sizing a property should have,
 * and the Property1D will automatically deal with the resizing (if necessary) during runtime
 * @author Deaddorks
 */
public class Property1D
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private Function<Integer, Integer> function;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a Property1D that will just return a CONSTANT value
	 * @param value value of the CONSTANT to be returned
	 */
	public Property1D(final int value)
	{
		function = v -> value;
	}
	/**
	 * Creates a Property2D that will return the PERCENT times the value passed in at runtime
	 * @param value value of the PERCENT to be used
	 */
	public Property1D(final float value)
	{
		function = v -> (int) (v * value);
	}
	/**
	 * Creates a Function with the specified Function as the Property
	 * @param function Function to use
	 */
	public Property1D(final Function<Integer, Integer> function)
	{
		this.function = function;
	}
	
	/**
	 * Returns the value calculated by the Function of this Property
	 * @param value the value of the parent that this Property belongs to
	 * @return the value calculated by the Function of this Property
	 */
	public int calculate(final int value)
	{
		return function.apply(value);
	}
	
}
