package hust.soict.hedspi.aims.model;

import hust.soict.hedspi.aims.exception.PlayerException;
import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost);
        this.artist = artist;
    }

    public void addTrack(Track t) { if (!tracks.contains(t)) tracks.add(t); }
    public void removeTrack(Track t) { tracks.remove(t); }

    @Override
    public void play() throws PlayerException {
        if (tracks.isEmpty()) throw new PlayerException("ERROR: CD has no tracks!");
        for (Track t : tracks) t.play();
    }
}
