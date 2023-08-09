/**--------------------------------------------------------------------------------
 * Assignment 3 - Driver.java
 * Written by: @author Sara Amri 
 * For COMP 249 Section CC – Summer 2023
 * Due : August 8th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class contains the main method where the user menu is implemented and
 * makes use of the methods from the BookList class.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args)throws IOException {
		ArrayList<Book> arrLst = new ArrayList<>();
		BookList bkLst = new BookList();

		// Read the contents of the Book.txt file and initialize arrLst and bkLst
		try (BufferedReader reader = new BufferedReader(new FileReader("Books.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {


				String[] fields = line.split(",");


				if (fields.length == 6) {
					String title = fields[0];
					String author = fields[1];
					double price = Double.parseDouble(fields[2]);
					long ISBN = Long.parseLong(fields[3].trim());
					String genre = fields[4];
					int year = Integer.parseInt(fields[5].trim());
					Book book = new Book(title, author, price, ISBN, genre, year);


					// Add to ArrayList and BookList based on year
					if (year >= 1900 && year <= 2023) {
						arrLst.add(book);
						bkLst.addToStart(book);
					} else {
						try (PrintWriter writer = new PrintWriter(new FileWriter("YearErr.txt", true))) {
							writer.println(book.toString());
							System.out.println("Invalid year record written to YearErr.txt");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean hasInvalidYearRecords = false;
		for (Book book : arrLst) {
			int year = book.getYear();
			if (year < 1900 || year > 2023) {
				hasInvalidYearRecords = true;
				break; // No need to continue checking
			}
		}

		// Record the contents of arrLst into YearErr.txt if there are invalid year records
		if (hasInvalidYearRecords) {
			try (PrintWriter writer = new PrintWriter(new FileWriter("YearErr.txt"))) {
				for (Book book : arrLst) {
					int year = book.getYear();
					if (year < 1900 || year > 2023) {
						writer.println(book.toString());
					}
				}
				System.out.println("Invalid year records written to YearErr.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Display the contents of the bkLst
		bkLst.displayContent();

		// Display menu and perform operations based on user input
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("=========================================================================");
			System.out.println("                                 MENU");
			System.out.println("     1. Extract records of a specific year and store them in a file");
			System.out.println("     2. Delete consecutive repeated records");
			System.out.println("     3. Create a new list for an author and display them");
			System.out.println("     4. Insert a new Node before a record with a specific ISBN");
			System.out.println("     5. Insert a new Node between two records with specific ISBNs");
			System.out.println("     6. Swap records using ISBN numbers");
			System.out.println("     7. Exit");
			System.out.println("=========================================================================");
			System.out.print("Enter your choice: ");

			choice = scanner.nextInt();

			String title = "";
			String author = "";
			double price = 0;
			long ISBN = 0;
			String genre = "";
			int year = 0;
			Book bookToInsert = null;
			boolean inserted = false;

			switch (choice) {
			case 1:
				System.out.print("Enter the year to extract records: ");
				int yearToExtract = scanner.nextInt();
				bkLst.storeRecordsByYear(yearToExtract);
				break;
			case 2:
				bkLst.delConsecutiveRepeatedRecords();
				bkLst.displayContent();
				break;
			case 3:
				System.out.print("Enter the author's name: ");
				scanner.nextLine(); 
				String authorToExtract = scanner.nextLine();
				BookList extractedList = bkLst.extractAuthList(authorToExtract);
				System.out.println("Extracted List:");
				extractedList.displayContent();
				break;
			case 4:
				System.out.print("Enter the ISBN number: ");
				long isbnToInsertBefore = scanner.nextLong();
				System.out.print("Enter the Book title: ");
				scanner.nextLine(); 
				title = scanner.nextLine();
				System.out.print("Enter the author: ");
				author = scanner.nextLine();
				System.out.print("Enter the price: ");
				price = scanner.nextDouble();
				System.out.print("Enter the ISBN: ");
				ISBN = scanner.nextLong();
				System.out.print("Enter the genre: ");
				scanner.nextLine(); 
				genre = scanner.nextLine();
				System.out.print("Enter the year: ");
				year = scanner.nextInt();

				bookToInsert = new Book(title, author, price, ISBN, genre, year);

				inserted = bkLst.insertBefore(isbnToInsertBefore, bookToInsert);
				if (inserted) {
					System.out.println("Book inserted successfully!");
				} else {
					System.out.println("ISBN not found. Book not inserted.");
				}

				bkLst.displayContent();
				break;
			case 5:
				System.out.print("Enter the first ISBN number: ");
				long isbn1 = scanner.nextLong();
				System.out.print("Enter the second ISBN number: ");
				long isbn2 = scanner.nextLong();

				System.out.print("Enter the Book title: ");
				scanner.nextLine(); // Consume newline
				title = scanner.nextLine();
				System.out.print("Enter the author: ");
				author = scanner.nextLine();
				System.out.print("Enter the price: ");
				price = scanner.nextDouble();
				System.out.print("Enter the ISBN: ");
				ISBN = scanner.nextLong();
				System.out.print("Enter the genre: ");
				scanner.nextLine(); // Consume newline
				genre = scanner.nextLine();
				System.out.print("Enter the year: ");
				year = scanner.nextInt();

				bookToInsert = new Book(title, author, price, ISBN, genre, year);

				inserted = bkLst.insertBetween(isbn1, isbn2, bookToInsert);
				if (inserted) {
					System.out.println("Book inserted successfully!");
				} else {
					System.out.println("ISBN combination not found. Book not inserted.");
				}

				bkLst.displayContent();
				break;
			case 6:
				System.out.print("Enter the first ISBN number: ");
				long isbn1Swap = scanner.nextLong();
				System.out.print("Enter the second ISBN number: ");
				long isbn2Swap = scanner.nextLong();
				bkLst.swap(isbn1Swap, isbn2Swap);
				bkLst.displayContent();
				break;
			case 7:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 7);

		scanner.close();

		// Using methods
		boolean removed = bkLst.delConsecutiveRepeatedRecords();
		if (removed) {
			System.out.println("Consecutive repeated records removed.");
			bkLst.displayContent();
		} else {
			System.out.println("No consecutive repeated records found.");
		}

		BookList extractedList = bkLst.extractAuthList("Roy Malan");
		System.out.println("Extracted List:");
		extractedList.displayContent();

		boolean swapped = bkLst.swap(1557835659, 1879103272);
		if (swapped) {
			System.out.println("Swapped records.");
			bkLst.displayContent();
		} else {
			System.out.println("ISBNs not found for swapping.");
		}
	}
}
