package gui;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import javax.swing.JFrame;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Allows easy creation of a JFrame that links well with the KPanel class
 * @author Deaddorks
 */
public abstract class KFrame extends JFrame
{
	
	// ----- KLabel -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	private final boolean DEFAULT_RESIZABLE = false;
	
	private final int DEFAULT_WIDTH = 500;
	private final int DEFAULT_HEIGHT = 500;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a KPanel with the specified JPanel as its child
	 * @param panel the specified KPanel
	 */
	public KFrame(KPanel panel)
	{
		setResizable(DEFAULT_RESIZABLE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(panel);
		
		settings_initFrame();
		
		setVisible(true);
	}
	
	/**
	 * Method that allows the extending class to initialize JFrame settings before the JFrame is displayed
	 */
	public abstract void settings_initFrame();
	
}
