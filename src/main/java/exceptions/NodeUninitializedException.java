package exceptions;

import structure.nodes.Node;

/**
 * RuntimeException that gets thrown when the Grid tries to display a Node that doesn't have its GridConstraints, Margins, and PaddingSet all initialized
 * @author Deaddorks
 */
public class NodeUninitializedException extends RuntimeException
{
	public NodeUninitializedException(final Node node)
	{
		super("Node has not been fully initialized: " + node.toString());
	}
}
