package exceptions;

public class NodeUninitializedException extends RuntimeException
{
	public NodeUninitializedException()
	{
		super("Node has not been fully initialized.");
	}
}
