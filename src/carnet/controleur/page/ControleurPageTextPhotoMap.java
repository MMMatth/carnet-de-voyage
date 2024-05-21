package carnet.controleur.page;

import carnet.model.Carnet;
import carnet.model.PageTextPhotoMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
        if (page.getModeEdition()){
            map.setDisable(true);
            mapEdit.setDisable(false);
        } else {
            map.setDisable(false);
            mapEdit.setDisable(true);
        }
    }

    private void initMap(){
        WebEngine webEngine = map.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(page.getOpenStreetMapLink());
        map.setVisible(true);
    }

    @FXML
    public void onClicEditMap() {
        System.out.println("Edit map");

    }


}
