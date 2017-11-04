package structure.nodes;

// ~~~~~~~~~~ Imports ~~~~~~~~~~
import exceptions.NodeUninitializedException;
import exceptions.InvalidParameterException;
import structure.orgainization.GridConstraint;
import structure.orgainization.Margin;
import structure.orgainization.PaddingSet;
import structure.orgainization.graphical.Location;
import structure.orgainization.graphical.Rectangle;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.function.Consumer;
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public abstract class Node
{
	
	// ----- Label -----
	// ~~~~~~~~~~ Constants ~~~~~~~~~~
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// ~~~~~~~~~~ Variables ~~~~~~~~~~
	// ----- Defaults -----
	protected PaddingSet defaultPaddingSet;
	protected Margin defaultMargins;
	
	// ----- Grid data -----
	private GridConstraint leftConstraint;
	private GridConstraint rightConstraint;
	private GridConstraint topConstraint;
	private GridConstraint bottomConstraint;
	
	private Margin margins;
	private PaddingSet paddingSet;
	
	private boolean constraintsAreDefined;
	private boolean marginsAreDefined;
	private boolean paddingSetIsDefined;
	
	// ----- Event Handling -----
	protected boolean listenForMouseEvents;
	protected boolean listenForMouseMotionEvents;
	protected boolean listenForMouseWheelEvents;
	protected boolean focusable;
	protected boolean listenForKeyEvents;
	
	protected Consumer<MouseEvent> onMousePressedAction;
	protected Consumer<MouseEvent> onMouseReleasedAction;
	protected Consumer<MouseEvent> onMouseClickedAction;
	protected Consumer<MouseEvent> onMouseEnteredAction;
	protected Consumer<MouseEvent> onMouseExitedAction;
	
	protected Consumer<MouseEvent> onMouseMovedAction;
	protected Consumer<MouseEvent> onMouseDraggedAction;
	
	protected Consumer<KeyEvent> onKeyPressedAction;
	protected Consumer<KeyEvent> onKeyReleasedAction;
	protected Consumer<KeyEvent> onKeyTypedAction;
	
	protected Consumer<MouseWheelEvent> onMouseScrolledAction;
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public Node()
	{
		init();
	}
	/**
	 * Creates a Node with the given specifications
	 * @param margins Margins around the Node, which creates an additional offset from its Constraint
	 * @param paddingSet Whether or not to align to the base Constraint or the Padding
	 * @param leftConstraint left Constraint
	 * @param rightConstraint right Constraint
	 * @param topConstraint top Constraint
	 * @param bottomConstraint bottom Constraint
	 */
	public Node(final Margin margins, final PaddingSet paddingSet,
				final GridConstraint leftConstraint, final GridConstraint rightConstraint,
				final GridConstraint topConstraint, final GridConstraint bottomConstraint)
	{
		init();
		setConstraints(leftConstraint, rightConstraint, topConstraint, bottomConstraint);
		setMargins(margins);
		setPaddingSet(paddingSet);
	}
	/**
	 * Creates a Node with the given specifications
	 * @param paddingSet Whether or not to align to the base Constraint or the Padding
	 * @param leftConstraint left Constraint
	 * @param rightConstraint right Constraint
	 * @param topConstraint top Constraint
	 * @param bottomConstraint bottom Constraint
	 */
	public Node(final PaddingSet paddingSet,
				final GridConstraint leftConstraint, final GridConstraint rightConstraint,
				final GridConstraint topConstraint, final GridConstraint bottomConstraint)
	{
		init();
		setConstraints(leftConstraint, rightConstraint, topConstraint, bottomConstraint);
		setMarginsToDefault();
		setPaddingSet(paddingSet);
	}
	/**
	 * Creates a Node with the given specifications
	 * @param margins Margins around the Node, which creates an additional offset from its Constraint
	 * @param leftConstraint left Constraint
	 * @param rightConstraint right Constraint
	 * @param topConstraint top Constraint
	 * @param bottomConstraint bottom Constraint
	 */
	public Node(final Margin margins,
				final GridConstraint leftConstraint, final GridConstraint rightConstraint,
				final GridConstraint topConstraint, final GridConstraint bottomConstraint)
	{
		init();
		setConstraints(leftConstraint, rightConstraint, topConstraint, bottomConstraint);
		setMargins(margins);
		setPaddingSetToDefault();
	}
	/**
	 * Creates a Node with the given specifications
	 * @param leftConstraint left Constraint
	 * @param rightConstraint right Constraint
	 * @param topConstraint top Constraint
	 * @param bottomConstraint bottom Constraint
	 */
	public Node(final GridConstraint leftConstraint, final GridConstraint rightConstraint,
				final GridConstraint topConstraint, final GridConstraint bottomConstraint)
	{
		init();
		setConstraints(leftConstraint, rightConstraint, topConstraint, bottomConstraint);
		setMarginsToDefault();
		setPaddingSetToDefault();
	}
	
	private void init()
	{
		leftConstraint = null;
		rightConstraint = null;
		topConstraint = null;
		bottomConstraint = null;
		
		margins = null;
		paddingSet = null;
		
		defaultPaddingSet = new PaddingSet(false);
		defaultMargins = new Margin(0);
		
		constraintsAreDefined = false;
		paddingSetIsDefined = false;
		marginsAreDefined = false;
		onMousePressedAction = e -> {};
		onMouseEnteredAction = e -> {};
		onMouseExitedAction = e -> {};
		
		onMouseMovedAction = e -> {};
		
		// Event Handling
		listenForMouseEvents = false;
		listenForMouseMotionEvents = false;
		focusable = false;
		listenForKeyEvents = false;
		
		onMousePressedAction = e -> {};
		onMouseReleasedAction = e -> {};
		onMouseClickedAction = e -> {};
		onMouseEnteredAction = e -> {};
		onMouseExitedAction = e -> {};
		
		onMouseMovedAction = e -> {};
		onMouseDraggedAction = e -> {};
		
		onKeyPressedAction = e -> {};
		onKeyReleasedAction = e -> {};
		onKeyTypedAction = e -> {};
		
		onMouseScrolledAction = e -> {};
	}
	
	/**
	 * Sets the GridConstraints of this Node to the specified Constraints.<br>
	 * Note that Grid has effective methods for doing this
	 * @param leftConstraint left GridConstraint
	 * @param rightConstraint right GridConstraint
	 * @param topConstraint top GridConstraint
	 * @param bottomConstraint bottom GridConstraint
	 */
	public void setConstraints(final GridConstraint leftConstraint, final GridConstraint rightConstraint,
							   final GridConstraint topConstraint, final GridConstraint bottomConstraint)
	{
		if (leftConstraint == null)
		{
			throw new InvalidParameterException("Node", "Node", "leftConstraint");
		}
		if (rightConstraint == null)
		{
			throw new InvalidParameterException("Node", "Node", "rightConstraint");
		}
		if (topConstraint == null)
		{
			throw new InvalidParameterException("Node", "Node", "topConstraint");
		}
		if (bottomConstraint == null)
		{
			throw new InvalidParameterException("Node", "Node", "bottomConstraint");
		}
		
		this.leftConstraint = leftConstraint;
		this.rightConstraint = rightConstraint;
		this.topConstraint = topConstraint;
		this.bottomConstraint = bottomConstraint;
		constraintsAreDefined = true;
	}
	
	/**
	 * If this Node does not already have a defined PaddingSet, it will be set to the DEFAULT. If not, no action
	 */
	public void setPaddingSetToDefaultIfUndefined()
	{
		if (!paddingSetIsDefined)
		{
			setPaddingSetToDefault();
		}
	}
	/**
	 * Sets the PaddingSet of this Node to the DEFAULT
	 */
	public void setPaddingSetToDefault()
	{
		setPaddingSet(defaultPaddingSet);
	}
	/**
	 * Sets the PaddingSet of this Node to the specified PaddingSet
	 * @param paddingSet the specified PaddingSet
	 */
	public void setPaddingSet(final PaddingSet paddingSet)
	{
		if (paddingSet == null)
		{
			throw new InvalidParameterException("Node", "Node", "paddingSet");
		}
		this.paddingSet = paddingSet;
		paddingSetIsDefined = true;
	}
	/**
	 * If this Node does not already have a defined Margin, it will be set to the DEFAULT. If not, no action
	 */
	public void setMarginsToDefaultIfUndefined()
	{
		if (!marginsAreDefined)
		{
			setMarginsToDefault();
		}
	}
	/**
	 * Sets the Margin of this Node to the DEFAULT
	 */
	public void setMarginsToDefault()
	{
		setMargins(defaultMargins);
	}
	/**
	 * Sets the Margin of this Node to the specified PaddingSet
	 * @param margins the specified Margin
	 */
	public void setMargins(final Margin margins)
	{
		if (margins == null)
		{
			throw new InvalidParameterException("Node", "Node", "margins");
		}
		this.margins = margins;
		marginsAreDefined = true;
	}
	
	// ------------------------------ Event Handling ------------------------------
	// ----- Runners -----
	/**
	 * Gets called when a Node is both mousePressed() & mouseReleased()
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseClicked(final MouseEvent e)
	{
		onMouseClickedAction.accept(e);
	}
	/**
	 * Gets called when a Node is mousePressed()
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMousePressed(final MouseEvent e)
	{
		onMousePressedAction.accept(e);
	}
	/**
	 * Gets called when a Node is mouseReleased()
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseReleased(final MouseEvent e)
	{
		onMouseReleasedAction.accept(e);
	}
	
	/**
	 * Gets called when the mouse enters a Node
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseEntered(final MouseEvent e)
	{
		onMouseEnteredAction.accept(e);
	}
	/**
	 * Gets called when the mouse exits a Node
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseExited(final MouseEvent e)
	{
		onMouseExitedAction.accept(e);
	}
	
	/**
	 * Gets called when the mouse is moved within a Node
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseMoved(final MouseEvent e)
	{
		onMouseMovedAction.accept(e);
	}
	/**
	 * Gets called when the mouse is dragged within a Node
	 * @param e the MouseEvent associated with this being called
	 */
	public void onMouseDragged(final MouseEvent e)
	{
		onMouseDraggedAction.accept(e);
	}
	
	/**
	 * Gets called when the mouse is scrolled within a Node
	 * @param e the MouseWheelEvent associated with this being called
	 */
	public void onMouseScrolled(final MouseWheelEvent e)
	{
		onMouseScrolledAction.accept(e);
	}
	
	/**
	 * Gets called when the Node is focused and a keyPressed KeyEvent happens
	 * @param e the KeyEvent associated with this being called
	 */
	public void onKeyPressed(final KeyEvent e)
	{
		onKeyPressedAction.accept(e);
	}
	/**
	 * Gets called when the Node is focused and a keyReleased KeyEvent happens
	 * @param e the KeyEvent associated with this being called
	 */
	public void onKeyReleased(final KeyEvent e)
	{
		onKeyReleasedAction.accept(e);
	}
	/**
	 * Gets called when the Node is focused and a keyTyped KeyEvent happens
	 * @param e the KeyEvent associated with this being called
	 */
	public void onKeyTyped(final KeyEvent e)
	{
		onKeyTypedAction.accept(e);
	}
	
	// ----- Setters -----
	
	/**
	 * Sets what will happen when this Node recognizes a mousePressed MouseEvent
	 * @param action the desired action
	 */
	public void setOnMousePressed(final Consumer<MouseEvent> action)
	{
		onMousePressedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a mouseReleased MouseEvent
	 * @param action the desired action
	 */
	public void setOnMouseReleased(final Consumer<MouseEvent> action)
	{
		onMouseReleasedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a mouseClicked MouseEvent.<br>
	 * It it worth noting that this is not a regular mouseClick, as those are only called if the mouse is<br>
	 * pressed and released on the same pixel, this will be called as long as the mouse is both pressed and released within the Node
	 * @param action the desired action
	 */
	public void setOnMouseClicked(final Consumer<MouseEvent> action)
	{
		onMouseClickedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a mouseEntered MouseEvent
	 * @param action the desired action
	 */
	public void setOnMouseEntered(final Consumer<MouseEvent> action)
	{
		onMouseEnteredAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a mouseExited MouseEvent
	 * @param action the desired action
	 */
	public void setOnMouseExited(final Consumer<MouseEvent> action)
	{
		onMouseExitedAction = action;
	}
	
	/**
	 * Sets what will happen when this Node recognizes a mouseWheel MouseWheelEvent
	 * @param action the desired action
	 */
	public void setOnMouseScrolled(final Consumer<MouseWheelEvent> action)
	{
		onMouseScrolledAction = action;
	}
	
	/**
	 * Sets what will happen when this Node recognizes a mouseMoved MouseEvent
	 * @param action the desired action
	 */
	public void setOnMouseMoved(final Consumer<MouseEvent> action)
	{
		onMouseMovedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a mouseDragged MouseEvent
	 * @param action the desired action
	 */
	public void setOnMouseDragged(final Consumer<MouseEvent> action)
	{
		onMouseDraggedAction = action;
	}
	
	/**
	 * Sets what will happen when this Node recognizes a keyPressed KeyEvent
	 * @param action the desired action
	 */
	public void setOnKeyPressed(final Consumer<KeyEvent> action)
	{
		onKeyPressedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a keyReleased KeyEvent
	 * @param action the desired action
	 */
	public void setOnKeyReleased(final Consumer<KeyEvent> action)
	{
		onKeyReleasedAction = action;
	}
	/**
	 * Sets what will happen when this Node recognizes a keyTyped KeyEvent
	 * @param action the desired action
	 */
	public void setOnKeyTyped(final Consumer<KeyEvent> action)
	{
		onKeyTypedAction = action;
	}
	
	/**
	 * Set whether or not this Node is focusable
	 * @param focusable whether or not this Node is focusable
	 */
	public void setFocusable(final boolean focusable)
	{
		this.focusable = focusable;
	}
	
	// ----- Getters -----
	/**
	 * Gets whether or not this Node is listening for Mouse Events
	 * @return whether or not this Node is listening for Mouse Events
	 */
	public boolean isListeningForMouseEvents()
	{
		return listenForMouseEvents;
	}
	/**
	 * Gets whether or not this Node is listening for MouseWheel Events
	 * @return whether or not this Node is listening for MouseWheel Events
	 */
	public boolean isListeningForMouseWheelEvents()
	{
		return listenForMouseWheelEvents;
	}
	/**
	 * Gets whether or not this Node is listening for MouseMotion Events
	 * @return whether or not this Node is listening for MouseMotion Events
	 */
	public boolean isListeningForMouseMotionEvents()
	{
		return listenForMouseMotionEvents;
	}
	/**
	 * Gets whether or not this Node is listening for Key Events
	 * @return whether or not this Node is listening for Key Events
	 */
	public boolean isListeningForKeyEvents()
	{
		return focusable && listenForKeyEvents;
	}
	/**
	 * Gets whether or not this Node is focusable
	 * @return whether or not this Node is focusable
	 */
	public boolean isFocusable()
	{
		return focusable;
	}
	
	// ------------------------------ Area and Graphics ------------------------------
	/**
	 * Must be defined per node
	 * @param g the Graphics of the KPanel
	 * @param nodeArea a Rectangle that represents the area the Node is to be drawn in
	 */
	protected abstract void display(Graphics g, final Rectangle nodeArea);
	
	/**
	 * Called during a KPanels paint() cycle and calls display(...)
	 * @param g the Graphics of the KPanel
	 */
	public void draw(Graphics g, final Rectangle parentArea)
	{
		display(g, getNodeArea(parentArea));
	}
	
	/**
	 * Determines whether or not a Location is within the area of the Node
	 * @param loc the Location to test
	 * @return whether or not the location is within the area of the Node
	 */
	public boolean isLocInArea(final Location loc, final Rectangle parentArea)
	{
		Rectangle area = getNodeArea(parentArea);
		return (loc.getXPos() >= area.getXMin() && loc.getXPos() <= area.getXMax() && loc.getYPos() >= area.getYMin() && loc.getYPos() <= area.getYMax());
	}
	
	/**
	 * Gets a Rectangle that represents the area of the Node
	 * @return a Rectangle that represents the area of the Node
	 */
	public Rectangle getNodeArea(final Rectangle parentArea)
	{
		if (!constraintsAreDefined || !marginsAreDefined || !paddingSetIsDefined)
		{
			throw new NodeUninitializedException();
		}
		
		return new Rectangle(parentArea.getXMin() + leftConstraint.getValue(paddingSet.getRightBoundary()) + margins.getLeftValue(),
				parentArea.getYMin() + topConstraint.getValue(paddingSet.getBottomBoundary()) + margins.getTopValue(),
				parentArea.getXMin() + rightConstraint.getValue(paddingSet.getLeftBoundary()) + margins.getRightValue(),
				parentArea.getYMin() + bottomConstraint.getValue(paddingSet.getTopBoundary()) + margins.getBottomValue());
	}
	
}
