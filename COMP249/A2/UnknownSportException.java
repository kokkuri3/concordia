/**--------------------------------------------------------------------------------
 * Assignment 2 - UnknownSportException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception UnknownSport.
 */
package main;

public class UnknownSportException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 170738119255520147L;

	int row;
	String file;

	/**
	 * This constructs an UnknownSportException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public UnknownSportException (int row, String file) {
		this.row=row;
		this.file=file;
	}
		
	public int getRow() {
		return row;
	}

	public String getFile() {
		return file;
	}

	/**
	 * Overrides toString() method
	 */
	@Override
	public String toString() {
		return ("ERROR: Unknown Sport Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}