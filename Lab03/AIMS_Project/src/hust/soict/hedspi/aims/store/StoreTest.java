package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd = new DigitalVideoDisc(1,"Star Wars","Sci-Fi","Lucas",124,24.95f);
        store.addMedia(dvd);
        store.displayStore();
        store.removeMedia(dvd);
        store.displayStore();
    }
}
