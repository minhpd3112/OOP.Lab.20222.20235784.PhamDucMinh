package hust.soict.hedspi.aims.controller;

import hust.soict.hedspi.aims.Aims;
import hust.soict.hedspi.aims.model.Media;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StoreController {
    @FXML private GridPane gridPane;
    @FXML private Button viewCartButton;

    @FXML
    public void initialize() throws Exception {
        int col = 0, row = 0;
        for (Media m : Aims.store.getItemsInStore()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/aims/view/Item.fxml"));
            Parent itemNode = loader.load();
            ItemController ctrl = loader.getController();
            ctrl.setData(m);
            gridPane.add(itemNode, col, row);
            col++;
            if (col == 3) { col = 0; row++; }
        }
        viewCartButton.setOnAction(e -> {
            try {
                Parent cartRoot = FXMLLoader.load(getClass().getResource("/hust/soict/hedspi/aims/view/Cart.fxml"));
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(cartRoot, 1024, 800));
            } catch (Exception ex) { ex.printStackTrace(); }
        });
    }
}
