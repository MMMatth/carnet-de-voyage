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

    public Double getZoom() {
        return zoom.getValue();
    }

    public Double getLat() throws PositionException{
        String latString = lat.getText();
        return Double.parseDouble(latString);
    }

    public Double getLon() throws PositionException {
        String lonString = lon.getText();
        return Double.parseDouble(lonString);
    }

}
