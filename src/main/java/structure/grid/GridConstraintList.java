package structure.grid;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.ConstraintAlreadyExistsException;
import structure.orgainization.GridConstraint;

import java.util.ArrayList;
import java.util.List;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Used by Grid to organize GridConstraints based on their Orientation and whether they are CONSTANT or PERCENT
 * @author Deaddorks
 */
public class GridConstraintList
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private List<GridConstraint> gridConstraints;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new GridConstraintList with a blank List
	 */
	public GridConstraintList()
	{
		gridConstraints = new ArrayList<>();
	}
	
	/**
	 * Attempts to add the given GridConstraint to the list<br>
	 * Will throw an Exception if the GridConstraint is a duplicate
	 * @param gridConstraint GridConstraint to be added to the list
	 */
	public void add(final GridConstraint gridConstraint)
	{
		if (gridConstraints.size() == 0)
		{
			gridConstraints.add(gridConstraint);
			return;
		}
		
		for (int i = 0; i < gridConstraints.size(); i++)
		{
			if (gridConstraint.compareTo(gridConstraints.get(i)) == 0)
			{
				throw new ConstraintAlreadyExistsException(gridConstraint);
			}
			else if (gridConstraint.compareTo(gridConstraints.get(i)) < 0)
			{
				gridConstraints.add(i, gridConstraint);
				return;
			}
		}
		
		gridConstraints.add(gridConstraint);
	}
	
	/**
	 * Gets the GridConstraint at the given index
	 * @param index index of the list to return
	 * @return GridConstraint at the given index
	 */
	public GridConstraint get(final int index)
	{
		return gridConstraints.get(index);
	}
	/**
	 * Gets all the constraints in the List
	 * @return all the constraints in the List
	 */
	public List<GridConstraint> getAll()
	{
		return gridConstraints;
	}
	
	/**
	 * Sets the totals for all the GridConstraints in the List
	 * @param total the total size of the GridConstraints container
	 */
	public void setTotals(final int total)
	{
		for (GridConstraint gC : gridConstraints)
		{
			gC.setTotal(total);
		}
	}
	
	/**
	 * Gets the String representation of this GridConstraintList
	 * @return the String representation of this GridConstraintList
	 */
	@Override
	public String toString()
	{
		String out = " --- Grid Constraints --- ";
		
		for (GridConstraint gC : gridConstraints)
		{
			out += "\n" + gC.toString();
		}
		
		return out;
	}
}
