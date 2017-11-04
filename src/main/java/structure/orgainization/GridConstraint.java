package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class GridConstraint implements Comparable<GridConstraint>
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	public enum Boundary
	{LEFT, CENTER, RIGHT}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private Constraint constraint;
	private Padding padding;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a GridConstraint with a CONSTANT Constraint and CONSTANT Padding values of 0
	 * @param constraintValue value of Constraint
	 */
	public GridConstraint(final int constraintValue)
	{
		init(new Constraint(constraintValue), new Padding(0));
	}
	/**
	 * Creates a GridConstraint with a CONSTANT Constraint and CONSTANT Padding values of leftRightPaddingValues
	 * @param constraintValue value of Constraint
	 * @param leftRightPaddingValues padding values
	 */
	public GridConstraint(final int constraintValue, final int leftRightPaddingValues)
	{
		init(new Constraint(constraintValue), new Padding(leftRightPaddingValues));
	}
	/**
	 * Creates a GridConstraint with a CONSTANT Constraint and CONSTANT Padding values of leftPaddingValue & rightPaddingValue
	 * @param constraintValue value of Constraint
	 * @param leftPaddingValue left padding value
	 * @param rightPaddingValue right padding value
	 */
	public GridConstraint(final int constraintValue, final int leftPaddingValue, final int rightPaddingValue)
	{
		init(new Constraint(constraintValue), new Padding(leftPaddingValue, rightPaddingValue));
	}
	
	/**
	 * Creates a GridConstraint with a PERCENT Constraint and CONSTANT Padding values of 0
	 * @param constraintValue value of Constraint
	 */
	public GridConstraint(final float constraintValue)
	{
		init(new Constraint(constraintValue), new Padding(0));
	}
	/**
	 * Creates a GridConstraint with a PERCENT Constraint and CONSTANT Padding values of leftRightPaddingValues
	 * @param constraintValue value of Constraint
	 * @param leftRightPaddingValues padding values
	 */
	public GridConstraint(final float constraintValue, final int leftRightPaddingValues)
	{
		init(new Constraint(constraintValue), new Padding(leftRightPaddingValues));
	}
	/**
	 * Creates a GridConstraint with a PERCENT Constraint and CONSTANT Padding values of leftPaddingValue & rightPaddingValue
	 * @param constraintValue value of Constraint
	 * @param leftPaddingValue left padding value
	 * @param rightPaddingValue right padding value
	 */
	public GridConstraint(final float constraintValue, final int leftPaddingValue, final int rightPaddingValue)
	{
		init(new Constraint(constraintValue), new Padding(leftPaddingValue, rightPaddingValue));
	}
	/**
	 * Creates a GridConstraint with a PERCENT Constraint and PERCENT Padding values of leftRightPaddingValues
	 * @param constraintValue value of Constraint
	 * @param leftRightPaddingValues padding values
	 */
	public GridConstraint(final float constraintValue, final float leftRightPaddingValues)
	{
		init(new Constraint(constraintValue), new Padding(leftRightPaddingValues));
	}
	/**
	 * Creates a GridConstraint with a PERCENT Constraint and PERCENT Padding values of leftPaddingValue & rightPaddingValue
	 * @param constraintValue value of Constraint
	 * @param leftPaddingValue left padding value
	 * @param rightPaddingValue right padding value
	 */
	public GridConstraint(final float constraintValue, final float leftPaddingValue, final float rightPaddingValue)
	{
		init(new Constraint(constraintValue), new Padding(leftPaddingValue, rightPaddingValue));
	}
	
	/**
	 * Creates a GridConstraint with the specified Constraint and CONSTANT padding values of 0
	 * @param constraint Constraint for Constraint
	 */
	public GridConstraint(final Constraint constraint)
	{
		init(constraint, new Padding(0));
	}
	/**
	 * Creates a GridConstraint with the specified Constraint and Padding with Constraints of paddingConstraints
	 * @param constraint Constraint for Constraint
	 * @param paddingConstraint Constraints for Padding
	 */
	public GridConstraint(final Constraint constraint, final Constraint paddingConstraint)
	{
		init(constraint, new Padding(paddingConstraint));
	}
	/**
	 * Creates a GridConstraint with the specified Constraint and specified Padding
	 * @param constraint Constraint for Constraint
	 * @param padding Padding for Padding
	 */
	public GridConstraint(final Constraint constraint, final Padding padding)
	{
		init(constraint, padding);
	}
	
	private void init(final Constraint constraint, final Padding padding)
	{
		if (constraint == null)
		{
			throw new InvalidParameterException("GridConstraint", "init", "constraint");
		}
		if (padding == null)
		{
			throw new InvalidParameterException("GridConstraint", "init", "padding");
		}
		
		this.constraint = constraint;
		this.padding = padding;
	}
	
	/**
	 * Sets the value of total
	 * @param total the total size of the Constraints container
	 */
	public void setTotal(final int total)
	{
		constraint.setTotal(total);
		padding.setTotal(total);
	}
	
	/**
	 * Returns the value relative to the base Constraint based off the Boundary
	 * @param boundary what respective value is desired
	 * @return
	 */
	public int getValue(final Boundary boundary)
	{
		switch (boundary)
		{
			case CENTER:
				return constraint.getValue();
			case LEFT:
				return constraint.getValue() - padding.getLeftValue();
			case RIGHT:
				return constraint.getValue() + padding.getRightValue();
			default:
				throw new RuntimeException("INVALID BOUNDARY");
		}
	}
	
	/**
	 * Gets the AlignmentType of the base Constraint
	 * @return the AlignmentType of the base Constraint
	 */
	public AlignmentType getAlignmentType()
	{
		return constraint.getAlignmentType();
	}
	
	/**
	 * Compares this GridConstraint to another GridConstraint
	 * @param gC the GridConstraint to compare this GridConstraint with
	 * @return whether this GridConstraint is less than, equal to, or greater than the other GridConstraint<br>
	 * uses the base Constraint to determine this
	 */
	public int compareTo(final GridConstraint gC)
	{
		return constraint.compareTo(gC.constraint);
	}
	
	/**
	 * Used to get a String representation of this GridConstraint
	 * @return the value of this GridConstraint as a String
	 */
	@Override
	public String toString()
	{
		return "Constraint: <"+constraint.toString()+">, Padding: <"+padding.toString()+">";
	}
	
}
