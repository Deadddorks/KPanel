package utilities.font;

// ~~~~~~~~~~ Imports ~~~~~~~~~~

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Works together with a FontGenerator to allow for specifying some Font values, while leaving the rest up to the defaults
 * @author Deaddorks
 */
public class FontSpecs
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private String name;
	private int style;
	
	private boolean nameDefined;
	private boolean styleDefined;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Will use DEFAULTS for: {name, style}
	 */
	public FontSpecs()
	{
		nameDefined = false;
		styleDefined = false;
	}
	/**
	 * Will use DEFAULTS for: {style}
	 * @param name name for the Font
	 */
	public FontSpecs(final String name)
	{
		this.name = name;
		
		nameDefined = true;
		styleDefined = false;
	}
	/**
	 * Will use DEFAULTS for: {name}
	 * @param style style for the Font
	 */
	public FontSpecs(final int style)
	{
		this.style = style;
		
		nameDefined = false;
		styleDefined = true;
	}
	/**
	 * Will use DEFAULTS for: {}
	 * @param name name for the Font
	 * @param style style for the Font
	 */
	public FontSpecs(final String name, final int style)
	{
		this.name = name;
		this.style = style;
		
		nameDefined = true;
		styleDefined = true;
	}
	
	/**
	 * Gets the name of the FontSpec
	 * @return the name of the FontSpec
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Gets the style of the FontSpec
	 * @return the style of the FontSpec
	 */
	public int getStyle()
	{
		return style;
	}
	
	/**
	 * Gets whether or not a name was specified when this FontSpec was created
	 * @return whether or not a name was specified when this FontSpec was created
	 */
	public boolean isNameDefined()
	{
		return nameDefined;
	}
	/**
	 * Gets whether or not a style was specified when this FontSpec was created
	 * @return whether or not a style was specified when this FontSpec was created
	 */
	public boolean isStyleDefined()
	{
		return styleDefined;
	}
}
