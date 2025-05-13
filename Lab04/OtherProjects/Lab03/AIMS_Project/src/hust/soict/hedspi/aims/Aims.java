package hust.soict.hedspi.aims;

import java.util.Scanner;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;

public class Aims {
    private static Store store = new Store();
    private static Cart cart   = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedStore();  // Thêm sẵn vài media vào cửa hàng
        int choice;
        do {
            showMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1: store.displayStore(); break;
                case 2: updateStoreMenu();    break;
                case 3: cart.printCart();      break;
                case 4: placeOrder();          break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again."); break;
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\nAIMS: Main Menu");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. View cart");
        System.out.println("4. Place order");
        System.out.println("0. Exit");
        System.out.print("Please choose a number: ");
    }

    private static void updateStoreMenu() {
        System.out.println("\n-- Update Store --");
        System.out.println("1. Add DVD");
        System.out.println("2. Remove DVD");
        System.out.println("3. Add Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Add CD");
        System.out.println("6. Remove CD");
        System.out.println("0. Back to Main");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: addDVD(); break;
            case 2: removeDVD(); break;
            case 3: addBook(); break;
            case 4: removeBook(); break;
            case 5: addCD(); break;
            case 6: removeCD(); break;
            case 0: return;
            default: System.out.println("Invalid."); break;
        }
    }

    private static void addDVD() {
        System.out.print("Enter DVD id: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Title: ");      String title    = scanner.nextLine();
        System.out.print("Category: ");   String category = scanner.nextLine();
        System.out.print("Director: ");   String director = scanner.nextLine();
        System.out.print("Length (min): "); int length    = scanner.nextInt();
        System.out.print("Cost ($): ");     float cost    = scanner.nextFloat();
        scanner.nextLine();
        store.addMedia(new DigitalVideoDisc(id, title, category, director, length, cost));
    }

    private static void removeDVD() {
        System.out.print("Enter DVD id to remove: ");
        int id = scanner.nextInt(); scanner.nextLine();
        // tìm DVD theo id rồi xóa
        boolean found = false;
        for (DigitalVideoDisc dvd : store.getItemsInStore(DigitalVideoDisc.class)) {
            if (dvd.getId() == id) {
                store.removeMedia(dvd);
                found = true; break;
            }
        }
        if (!found) System.out.println("DVD not found in store.");
    }

    private static void addBook() {
        System.out.print("Enter Book id: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Title: ");     String title    = scanner.nextLine();
        System.out.print("Category: ");  String category = scanner.nextLine();
        System.out.print("Cost ($): ");  float cost      = scanner.nextFloat();
        scanner.nextLine();
        Book b = new Book(id, title, category, cost);
        System.out.print("Number of authors: ");
        int n = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.printf("Author %d: ", i+1);
            b.addAuthor(scanner.nextLine());
        }
        store.addMedia(b);
    }

    private static void removeBook() {
        System.out.print("Enter Book id to remove: ");
        int id = scanner.nextInt(); scanner.nextLine();
        boolean found = false;
        for (Book b : store.getItemsInStore(Book.class)) {
            if (b.getId() == id) {
                store.removeMedia(b);
                found = true; break;
            }
        }
        if (!found) System.out.println("Book not found in store.");
    }

    private static void addCD() {
        System.out.print("Enter CD id: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Title: ");     String title    = scanner.nextLine();
        System.out.print("Category: ");  String category = scanner.nextLine();
        System.out.print("Director: ");  String director = scanner.nextLine();
        System.out.print("Artist: ");    String artist   = scanner.nextLine();
        System.out.print("Cost ($): ");  float cost     = scanner.nextFloat();
        scanner.nextLine();
        CompactDisc cd = new CompactDisc(id, title, category, director, artist, cost);
        System.out.print("Number of tracks: ");
        int t = scanner.nextInt(); scanner.nextLine();
        for (int i = 0; i < t; i++) {
            System.out.printf("Track %d title: ", i+1);
            String tt = scanner.nextLine();
            System.out.printf("Track %d length: ", i+1);
            int len = scanner.nextInt(); scanner.nextLine();
            cd.addTrack(new Track(tt, len));
        }
        store.addMedia(cd);
    }

    private static void removeCD() {
        System.out.print("Enter CD id to remove: ");
        int id = scanner.nextInt(); scanner.nextLine();
        boolean found = false;
        for (CompactDisc cd : store.getItemsInStore(CompactDisc.class)) {
            if (cd.getId() == id) {
                store.removeMedia(cd);
                found = true; break;
            }
        }
        if (!found) System.out.println("CD not found in store.");
    }

    private static void placeOrder() {
        System.out.println("\n-- Place Order --");
        cart.printCart();
        System.out.println("Order placed. Thank you!");
        for (var m : cart.getItems()) {
            cart.removeMedia(m);
        }
    }

    private static void seedStore() {
        store.addMedia(new DigitalVideoDisc(1, "Star Wars", "Sci-Fi", "George Lucas", 125, 24.95f));
        store.addMedia(new DigitalVideoDisc(2, "Aladdin",   "Animation", "John Musker", 90, 18.99f));
        store.addMedia(new Book(3, "Effective Java", "Programming", 45.00f));
        store.addMedia(new CompactDisc(4, "Hybrid Theory", "Rock", "Don Gilmore", "Linkin Park", 15.99f));
    }
}
