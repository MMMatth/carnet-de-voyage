package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.model.Carnet;
import carnet.model.PageTextPhotoMap;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

public class ControleurPageTextPhotoMap extends ControleurPageTextPhoto implements Observateur {

    @FXML
    private MapView map;

    private PageTextPhotoMap page;

    private Coordinate centreCoord;

    private Coordinate pointCoord;

    private Marker marker;

    public ControleurPageTextPhotoMap(Carnet carnet)  {
        super(carnet);
    }


    private void initMap(){
        map.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                map.setZoom(page.getZoom());
                map.addMarker(marker);
                map.setCenter(centreCoord);
            }
        });

        map.addEventHandler(MapViewEvent.MAP_CLICKED, this::onMapClicked);

        map.initialize();

    }

    private void onMapClicked(MapViewEvent event) {
        if (page.getModeEdition()) {
            pointCoord = event.getCoordinate();
            updateMarker();
        }
    }

    private void updateMarker(){
        if (map.getInitialized()) {
            map.removeMarker(marker);
            marker = Marker.createProvided(Marker.Provided.RED).setPosition(pointCoord).setVisible(true);
            map.addMarker(marker);
        }
    }

    @Override
    protected void save() {
        if (page.estTextPhotoMap() && !page.estTextPhoto()) {
            try {
                URI path = URI.create(img.getImage().getUrl());
                page.setData(contenu.getText(), date.getValue(), path, pointCoord.getLongitude(), pointCoord.getLatitude(), map.getCenter().getLongitude(), map.getCenter().getLatitude(), map.getZoom());
            }catch (Exception e){
                page.setData(contenu.getText(), date.getValue(), null, pointCoord.getLongitude(), pointCoord.getLatitude(), map.getCenter().getLongitude(), map.getCenter().getLatitude(), map.getZoom());
            }
        }

        page.setModeEdition(false);
    }

    @Override
    public void reagir() {

        if (carnet.getPageCourante().estTextPhotoMap() && !carnet.getPageCourante().estTextPhoto()) {

            this.page = (PageTextPhotoMap) carnet.getPageCourante();

            initTextPhoto("/image/page/imgBasePetit.png", page);

            centreCoord = new Coordinate(page.getCenter_lat(), page.getCenter_long());
            pointCoord = new Coordinate(page.getMarker_lat(), page.getMarker_long());
            marker = Marker.createProvided(Marker.Provided.RED).setPosition(pointCoord).setVisible(true);

            if (map.getInitialized()){
                updateMarker();
                map.setCenter(centreCoord);
            }else {
                initMap();
            }


        }
    }



}
