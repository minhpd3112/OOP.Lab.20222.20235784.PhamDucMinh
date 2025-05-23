package hust.soict.hedspi.aims.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;

    private final ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media m) throws Exception {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new Exception("Cart is full!");
        }

        itemsOrdered.add(m);
    }

    public void removeMedia(Media m) {
        itemsOrdered.remove(m);
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public float totalCost() {
        float total = 0.0f;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }
}