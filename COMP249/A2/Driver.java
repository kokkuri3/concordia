/**--------------------------------------------------------------------------------
 * Assignment 2 - Driver.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class contains the main method as well as the three methods each covering
 * a part of the assignment.
 */
package main;
import java.io.*;
import java.util.Scanner;
public class Driver {


	/**
	 * This method reads team records from a number of CSV-formatted text files and checks whether there are syntax errors.
	 * @param filesEntered
	 * @param hokeyWriter
	 * @param basketballWriter
	 * @param footballWriter
	 * @param syntax_errorWriter
	 * @throws IOException
	 * @throws TooManyFieldsException
	 * @throws TooFewFieldsException
	 * @throws MissingFieldException
	 * @throws UnknownSportException
	 */
	private static void do_part1(String filesEntered, BufferedWriter hokeyWriter, BufferedWriter basketballWriter, BufferedWriter footballWriter, BufferedWriter syntax_errorWriter)
			throws IOException, TooManyFieldsException, TooFewFieldsException, MissingFieldException, UnknownSportException {

		BufferedReader br = new BufferedReader(new FileReader(filesEntered));

		String line;
		int rowCounter = 1;

		while ((line = br.readLine()) != null) {
			String[] fields = line.split(",");

			// boolean used to break out of the loop for MissingFieldException
			boolean missingField = false;

			// checking for missing or extra fields
			if (fields.length > 5) {
				syntax_errorWriter.append(new TooManyFieldsException(rowCounter, filesEntered).toString());
				rowCounter++;
				continue;
			} else if (fields.length < 5) {
				syntax_errorWriter.append(new TooFewFieldsException(rowCounter, filesEntered).toString());
				rowCounter++;
				continue;
			}
			else {
				for (int j = 0; j < 5; j++) {
					if (fields[j].trim().equals("")) {
						syntax_errorWriter.append(new MissingFieldException(rowCounter, filesEntered).toString());
						missingField = true;
						break;
					}
				}
			}

			// Skip the entire line if missing field encountered
			if (missingField) {
				rowCounter++;
				continue;
			}

			// Code for appending valid fields to respective files...
			for (int j = 0; j < 5; j++) {
				fields[j] = fields[j].trim();
				if (fields[1].trim().equalsIgnoreCase("Hokey")) { //.trim() takes off white spaces
					hokeyWriter.append(fields[j]);
					if (j == 4)
						hokeyWriter.append("\n");
					else
						hokeyWriter.append(",");
				} else if (fields[1].trim().equalsIgnoreCase("Football")) {
					footballWriter.append(fields[j]);
					if (j == 4)
						footballWriter.append("\n");
					else
						footballWriter.append(",");
				} else if (fields[1].trim().equalsIgnoreCase("Basketball")) {
					basketballWriter.append(fields[j]);
					if (j == 4)
						basketballWriter.append("\n");
					else
						basketballWriter.append(",");
				} else {
					syntax_errorWriter.append(new UnknownSportException(rowCounter, filesEntered).toString());
					break;
				}
			}

			rowCounter++;
		}

		br.close();
	}

	/**
	 * This method checks if a year is valid.
	 * @param year
	 * @return
	 */
	private static boolean validYearOption(int year) {
		return year == 2001 || year == 2011 || year == 2019;
	}

	/**
	 * This method checks if a sport is valid.
	 * @param sport
	 * @return
	 */
	private static boolean validSportOption(String sport) {
		return sport.equalsIgnoreCase("hokey") || sport.equalsIgnoreCase("football") || sport.equalsIgnoreCase("basketball");
	}

	/**
	 * This method checks if the record is valid.
	 * @param record
	 * @return
	 */
	private static boolean validRecordOption(String record) {
		int hasDash=0;
		for (int x=0;x<record.length();x++) {
			if (record.charAt(x)=='-') hasDash++;
		}
		if (hasDash==1)return true;
		else return false;
	}

