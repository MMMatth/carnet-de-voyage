package carnet.controleur.vignette;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import javafx.fxml.FXML;

public class ControleurVignettePlus {
    private Carnet carnet;

    public ControleurVignettePlus(Carnet carnet ){
        this.carnet = carnet;
    }

    @FXML
    public void afficherPage() throws PageOutOfRangeException {
        carnet.modeAjouterPage();
    }
}
