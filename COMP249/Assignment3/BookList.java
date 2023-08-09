/**--------------------------------------------------------------------------------
 * Assignment 3 - BookList.java
 * Written by: @author Sara Amri 
 * For COMP 249 Section CC – Summer 2023
 * Due : August 8th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class contains all the methods used to manipulate the records.
 */

import java.io.*;

public class BookList {
	private class Node {
		private Book b;
		private Node next;

		/**
		 * Constructs a new node with the given book.
		 * @param book The book to be stored in the node.
		 */
		public Node(Book book) {
			this.b = book;
			this.next = null;
		}
	}

	private Node head;

	/**
	 * Constructs a default BookList.
	 */
	public BookList() {
		head = new Node(null);
		head.next = head; 
	}

	/**
	 * Adds a Book object to the start of the list.
	 * @param b The book to be added.
	 */
	public void addToStart(Book b) {
		Node newNode = new Node(b);

		if (head.next == head) { // List is empty
			head.next = newNode;
			newNode.next = head; // makes it circular
		} else {
			newNode.next = head.next;
			head.next = newNode;
		}
	}


	/**
	 * Stores the records of a specific year in a file.
	 * @param yr The year to extract records for.
	 */
	public void storeRecordsByYear(int yr) {
		if (head == null) {
			return;
		}

		Node current = head.next; // Start from the first node

		try (PrintWriter writer = new PrintWriter(yr + ".txt")) {
			do {
				if (current.b != null && current.b.getYear() == yr) {
					writer.println(current.b.toString());
				}
				current = current.next;
			} while (current != head);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Stored a given Book object right before a given ISBN
	 * @param isbn ISBN given
	 * @param b Book object to store
	 * @return
	 */
	public boolean insertBefore(long isbn, Book b) {
		if (head == null) {
			return false; // List is empty
		}

		Node current = head;
		Node prev = null;

		// Find the node with the given ISBN
		do {
			if (current.b != null && current.b.getISBN() == isbn) {
				break;
			}
			prev = current;
			current = current.next;
		} while (current != head);

		// If ISBN not found, return false
		if (current == head) {
			return false;
		}

		// Insert the new node before the found node
		Node newNode = new Node(b);
		newNode.next = current;

		if (prev == null) {
			// Insertion at the beginning
			Node tail = head;
			while (tail.next != head) {
				tail = tail.next;
			}
			tail.next = newNode;
			head = newNode;
		} else {
			prev.next = newNode;
		}

		return true;
	}

	/**
	 * Inserts a Book object between two given isbns
	 * @param isbn1 First given ISBN
	 * @param isbn2 Second given ISBN
	 * @param b Book object to insert
	 * @return
	 */
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		if (head == null) {
			return false; 
		}

		Node current = head;
		Node prev1 = null, prev2 = null;

		// Find the nodes with the given isbns
		while (current.next != head) {
			if (current.b != null && current.b.getISBN() == isbn1) {
				prev1 = current;
			}
			if (current.b != null && current.b.getISBN() == isbn2) {
				prev2 = current;
				break;
			}
			current = current.next;
		}

		// If isbn not found, return false
		if (prev1 == null || prev2 == null) {
			return false;
		}

		// Insert the new node between the 2 isbns' nodes
		Node newNode = new Node(b);
		newNode.next = prev2; // Insert after prev1 and before prev2
		prev1.next = newNode;

		return true;
	}

	/**
	 * Displays the Books in the list
	 */
	public void displayContent() {
		if (head == null) {
			System.out.println("List is empty.");
			return;
		}

		Node current = head.next;
		while (current != head) {
			System.out.println(current.b);
			current = current.next;
		}
		System.out.println("==> head");
	}

	/**
	 * Deletes any duplicate Book objects
	 * @return
	 */
	public boolean delConsecutiveRepeatedRecords() {
		if (head == null || head.next == head) {
			return false; // List is empty or has only one node
		}

		Node current = head;

		while (current.next != head) {
			if (current.next.b != null && current.next.b.equals(current.b)) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}

		if (head.next.b != null && head.next.b.equals(head.b)) {
			head = head.next;
		}

		return true;
	}

	/**
	 * Extracts all Book objects that have the same author attribute as the one given
	 * @param aut Given author attribute
	 * @return
	 */
	public BookList extractAuthList(String aut) {
		BookList extractedList = new BookList();

		if (head == null) {
			return extractedList; // Empty list
		}

		Node current = head;
		do {
			if (current.b != null && current.b.getAuthor().trim().equalsIgnoreCase(aut.trim())) {
				extractedList.addToStart(current.b);
			}
			current = current.next;
		} while (current != head);

		return extractedList;
	}

	/**
	 * Swaps the position of 2 Book objects using their ISBN
	 * @param isbn1 ISBN of the first book
	 * @param isbn2 ISBN of the second book
	 * @return
	 */
	public boolean swap(long isbn1, long isbn2) {
		if (head == null || head.next == head) {
			return false; // List is empty or has only one node
		}

		Node prev1 = null, prev2 = null, node1 = null, node2 = null;
		Node current = head;

		// Find the nodes with the given ISBNs
		while (current.next != head) {
			if (current.next.b.getISBN() == isbn1) {
				prev1 = current;
				node1 = current.next;
			}
			if (current.next.b.getISBN() == isbn2) {
				prev2 = current;
				node2 = current.next;
			}
			current = current.next;
		}

		if (node1 == null || node2 == null) {
			return false; // One or both ISBNs not found
		}

		if (prev1 == null) {
			head = node2;
		} else {
			prev1.next = node2;
		}

		if (prev2 == null) {
			head = node1;
		} else {
			prev2.next = node1;
		}

		Node temp = node1.next;
		node1.next = node2.next;
		node2.next = temp;

		return true;
	}
}
