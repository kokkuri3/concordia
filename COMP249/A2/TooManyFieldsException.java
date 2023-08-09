/**--------------------------------------------------------------------------------
 * Assignment 2 - TooManyFieldsException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception TooManyFields.
 */
package main;

public class TooManyFieldsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3563678446135653985L;

	int row;
	String file;

	/**
	 * This constructs a TooManyFieldsException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public TooManyFieldsException (int row, String file) {
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
		return ("ERROR: Too Many Fields Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}