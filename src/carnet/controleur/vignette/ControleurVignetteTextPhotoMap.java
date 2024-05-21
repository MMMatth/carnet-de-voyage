package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageTextPhoto;
import carnet.model.PageTextPhotoMap;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ControleurVignetteTextPhotoMap extends ControleurVignetteTextPhoto{

    private PageTextPhotoMap page;

    @FXML
    private WebView map;

    @FXML
    private ImageView imgMap;

    public ControleurVignetteTextPhotoMap(PageTextPhotoMap page, Carnet carnet) {
        super(page, carnet);
        this.page = page;


    }

    public void initialize() {
        super.initialize();
        initMap();
    }

    private void initMap(){
        map.setZoom(0.5); // on zoom à 50% pour que la carte soit visible
        map.setDisable(true); // on désactive la possibilité de zoomer
        map.setVisible(true);

        WebEngine webEngine = map.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(page.getOpenStreetMapLink());
        imgMap.setVisible(false);
    }
}