package hust.soict.hedspi.aims.comparator;

import hust.soict.hedspi.aims.model.Media;
import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        int res = m1.getTitle().compareTo(m2.getTitle());
        return res != 0 ? res : Float.compare(m1.getCost(), m2.getCost());
    }
}
