package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media m) {
        itemsInStore.add(m);
        System.out.println("Store add: " + m.getTitle());
    }

    public void removeMedia(Media m) {
        if (itemsInStore.remove(m)) {
            System.out.println("Store remove: " + m.getTitle());
        } else {
            System.out.println("Media not in store.");
        }
    }

    public void displayStore() {
        System.out.println("***** STORE *****");
        for (Media m : itemsInStore) {
            System.out.println(m);
        }
        System.out.println("*****************");
    }

    public <T extends Media> List<T> getItemsInStore(Class<T> clazz) {
        return itemsInStore.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }
}
