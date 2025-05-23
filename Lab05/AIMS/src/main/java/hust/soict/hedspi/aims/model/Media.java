package hust.soict.hedspi.aims.model;

public abstract class Media implements Comparable<Media> {
    // Tự động cấp ID cho mỗi media
    private static int nbMedia = 0;
    private final int id;
    private String title;
    private String category;
    private float cost;

    public Media(String title, String category, float cost) {
        this.id = ++nbMedia;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Media)) return false;
        Media other = (Media) obj;
        return title.equals(other.title)
                && Float.compare(cost, other.cost) == 0;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) throw new NullPointerException("Compared Media is null");
        int cmp = this.title.compareTo(other.title);
        if (cmp != 0) return cmp;
        return Float.compare(this.cost, other.cost);
    }
}