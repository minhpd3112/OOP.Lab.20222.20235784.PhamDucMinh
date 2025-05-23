package hust.soict.hedspi.aims.model;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Media implements Playable {
    private String director;
    private int length;

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) throw new PlayerException("ERROR: DVD length is non-positive!");
        System.out.println("Playing DVD: " + getTitle() + " (" + length + "s) by " + director);
    }
}