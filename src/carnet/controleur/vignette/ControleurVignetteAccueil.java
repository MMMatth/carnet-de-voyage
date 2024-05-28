package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageAccueil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControleurVignetteAccueil extends ControleurVignetteContenu {
    private PageAccueil page;

    @FXML
    private Label titre;

    public ControleurVignetteAccueil(PageAccueil page, Carnet carnet){
        super(carnet, page);
        this.page = page;
        this.carnet = carnet;
    }

    @FXML
    public void initialize(){
        titre.setText(page.getTitre());
    }

}



