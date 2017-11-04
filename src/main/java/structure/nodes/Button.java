package structure.nodes;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import structure.orgainization.PaddingSet;
import structure.orgainization.graphical.Location;
import structure.orgainization.graphical.Rectangle;

import java.awt.*;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public class Button extends Node
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public Button()
	{
		super();
		init();
		
	}
	
	private void init()
	{
		setPaddingSet(new PaddingSet(true));
	}
	
	@Override
	protected void display(Graphics g, Rectangle nodeArea)
	{
		g.setColor(Color.BLUE);
		g.fillRect(nodeArea.getXMin(), nodeArea.getYMin(), nodeArea.getWidth(), nodeArea.getHeight());
	}
	
}
