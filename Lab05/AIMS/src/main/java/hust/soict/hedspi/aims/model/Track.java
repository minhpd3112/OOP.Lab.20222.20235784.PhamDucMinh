package hust.soict.hedspi.aims.model;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() { return title; }
    public int getLength() { return length; }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) throw new PlayerException("ERROR: Track length is non-positive!");
        System.out.println("Playing track: " + title + " (" + length + "s)");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Track)) return false;
        Track other = (Track) obj;
        return title.equals(other.title) && length == other.length;
    }
}
