import gui.KFrame;
import gui.KPanel;
import structure.grid.Grid;
import structure.nodes.Button;
import structure.orgainization.AlignmentType;
import structure.orgainization.GCSpec;
import structure.orgainization.GridConstraint;

import java.awt.*;

public class KPanelTest extends KPanel
{
	
	@Override
	protected void run_init()
	{
		grid = new Grid(10);
		
		grid.showGridConstraintLines(true);
		grid.showPaddingLines(true);
		
		grid.addConstraint(Grid.Orientation.VERTICAL, new GridConstraint(0.25f, 10));
		grid.addConstraint(Grid.Orientation.VERTICAL, new GridConstraint(0.5f, 10));
		grid.addConstraint(Grid.Orientation.VERTICAL, new GridConstraint(0.75f, 10));
		grid.addConstraint(Grid.Orientation.HORIZONTAL, new GridConstraint(0.25f, 10));
		grid.addConstraint(Grid.Orientation.HORIZONTAL, new GridConstraint(0.5f, 10));
		grid.addConstraint(Grid.Orientation.HORIZONTAL, new GridConstraint(0.75f, 10));
		
		Button b = new Button();
		grid.addNode(b, new GCSpec(AlignmentType.PERCENT, 1), new GCSpec(AlignmentType.PERCENT, 1));
		
	}
	
	@Override
	protected void run_draw(Graphics g)
	{
	
	}
	
	public static void main(String[] args)
	{
		KFrame frame = new KFrame(new KPanelTest())
		{
			@Override
			public void settings_initFrame()
			{
				setResizable(true);
			}
		};
	}
	
}
