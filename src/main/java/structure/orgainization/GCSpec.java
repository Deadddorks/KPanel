package structure.orgainization;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import structure.grid.Grid;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class GCSpec
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private AlignmentType alignment;
	private int index;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a GridConstraintSpec with the specified values,<br>
	 * used in order to specify a GridConstraint within a Grid
	 * @param alignment AlignmentType of the GridConstraint
	 * @param index int index of the GridConstraint in its respective GridConstraintList
	 */
	public GCSpec(final AlignmentType alignment, final int index)
	{
		this.alignment = alignment;
		this.index = index;
	}
	
	/**
	 * Gets the AlignmentType of this GCSpec
	 * @return the AlignmentType of this GCSpec
	 */
	public AlignmentType getAlignment()
	{
		return alignment;
	}
	/**
	 * Gets the index of this GCSpec
	 * @return the index of this GCSpec
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * Gets the GCSpec for the next GridConstraint in the List by returning<br>
	 * essentially the same GCSpec, but with an index 1 greater
	 * @return the next GCSpec by adding 1 to this GCSpecs index
	 */
	public GCSpec getNext()
	{
		return new GCSpec(alignment, index + 1);
	}
}
