package hust.soict.hedspi.aims.comparator;

import hust.soict.hedspi.aims.model.Media;
import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        int res = Float.compare(m1.getCost(), m2.getCost());
        return res != 0 ? res : m1.getTitle().compareTo(m2.getTitle());
    }
}
