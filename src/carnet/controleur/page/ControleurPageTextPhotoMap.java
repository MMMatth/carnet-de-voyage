package carnet.controleur.page;

import carnet.controleur.popUp.ControleurEditionMap;
import carnet.exceptions.PositionException;
import carnet.model.Carnet;
import carnet.model.PageTextPhotoMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.Map;

public class ControleurPageTextPhotoMap extends ControleurPageTextPhoto{

    @FXML
    private WebView map;
    @FXML
    private ImageView mapImg;
    @FXML
    private Button mapEdit;

    private PageTextPhotoMap page;

    public ControleurPageTextPhotoMap(Carnet carnet) {
        super(carnet);
        this.page = (PageTextPhotoMap) carnet.getPageCourante();
    }

    @FXML
    public void initialize() {
        super.initialize();
        initMap();
        mapImg.setVisible(false);
        mapEdit.setDisable(!page.getModeEdition());
        map.setDisable(page.getModeEdition());
    }

    private void initMap(){
        WebEngine webEngine = map.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(page.getOpenStreetMapLink());
        map.setVisible(true);
    }

    @FXML
    public void onClicEditMap() {
        openPopUp();
    }

    private void openPopUp(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edition de la carte");
        dialog.setGraphic(null);
        dialog.setHeaderText(null);

        // on charge le fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/popUp/PopUpPositionMap.fxml"));
        ControleurEditionMap controlEdit = new ControleurEditionMap();
        loader.setController(controlEdit);

        try {
            VBox vbox = loader.load();
            dialog.getDialogPane().setContent(vbox);
            dialog.showAndWait();

            // on récupère les données
            try {
                setData(controlEdit.getZoom(), controlEdit.getLat(), controlEdit.getLon());
                carnet.notifierObservateurs();
            }catch (PositionException e){ // le cas ou les données des latitudes et longitudes ne sont pas des float
                displayAlert(e);
            }

        } catch (IOException e) {
            displayAlert(e);
        }
    }

    private void setData(int zoom, float lat, float lon) {
        page.setZoom(zoom);
        page.setLatitude(lat);
        page.setLongitude(lon);
    }

    private void displayAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


}
