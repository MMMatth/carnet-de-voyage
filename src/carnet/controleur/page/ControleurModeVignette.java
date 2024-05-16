package carnet.controleur.page;

import carnet.controleur.vignette.ControleurVignetteContenu;
import carnet.controleur.vignette.ControleurVignetteAccueil;
import carnet.controleur.vignette.ControleurVignettePlus;
import carnet.controleur.vignette.ControleurVignetteTextPhoto;
import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageAccueil;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ControleurModeVignette {
    Carnet carnet;

    @FXML
    GridPane grille;

    public ControleurModeVignette(Carnet carnet) {
        this.carnet = carnet;
    }

    private int getRow(int numero){
        return (numero +1) / 2;
    }
    private int getColumn(int numero){
        return (numero +1) % 2;
    }

    @FXML
    public void initialize() throws Exception {
        grille.getChildren().clear();
        int row, column;
        for (Page page : carnet){
            FXMLLoader loader = new FXMLLoader();
            column = getColumn(page.getNumero());
            row = getRow(page.getNumero());
            if (page.estAccueil()){
                displayVignette("/fxml/vignettes/VignetteAccueil.fxml", column, row, new ControleurVignetteAccueil((PageAccueil) page, carnet));
            } else if (page.estTextPhoto()){
                displayVignette("/fxml/vignettes/VignetteTextPhoto.fxml", column, row, new ControleurVignetteTextPhoto((PageTextPhoto) page, carnet));
            }

        }
        column = getColumn(carnet.getNombrePages() + 1);
        row = getRow(carnet.getNombrePages() + 1 );
        displayVignette("/fxml/vignettes/VignettePlus.fxml", column, row, new ControleurVignettePlus(carnet));

    }

    private void displayVignette(String fxmlPath, int column, int row, ControleurVignetteContenu controller) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        loader.setControllerFactory(iC -> controller);
        Node vignette = loader.load();
        grille.add(vignette, column, row);
    }

    private void displayVignette(String fxmlPath, int column, int row, ControleurVignettePlus controller) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        loader.setControllerFactory(iC -> controller);
        Node vignette = loader.load();
        grille.add(vignette, column, row);
    }
}
