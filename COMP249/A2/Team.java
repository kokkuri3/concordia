/**--------------------------------------------------------------------------------
 * Assignment 2 - Team.java
 * Written by: @author Sara Amri & @author Reema Aboudraz
 * For COMP 249 Section CC – Summer 2023
 * Due : July 27th 2023
 * @version 1.0
 * --------------------------------------------------------------------------------
 * This class defines a Team objects with parameters and implements Serializable.
 */
package main;

import java.io.Serializable;

public class Team implements Serializable {
		

	private static final long serialVersionUID = 5926432724655925281L;
		private static String name;
        private static String sport;
        private static int year;
        private static String record;
        private static boolean championship;

        /**
    	 * This constructs a Team object with parameters.
    	 * @param name Team name
    	 * @param sport Sport
    	 * @param year Year
    	 * @param record Record
    	 * @param championship Whether this team won the championship
    	 */
        public Team(String name, String sport, int year, String record, boolean championship) {
            Team.name = name;
            Team.sport = sport;
            Team.year = year;
            Team.record = record;
            Team.championship = championship;
        }
        
        

        public static String getName() {
			return name;
		}
		public String getSport() {
			return sport;
		}
		public int getYear() {
			return year;
		}
		public String getRecord() {
			return record;
		}
		public boolean isChampionship() {
			return championship;
		}

		
		/**
		 * Overrides toString() method
		 */
        public String toString() {
			return (getName() + "," + getSport() + "," + getYear() + "," + getRecord() + "," + isChampionship() + "\n");
		}
}
     