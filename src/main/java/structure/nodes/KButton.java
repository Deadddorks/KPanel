package structure.nodes;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
import structure.orgainization.Margin;
import structure.orgainization.PaddingSet;
import structure.orgainization.Property2D;
import structure.orgainization.graphical.Rectangle;

import java.awt.*;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class KButton extends Node
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	public static Color defaultFillColor = Color.WHITE;
	public static Color defaultStrokeColor = Color.BLACK;
	public static Property2D defaultStrokeWidth = new Property2D(3);
	public static Property2D defaultCornerRadius = new Property2D(0);
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	private KLabel label;
	
	private Color fillColor;
	private Color strokeColor;
	
	private Property2D strokeWidth;
	private Property2D cornerRadius;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public KButton()
	{
		init();
	}
	
	private void init()
	{
		defaultPaddingSet = new PaddingSet(true);
		
		listenForMouseEvents = true;
		
		label = new KLabel();
		setFillColor(defaultFillColor);
		setStrokeColor(defaultStrokeColor);
		
		setStrokeWidth(defaultStrokeWidth);
		setCornerRadius(defaultCornerRadius);
	}
	
	public KLabel getLabel()
	{
		return label;
	}
	
	public KButton setFillColor(final Color color)
	{
		if (color == null)
		{
			throw new InvalidParameterException("KButton", "setFillColor", "color");
		}
		fillColor = color;
		return this;
	}
	public KButton setStrokeColor(final Color color)
	{
		if (color == null)
		{
			throw new InvalidParameterException("KButton", "setStrokeColor", "color");
		}
		strokeColor = color;
		return this;
	}
	
	public KButton setStrokeWidth(final int strokeWidth)
	{
		if (strokeWidth < 0)
		{
			throw new InvalidParameterException("KButton", "setStrokeWidth", "strokeWidth", Integer.toString(strokeWidth), "Can not be < 0");
		}
		this.strokeWidth = new Property2D(strokeWidth);
		return this;
	}
	public KButton setStrokeWidth(final float strokeWidth)
	{
		if (strokeWidth < 0)
		{
			throw new InvalidParameterException("KButton", "setStrokeWidth", "strokeWidth", Float.toString(strokeWidth), "Can not be < 0");
		}
		this.strokeWidth = new Property2D(strokeWidth);
		return this;
	}
	public KButton setStrokeWidth(final Property2D strokeWidth)
	{
		if (strokeWidth == null)
		{
			throw new InvalidParameterException("KButton", "setStrokeWidth", "strokeWidth");
		}
		this.strokeWidth = strokeWidth;
		return this;
	}
	
	public KButton setCornerRadius(final int cornerRadius)
	{
		if (cornerRadius < 0)
		{
			throw new InvalidParameterException("KButton", "setCornerRadius", "cornerRadius", Integer.toString(cornerRadius), "Can not be < 0");
		}
		this.cornerRadius = new Property2D(cornerRadius);
		return this;
	}
	public KButton setCornerRadius(final float cornerRadius)
	{
		if (cornerRadius < 0)
		{
			throw new InvalidParameterException("KButton", "setCornerRadius", "cornerRadius", Float.toString(cornerRadius), "Can not be < 0");
		}
		this.cornerRadius = new Property2D(cornerRadius);
		return this;
	}
	public KButton setCornerRadius(final Property2D cornerRadius)
	{
		if (cornerRadius == null)
		{
			throw new InvalidParameterException("KButton", "setCornerRadius", "cornerRadius");
		}
		this.cornerRadius = cornerRadius;
		return this;
	}
	
	@Override
	protected void display(Graphics g, Rectangle nodeArea)
	{
		int stroke = strokeWidth.calculate(nodeArea.getWidth(), nodeArea.getHeight());
		int radius = cornerRadius.calculate(nodeArea.getWidth(), nodeArea.getHeight());
		
		if (stroke > 0)
		{
			g.setColor(strokeColor);
			if (radius > 0)
			{
				g.fillRoundRect(nodeArea.getXMin(), nodeArea.getYMin(), nodeArea.getWidth(), nodeArea.getHeight(), radius * 2, radius * 2);
			}
			else
			{
				g.fillRect(nodeArea.getXMin(), nodeArea.getYMin(), nodeArea.getWidth(), nodeArea.getHeight());
			}
		}
		
		g.setColor(fillColor);
		if (radius > 0)
		{
			g.fillRoundRect(nodeArea.getXMin() + stroke, nodeArea.getYMin() + stroke, nodeArea.getWidth() - 2 * stroke, nodeArea.getHeight() - 2 * stroke,
					radius * 2 - stroke, radius * 2 - stroke);
		}
		else
		{
			g.fillRect(nodeArea.getXMin() + stroke, nodeArea.getYMin() + stroke, nodeArea.getWidth() - 2 * stroke, nodeArea.getHeight() - 2 * stroke);
		}
		
		label.display(g, new Rectangle(nodeArea.getXMin() + stroke, nodeArea.getYMin() + stroke, nodeArea.getXMax() - 2 * stroke, nodeArea.getYMax() - 2 * stroke));
	}
	
}
