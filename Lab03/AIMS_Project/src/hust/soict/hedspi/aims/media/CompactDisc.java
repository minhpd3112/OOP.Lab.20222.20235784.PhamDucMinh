package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category,
                       String director, String artist, float cost) {
        super(id, title, category, director, 0, cost);
        this.artist = artist;
    }
    public String getArtist() { return artist; }
    public void addTrack(Track t) {
        if (!tracks.contains(t)) tracks.add(t);
    }
    public void removeTrack(Track t) {
        tracks.remove(t);
    }
    @Override
    public int getLength() {
        return tracks.stream().mapToInt(Track::getLength).sum();
    }
    @Override
    public void play() {
        int length = getLength();
        if (length>0) {
            System.out.printf("Playing CD: %s (%d mins)%n", getTitle(), length);
            tracks.forEach(Track::play);
        } else {
            System.out.println("ERROR: CD length non-positive.");
        }
    }
    @Override
    public String toString() {
        return "CD - " + super.toString() + String.format(" - %s - %d min", artist, getLength());
    }
}
