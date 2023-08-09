/**--------------------------------------------------------------------------------
 * Assignment 2 - BadYearException.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines the custom exception BadYear.
 */
package main;

public class BadYearException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6576295733244570682L;

	int row;
	String file;

	/**
	 * This constructs a BadYearException object with parameters.
	 * @param row Row 
	 * @param file File name
	 */
	public BadYearException (int row, String file) {
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
		return ("ERROR: Bad Year Exception on row " + getRow()+" of file " + getFile() +"\n");
	}
}
