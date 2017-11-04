package structure.grid;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.InvalidParameterException;
import structure.nodes.Node;
import structure.orgainization.*;
import structure.orgainization.graphical.Location;
import structure.orgainization.graphical.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * Used by the KPanel to organize Nodes in a Grid fashion.<br>
 * Also deals with the delegation of all Events to Nodes
 * @author Deaddorks
 */
public class Grid
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	public enum Orientation {VERTICAL, HORIZONTAL}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	// ----- Nodes -----
	private List<Node> nodes;
	private List<Node> mouseNodes;
	private List<Node> mouseMotionNodes;
	private List<Node> mouseScrollNodes;
	private List<Node> keyNodes;
	private List<Node> focusableNodes;
	// ----- Constraints -----
	private GridConstraintList verticalConstantConstraints;
	private GridConstraintList verticalPercentConstraints;
	private GridConstraintList horizontalConstantConstraints;
	private GridConstraintList horizontalPercentConstraints;
	// ----- Settings -----
	private boolean drawGridConstraints;
	private boolean drawGridPaddings;
	private Color constraintLineColor;
	private Color paddingLineColor;
	private int constraintLineStroke;
	private int paddingLineStroke;
	// ----- Size -----
	private Rectangle area;
	// ----- Event Handling -----
	private List<Node> pressedNodes;
	private List<Node> mouseInsideNodes;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a Grid with an outerPadding with a CONSTANT value of 0
	 */
	public Grid()
	{
		init();
		
		addConstraint(Orientation.VERTICAL, new GridConstraint(0f));
		addConstraint(Orientation.VERTICAL, new GridConstraint(1f));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(0f));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(1f));
	}
	/**
	 * Creates a Grid with an outerPadding with a CONSTANT value of the specified outerPadding
	 * @param outerPadding CONSTANT value for the padding around the edge of the Grid
	 */
	public Grid(final int outerPadding)
	{
		init();
		
		addConstraint(Orientation.VERTICAL, new GridConstraint(0f, outerPadding));
		addConstraint(Orientation.VERTICAL, new GridConstraint(1f, outerPadding));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(0f, outerPadding));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(1f, outerPadding));
	}
	/**
	 * Creates a Grid with an outerPadding with a PERCENT value of the specified outerPadding
	 * @param outerPadding PERCENT value for the padding around the edge of the Grid
	 */
	public Grid(final float outerPadding)
	{
		init();
		
		addConstraint(Orientation.VERTICAL, new GridConstraint(0f, outerPadding));
		addConstraint(Orientation.VERTICAL, new GridConstraint(1f, outerPadding));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(0f, outerPadding));
		addConstraint(Orientation.HORIZONTAL, new GridConstraint(1f, outerPadding));
	}
	
	private void init()
	{
		nodes = new ArrayList<>();
		mouseNodes = new ArrayList<>();
		mouseMotionNodes = new ArrayList<>();
		mouseScrollNodes = new ArrayList<>();
		focusableNodes = new ArrayList<>();
		keyNodes = new ArrayList<>();
		
		mouseInsideNodes = new ArrayList<>();
		
		verticalConstantConstraints = new GridConstraintList();
		verticalPercentConstraints = new GridConstraintList();
		horizontalConstantConstraints = new GridConstraintList();
		horizontalPercentConstraints = new GridConstraintList();
		
		drawGridConstraints = false;
		drawGridPaddings = false;
		constraintLineColor = Color.BLACK;
		paddingLineColor = Color.RED;
		constraintLineStroke = 2;
		paddingLineStroke = 1;
		
		area = null;
	}
	
	/**
	 * Take all the nodes and add them to the separate Lists based on what sort of events they are listening for
	 */
	public void refactorNodes()
	{
		
		mouseNodes = new ArrayList<>();
		mouseMotionNodes = new ArrayList<>();
		mouseScrollNodes = new ArrayList<>();
		focusableNodes = new ArrayList<>();
		keyNodes = new ArrayList<>();
		
		for (Node node : nodes)
		{
			if (node.isListeningForMouseEvents())
			{
				mouseNodes.add(node);
			}
			if (node.isListeningForMouseMotionEvents())
			{
				mouseMotionNodes.add(node);
			}
			if (node.isFocusable())
			{
				focusableNodes.add(node);
			}
			if (node.isListeningForKeyEvents())
			{
				keyNodes.add(node);
			}
			if (node.isListeningForMouseWheelEvents())
			{
				mouseScrollNodes.add(node);
			}
		}
	}
	
	/**
	 * Is called by the KPanel whenever a MousePress is detected.<br>
	 * Will search through all Nodes which are listening for MouseEvents and<br>
	 * call its onMousePressed() method if the press is within the Nodes bounds
	 * @param e MouseEvent associated with the call of this method
	 */
	public void mousePressDetected(final MouseEvent e)
	{
		Location loc = new Location(e);
		pressedNodes = new ArrayList<>();
		for (Node node : mouseNodes)
		{
			if (node.isLocInArea(loc, area))
			{
				pressedNodes.add(node);
				node.onMousePressed(e);
			}
		}
	}
	/**
	 * Is called by the KPanel whenever a MouseRelease is detected.<br>
	 * Will search through all Nodes which are listening for MouseEvents and<br>
	 * call its onMouseReleased() method if the press is within the Nodes bounds,<br>
	 * the Nodes onMouseClicked() method will also be called if it was both pressed and released
	 * @param e MouseEvent associated with the call of this method
	 */
	public void mouseReleaseDetected(final MouseEvent e)
	{
		Location loc = new Location(e);
		for (Node node : mouseNodes)
		{
			if (node.isLocInArea(loc, area))
			{
				node.onMouseReleased(e);
				if (pressedNodes.contains(node))
				{
					node.onMouseClicked(e);
				}
			}
		}
	}
	
	/**
	 * Is called by the KPanel whenever a MouseMove is detected.<br>
	 * Will search through all Nodes which are listening for MouseMotionEvents and<br>
	 * call its onMouseMoved() method if the movement is within the Nodes bounds
	 * @param e MouseEvent associated with the call of this method
	 */
	public void mouseMoveDetected(final MouseEvent e)
	{
		Location loc = new Location(e);
		for (Node node : mouseNodes)
		{
			if (!mouseInsideNodes.contains(node) && node.isLocInArea(loc, area))
			{
				mouseInsideNodes.add(node);
				node.onMouseEntered(e);
			}
			else if (mouseInsideNodes.contains(node) && !node.isLocInArea(loc, area))
			{
				mouseInsideNodes.remove(node);
				node.onMouseExited(e);
			}
		}
		for (Node node : mouseMotionNodes)
		{
			if (node.isLocInArea(loc, area))
			{
				node.onMouseMoved(e);
			}
		}
	}
	/**
	 * Is called by the KPanel whenever a MouseDrag is detected.<br>
	 * Will search through all Nodes which are listening for MouseMotionEvents and<br>
	 * call its onMouseDragged() method if the drag is within the Nodes bounds
	 * @param e MouseEvent associated with the call of this method
	 */
	public void mouseDragDetected(final MouseEvent e)
	{
		Location loc = new Location(e);
		for (Node node : mouseMotionNodes)
		{
			if (node.isLocInArea(loc, area))
			{
				node.onMouseDragged(e);
			}
		}
	}
	
	/**
	 * Is called by the KPanel whenever a MouseWheelEvent is detected.<br>
	 * Will search through all Nodes which are listening for MouseWheelEvents and<br>
	 * call its onMouseScrolled() method if the scroll is within the Nodes bounds
	 * @param e MouseWheelEvent associated with the call of this method
	 */
	public void mouseScrollDetected(final MouseWheelEvent e)
	{
		Location loc = new Location(e);
		for (Node node : mouseScrollNodes)
		{
			if (node.isLocInArea(loc, area))
			{
				node.onMouseScrolled(e);
			}
		}
	}
	
	/**
	 * Takes an Orientation and GridConstraint and adds it to its respective GridConstraintList
	 * @param orientation Orientation of the GridConstraint
	 * @param gridConstraint GridConstraint to be added
	 */
	public void addConstraint(final Orientation orientation, final GridConstraint gridConstraint)
	{
		if (orientation == null)
		{
			throw new InvalidParameterException("Grid", "addConstraint", "orientation");
		}
		if (gridConstraint == null)
		{
			throw new InvalidParameterException("Grid", "addConstraint", "gridConstraint");
		}
		
		if (orientation == Orientation.VERTICAL && gridConstraint.getAlignmentType() == AlignmentType.CONSTANT)
		{
			verticalConstantConstraints.add(gridConstraint);
		}
		else if (orientation == Orientation.VERTICAL && gridConstraint.getAlignmentType() == AlignmentType.PERCENT)
		{
			verticalPercentConstraints.add(gridConstraint);
		}
		else if (orientation == Orientation.HORIZONTAL && gridConstraint.getAlignmentType() == AlignmentType.CONSTANT)
		{
			horizontalConstantConstraints.add(gridConstraint);
		}
		else if (orientation == Orientation.HORIZONTAL && gridConstraint.getAlignmentType() == AlignmentType.PERCENT)
		{
			horizontalPercentConstraints.add(gridConstraint);
		}
		else
		{
			throw new RuntimeException("INVALID ORIENTATION or CONSTRAINT");
		}
	}
	
	/**
	 * Gets the GridConstraint from the specified GridConstraintList at the specified GCSpec
	 * @param orientation Orientation of the GridConstraintList
	 * @param spec GCSpec of the GridConstraintList
	 * @return the GridConstraint from the specified GridConstraintList at the specified index
	 */
	public GridConstraint getConstraint(final Orientation orientation, final GCSpec spec)
	{
		return getConstraint(orientation, spec.getAlignment(), spec.getIndex());
	}
	/**
	 * Gets the GridConstraint from the specified GridConstraintList at the specified index
	 * @param orientation Orientation of the GridConstraintList
	 * @param alignment AlignmentType of the GridConstraintList
	 * @param index index in the list
	 * @return the GridConstraint from the specified GridConstraintList at the specified index
	 */
	public GridConstraint getConstraint(final Orientation orientation, final AlignmentType alignment, final int index)
	{
		switch (orientation)
		{
			case VERTICAL:
				switch (alignment)
				{
					case CONSTANT:
						return verticalConstantConstraints.get(index);
					case PERCENT:
						return verticalPercentConstraints.get(index);
					default:
						throw new RuntimeException("{VERTICAL} INVALID ALIGNMENT");
				}
			case HORIZONTAL:
				switch (alignment)
				{
					case CONSTANT:
						return horizontalConstantConstraints.get(index);
					case PERCENT:
						return horizontalPercentConstraints.get(index);
					default:
						throw new RuntimeException("{HORIZONTAL} INVALID ALIGNMENT");
				}
			default:
				throw new RuntimeException("INVALID ORIENTATION");
		}
	}
	
	/**
	 * Sets the totals for the Vertical PERCENT GridConstraints
	 */
	public void setWidthTotal()
	{
		verticalPercentConstraints.setTotals(area.getWidth());
	}
	/**
	 * Sets the totals for the Horizontal PERCENT GridConstraints
	 */
	public void setHeightTotal()
	{
		horizontalPercentConstraints.setTotals(area.getHeight());
	}
	
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the Margin to the DEFAULT Margin of the Node if it is not already defined<br>
	 * Sets the PaddingSet to the DEFAULT PaddingSet of the Node if it is not already defined<br>
	 * Sets the rightGridConstraint to the next GridConstraint after the the leftGridConstraint<br>
	 * Sets the bottomGridConstraint to the next GridConstraint after the the topGridConstraint
	 * @param node the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 */
	public void addNode(final Node node, final GCSpec leftGridConstraint, final GCSpec topGridConstraint)
	{
		node.setMarginsToDefaultIfUndefined();
		node.setPaddingSetToDefaultIfUndefined();
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, leftGridConstraint.getNext()),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint.getNext()));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the PaddingSet to the DEFAULT PaddingSet of the Node if it is not already defined<br>
	 * Sets the rightGridConstraint to the next GridConstraint after the the leftGridConstraint<br>
	 * Sets the bottomGridConstraint to the next GridConstraint after the the topGridConstraint
	 * @param node the Node
	 * @param margins Margin of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 */
	public void addNode(final Node node, final Margin margins,
						final GCSpec leftGridConstraint, final GCSpec topGridConstraint)
	{
		node.setMargins(margins);
		node.setPaddingSetToDefaultIfUndefined();
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, leftGridConstraint.getNext()),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint.getNext()));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the Margin to the DEFAULT Margin of the Node if it is not already defined<br>
	 * Sets the rightGridConstraint to the next GridConstraint after the the leftGridConstraint<br>
	 * Sets the bottomGridConstraint to the next GridConstraint after the the topGridConstraint
	 * @param node the Node
	 * @param paddingSet PaddingSet of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 */
	public void addNode(final Node node, final PaddingSet paddingSet,
						final GCSpec leftGridConstraint, final GCSpec topGridConstraint)
	{
		node.setMarginsToDefaultIfUndefined();
		node.setPaddingSet(paddingSet);
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, leftGridConstraint.getNext()),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint.getNext()));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the rightGridConstraint to the next GridConstraint after the the leftGridConstraint<br>
	 * Sets the bottomGridConstraint to the next GridConstraint after the the topGridConstraint
	 * @param node the Node
	 * @param margins Margin of the Node
	 * @param paddingSet PaddingSet of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 */
	public void addNode(final Node node, final Margin margins, final PaddingSet paddingSet,
						final GCSpec leftGridConstraint, final GCSpec topGridConstraint)
	{
		node.setMargins(margins);
		node.setPaddingSet(paddingSet);
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, leftGridConstraint.getNext()),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint.getNext()));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the Margin to the DEFAULT Margin of the Node if it is not already defined<br>
	 * Sets the PaddingSet to the DEFAULT PaddingSet of the Node if it is not already defined
	 * @param node the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param rightGridConstraint GCSpec for the right GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 * @param bottomGridConstraint GCSpec for bottom left GridConstraint
	 */
	public void addNode(final Node node,
						final GCSpec leftGridConstraint, final GCSpec rightGridConstraint,
						final GCSpec topGridConstraint, final GCSpec bottomGridConstraint)
	{
		node.setMarginsToDefaultIfUndefined();
		node.setPaddingSetToDefaultIfUndefined();
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, rightGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, bottomGridConstraint));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the PaddingSet to the DEFAULT PaddingSet of the Node if it is not already defined
	 * @param node the Node
	 * @param margins Margin of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param rightGridConstraint GCSpec for the right GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 * @param bottomGridConstraint GCSpec for bottom left GridConstraint
	 */
	public void addNode(final Node node, final Margin margins,
						final GCSpec leftGridConstraint, final GCSpec rightGridConstraint,
						final GCSpec topGridConstraint, final GCSpec bottomGridConstraint)
	{
		node.setMargins(margins);
		node.setPaddingSetToDefaultIfUndefined();
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, rightGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, bottomGridConstraint));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints<br>
	 * Sets the Margin to the DEFAULT Margin of the Node if it is not already defined
	 * @param node the Node
	 * @param paddingSet PaddingSet of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param rightGridConstraint GCSpec for the right GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 * @param bottomGridConstraint GCSpec for bottom left GridConstraint
	 */
	public void addNode(final Node node, final PaddingSet paddingSet,
						final GCSpec leftGridConstraint, final GCSpec rightGridConstraint,
						final GCSpec topGridConstraint, final GCSpec bottomGridConstraint)
	{
		node.setMarginsToDefaultIfUndefined();
		node.setPaddingSet(paddingSet);
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, rightGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, bottomGridConstraint));
		addNode(node);
	}
	/**
	 * Takes the specified Node and adds it to the Grid with the specified constraints
	 * @param node the Node
	 * @param margins Margin of the Node
	 * @param paddingSet PaddingSet of the Node
	 * @param leftGridConstraint GCSpec for the left GridConstraint
	 * @param rightGridConstraint GCSpec for the right GridConstraint
	 * @param topGridConstraint GCSpec for the top GridConstraint
	 * @param bottomGridConstraint GCSpec for bottom left GridConstraint
	 */
	public void addNode(final Node node, final Margin margins, final PaddingSet paddingSet,
						final GCSpec leftGridConstraint, final GCSpec rightGridConstraint,
						final GCSpec topGridConstraint, final GCSpec bottomGridConstraint)
	{
		node.setMargins(margins);
		node.setPaddingSet(paddingSet);
		node.setConstraints(
				getConstraint(Orientation.VERTICAL, leftGridConstraint),
				getConstraint(Orientation.VERTICAL, rightGridConstraint),
				getConstraint(Orientation.HORIZONTAL, topGridConstraint),
				getConstraint(Orientation.HORIZONTAL, bottomGridConstraint));
		addNode(node);
	}
	/**
	 * Adds the specified Nodes to the list of Nodes.<br>
	 * Should be avoided unless you know that all of the aspects of the Node<br>
	 * have been initialized
	 * @param node Node to add
	 */
	public void addNode(final Node node)
	{
		nodes.add(node);
		if (node.isListeningForMouseEvents())
		{
			mouseNodes.add(node);
		}
		if (node.isListeningForMouseMotionEvents())
		{
			mouseMotionNodes.add(node);
		}
		if (node.isFocusable())
		{
			focusableNodes.add(node);
		}
		if (node.isListeningForKeyEvents())
		{
			keyNodes.add(node);
		}
		if (node.isListeningForMouseWheelEvents())
		{
			mouseScrollNodes.add(node);
		}
	}
	/**
	 * Gets the List of Nodes
	 * @return the List of Nodes
	 */
	public List<Node> getNodes()
	{
		return nodes;
	}
	
	/**
	 * Draws all of the Nodes contained within the grid
	 * @param g Graphics of the Panel
	 * @param area Rectangle that represents the area of the Grid
	 */
	public void draw(Graphics g, final Rectangle area)
	{
		this.area = area;
		setWidthTotal();
		setHeightTotal();
		
		if (drawGridPaddings)
		{
			// Sets the color to the color for constraints
			g.setColor(paddingLineColor);
			// Draws the VERTICAL constraints
			for (GridConstraint gC : verticalConstantConstraints.getAll())
			{
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.LEFT) - paddingLineStroke / 2, area.getYMin(),
						paddingLineStroke, area.getHeight());
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.RIGHT) - paddingLineStroke / 2, area.getYMin(),
						paddingLineStroke, area.getHeight());
			}
			for (GridConstraint gC : verticalPercentConstraints.getAll())
			{
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.LEFT) - paddingLineStroke / 2, area.getYMin(),
						paddingLineStroke, area.getHeight());
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.RIGHT) - paddingLineStroke / 2, area.getYMin(),
						paddingLineStroke, area.getHeight());
			}
			// Draws the VERTICAL constraints
			for (GridConstraint gC : horizontalConstantConstraints.getAll())
			{
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.LEFT) - paddingLineStroke / 2,
						area.getWidth(), paddingLineStroke);
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.RIGHT) - paddingLineStroke / 2,
						area.getWidth(), paddingLineStroke);
			}
			for (GridConstraint gC : horizontalPercentConstraints.getAll())
			{
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.LEFT) - paddingLineStroke / 2,
						area.getWidth(), paddingLineStroke);
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.RIGHT) - paddingLineStroke / 2,
						area.getWidth(), paddingLineStroke);
			}
		}
		if (drawGridConstraints)
		{
			// Sets the color to the color for constraints
			g.setColor(constraintLineColor);
			// Draws the VERTICAL constraints
			for (GridConstraint gC : verticalConstantConstraints.getAll())
			{
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.CENTER) - constraintLineStroke / 2, area.getYMin(),
						constraintLineStroke, area.getHeight());
			}
			for (GridConstraint gC : verticalPercentConstraints.getAll())
			{
				g.fillRect(area.getXMin() + gC.getValue(GridConstraint.Boundary.CENTER) - constraintLineStroke / 2, area.getYMin(),
						constraintLineStroke, area.getHeight());
			}
			// Draws the VERTICAL constraints
			for (GridConstraint gC : horizontalConstantConstraints.getAll())
			{
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.CENTER) - constraintLineStroke / 2,
						area.getWidth(), constraintLineStroke);
			}
			for (GridConstraint gC : horizontalPercentConstraints.getAll())
			{
				g.fillRect(area.getXMin(), area.getYMin() + gC.getValue(GridConstraint.Boundary.CENTER) - constraintLineStroke / 2,
						area.getWidth(), constraintLineStroke);
			}
		}
		
		for (Node node : nodes)
		{
			node.draw(g, area);
		}
	}
	
	/**
	 * Sets whether or not the Grid will draw its Constraints on the Panel
	 * @param show value for whether or not the Constraints will be drawn
	 */
	public void showGridConstraintLines(final boolean show)
	{
		drawGridConstraints = show;
	}
	/**
	 * Sets whether or not the Grid will draw its Paddings on the Panel
	 * @param show value for whether or not the Paddings will be drawn
	 */
	public void showPaddingLines(final boolean show)
	{
		drawGridPaddings = show;
	}
	/**
	 * Sets whether or not the Grid will draw its Constraints & Paddings on the Panel
	 * @param show value for whether or not the Constraints & Paddings will be drawn
	 */
	public void showLines(final boolean show)
	{
		drawGridConstraints = show;
		drawGridPaddings = show;
	}
	/**
	 * Sets whether or not the Grid will draw its Constraints & Paddings on the Panel
	 * @param showGridConstraintLines value for whether or not the Constraints will be drawn
	 * @param showPaddingLines value for whether or not the Paddings will be drawn
	 */
	public void showLines(final boolean showGridConstraintLines, final boolean showPaddingLines)
	{
		drawGridConstraints = showGridConstraintLines;
		drawGridPaddings = showPaddingLines;
	}
	
	/**
	 * Sets the Color of the lines that represent
	 * @param color Color of the lines that represent GridConstraints
	 */
	public void setConstraintLineColor(final Color color)
	{
		constraintLineColor = color;
	}
	/**
	 * Sets the Color of the lines that represent
	 * @param color Color of the lines that represent Paddings
	 */
	public void setPaddingLineColor(final Color color)
	{
		paddingLineColor = color;
	}
	/**
	 * Sets the Color of the lines that represent
	 * @param constraintLineColor Color of the lines that represent GridConstraints
	 * @param paddingLineColor Color of the lines that represent Paddings
	 */
	public void setLineColors(final Color constraintLineColor, final Color paddingLineColor)
	{
		this.constraintLineColor = constraintLineColor;
		this.paddingLineColor = paddingLineColor;
	}
	
	/**
	 * Sets the stroke of the line that represents a GridConstraints Constraint
	 * @param stroke the stroke of the line that represents a GridConstraints Constraint
	 */
	public void setConstraintLineStroke(final int stroke)
	{
		constraintLineStroke = stroke;
	}
	/**
	 * Sets the stroke of the line that represents a GridConstraints Padding
	 * @param stroke the stroke of the line that represents a GridConstraints Padding
	 */
	public void setPaddingLineStroke(final int stroke)
	{
		paddingLineStroke = stroke;
	}
	/**
	 * Sets the stroke of the line that represents a GridConstraints Constraint
	 * @param constraintLineStroke the stroke of the line that represents a GridConstraints Constraint
	 * @param paddingLineStroke the stroke of the line that represents a GridConstraints Padding
	 */
	public void setLineStrokes(final int constraintLineStroke, final int paddingLineStroke)
	{
		this.constraintLineStroke = constraintLineStroke;
		this.paddingLineStroke = paddingLineStroke;
	}
	
	/**
	 * Gets a string representation of this Grid
	 * @return a string representation of this Grid
	 */
	@Override
	public String toString()
	{
		String out = " --- Grid --- \n";
		out += " - Vertical {CONSTANT} - \n";
		out += verticalConstantConstraints.toString() + "\n";
		out += " - Vertical {PERCENT} - \n";
		out += verticalPercentConstraints.toString() + "\n";
		out += " - Horizontal {CONSTANT} - \n";
		out += horizontalConstantConstraints.toString() + "\n";
		out += " - Horizontal {PERCENT} - \n";
		out += horizontalPercentConstraints.toString() + "\n";
		out += " - Nodes - ";
		for (Node node : nodes)
		{
			out += "\n" + node.toString();
		}
		return out;
	}
}
