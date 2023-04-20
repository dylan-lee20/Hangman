/*
Dylan Rowat
ICS3U1
April 4, 2023
hangman game
 */

import java.io.File;
import java.util.Scanner;

public class Hangman {
	public static void main(String[] args) {
		// initialization
		Scanner scFile = null;
		Scanner sc = new Scanner(System.in);
		Scanner scFileDrawing = null;
		String difficulty = "";
		String file_name = "";
		String word = "";
		String correct_word = "";
		char user_letter;
		boolean correct_input = false;
		int in_word = 0;
		int random_line = 0;
		
		
		// checks input and tells user number of letters in word
		while (!correct_input) {
			System.out.println("What difficulty would you like to play? (easy, medium, hard): ");
			difficulty = sc.nextLine();
			difficulty = difficulty.toLowerCase();
			difficulty = difficulty.trim();
			
			if (difficulty.equals("easy")) {
				System.out.println("The word will be 4 letters long.");
				word = "____";
				break;
			}
			else if (difficulty.equals("medium")) {
				System.out.println("The word will be 5 letters long.");
				word = "_____";
				break;
			}
			else if (difficulty.equals("hard")){
				System.out.println("The word will be 7 letters long.");
				word = "_______";
				break;
			}
			else {
				System.out.println("Please enter either easy, medium, or hard.");
			}
		}
		
		// file scanner
		file_name = difficulty + "wordslist.txt";
		try {
			scFile = new Scanner(new File(file_name));
		}
		catch(Exception e) {
			System.out.print("Error");
		}
		
		// choosing random word
		random_line = (int)(Math.random()*500) + 1;
		for(int i = 0; i < random_line; i++) {
			correct_word = scFile.nextLine();
		}
		
		// game loop
		for(int i = 6; i > -1; i--) {
			
			// initializing scanner for printing hangman
			try {
				scFileDrawing = new Scanner(new File(i + ".txt"));
			}
			catch(Exception e) {
				System.out.print("Error");
			}
			
			// breaking if out of tries
			if (i == 0) {
				break;
			}
			
			// printing hangman
			for (int line = 0; line < 7; line ++) {
				System.out.println(scFileDrawing.nextLine());
			}
			
			// check if user letter is in word
			System.out.print("Enter a letter: ");
			user_letter = sc.next().charAt(0);
			for (int j = 0; j < (correct_word.length()); j++) {
				if (correct_word.charAt(j) == user_letter) {
					in_word++;
					word = word.substring(0, j) + user_letter + word.substring(j + 1);
				}
			}
			System.out.print(word);
			System.out.println();
			
			// resets loop
			if (in_word > 0) {
				i++;
			}
			
			// checks if user has the correct word
			if (word.equals(correct_word)) {
				break;
			}
			
			// resets counter
			in_word = 0;
			
			
		}
		// user wins
		if (word.equals(correct_word)) {
			System.out.print("Congratulations! You got the correct word.");
		}
		
		// user loses
		else {
			for (int line = 0; line < 7; line ++) {
				System.out.println(scFileDrawing.nextLine());
			}
			System.out.print("You have failed, the correct word was \"" + correct_word + "\".");
		}
		sc.close();
		scFile.close();
	}
}