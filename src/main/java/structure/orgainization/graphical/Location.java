package structure.orgainization.graphical;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.awt.event.MouseEvent;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * A simple (x,y) representation used to denote a Location and
 * remove some of the complication and unnecessary information from MouseEvents in cases where it is not needed
 */
public class Location
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private int xPos;
	private int yPos;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a Location at location (xPos, yPos)
	 * @param xPos X-coordinate of the Location
	 * @param yPos Y-coordinate of the Location
	 */
	public Location(final int xPos, final int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	/**
	 * Creates a Location from a MouseEvent
	 * @param e MouseEvent to get the (xPos, yPos) from
	 */
	public Location(MouseEvent e)
	{
		this.xPos = e.getX();
		this.yPos = e.getY();
	}
	
	/**
	 * Gets the X-coordinate of the Location
	 * @return X-coordinate
	 */
	public int getXPos()
	{
		return xPos;
	}
	/**
	 * Gets the Y-coordinate of the Location
	 * @return Y-coordinate
	 */
	public int getYPos()
	{
		return yPos;
	}
	
	/**
	 * Gets the coordinates of the Location as array
	 * @return int[] {xPos, yPos}
	 */
	public int[] getPosArray()
	{
		return new int[] {xPos, yPos};
	}
	
	/**
	 * Returns a String representation of this Location
	 * @return a String representation of this Location
	 */
	@Override
	public String toString()
	{
		return "Location: ("+xPos+", "+yPos+")";
	}
}
