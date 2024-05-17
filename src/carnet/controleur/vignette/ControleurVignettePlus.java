package carnet.controleur.vignette;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;

public class ControleurVignettePlus {
    Carnet carnet;

    public ControleurVignettePlus(Carnet carnet ){
        this.carnet = carnet;
    }

    @FXML
    public void afficherPage() throws PageOutOfRangeException {
        carnet.modeAjouterPage();
        carnet.notifierObservateurs();
    }
}
