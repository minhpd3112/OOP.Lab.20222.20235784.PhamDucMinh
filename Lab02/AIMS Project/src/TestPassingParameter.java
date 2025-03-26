public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle", "Adventure", 15.99f);
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella", "Animation", 12.99f);

        System.out.println("Before swap:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());

        swap(jungleDVD, cinderellaDVD);

        System.out.println("After swap (no change expected):");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());

        // Test changeTitle (pass by value but object attribute can change)
        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("After changeTitle:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
    }
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        DigitalVideoDisc temp = dvd1;
        dvd1 = dvd2;
        dvd2 = temp;
    }
    public static void changeTitle(DigitalVideoDisc dvd, String newTitle) {
        dvd.setTitle(newTitle);
    }
}
