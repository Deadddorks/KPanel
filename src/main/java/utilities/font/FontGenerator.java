package utilities.font;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import java.awt.Font;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Aids in easily allowing for swift generation of Fonts, allowing a Node to specify what sort of Default Font it would like,
 * and being able to generate fonts based on defaults and some provided values
 * @author Deaddorks
 */
public class FontGenerator
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private String defaultFontName = Font.DIALOG;
	private int defaultFontStyle = Font.PLAIN;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Generates a Font based off a FontSpecs Object
	 * @param specs the specified FontSpecs to use
	 * @return a Font based off the specified FontSpecs
	 */
	public Font getFont(final FontSpecs specs)
	{
		return generateFont((specs.isNameDefined() ? specs.getName() : defaultFontName),
				(specs.isStyleDefined() ? specs.getStyle() : defaultFontStyle));
	}
	
	/**
	 * Generates a Font
	 * @param name
	 * @param style
	 * @return
	 */
	private Font generateFont(final String name, final int style)
	{
		return new Font(name, style, 0);
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
	
}
