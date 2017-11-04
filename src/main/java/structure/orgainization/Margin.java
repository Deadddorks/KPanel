package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Used by Nodes to specify extra space outside of the Node between itself and its Constraint
 * @author Deaddorks
 */
public class Margin
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private Constraint leftMargin;
	private Constraint rightMargin;
	private Constraint topMargin;
	private Constraint bottomMargin;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a set of Constraints with CONSTANT values
	 * @param allMarginValues value for all sides
	 */
	public Margin(final int allMarginValues)
	{
		init(new Constraint(allMarginValues), new Constraint(allMarginValues), new Constraint(allMarginValues), new Constraint(allMarginValues));
	}
	/**
	 * Creates a set of Constraints with CONSTANT values
	 * @param leftRightValues value for left & right sides
	 * @param topBottomValues value for top & bottom sides
	 */
	public Margin(final int leftRightValues, final int topBottomValues)
	{
		init(new Constraint(leftRightValues), new Constraint(leftRightValues), new Constraint(topBottomValues), new Constraint(topBottomValues));
	}
	/**
	 * Creates a set of Constraints with CONSTANT values
	 * @param leftValue value for left side
	 * @param rightValue value for right side
	 * @param topValue value for top side
	 * @param bottomValue value for bottom side
	 */
	public Margin(final int leftValue, final int rightValue, final int topValue, final int bottomValue)
	{
		init(new Constraint(leftValue), new Constraint(rightValue), new Constraint(topValue), new Constraint(bottomValue));
	}
	
	/**
	 * Creates a set of Constraints with PERCENT values
	 * @param allMarginValues value for all sides
	 */
	public Margin(final float allMarginValues)
	{
		init(new Constraint(allMarginValues), new Constraint(allMarginValues), new Constraint(allMarginValues), new Constraint(allMarginValues));
	}
	/**
	 * Creates a set of Constraints with PERCENT values
	 * @param leftRightValues value for left & right sides
	 * @param topBottomValues value for top & bottom sides
	 */
	public Margin(final float leftRightValues, final float topBottomValues)
	{
		init(new Constraint(leftRightValues), new Constraint(leftRightValues), new Constraint(topBottomValues), new Constraint(topBottomValues));
	}
	/**
	 * Creates a set of Constraints with PERCENT values
	 * @param leftValue value for left side
	 * @param rightValue value for right side
	 * @param topValue value for top side
	 * @param bottomValue value for bottom side
	 */
	public Margin(final float leftValue, final float rightValue, final float topValue, final float bottomValue)
	{
		init(new Constraint(leftValue), new Constraint(rightValue), new Constraint(topValue), new Constraint(bottomValue));
	}
	
	/**
	 * Creates a set of Constraints with the specified Constraint(s)
	 * @param allConstraints Constraint for all sides
	 */
	public Margin(final Constraint allConstraints)
	{
		init(allConstraints, allConstraints, allConstraints, allConstraints);
	}
	/**
	 * Creates a set of Constraints with the specified Constraint(s)
	 * @param leftRightConstraints Constraint for left & right sides
	 * @param topBottomConstraints Constraint for top & bottom sides
	 */
	public Margin(final Constraint leftRightConstraints, final Constraint topBottomConstraints)
	{
		init(leftRightConstraints, leftRightConstraints, topBottomConstraints, topBottomConstraints);
	}
	/**
	 * Creates a set of Constraints with the specified Constraint(s)
	 * @param leftConstraint Constraint for left side
	 * @param rightConstraint Constraint for right side
	 * @param topConstraint Constraint for top side
	 * @param bottomConstraint Constraint for bottom side
	 */
	public Margin(final Constraint leftConstraint, final Constraint rightConstraint, final Constraint topConstraint, final Constraint bottomConstraint)
	{
		init(leftConstraint, rightConstraint, topConstraint, bottomConstraint);
	}
	
	private void init(final Constraint leftMargin, final Constraint rightMargin, final Constraint topMargin, final Constraint bottomMargin)
	{
		if (leftMargin == null)
		{
			throw new InvalidParameterException("Margin", "init", "leftMargin");
		}
		if (rightMargin == null)
		{
			throw new InvalidParameterException("Margin", "init", "rightMargin");
		}
		if (topMargin == null)
		{
			throw new InvalidParameterException("Margin", "init", "topMargin");
		}
		if (bottomMargin == null)
		{
			throw new InvalidParameterException("Margin", "init", "bottomMargin");
		}
		
		this.leftMargin = leftMargin;
		this.rightMargin = rightMargin;
		this.topMargin = topMargin;
		this.bottomMargin = bottomMargin;
	}
	
	/**
	 * Sets the value of total
	 * @param total the total size of the Constraints container
	 */
	public void setTotal(final int total)
	{
		leftMargin.setTotal(total);
		rightMargin.setTotal(total);
		topMargin.setTotal(total);
		bottomMargin.setTotal(total);
	}
	
	/**
	 * Gets the value of the left Constraint based off the total size of its container
	 * @return the value of the left Constraint based off total
	 */
	public int getLeftValue()
	{
		return leftMargin.getValue();
	}
	/**
	 * Gets the value of the right Constraint based off the total size of its container
	 * @return the value of the right Constraint based off total
	 */
	public int getRightValue()
	{
		return rightMargin.getValue();
	}
	/**
	 * Gets the value of the top Constraint based off the total size of its container
	 * @return the value of the top Constraint based off total
	 */
	public int getTopValue()
	{
		return topMargin.getValue();
	}
	/**
	 * Gets the value of the bottom Constraint based off the total size of its container
	 * @return the value of the bottom Constraint based off total
	 */
	public int getBottomValue()
	{
		return bottomMargin.getValue();
	}
	
	/**
	 * Gets the values for the all the Constraints based off the total size of its container
	 * @param total the total size of the Constraints container
	 * @return the values for all Constraints based off total<br>
	 * in the form of {left, right, top, bottom}
	 */
	public int[] getValues(final int total)
	{
		return new int[] {
				leftMargin.getValue(),
				rightMargin.getValue(),
				topMargin.getValue(),
				bottomMargin.getValue()
		};
	}
	
}
