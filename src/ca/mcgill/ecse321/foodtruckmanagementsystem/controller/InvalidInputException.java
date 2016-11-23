package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = -5633915762803837868L;
	
	public InvalidInputException(String errorMessage)
	{
		super(errorMessage);
	}
}
