package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.controleur.popUp.ControleurEditionMap;
import carnet.exceptions.PositionException;
import carnet.model.Carnet;
import carnet.model.PageTextPhotoMap;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControleurPageTextPhotoMap extends ControleurPageTextPhoto implements Observateur {

    @FXML
    private MapView map;

    private PageTextPhotoMap page;

    private Coordinate centreCoord;

    private Coordinate pointCoord;

    private Marker marker;

    public ControleurPageTextPhotoMap(Carnet carnet) {
        super(carnet);
        carnet.ajouterObservateur(this);
        this.page = (PageTextPhotoMap) carnet.getPageCourante();

        this.centreCoord = new Coordinate(page.getCenter_lat(), page.getCenter_long());

        this.pointCoord = new Coordinate(page.getMarker_lat(), page.getMarker_long());

        this.marker = Marker.createProvided(Marker.Provided.RED).setPosition(pointCoord).setVisible(true);
    }

    @FXML
    public void initialize() {
        super.initialize();
        initMap();
    }

    private void initMap(){
        map.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                map.setZoom(page.getZoom());
                map.addMarker(marker);
                map.setCenter(centreCoord);
            }
        });
        // on ajoute un ajouter au bouton de zoom
        map.zoomProperty().addListener((observable, oldValue, newValue) -> updateMarker());

        map.initialize();

        map.addEventHandler(MapViewEvent.MAP_CLICKED, this::onMapClicked);
    }

    private void onMapClicked(MapViewEvent event) {
        if (page.getModeEdition()) {
            pointCoord = event.getCoordinate();
            updateMarker();
        }
    }

    private void updateMarker(){
        map.removeMarker(marker);
        marker = Marker.createProvided(Marker.Provided.RED).setPosition(pointCoord).setVisible(true);
        map.addMarker(marker);
    }

    @Override
    protected void save() {
        super.save();

        page.setMarker_long(pointCoord.getLongitude());
        page.setMarker_lat(pointCoord.getLatitude());

        page.setCenter_long(map.getCenter().getLongitude());
        page.setCenter_lat(map.getCenter().getLatitude());

        page.setZoom(map.getZoom());
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


    private void setData(Double zoom, Double lat, Double lon) {
        page.setZoom(zoom);
        page.setMarker_long(lat);
        page.setMarker_lat(lon);
    }

    private void displayAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }


    @Override
    public void reagir() {

    }
}
