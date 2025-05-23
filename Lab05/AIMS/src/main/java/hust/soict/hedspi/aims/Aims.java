package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aims extends Application {
    public static Store store = new Store();
    public static Cart cart = new Cart();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize sample data
        store.addMedia(new Book("The Alchemist", "Fiction", 9.99f));
        store.addMedia(new DigitalVideoDisc("Matrix", "Sci-Fi", "Wachowski", 1360, 14.99f));
        store.addMedia(new CompactDisc("Thriller", "Pop", "Michael Jackson", 11.99f));
        store.addMedia(new Book("The Dog", "School", 0.99f));
        store.addMedia(new DigitalVideoDisc("Hello Wolrd!", "Coding", "Nguyen Xuan Son", 1360, 24.99f));
        store.addMedia(new CompactDisc("Apattu", "Pop", "Huhu", 1.99f));

        Parent root = FXMLLoader.load(getClass().getResource("/hust/soict/hedspi/aims/view/Store.fxml"));
        primaryStage.setTitle("AIMS Store");
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
