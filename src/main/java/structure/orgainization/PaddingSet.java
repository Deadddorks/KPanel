package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Allows Nodes to specify whether or not they want to be aligned with their main GridConstraints or their Paddings
 * @author Deaddorks
 */
public class PaddingSet
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private boolean useLeftPadding;
	private boolean useRightPadding;
	private boolean useTopPadding;
	private boolean useBottomPadding;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Set whether or not a Node will use a Constraints base or its Padding
	 * @param useAllPaddings the value for all sides
	 */
	public PaddingSet(final boolean useAllPaddings)
	{
		init(useAllPaddings, useAllPaddings, useAllPaddings, useAllPaddings);
	}
	/**
	 * Set whether or not a Node will use a Constraints base or its Padding
	 * @param useLeftRightPaddings the value for left & right sides
	 * @param useTopBottomPaddings the value for top & bottom sides
	 */
	public PaddingSet(final boolean useLeftRightPaddings, final boolean useTopBottomPaddings)
	{
		init(useLeftRightPaddings, useLeftRightPaddings, useTopBottomPaddings, useTopBottomPaddings);
	}
	/**
	 * Set whether or not a Node will use a Constraints base or its Padding
	 * @param useLeftPadding the value for the left side
	 * @param useRightPadding the value for the right side
	 * @param useTopPadding the value for the top side
	 * @param useBottomPadding the value for the bottom side
	 */
	private PaddingSet(final boolean useLeftPadding, final boolean useRightPadding, final boolean useTopPadding, final boolean useBottomPadding)
	{
		init(useLeftPadding, useRightPadding, useTopPadding, useBottomPadding);
	}
	
	private void init(final boolean useLeftPadding, final boolean useRightPadding, final boolean useTopPadding, final boolean useBottomPadding)
	{
		this.useLeftPadding = useLeftPadding;
		this.useRightPadding = useRightPadding;
		this.useTopPadding = useTopPadding;
		this.useBottomPadding = useBottomPadding;
	}
	
	/**
	 * Get an array with all the Boundaries
	 * @return an array with all the Boundaries<br>
	 * in the form of {left, right, top, bottom}
	 */
	public GridConstraint.Boundary[] getBoundaries()
	{
		return new GridConstraint.Boundary[] {
				getLeftBoundary(),
				getRightBoundary(),
				getTopBoundary(),
				getBottomBoundary()
		};
	}
	
	/**
	 * Get which boundary to use for the left side
	 * @return the boundary for the left side
	 */
	public GridConstraint.Boundary getLeftBoundary()
	{
		return useLeftPadding ? GridConstraint.Boundary.LEFT : GridConstraint.Boundary.CENTER;
	}
	/**
	 * Get which boundary to use for the right side
	 * @return the boundary for the right side
	 */
	public GridConstraint.Boundary getRightBoundary()
	{
		return useRightPadding ? GridConstraint.Boundary.RIGHT : GridConstraint.Boundary.CENTER;
	}
	/**
	 * Get which boundary to use for the top side
	 * @return the boundary for the top side
	 */
	public GridConstraint.Boundary getTopBoundary()
	{
		return useTopPadding ? GridConstraint.Boundary.LEFT : GridConstraint.Boundary.CENTER;
	}
	/**
	 * Get which boundary to use for the bottom side
	 * @return the boundary for the bottom side
	 */
	public GridConstraint.Boundary getBottomBoundary()
	{
		return useBottomPadding ? GridConstraint.Boundary.RIGHT : GridConstraint.Boundary.CENTER;
	}
	
}
