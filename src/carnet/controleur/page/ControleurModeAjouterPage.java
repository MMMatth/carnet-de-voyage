package carnet.controleur.page;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import carnet.model.PageAccueil;
import carnet.model.PageTextPhoto;
import carnet.model.PageTextPhotoMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControleurModeAjouterPage {
    private Carnet carnet;

    @FXML
    Button quitButton;
    @FXML
    Button addAccueil;
    @FXML
    Button addTextPhoto;

    public ControleurModeAjouterPage(Carnet carnet){
        this.carnet = carnet;
    }

    @FXML
    public void modeVignette() throws PageOutOfRangeException {
        carnet.modeVignette();
        carnet.notifierObservateurs();
    }

    @FXML
    public void addAccueil() {
        carnet.addPageContenu(new PageAccueil());
        carnet.moveTo(carnet.getNombrePagesContenu());
        carnet.notifierObservateurs();
    }

    @FXML
    public void addTextPhoto() {
        carnet.addPageContenu(new PageTextPhoto());
        carnet.moveTo(carnet.getNombrePagesContenu());
        carnet.notifierObservateurs();
    }

    @FXML
    public void addTextPhotoMap(){
        carnet.addPageContenu(new PageTextPhotoMap());
        carnet.moveTo(carnet.getNombrePagesContenu());
        carnet.notifierObservateurs();
    }
}
