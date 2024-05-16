package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageContenu;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;

public class ControleurVignettePlus {
    Carnet carnet;

    public ControleurVignettePlus(Carnet carnet ){
        this.carnet = carnet;
    }

    @FXML
    public void afficherPage() {
        carnet.addPage(new PageTextPhoto());
        carnet.moveTo(carnet.getNombrePages());
        carnet.notifierObservateurs();
    }
}
