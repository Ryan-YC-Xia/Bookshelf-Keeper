import java.util.ArrayList;

public class BookshelfTester {
   public static void main(String[] args) {
      Bookshelf bookshelf1 = new Bookshelf();
      System.out.println(bookshelf1.toString());
      ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
      pileOfBooks.add(1);
      pileOfBooks.add(10);
      pileOfBooks.add(100);
      Bookshelf bookshelf2 = new Bookshelf(pileOfBooks);
      System.out.println(bookshelf2.toString());
      System.out.println("");

      bookshelf2.addFront(1);
      System.out.println(bookshelf2.toString());
      bookshelf2.addLast(10000);
      System.out.println(bookshelf2.toString());
      bookshelf2.removeFront();
      System.out.println(bookshelf2.toString());
      bookshelf2.removeLast();
      System.out.println(bookshelf2.toString());
      System.out.println("");

      System.out.println(bookshelf2.toString() + " size is: " + bookshelf2.size());
      System.out.println("Third book height is: " + bookshelf2.getHeight(2));
      System.out.println(bookshelf2.toString() + " is sorted? " + bookshelf2.isSorted());
      bookshelf2.addFront(100);
      System.out.println(bookshelf2.toString() + " is sorted? " + bookshelf2.isSorted());
   }
}