package carnet.controleur.popUp;

import carnet.exceptions.PositionException;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class ControleurEditionMap {

    @FXML
    private Slider zoom;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;

    public ControleurEditionMap() {
    }

    public int getZoom() {
        return (int) zoom.getValue();
    }

    public float getLat() throws PositionException{
        String latString = lat.getText();
        return Float.parseFloat(latString);
    }

    public float getLon() throws PositionException {
        String lonString = lon.getText();
        return Float.parseFloat(lonString);
    }

}
