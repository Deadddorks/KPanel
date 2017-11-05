package structure.nodes;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
import structure.orgainization.Property2D;
import structure.orgainization.graphical.Rectangle;
import utilities.font.FontGenerator;
import utilities.font.FontSpecs;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class KLabel extends Node
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	public enum VerticalAlignment {TOP, CENTER, BOTTOM}
	public enum HorizontalAlignment {LEFT, CENTER, RIGHT}
	
	public static Color defaultFontColor = Color.BLACK;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private FontGenerator fontGenerator;
	
	private String text;
	private Property2D fontSize;
	private Font font;
	
	private Color fontColor;
	
	private VerticalAlignment verticalAlignment;
	private HorizontalAlignment horizontalAlignment;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public KLabel()
	{
		super();
		init();
		
	}
	public KLabel(final String text)
	{
		super();
		init();
		
		this.text = text;
	}
	public KLabel(final Property2D fontSize)
	{
		super();
		init();
		
		setFontSize(fontSize);
	}
	public KLabel(final int fontSize)
	{
		super();
		init();
		
		setFontSize(fontSize);
	}
	public KLabel(final float fontSize)
	{
		super();
		init();
		
		setFontSize(fontSize);
	}
	public KLabel(final String text, final Property2D fontSize)
	{
		super();
		init();
		
		this.text = text;
		setFontSize(fontSize);
	}
	public KLabel(final String text, final int fontSize)
	{
		super();
		init();
		
		this.text = text;
		setFontSize(fontSize);
	}
	public KLabel(final String text, final float fontSize)
	{
		super();
		init();
		
		this.text = text;
		setFontSize(fontSize);
	}
	
	private void init()
	{
		text = "";
		
		setFontSize(20);
		fontGenerator = new FontGenerator();
		font = fontGenerator.getFont(new FontSpecs());
		fontColor = defaultFontColor;
		
		verticalAlignment = VerticalAlignment.CENTER;
		horizontalAlignment = HorizontalAlignment.CENTER;
	}
	
	public KLabel setText(final String text)
	{
		this.text = text;
		return this;
	}
	
	public KLabel setFontSize(final int fontSize)
	{
		if (fontSize < 0)
		{
			throw new InvalidParameterException("KLabel", "setFontSize", "fontSize", Integer.toString(fontSize), "Can not be < 0");
		}
		
		this.fontSize = new Property2D(fontSize);
		return this;
	}
	public KLabel setFontSize(final float fontSize)
	{
		if (fontSize < 0)
		{
			throw new InvalidParameterException("KLabel", "setFontSize", "fontSize", Float.toString(fontSize), "Can not be < 0");
		}
		
		this.fontSize = new Property2D(fontSize);
		return this;
	}
	public KLabel setFontSize(final Property2D fontSize)
	{
		if (fontSize == null)
		{
			throw new InvalidParameterException("KLabel", "setFontSize", "fontSize");
		}
		
		this.fontSize = fontSize;
		return this;
	}
	
	public KLabel setVertAlignment(final VerticalAlignment verticalAlignment)
	{
		this.verticalAlignment = verticalAlignment;
		return this;
	}
	public KLabel setHorizAlignment(final HorizontalAlignment horizontalAlignment)
	{
		this.horizontalAlignment = horizontalAlignment;
		return this;
	}
	
	public KLabel setFont(final FontSpecs fontSpecs)
	{
		font = fontGenerator.getFont(fontSpecs);
		return this;
	}
	public KLabel setFontStyle(final int style)
	{
		font = font.deriveFont(style);
		return this;
	}
	public KLabel setFontColor(final Color color)
	{
		fontColor = color;
		return this;
	}
	
	@Override
	protected void display(Graphics g, Rectangle nodeArea)
	{
		int x = 0;
		int y = 0;
		FontMetrics fm;
		
		font = font.deriveFont((float) fontSize.calculate(nodeArea.getWidth(), nodeArea.getHeight()));
		g.setFont(font);
		fm = g.getFontMetrics();
		
		int height = fm.getHeight() - fm.getLeading();
		int width = fm.stringWidth(text);
		
		switch (horizontalAlignment)
		{
			case LEFT:
				x = nodeArea.getXMin();
				break;
			case RIGHT:
				x = nodeArea.getXMax() - width;
				break;
			case CENTER:
				x = nodeArea.getCenterX() - width / 2;
				break;
			default:
				throw new RuntimeException("INVALID HORIZONTAL ALIGNMENT");
		}
		switch (verticalAlignment)
		{
			case TOP:
				y = nodeArea.getYMin() + height - fm.getAscent() / 2;
				break;
			case BOTTOM:
				y = nodeArea.getYMax() - fm.getDescent();
				break;
			case CENTER:
				y = nodeArea.getCenterY() - (height + fm.getDescent()) / 2 + fm.getAscent();
				break;
			default:
				throw new RuntimeException("INVALID VERTICAL ALIGNMENT");
		}
		
		g.setColor(fontColor);
		g.drawString(text, x, y);
	}
	
}
