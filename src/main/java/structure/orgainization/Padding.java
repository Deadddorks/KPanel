package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Used by GridConstraints in order to easily allow Nodes to have a default offset from the actual main GridConstraint
 * @author Deaddorks
 */
public class Padding
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private Constraint leftConstraint;
	private Constraint rightConstraint;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a Padding with 2 CONSTANT Constraints of leftRightValue
	 * @param leftRightValue the int value for the Constraints
	 */
	public Padding(final int leftRightValue)
	{
		init(new Constraint(leftRightValue), new Constraint(leftRightValue));
	}
	
	/**
	 * Creates a Padding with 2 CONSTANT Constraints of leftValue & rightValue
	 * @param leftValue the int value for the left Constraint
	 * @param rightValue the int value for the right Constraint
	 */
	public Padding(final int leftValue, final int rightValue)
	{
		init(new Constraint(leftValue), new Constraint(rightValue));
	}
	
	/**
	 * Creates a Padding with 2 PERCENT Constraints of leftRightValue
	 * @param leftRightValue the percent value for the Constraints
	 */
	public Padding(final float leftRightValue)
	{
		init(new Constraint(leftRightValue), new Constraint(leftRightValue));
	}
	/**
	 * Creates a Padding with 2 PERCENT Constraints of leftValue & rightValue
	 * @param leftValue the percent value for the left Constraint
	 * @param rightValue the percent value for the right Constraint
	 */
	public Padding(final float leftValue, final float rightValue)
	{
		init(new Constraint(leftValue), new Constraint(rightValue));
	}
	
	/**
	 * Creates a Padding with the Constraints both as the specified Constraint
	 * @param leftRightConstraint the Constraint to be set as both Constraints
	 */
	public Padding(final Constraint leftRightConstraint)
	{
		init(leftRightConstraint, leftRightConstraint);
	}
	/**
	 * Creates a Padding with the specified Constraints
	 * @param leftConstraint the left Constraint
	 * @param rightConstraint the right Constraint
	 */
	public Padding(final Constraint leftConstraint, final Constraint rightConstraint)
	{
		init(leftConstraint, rightConstraint);
	}
	
	private void init(final Constraint leftConstraint, final Constraint rightConstraint)
	{
		if (leftConstraint == null)
		{
			throw new InvalidParameterException("Padding", "init", "leftConstraint");
		}
		if (rightConstraint == null)
		{
			throw new InvalidParameterException("Padding", "init", "rightConstraint");
		}
		
		this.leftConstraint = leftConstraint;
		this.rightConstraint = rightConstraint;
	}
	
	/**
	 * Sets the value of total
	 * @param total the total size of the Constraints container
	 */
	public void setTotal(final int total)
	{
		leftConstraint.setTotal(total);
		rightConstraint.setTotal(total);
	}
	
	/**
	 * Gets the value of the left Constraint based off the total size of its container
	 * @return the value of the left Constraint based off total
	 */
	public int getLeftValue()
	{
		return leftConstraint.getValue();
	}
	/**
	 * Gets the value of the right Constraint based off the total size of its container
	 * @return the value of the right Constraint based off total
	 */
	public int getRightValue()
	{
		return rightConstraint.getValue();
	}
	
	/**
	 * Used to get a String representation of this Padding
	 * @return the value of this Padding as a String
	 */
	@Override
	public String toString()
	{
		return "leftConstraint: ["+leftConstraint.toString()+"], rightConstraint: ["+rightConstraint.toString()+"]";
	}
}