	/**
	 * This method checks each of the syntactically valid team records for semantic errors, 
	 * setting out to serialize both syntactically and semantically valid team records into binary files
	 * @param filesEntered
	 * @param binaryHokey
	 * @param binaryBasketball
	 * @param binaryFootball
	 * @param semantic_errorWriter
	 * @throws IOException
	 * @throws BadRecordException
	 * @throws BadSportException
	 * @throws BadYearException
	 */
	private static void do_part2(String filesEntered, FileOutputStream binaryHokey, FileOutputStream binaryBasketball, FileOutputStream binaryFootball, BufferedWriter semantic_errorWriter) 
			throws IOException, BadRecordException, BadYearException {

		BufferedReader br = new BufferedReader(new FileReader(filesEntered));
		Team[] validTeams = new Team[getNumberOfValidTeams(filesEntered)];
		int validTeamIndex = 0;

		String line;
		int rowCounter = 1;
		while ((line = br.readLine()) != null) {
			String[] fields = line.split(",");

			if (fields.length == 5) {

				String sport = fields[1].trim().toLowerCase();
				int year = Integer.parseInt(fields[2].trim());
				String record = fields[3].trim();
				boolean championship = fields[4].trim().equalsIgnoreCase("Y");
				rowCounter++;

				// checking for errors
				if (!validYearOption(year)) {
					semantic_errorWriter.append(new BadYearException(rowCounter, filesEntered).toString());
					continue;
				}

				if (!validSportOption(sport)) {
					semantic_errorWriter.append(new BadSportException(rowCounter, filesEntered).toString());
					continue;
				}

				if (!validRecordOption(record)) {
					semantic_errorWriter.append(new BadRecordException(rowCounter, filesEntered).toString());
					continue;
				}

				// If the line is semantically correct, create a Team object and store it in the array
				Team team = new Team(fields[0], sport, year, record, championship);
				validTeams[validTeamIndex] = team;
				System.out.println(validTeams[validTeamIndex]);
				validTeamIndex++;

				// Create the arrays for each sport
				Team[] hokeyTeamsArray = new Team[getNumberOfValidTeams("Hokey.csv")];
				Team[] basketballTeamsArray = new Team[getNumberOfValidTeams("Basketball.csv")];
				Team[] footballTeamsArray = new Team[getNumberOfValidTeams("Football.csv")];

				// Assign validTeams to the corresponding arrays
				int h = 0, f = 0, b = 0;
				for (int i = 0; i < validTeamIndex; i++) {
					Team currentTeam = validTeams[i];
					if (currentTeam != null) {
						if (currentTeam.getSport().equalsIgnoreCase("hokey")) {
							hokeyTeamsArray[h] = currentTeam;
							h++;
						} else if (currentTeam.getSport().equalsIgnoreCase("football")) {
							footballTeamsArray[f] = currentTeam;
							f++;
						} else if (currentTeam.getSport().equalsIgnoreCase("basketball")) {
							basketballTeamsArray[b] = currentTeam;
							b++;
						}
					}
				}

				// Serialize and write the arrays to the binary files
				serializeAndWriteTeams(hokeyTeamsArray, binaryHokey);
				serializeAndWriteTeams(basketballTeamsArray, binaryBasketball);
				serializeAndWriteTeams(footballTeamsArray, binaryFootball);
			}
		}
	}


private static void serializeAndWriteTeams(Team[] teams, FileOutputStream outputStream) throws IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	objectOutputStream.writeObject(teams);
}

//number of teams using appropriate field
private static int getNumberOfValidTeams(String fileName) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader(fileName));
	int count = 0;
	String line;
	while ((line = br.readLine()) != null) {
		String[] fields = line.split(",");
		if (fields.length == 5) {
			count++;
		}
	}
	br.close();
	return count;
}

//initializing values for part 3
private static final String HOKEY_FILE = "Hokey.csv.ser";
private static final String FOOTBALL_FILE = "Football.csv.ser";
private static final String BASKETBALL_FILE = "Basketball.csv.ser";

private static Team[] hokeyTeams;
private static Team[] footballTeams;
private static Team[] basketballTeams;
private static Team[] currentTeams;

private static Team[] deserializeTeamArray(String fileName) throws IOException, ClassNotFoundException {
	ObjectInputStream fileInput = new ObjectInputStream(new FileInputStream(fileName));
	Team[] teams = (Team[]) fileInput.readObject();
	fileInput.close();
	return teams;
}

private static void deserializeTeams() throws IOException, ClassNotFoundException {
	hokeyTeams = deserializeTeamArray(HOKEY_FILE);
	footballTeams = deserializeTeamArray(FOOTBALL_FILE);
	basketballTeams = deserializeTeamArray(BASKETBALL_FILE);
	currentTeams = hokeyTeams;
}

