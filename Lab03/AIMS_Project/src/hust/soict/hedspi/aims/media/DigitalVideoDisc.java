package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc {
    public DigitalVideoDisc(int id, String title, String category,
                            String director, int length, float cost) {
        super(id, title, category, director, length, cost);
    }
    @Override
    public void play() {
        if (getLength()>0) {
            System.out.printf("Playing DVD: %s (%d mins)%n", getTitle(), getLength());
        } else {
            System.out.println("ERROR: DVD length non-positive.");
        }
    }
    @Override
    public String toString() {
        return "DVD - " + super.toString() + String.format(" - %s - %d min", getDirector(), getLength());
    }
}
