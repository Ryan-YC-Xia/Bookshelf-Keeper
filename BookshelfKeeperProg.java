// Name: Yuchen Xia
// USC NetID: xiayuche
// CSCI455 PA2
// Fall 2021

import java.util.Scanner;

/**
 * Class BookshelfKeeperProg
 *
 * Prompts user for an input of the initial arrangement of books on the bookshelf.
 * Then prompts user to make commands and takes user input from the console.
 * Performs the action, or return error message and exit the program if the input isn't valid.
 * 
 */
public class BookshelfKeeperProg {
   public static void main(String[] args) {

      Scanner inputScanner = new Scanner(System.in);

      System.out.println("Please enter initial arrangement of books followed by newline:");
      String lineInitialization = inputScanner.nextLine();
      Bookshelf initialBookshelf = checkInitialization(lineInitialization);
      if (initialBookshelf == null) {
         inputScanner.close();
         return;
      }

      BookshelfKeeper keeper = new BookshelfKeeper(initialBookshelf);
      System.out.println(keeper.toString());

      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      while (inputScanner.hasNextLine()) {
         String lineCommand = inputScanner.nextLine();
         int numBooks = keeper.getNumBooks();
         int situation = checkCommand(lineCommand, numBooks);
         if (situation == 0) {
            inputScanner.close();
            return;
         }
         Scanner lineScanner = new Scanner(lineCommand);
         if (situation == 1) {
            lineScanner.next();
            keeper.putHeight(lineScanner.nextInt());
            System.out.println(keeper.toString());
         } else if (situation == 2) {
            lineScanner.next();
            keeper.pickPos(lineScanner.nextInt());
            System.out.println(keeper.toString());
         }
         lineScanner.close();
      }
      inputScanner.close();
   }

   /**
    * Reads a String of the user input line for initialization,
    * and checks whether the input is valid.
    * Returns a sorted bookshelf if the input is valid.
    * Returns null if the input is not valid and print error messages.
    */
   private static Bookshelf checkInitialization(String line) {

      Bookshelf initialBookshelf = new Bookshelf();
      Scanner lineScanner = new Scanner(line);

      while (lineScanner.hasNextInt()) {
         int next = lineScanner.nextInt();
         if (next <= 0) {
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
            lineScanner.close();
            return null;
         }
         initialBookshelf.addLast(next);
      }

      lineScanner.close();

      if (!initialBookshelf.isSorted()) {
         System.out.println("ERROR: Heights must be specified in non-decreasing order.");
         System.out.println("Exiting Program.");
         return null;
      }
      return initialBookshelf;
   }

   /**
    * Reads a String of the user input line of command, and takes the number of Books on the bookshelf.
    * Returns the situation based on the input:
    * Returns 1 if the put command is valid;
    * Returns 2 if the pick command is valid;
    * Returns 0 if the command is end or the input is not valid;
    * Also prints error messages if input is not valid.
    */
   private static int checkCommand(String line, int numBooks) {

      Scanner lineScanner = new Scanner(line);
      String operation = lineScanner.next();
      int situation = 0;

      if (operation.equals("put") && lineScanner.hasNextInt()) {
         int height = lineScanner.nextInt();
         if (height <= 0) {
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
         } else {
            situation = 1;
         }
      } else if (operation.equals("pick") && lineScanner.hasNextInt()) {
         int position = lineScanner.nextInt();
         if (position < 0 || position >= numBooks) {
            System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
            System.out.println("Exiting Program.");
         } else {
            situation = 2;
         }
      } else if (operation.equals("end")) {
         System.out.println("Exiting Program.");
      } else {
         System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
         System.out.println("Exiting Program.");
      }
      lineScanner.close();
      return situation;
   }
}