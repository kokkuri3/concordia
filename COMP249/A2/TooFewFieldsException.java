/**--------------------------------------------------------------------------------
 * Assignment 2 - TooFewFieldsException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception TooFewFields.
 */
package main;

public class TooFewFieldsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237278008612336994L;

	int row;
	String file;

	/**
	 * This constructs a TooFewFieldsException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public TooFewFieldsException (int row, String file) {
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
		return ("ERROR: Too Few Fields Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}