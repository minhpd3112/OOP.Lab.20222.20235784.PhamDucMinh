package hust.soict.hedspi.aims.model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media m) { if (!itemsInStore.contains(m)) itemsInStore.add(m); }
    public List<Media> getItemsInStore() { return itemsInStore; }
    public List<Media> search(String title) {
        List<Media> result = new ArrayList<>();
        for (Media m : itemsInStore) if (m.getTitle().toLowerCase().contains(title.toLowerCase())) result.add(m);
        return result;
    }
}

