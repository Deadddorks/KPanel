package utilities.font;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.awt.Font;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class FontGenerator
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private String defaultFontName = Font.DIALOG;
	private int defaultFontStyle = Font.PLAIN;
	private int defaultFontSize = 15;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Generates a Font based off a FontSpecs Object
	 * @param specs the specified FontSpecs to use
	 * @return a Font based off the specified FontSpecs
	 */
	public Font getFont(final FontSpecs specs)
	{
		return generateFont((specs.isNameDefined() ? specs.getName() : defaultFontName),
				(specs.isStyleDefined() ? specs.getStyle() : defaultFontStyle),
				(specs.isSizeDefined() ? specs.getSize() : defaultFontSize));
	}
	
	/**
	 * Generates a Font
	 * @param name
	 * @param style
	 * @param size
	 * @return
	 */
	private Font generateFont(final String name, final int style, final int size)
	{
		return new Font(name, style, size);
	}
	
	public void setDefaultFontName(final String name)
	{
		this.defaultFontName = name;
	}
	/**
	 * Sets the default Font style
	 * @param style the desired default Font style
	 */
	public void setDefaultFontStyle(final int style)
	{
		this.defaultFontStyle = style;
	}
	/**
	 * Sets the default Font size
	 * @param size the desired default Font size
	 */
	public void setDefaultFontSize(final int size)
	{
		this.defaultFontStyle = size;
	}
	
}
