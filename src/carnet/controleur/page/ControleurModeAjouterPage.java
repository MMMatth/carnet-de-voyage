package carnet.controleur.page;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import carnet.model.PageAccueil;
import carnet.model.PageTextPhoto;
import carnet.model.PageTextPhotoMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControleurModeAjouterPage {
    private final Carnet carnet;

    public ControleurModeAjouterPage(Carnet carnet){
        this.carnet = carnet;
    }

    @FXML
    public void modeVignette() throws PageOutOfRangeException {
        carnet.modeVignette();
    }

    @FXML
    public void addAccueil() {
        carnet.addPageContenu(new PageAccueil());
        carnet.moveTo(carnet.getNombrePagesContenu());
    }

    @FXML
    public void addTextPhoto() {
        carnet.addPageContenu(new PageTextPhoto());
        carnet.moveTo(carnet.getNombrePagesContenu());
    }

    @FXML
    public void addTextPhotoMap(){
        carnet.addPageContenu(new PageTextPhotoMap());
        carnet.moveTo(carnet.getNombrePagesContenu());
    }
}
