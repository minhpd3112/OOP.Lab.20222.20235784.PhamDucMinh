package hust.soict.hedspi.aims.controller;

import hust.soict.hedspi.aims.Aims;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.model.Media;
import hust.soict.hedspi.aims.model.Playable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media,Integer> colMediaId;
    @FXML private TableColumn<Media,String>  colMediaTitle;
    @FXML private TableColumn<Media,String>  colMediaCategory;
    @FXML private TableColumn<Media,Float>   colMediaCost;

    @FXML private TextField    tfFilter;
    @FXML private RadioButton  radioBtnFilterId;
    @FXML private RadioButton  radioBtnFilterTitle;
    @FXML private ToggleGroup  filterCategory;

    @FXML private Button btnViewStore;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Button btnPlaceOrder;
    @FXML private Label  costLabel;

    private ObservableList<Media> list;

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        list = Aims.cart.getItemsOrdered();

        FilteredList<Media> filtered = new FilteredList<>(list, m -> true);
        tfFilter.textProperty().addListener((obs, o, n) -> {
            String low = (n==null?"":n.toLowerCase());
            filtered.setPredicate(media -> {
                if (low.isEmpty()) return true;
                return radioBtnFilterId.isSelected()
                        ? String.valueOf(media.getId()).contains(low)
                        : media.getTitle().toLowerCase().contains(low);
            });
        });
        SortedList<Media> sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(tblMedia.comparatorProperty());
        tblMedia.setItems(sorted);

        btnPlay.setDisable(true);
        btnRemove.setDisable(true);
        tblMedia.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            boolean disable = (n == null);
            btnPlay.setDisable(disable);
            btnRemove.setDisable(disable);
        });
        updateTotal();
    }

    @FXML
    private void btnViewStorePressed(ActionEvent e) {
        try {
            Parent storeRoot = FXMLLoader.load(
                    getClass().getResource("/hust/soict/hedspi/aims/view/Store.fxml")
            );
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(storeRoot, 1024, 800));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    @FXML
    private void PlayButton(ActionEvent e) {
        Media m = tblMedia.getSelectionModel().getSelectedItem();
        if (m instanceof Playable) {
            try { ((Playable)m).play(); }
            catch (PlayerException ex) { showAlert(Alert.AlertType.ERROR, ex.getMessage()); }
        }
    }

    @FXML
    private void RemoveButton(ActionEvent e) {
        Media m = tblMedia.getSelectionModel().getSelectedItem();
        if (m != null) {
            Aims.cart.removeMedia(m);
            updateTotal();
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent e) {
        Aims.cart.getItemsOrdered().clear();
        updateTotal();
        showAlert(Alert.AlertType.INFORMATION, "Order placed!");
    }

    private void updateTotal() {
        costLabel.setText(String.format("%.2f $", Aims.cart.totalCost()));
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}