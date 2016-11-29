/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = -5633915762803837868L;
	
	public InvalidInputException(String errorMessage)
	{
		super(errorMessage);
	}
}
