module hust.soict.hedspi.aims {
    requires javafx.controls;
    requires javafx.fxml;

    opens hust.soict.hedspi.aims.controller to javafx.fxml;
    opens hust.soict.hedspi.aims.model      to javafx.base;
    opens hust.soict.hedspi.aims.view       to javafx.fxml;
    exports hust.soict.hedspi.aims;
}