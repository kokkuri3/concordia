/**--------------------------------------------------------------------------------
 * Assignment 2 - MissingFieldException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception MissingField.
 */

package main;
public class MissingFieldException extends Exception {


	private static final long serialVersionUID = 2275207142832748676L;

	int row;
	String file;
	
	/**
	 * This constructs a MissingFieldException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public MissingFieldException (int row, String file) {
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
		return ("ERROR: Missing Field Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}