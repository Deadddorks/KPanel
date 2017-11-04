package exceptions;

import structure.orgainization.GridConstraint;

public class ConstraintAlreadyExistsException extends RuntimeException
{
	public ConstraintAlreadyExistsException (final GridConstraint gridConstraint)
	{
		super("Can not add duplicate GridConstraint: " + gridConstraint.toString());
	}
}
