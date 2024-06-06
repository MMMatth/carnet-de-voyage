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
import java.net.URI;

public class ControleurPageTextPhotoMap extends ControleurPageTextPhoto implements Observateur {

    private PageTextPhotoMap page;

    @FXML
    private MapView map;

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

    /**
     * fonction qui permet de mettre à jour la position du marqueur sur la carte
     * @param event l'événement de clic sur la carte
     */
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
    protected void save(){
        if (carnet.getPageCourante().estTextPhotoMap()) {
            try {
                URI path = page.getImgPath();
                System.out.println("z");
                page.setData(contenu.getText(), date.getValue(), path,
                        marker.getPosition().getLongitude(),
                        marker.getPosition().getLatitude(),
                        map.getCenter().getLongitude(),
                        map.getCenter().getLatitude(),
                        map.getZoom());
            } catch (Exception e) { // cas où l'image = null
                System.out.println("z2");
                page.setData(contenu.getText(), date.getValue(), null,
                        marker.getPosition().getLongitude(),
                        marker.getPosition().getLatitude(),
                        map.getCenter().getLongitude(),
                        map.getCenter().getLatitude(),
                        map.getZoom());
            }
        }
        page.setModeEdition(false);
    }

    @Override
    protected void applyImage(File selectedFile) {
        Image image = new Image(selectedFile.toURI().toString());
        img.setImage(image);
        page.setImgPath(URI.create(image.getUrl()));
    }

    private void updatePageTextPhotoMap(PageTextPhotoMap page){
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

    @Override
    public void reagir() {

        if (carnet.getPageCourante().estTextPhotoMap() && !carnet.getPageCourante().estTextPhoto()) {
            this.page = (PageTextPhotoMap) carnet.getPageCourante();

            updatePageTextPhoto("/image/page/imgBasePetit.png", page);

            updatePageTextPhotoMap(page);

        }
    }



}
