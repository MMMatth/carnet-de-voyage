package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.controleur.vignette.*;
import carnet.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.util.HashMap;

public class ControleurModeVignette implements Observateur {
    private Carnet carnet;

    @FXML
    private GridPane grille;

    private HashMap<String, Node> vignettes;

    public ControleurModeVignette(Carnet carnet) {
        this.carnet = carnet;
        carnet.ajouterObservateur(this);
        this.vignettes = new HashMap<>();
    }

    private Node loadVignette(String fxmlPath, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(iC -> controller);
        return loader.load();
    }

    private int getColumn(int numero){
        return (numero - 1) % 2;
    }

    private int getRow(int numero){
        return (numero - 1) / 2;
    }

    @Override
    public void reagir() {
        if (carnet.getPageCourante().estModeVignette()){
            try {
                grille.getChildren().clear();
                int row, column;
                for (Page page : carnet){
                    column = getColumn(page.getNumero());
                    row = getRow(page.getNumero());
                    Node vignette;
                    if (page.estAccueil()){
                        vignette = loadVignette("/fxml/vignettes/VignetteAccueil.fxml", new ControleurVignetteAccueil((PageAccueil) page, carnet));
                        grille.add(vignette, column, row);
                    } else if (page.estTextPhotoMap()) {
                        vignette = loadVignette("/fxml/vignettes/VignetteTextPhotoMap.fxml", new ControleurVignetteTextPhotoMap((PageTextPhotoMap) page, carnet));
                        grille.add(vignette, column, row);
                    } else if (page.estTextPhoto()) {
                        vignette = loadVignette("/fxml/vignettes/VignetteTextPhoto.fxml", new ControleurVignetteTextPhoto((PageTextPhoto) page, carnet));
                        grille.add(vignette, column, row);
                    }
                }
                Node vignette = loadVignette("/fxml/vignettes/VignettePlus.fxml", new ControleurVignettePlus(carnet));
                grille.add(vignette, getColumn(carnet.getNombrePagesContenu() + 1), getRow(carnet.getNombrePagesContenu() + 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
