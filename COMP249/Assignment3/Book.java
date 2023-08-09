/**--------------------------------------------------------------------------------
 * Assignment 3 - Book.java
 * Written by: @author Sara Amri 
 * For COMP 249 Section CC – Summer 2023
 * Due : August 8th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class constructs Book objects and overwrites the toString and equals
 * methods to accommodate the Book object's attributes.
 */

import java.util.Objects;

public class Book {
	private String title;
	private String author;
	private double price;
	private long ISBN;
	private String genre;
	private int year;

	/**
	 * Constructs a Book object with parameters
	 * @param title Title of the book
	 * @param author Author of the book
	 * @param price Price of the book
	 * @param ISBN ISBN of the book
	 * @param genre Genre of the book
	 * @param year Year the book was released
	 */
	public Book(String title, String author, double price, long ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public long getISBN() {
		return ISBN;
	}

	public String getGenre() {
		return genre;
	}

	public int getYear() {
		return year;
	}

	/**
	 * Displays the content of the Book object in a string
	 */
	@Override
	public String toString() {
		return "\"" + title + "\", " + author + ", " + price + ", " + ISBN + ", " + genre + ", " + year;
	}

	/**
	 * Compares if two Book objects have the same attributes
	 * @param obj Object with which the book is compared
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Book otherBook = (Book) obj;
		return Double.compare(otherBook.price, price) == 0 &&
				ISBN == otherBook.ISBN &&
				year == otherBook.year &&
				Objects.equals(title, otherBook.title) &&
				Objects.equals(author, otherBook.author) &&
				Objects.equals(genre, otherBook.genre);
	}

}
