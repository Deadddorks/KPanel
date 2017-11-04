package structure.orgainization.graphical;

// ~~~~~~~~~~ Imports ~~~~~~~~~~

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class Rectangle
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a rectangle with the given specifications
	 * @param xMin left x-coord
	 * @param yMin top y-coord
	 * @param xMax right x-coord
	 * @param yMax bottom y-coord
	 */
	public Rectangle(final int xMin, final int yMin, final int xMax, final int yMax)
	{
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	/**
	 * Gets the x-coordinate of the left side of the Rectangle
	 * @return left x-coord
	 */
	public int getXMin()
	{
		return xMin;
	}
	/**
	 * Gets the y-coordinate of the top side of the Rectangle
	 * @return top y-coord
	 */
	public int getYMin()
	{
		return yMin;
	}
	/**
	 * Gets the x-coordinate of the right side of the Rectangle
	 * @return right x-coord
	 */
	public int getXMax()
	{
		return xMax;
	}
	/**
	 * Gets the y-coordinate of the bottom side of the Rectangle
	 * @return bottom y-coord
	 */
	public int getYMax()
	{
		return yMax;
	}
	
	/**
	 * Gets the width of the Rectangle
	 * @return with of the Rectangle
	 */
	public int getWidth()
	{
		return xMax - xMin;
	}
	/**
	 * Gets the height of the Rectangle
	 * @return height of the Rectangle
	 */
	public int getHeight()
	{
		return yMax - yMin;
	}
	
	/**
	 * Gets a String representation of this Rectangle
	 * @return a String representation of this Rectangle
	 */
	@Override
	public String toString()
	{
		return "Rectangle: {xMin: ["+xMin+"], yMin: ["+yMin+"], xMax: ["+xMax+"], yMax: ["+yMax+"], width: ["+getWidth()+"], height: ["+getHeight()+"]}";
	}
}
