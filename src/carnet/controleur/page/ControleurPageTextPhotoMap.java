package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.exceptions.PositionException;
import carnet.model.Carnet;
import carnet.model.PageTextPhotoMap;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;

public class ControleurPageTextPhotoMap extends ControleurPageTextPhoto {

    @FXML
    private MapView map;

    private PageTextPhotoMap page;

    private Coordinate centreCoord;

    private Coordinate pointCoord;

    private Marker marker;

    public ControleurPageTextPhotoMap(Carnet carnet) {
        super(carnet);
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



}
