package hust.soict.hedspi.aims.controller;

import hust.soict.hedspi.aims.Aims;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.model.Media;
import hust.soict.hedspi.aims.model.Playable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

public class ItemController {
    @FXML private Label titleLabel;
    @FXML private Label costLabel;
    @FXML private Button addToCartButton;
    @FXML private Button playButton;

    private Media media;

    public void setData(Media media) {
        this.media = media;
        titleLabel.setText(media.getTitle());
        costLabel.setText(String.valueOf(media.getCost()));

        addToCartButton.setOnAction(e -> {
            try {
                Aims.cart.addMedia(media);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        });

        playButton.setOnAction(e -> {
            try {
                if (media instanceof Playable) ((Playable)media).play();
            } catch (PlayerException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.showAndWait();
            }
        });
    }
}
