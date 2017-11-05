package gui;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import structure.grid.Grid;
import structure.orgainization.graphical.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Modified JPanel that allows a programmer to easily display Nodes in a Grid format
 * and automatically set up event handling for those Nodes
 * @author Deaddorks
 */
public abstract class KPanel extends JPanel
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	// ----- Init -----
	protected boolean setting_hasRunInit;
	// ----- Background -----
	protected boolean setting_drawBackground;
	protected Color setting_backgroundColor;
	// ----- Repainting -----
	protected boolean setting_autoRepaint;
	// ----- Grid -----
	protected Grid grid;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public KPanel()
	{
		grid = new Grid();
		init();
	}
	
	private void init()
	{
		addKeyListener(new KeyClickHandler());
		addMouseListener(new MouseClickHandler());
		addMouseMotionListener(new MouseMotionHandler());
		addMouseWheelListener(new ScrollHandler());
		
		setting_hasRunInit = false;
		setting_drawBackground = true;
		setting_backgroundColor = Color.WHITE;
		setting_autoRepaint = false;
	}
	
	/**
	 * Is called as soon as the paint method starts running, and only runs once<br>
	 * Allows the user to initialize variables and such that needs to happen after<br>
	 * the JPanel is initialized
	 */
	protected abstract void run_init();
	/**
	 * The method that the is used to draw to draw to the JPanel,<br>
	 * should be used as opposed to @Overriding the original paint() method
	 * @param g Graphics to draw to the parent JPanel
	 */
	protected abstract void run_draw(Graphics g);
	
	/**
	 * Runs some features for the user automatically in the background<br>
	 * Children should extend run_draw() for normally paint() functionality<br>
	 * unless they really want to Override this functionality
	 * @param g Graphics used to draw to the parent JPanel
	 */
	@Override
	public void paint(Graphics g)
	{
		// Run run_init at the beginning
		if (!setting_hasRunInit)
		{
			run_init();
			setting_hasRunInit = true;
		}
		// Draw background
		if (setting_drawBackground)
		{
			g.setColor(setting_backgroundColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		// Draw the Grid
		grid.draw(g, new Rectangle(0, 0, getWidth(), getHeight()));
		// Run the run_draw command
		run_draw(g);
		// Repaint
		if (setting_autoRepaint)
		{
			repaint();
		}
	}
	
	// ---------- Listener Methods ----------
	// ----- Mouse -----
	/**
	 * Allows a class that extends KPanel to handle MouseEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseClicked(MouseEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle MouseEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mousePressed(MouseEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle MouseEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseReleased(MouseEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle MouseEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseEntered(MouseEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle MouseEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseExited(MouseEvent e) {}
	// ----- Mouse Motion -----
	/**
	 * Allows a class that extends KPanel to handle MouseMotionEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseMoved(MouseEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle MouseMotionEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseEvent that triggered this method
	 */
	protected void handle_mouseDragged(MouseEvent e) {}
	// ----- Mouse Wheel -----
	/**
	 * Allows a class that extends KPanel to handle MouseWheelEvents<br>
	 * easily by @Overriding this method
	 * @param e MouseWheelEvent that triggered this method
	 */
	protected void handle_mouseScrolled(MouseWheelEvent e) {}
	// ----- Keyboard -----
	/**
	 * Allows a class that extends KPanel to handle KeyEvents<br>
	 * easily by @Overriding this method
	 * @param e KeyEvent that triggered this method
	 */
	protected void handle_keyTyped(KeyEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle KeyEvents<br>
	 * easily by @Overriding this method
	 * @param e KeyEvent that triggered this method
	 */
	protected void handle_keyPressed(KeyEvent e) {}
	/**
	 * Allows a class that extends KPanel to handle KeyEvents<br>
	 * easily by @Overriding this method
	 * @param e KeyEvent that triggered this method
	 */
	protected void handle_keyReleased(KeyEvent e) {}
	
	// ----------------------------------- Listeners -----------------------------------
	/**
	 * MouseListener for the KPanel
	 * @author Deaddorks
	 */
	private class MouseClickHandler implements MouseListener
	{
		
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseClicked(MouseEvent e)
		{
			handle_mouseClicked(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mousePressed(MouseEvent e)
		{
			grid.mousePressDetected(e);
			handle_mousePressed(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseReleased(MouseEvent e)
		{
			grid.mouseReleaseDetected(e);
			handle_mouseReleased(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseEntered(MouseEvent e)
		{
			handle_mouseEntered(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseExited(MouseEvent e)
		{
			handle_mouseExited(e);
		}
	}
	/**
	 * MouseMotionListener for the KPanel
	 * @author Deaddorks
	 */
	private class MouseMotionHandler implements MouseMotionListener
	{
		
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseDragged(MouseEvent e)
		{
			grid.mouseDragDetected(e);
			handle_mouseDragged(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseMoved(MouseEvent e)
		{
			grid.mouseMoveDetected(e);
			handle_mouseMoved(e);
		}
	}
	/**
	 * KeyListener for the KPanel
	 * @author Deaddorks
	 */
	private class KeyClickHandler implements KeyListener
	{
		
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void keyTyped(KeyEvent e)
		{
			handle_keyTyped(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void keyPressed(KeyEvent e)
		{
			handle_keyPressed(e);
		}
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void keyReleased(KeyEvent e)
		{
			handle_keyReleased(e);
		}
	}
	/**
	 * MouseWheelListener for the KPanel
	 * @author Deaddorks
	 */
	private class ScrollHandler implements MouseWheelListener
	{
		
		/**
		 * Calls the corresponding method in KPanel
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			grid.mouseScrollDetected(e);
			handle_mouseScrolled(e);
		}
	}
}
