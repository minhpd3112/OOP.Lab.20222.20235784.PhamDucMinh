public class DigitalVideoDisc {
    // Biến tĩnh để đếm số DVD đã tạo (classifier member)
    private static int nbDigitalVideoDiscs = 0;

    // Các thuộc tính instance
    private int id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    // Constructor theo title
    public DigitalVideoDisc(String title) {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
        this.title = title;
    }

    // Constructor theo title, category, cost
    public DigitalVideoDisc(String title, String category, float cost) {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Constructor theo title, category, director, cost
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
        this.title = title;
        this.category = category;
        this.director = director;
        this.cost = cost;
    }

    // Constructor theo tất cả các thuộc tính
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getDirector() { return director; }
    public int getLength() { return length; }
    public float getCost() { return cost; }

    // Setter cho title (dùng cho bài kiểm tra pass-by-value)
    public void setTitle(String title) { this.title = title; }

    // Phương thức play: chỉ thực hiện nếu length > 0
    public void play() {
        if (length > 0) {
            System.out.println("Playing DVD: " + title);
        } else {
            System.out.println("DVD \"" + title + "\" cannot be played because its length is 0 or less.");
        }
    }

    @Override
    public String toString() {
        return "DVD [ID=" + id + ", Title=" + title + ", Category=" + category +
                ", Director=" + director + ", Length=" + length + ", Cost=" + cost + "]";
    }
}
