/**--------------------------------------------------------------------------------
 * Assignment 2 - BadSportException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception BadSport.
 */
package main;

public class BadSportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4970410023710050645L;

	int row;
	String file;

	/**
	 * This constructs a BadSportException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public BadSportException (int row, String file) {
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
		return ("ERROR: Bad Sport Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}
