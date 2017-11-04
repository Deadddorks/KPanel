package utilities.font;

// ~~~~~~~~~~ Imports ~~~~~~~~~~

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class FontSpecs
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private String name;
	private int style;
	private int size;
	
	private boolean nameDefined;
	private boolean styleDefined;
	private boolean sizeDefined;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Will use DEFAULTS for: {name, style, size}
	 */
	public FontSpecs()
	{
		nameDefined = false;
		styleDefined = false;
		sizeDefined = false;
	}
	/**
	 * Will use DEFAULTS for: {style, size}
	 * @param name name for the Font
	 */
	public FontSpecs(final String name)
	{
		this.name = name;
		
		nameDefined = true;
		styleDefined = false;
		sizeDefined = false;
	}
	/**
	 * Will use DEFAULTS for: {name, style}
	 * @param size size for the Font
	 */
	public FontSpecs(final int size)
	{
		this.size = size;
		
		nameDefined = false;
		styleDefined = true;
		sizeDefined = false;
	}
	/**
	 * Will use DEFAULTS for: {style}
	 * @param name name for the Font
	 * @param size size for the Font
	 */
	public FontSpecs(final String name, final int size)
	{
		this.name = name;
		this.size = size;
		
		nameDefined = true;
		styleDefined = false;
		sizeDefined = true;
	}
	/**
	 * Will use DEFAULTS for: {name}
	 * @param style style for the Font
	 * @param size size for the Font
	 */
	public FontSpecs(final int style, final int size)
	{
		this.style = style;
		this.size = size;
		
		nameDefined = false;
		styleDefined = true;
		sizeDefined = true;
	}
	/**
	 * Will use DEFAULTS for: {}
	 * @param name name for the Font
	 * @param style style for the Font
	 * @param size size for the Font
	 */
	public FontSpecs(final String name, final int style, final int size)
	{
		this.name = name;
		this.style = style;
		this.size = size;
		
		nameDefined = true;
		styleDefined = true;
		sizeDefined = true;
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
	 * Gets the size of the FontSpec
	 * @return the size of the FontSpec
	 */
	public int getSize()
	{
		return size;
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
	/**
	 * Gets whether or not a size was specified when this FontSpec was created
	 * @return whether or not a size was specified when this FontSpec was created
	 */
	public boolean isSizeDefined()
	{
		return sizeDefined;
	}
}
