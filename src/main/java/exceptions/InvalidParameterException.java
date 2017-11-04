package exceptions;

// TODO add @throws to all classes

/**
 * RuntimeException that gets thrown when a parameter is passed with an undesirable value
 * @author Deaddorks
 */
public class InvalidParameterException extends RuntimeException
{
	
	/**
	 * Creates an InvalidParameterException to notify the programmer where the issue occurred
	 * @param className name of the class where the Error happened
	 * @param methodName name of the method where the Error happened
	 * @param parameterName name of the parameter that was incorrect
	 * @param value actual value of the parameter
	 * @param reason reason why this parameter was invalid
	 */
	public InvalidParameterException(final String className, final String methodName, final String parameterName, final String value, final String reason)
	{
		super("Method ["+className+"."+methodName+"()] has invalid parameter ["+parameterName+"]. Value: ["+value+"], Reason: ["+reason+"].");
	}
	/**
	 * Creates an InvalidParameterException to notify the programmer where the issue occurred. Automatically specifies that the cause was null
	 * @param className name of the class where the Error happened
	 * @param methodName name of the method where the Error happened
	 * @param parameterName name of the parameter that was incorrect
	 */
	public InvalidParameterException(final String className, final String methodName, final String parameterName)
	{
		super("Method ["+className+"."+methodName+"()] has invalid parameter ["+parameterName+"]. Value: [null], Reason: [Can not be null].");
	}
}
