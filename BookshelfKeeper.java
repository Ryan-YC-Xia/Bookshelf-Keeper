// Name: Yuchen Xia
// USC NetID: xiayuche
// CSCI455 PA2
// Fall 2021

import java.util.*;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a
 * bookshelf of books kept in non-decreasing order by height, with the
 * restriction that single books can only be added or removed from one of the
 * two *ends* of the bookshelf to complete a higher level pick or put operation.
 * Pick or put operations are performed with minimum number of such adds or
 * removes.
 */
public class BookshelfKeeper {

   /**
    * Representation invariant:
    * 
    * The contained bookshelf is sorted.
    * The total number of mutators to bookshelf is stored in totalMutators.
    * Number of mutators to bookshelf in the recent operation is stored in recentMutators.
    * totalMutators and recentMutators are always non-negative.
    * 
    */
   private Bookshelf bookshelf;
   private int totalMutators = 0;
   private int recentMutators = 0;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf();
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps
    * bookshelf sorted after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      Stack<Integer> bufferPile = new Stack<Integer>();
      int operationCount = 0;
      if (position + 1 <= getNumBooks() / 2) {
         for (int i = 0; i < position; i++) {
            bufferPile.push(bookshelf.removeFront());
            operationCount++;
         }
         bookshelf.removeFront();
         operationCount++;
         while (!bufferPile.empty()) {
            bookshelf.addFront(bufferPile.pop());
            operationCount++;
         }
      } else {
         for (int i = getNumBooks() - 1; i > position; i--) {
            bufferPile.push(bookshelf.removeLast());
            operationCount++;
         }
         bookshelf.removeLast();
         operationCount++;
         while (!bufferPile.empty()) {
            bookshelf.addLast(bufferPile.pop());
            operationCount++;
         }
      }
      recentMutators = operationCount;
      totalMutators += operationCount;
      assert isValidBookshelfKeeper();
      return operationCount;
   }

   /**
    * Inserts book with specified height into the shelf. Keeps the contained
    * bookshelf sorted after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      Stack<Integer> bufferPile = new Stack<Integer>();
      int operationCount = 0;
      int numRemoveFront = numRemoveFront(height);
      int numRemoveBack = numRemoveBack(height);
      if (numRemoveFront <= numRemoveBack) {
         for (int i = 0; i < numRemoveFront; i++) {
            bufferPile.push(bookshelf.removeFront());
            operationCount++;
         }
         bookshelf.addFront(height);
         operationCount++;
         while (!bufferPile.empty()) {
            bookshelf.addFront(bufferPile.pop());
            operationCount++;
         }
      } else {
         for (int i = 0; i < numRemoveBack; i++) {
            bufferPile.push(bookshelf.removeLast());
            operationCount++;
         }
         bookshelf.addLast(height);
         operationCount++;
         while (!bufferPile.empty()) {
            bookshelf.addLast(bufferPile.pop());
            operationCount++;
         }
      }
      recentMutators = operationCount;
      totalMutators += operationCount;
      assert isValidBookshelfKeeper();
      return operationCount;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return totalMutators; // dummy code to get stub to compile
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return bookshelf.size(); // dummy code to get stub to compile
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String
    * containing height of all books present in the bookshelf in the order they are
    * on the bookshelf, followed by the number of bookshelf mutator calls made to
    * perform the last pick or put operation, followed by the total number of such
    * calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      String string = bookshelf.toString();
      string += " " + recentMutators + " " + totalMutators;
      assert isValidBookshelfKeeper();
      return string;
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state. (See
    * representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return bookshelf.isSorted() && totalMutators >= 0 && recentMutators >= 0;
   }

   /**
    * Returns the number of removes required if book of the given heighr is to
    * be inserted onto the contained bookshelf from the front.
    * Used in the putHeight() method.
    */
   private int numRemoveFront(int height) {
      int removeRequired = 0;
      int size = bookshelf.size();
      while (removeRequired < size && height > bookshelf.getHeight(removeRequired)) {
         removeRequired++;
      }
      return removeRequired;
   }

   /**
    * Returns the number of removes required if book of the given heighr is to
    * be inserted onto the contained bookshelf from the back.
    * Used in the putHeight() method.
    */
   private int numRemoveBack(int height) {
      int removeRequired = 0;
      int size = bookshelf.size();
      while (removeRequired < size && height < bookshelf.getHeight(size - 1 - removeRequired)) {
         removeRequired++;
      }
      return removeRequired;
   }
}
