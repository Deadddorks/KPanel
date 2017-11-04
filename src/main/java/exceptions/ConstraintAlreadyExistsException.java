package exceptions;

import structure.orgainization.GridConstraint;

/**
 * RuntimeException that gets thrown when a GridConstraint is added, but a GridConstraint already exists at the specified location
 * @author Deaddorks
 */
public class ConstraintAlreadyExistsException extends RuntimeException
{
	/**
	 * Creates a new ConstraintAlreadyExistsException with the specified GridConstraint being the GridConstraint that caused the Error
	 * @param gridConstraint
	 */
	public ConstraintAlreadyExistsException (final GridConstraint gridConstraint)
	{
		super("Can not add duplicate GridConstraint: " + gridConstraint.toString());
	}
}