/**
 * This method implements a menu to allow the user to navigate the files.
 * @param filesEntered
 * @param hokeyReader
 * @param basketballReader
 * @param footballReader
 */
private static void do_part3() throws IOException, ClassNotFoundException {
	deserializeTeams();
	showMainMenu();
}

private static void showMainMenu() throws IOException {
	Scanner scanner = new Scanner(System.in);

	while (true) {
		System.out.println("-----------------------------");
		System.out.println("Main Menu");
		System.out.println("-----------------------------");
		System.out.println("v View the selected file: " + getCurrentFileName() + " (" + currentTeams.length + " records)");
		System.out.println("s Select a file to view");
		System.out.println("x Exit");
		System.out.println("-----------------------------");
		System.out.print("Enter Your Choice: ");

		String choice = scanner.nextLine().trim().toLowerCase();
		switch (choice) {
		case "v":
			showCurrentFileRecords(currentTeams);
			break;
		case "s":
			showFileSubMenu();
			break;
		case "x":
			System.out.println("Goodbye!");
			System.exit(0);
		default:
			System.out.println("Invalid choice! Please try again.");
		}
		scanner.close();
	}
}


private static void displayTeams(int currentIndex) {
	for (int i = currentIndex; i<currentIndex+3;i++) {

		if (currentTeams[i] != null) {
			System.out.println(currentTeams[i].toString());
		}
	}
	if (currentIndex>0) {
		// Display the current object and (n-1) objects below it, if any
		int lastObjectIndex = Math.min(currentIndex + currentIndex - 1, currentTeams.length - 1);
		for (int i = currentIndex + 1; i <= lastObjectIndex; i++) {
			printTeam(currentTeams[i]);
		}
		// Check if EOF (end of file) has been reached
		if (lastObjectIndex == currentTeams.length - 1) {
			System.out.println("EOF has been reached.");
			return;
		}
		// Update the current object index
		currentIndex = lastObjectIndex;
	} 
	else {
		// Display the current object and |n|-1 objects above it, if any
		int firstObjectIndex = Math.max(currentIndex + currentIndex + 1, 0);
		for (int i = currentIndex - 1; i >= firstObjectIndex; i--) {
			printTeam(currentTeams[i]);
		}

		// Check if BOF (beginning of file) has been reached
		if (firstObjectIndex == 0) {
			System.out.println("BOF has been reached.");
		}

		// Update the current object index
		currentIndex = firstObjectIndex;
	}
}

private static void printTeam(Team team) {
	if (team != null) {
		System.out.print("Team Name: " + Team.getName()+ " || ");
		System.out.print("Sport: " + team.getSport()+ " || ");
		System.out.print("Year: " + team.getYear()+ " || ");
		System.out.print("Record: " + team.getRecord()+ " || ");
		System.out.println("Championship: " + team.isChampionship());
		System.out.println();
	}
}

private static void showCurrentFileRecords(Team[] team) throws IOException {
	int numRecords = team.length;
	int currentIndex = 1;

	Scanner scanner = new Scanner(System.in);

	while (true) {
		System.out.println("-----------------------------");
		System.out.println("Viewing: " + getCurrentFileName() + " (" + numRecords + " records)");
		System.out.println("-----------------------------");

		displayTeams(currentIndex);

		System.out.println("-----------------------------");
		System.out.println("Enter '0' to return to Main Menu or any other integer to view next records.");
		System.out.println("-----------------------------");
		System.out.print("Enter Your Choice: ");

		int choice = scanner.nextInt();

		if (choice>0) {
			currentIndex = choice-1;
			displayTeams(currentIndex);
			System.out.println("-----------------------------");
			System.out.println("Enter '0' to return to Main Menu or any other integer to view next records.");
			System.out.println("-----------------------------");
			System.out.print("Enter Your Choice: ");
			choice = scanner.nextInt();
		}
		else if (choice<0) {
			currentIndex = (-choice)-1;
			displayTeams(currentIndex);
			System.out.println("-----------------------------");
			System.out.println("Enter '0' to return to Main Menu or any other integer to view next records.");
			System.out.println("-----------------------------");
			System.out.print("Enter Your Choice: ");
			choice = scanner.nextInt();
		}
		else if (choice==0) {
			showMainMenu();
			break;
		}
		else {
			System.out.println("Invalid input! Please enter an integer.");
			choice = scanner.nextInt();
		}
		scanner.close();
	}
}

