package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class Constraint implements Comparable<Constraint>
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private AlignmentType alignment;
	private float value;
	
	private int total;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a CONSTANT Constraint of size value
	 * @param value the CONSTANT value of the Constraint
	 */
	public Constraint(final int value)
	{
		init(AlignmentType.CONSTANT, value);
	}
	/**
	 * Creates a PERCENT Constraint of size value
	 * @param value the PERCENT value of the Constraint
	 */
	public Constraint(final float value)
	{
		init(AlignmentType.PERCENT, value);
	}
	
	private void init(final AlignmentType alignment, final float value)
	{
		// alignment can not be null
		if (alignment == null)
		{
			throw new InvalidParameterException("Constraint", "init", "alignment");
		}
		// values must be >= 0
		if (value < 0)
		{
			throw new InvalidParameterException("Constraint", "init", "value", Float.toString(value), "Must be >= 0");
		}
		
		this.alignment = alignment;
		this.value = value;
	}
	
	/**
	 * Sets the value of total
	 * @param total the total size of the Constraints container
	 */
	public void setTotal(final int total)
	{
		this.total = total;
	}
	/**
	 * Gets the value of the Constraint based off the total size of its container<br>
	 * CONSTANT : value<br>
	 * PERCENT : value * total;
	 * @return
	 */
	public int getValue()
	{
		switch (alignment)
		{
			case CONSTANT:
				return (int) value;
			case PERCENT:
				return (int) (value * total);
			default:
				throw new RuntimeException("Invalid AlignmentType");
		}
	}
	
	/**
	 * Gets the AlignmentType of this Constraint
	 * @return the AlignmentType of this Constraint
	 */
	public AlignmentType getAlignmentType()
	{
		return alignment;
	}
	
	/**
	 * Compares this Constraint to another Constraint
	 * @param c the Constraint to compare this Constraint with
	 * @return whether this Constraint is less than, equal to, or greater than the other Constraint
	 */
	@Override
	public int compareTo(final Constraint c)
	{
		if (alignment != c.alignment)
		{
			return alignment == AlignmentType.CONSTANT ? -1 : 1;
		}
		
		if (value == c.value)
		{
			return 0;
		}
		else
		{
			return value < c.value ? -1 : 1;
		}
	}
	
	/**
	 * Used to get a String representation of this Constraint
	 * @return the value of this Constraint as a String
	 */
	@Override
	public String toString()
	{
		switch (alignment)
		{
			case CONSTANT:
				return "{CONSTANT} ("+((int) value)+")";
			case PERCENT:
				return "{PERCENT} ("+(100 * value)+"%)";
			default:
				throw new RuntimeException("Invalid AlignmentType");
		}
	}
	
}
