package exceptions;

public class InvalidParameterException extends RuntimeException
{
	// TODO add @throws to all classes
	
	public InvalidParameterException(final String className, final String methodName, final String parameterName, final String value, final String reason)
	{
		super("Method ["+className+"."+methodName+"()] has invalid parameter ["+parameterName+"]. Value: ["+value+"], Reason: ["+reason+"].");
	}
	public InvalidParameterException(final String className, final String methodName, final String parameterName)
	{
		super("Method ["+className+"."+methodName+"()] has invalid parameter ["+parameterName+"]. Value: [null], Reason: [Can not be null].");
	}
}
