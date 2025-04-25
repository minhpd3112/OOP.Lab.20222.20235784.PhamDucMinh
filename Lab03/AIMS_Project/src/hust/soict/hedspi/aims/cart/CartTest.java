package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd = new DigitalVideoDisc(1,"Jungle","Anim","Tar",120,19.95f);
        cart.addMedia(dvd);
        cart.searchById(1);
        cart.searchByTitle("Jungle");
        cart.printCart();

        Book book = new Book(2,"Java","Edu",29.99f);
        book.addAuthor("Alice");
        cart.addMedia(book);
        cart.printCart();
    }
}