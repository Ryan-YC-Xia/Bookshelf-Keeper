public class BookshelfKeeperTester {
   public static void main(String[] args) {
      Bookshelf bookshelf = new Bookshelf();
      bookshelf.addFront(7);
      bookshelf.addFront(6);
      bookshelf.addFront(5);
      bookshelf.addFront(4);
      bookshelf.addFront(3);
      bookshelf.addFront(2);
      bookshelf.addFront(1);
      BookshelfKeeper bsk = new BookshelfKeeper(bookshelf);
      
      System.out.println(bsk.toString());
      System.out.println(bsk.putHeight(4));
      System.out.println(bsk.toString());
      System.out.println(bsk.putHeight(4));
      System.out.println(bsk.toString());

   }
}
