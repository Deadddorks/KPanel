package gui;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import javax.swing.JFrame;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public abstract class KFrame extends JFrame
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	private final boolean DEFAULT_RESIZABLE = false;
	
	private final int DEFAULT_WIDTH = 500;
	private final int DEFAULT_HEIGHT = 500;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
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
	
	public abstract void settings_initFrame();
	
}
