package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.util.function.BiFunction;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Allows a Node to have a fast and easy way to define what sort of sizing a property should have,
 * and the Property2D will automatically deal with the resizing (if necessary) during runtime
 * @author Deaddorks
 */
public class Property2D
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private BiFunction<Integer, Integer, Integer> function;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a Property2D that will just return a CONSTANT value
	 * @param value value of the CONSTANT to be returned
	 */
	public Property2D(final int value)
	{
		function = (width, height) -> value;
	}
	/**
	 * Creates a Property2D that will return the PERCENT times the average of the values passed in at runtime
	 * @param value value of the PERCENT to be used
	 */
	public Property2D(final float value)
	{
		function = (width, height) -> (int) (value * (width + height) / 2);
	}
	/**
	 * Creates a Property2D that will return the sum of the PERCENTs times the values passed in at runtime
	 * @param widthValue value of the PERCENT to be used in respect to the width
	 * @param heightValue value of the PERCENT to be used in respect to the height
	 */
	public Property2D(final float widthValue, final float heightValue)
	{
		function = (width, height) -> (int) (widthValue * width + heightValue * height);
	}
	/**
	 * Creates a Property2D that will return the PERCENTs times the values passed in at runtime with the specified relation between the two
	 * @param widthValue value of the PERCENT to be used in respect to the width
	 * @param heightValue value of the PERCENT to be used in respect to the height
	 */
	public Property2D(final float widthValue, final float heightValue, final BiFunction<Integer, Integer, Integer> relation)
	{
		function = (width, height) -> relation.apply((int) (widthValue * width), (int) (heightValue * height));
	}
	/**
	 * Creates a Function with the specified Function as the Property
	 * @param function Function to use
	 */
	public Property2D(final BiFunction<Integer, Integer, Integer> function)
	{
		this.function = function;
	}
	
	/**
	 * Returns the value calculated by the Function of this Property
	 * @param width the width of the parent area that this Property belongs to
	 * @param height the height of the parent area that this Property belongs to
	 * @return the value calculated by the Function of this Property
	 */
	public int calculate(final int width, final int height)
	{
		return function.apply(width, height);
	}
	
}