private static void showFileSubMenu() throws IOException {
	Scanner scanner = new Scanner(System.in);

	while (true) {
		System.out.println("-----------------------------");
		System.out.println("File Sub-Menu");
		System.out.println("-----------------------------");
		System.out.println("1 Hokey.csv.ser (" + hokeyTeams.length + " records)");
		System.out.println("2 Football.csv.ser (" + footballTeams.length + " records)");
		System.out.println("3 Basketball.csv.ser (" + basketballTeams.length + " records)");
		System.out.println("4 Exit");
		System.out.println("-----------------------------");
		System.out.print("Enter Your Choice: ");

		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			currentTeams = hokeyTeams;
			showCurrentFileRecords(currentTeams);
			break;
		case 2:
			currentTeams = footballTeams;
			showCurrentFileRecords(currentTeams);
			break;
		case 3:
			currentTeams = basketballTeams;
			showCurrentFileRecords(currentTeams);
			break;
		case 4:
			showMainMenu();
		default:
			System.out.println("Invalid input! Please type an integer.");
			choice = scanner.nextInt();
		}
		scanner.close();
	}
}
private static String getCurrentFileName() {
	if (currentTeams == hokeyTeams) {
		return "Hokey.csv.ser";
	} else if (currentTeams == footballTeams) {
		return "Football.csv.ser";
	} else if (currentTeams == basketballTeams) {
		return "Basketball.csv.ser";
	} else {
		return "Unknown File";
	}
}

public static void main(String[] args) throws TooManyFieldsException, TooFewFieldsException, MissingFieldException, UnknownSportException, BadRecordException, BadSportException, BadYearException, ClassNotFoundException, IOException {

	BufferedWriter hokeyWriter = null;
	BufferedWriter basketballWriter= null;
	BufferedWriter footballWriter= null;
	BufferedWriter syntax_errorWriter= null;

	FileOutputStream binaryHokey= null;
	FileOutputStream binaryBasketball= null;
	FileOutputStream binaryFootball= null;
	BufferedWriter semantic_errorWriter= null;

	try {

		String [] filesEntered = { "games2001.csv", "games2011.csv", "games2019.csv"};
		String [] part2Input = { "Hokey.csv", "Football.csv", "Basketball.csv"};

		hokeyWriter = new BufferedWriter(new FileWriter("Hokey.csv"));
		basketballWriter = new BufferedWriter(new FileWriter("Basketball.csv"));
		footballWriter = new BufferedWriter(new FileWriter("Football.csv"));
		syntax_errorWriter = new BufferedWriter(new FileWriter("syntax_error.txt"));
		semantic_errorWriter = new BufferedWriter(new FileWriter("semantic_error.txt"));

		binaryHokey = new FileOutputStream("Hokey.csv.ser");
		binaryBasketball = new FileOutputStream("Basketball.csv.ser");
		binaryFootball = new FileOutputStream("Football.csv.ser");

		//EXECUTE PART 1
		for (String fileEntered : filesEntered) {
			do_part1(fileEntered, hokeyWriter,basketballWriter, footballWriter,syntax_errorWriter );
			hokeyWriter.flush();
			basketballWriter.flush();
			footballWriter.flush();
			syntax_errorWriter.flush();
		}


		//EXECUTE PART 2
		for (String fileEntered : part2Input) {
			do_part2(fileEntered,binaryHokey,binaryBasketball,binaryFootball,semantic_errorWriter);
			semantic_errorWriter.flush();
			binaryHokey.flush();
			binaryBasketball.flush();
			binaryFootball.flush();
		}


		//EXECUTE PART 3
		//do_part3();


	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (TooManyFieldsException e) {
		e.printStackTrace();
	}
	catch (TooFewFieldsException e) {
		e.printStackTrace();
	}
	catch (MissingFieldException e) {
		e.printStackTrace();
	}
	catch (UnknownSportException e) {
		e.printStackTrace();
	}
	finally {
		hokeyWriter.close();
		basketballWriter.close();
		footballWriter.close();
		syntax_errorWriter.close();

		semantic_errorWriter.close();
		binaryHokey.close();
		binaryBasketball.close();
		binaryFootball.close();
	}
}
}