// File: src/hust/soict/hedspi/aims/cart/Cart.java
package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<Media> items = new ArrayList<>();

    public void addMedia(Media m) {
        items.add(m);
        System.out.println("Added to cart: " + m.getTitle());
    }

    public void removeMedia(Media m) {
        if (items.remove(m)) {
            System.out.println("Removed from cart: " + m.getTitle());
        } else {
            System.out.println("Not found in cart.");
        }
    }

    public void printCart() {
        System.out.println("***** CART *****");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
        System.out.printf("Total: %.2f$%n", totalCost());
        System.out.println("****************");
    }

    public float totalCost() {
        float total = 0f;
        for (Media m : items) {
            total += m.getCost();
        }
        return total;
    }
    public void searchById(int id) {
        boolean found=false;
        for (Media m: items) if (m.getId()==id) {
            System.out.println(m); found=true;
        }
        if (!found) System.out.println("No media with ID "+id);
    }
    public void searchByTitle(String title) {
        boolean found=false;
        for (Media m: items) if (m.getTitle().equalsIgnoreCase(title)) {
            System.out.println(m); found=true;
        }
        if (!found) System.out.println("No media with title \""+title+"\"");
    }
    public List<Media> getItems() {
        return items;
    }
}