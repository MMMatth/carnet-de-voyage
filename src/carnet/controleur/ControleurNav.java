package carnet.controleur;

import carnet.controleur.page.ControleurModeAjouterPage;
import carnet.controleur.page.ControleurModeVignette;
import carnet.controleur.page.ControleurPageAccueil;
import carnet.controleur.page.ControleurPageTextPhoto;
import carnet.model.Carnet;
import carnet.model.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ControleurNav implements Observateur {
    Carnet carnet;

    @FXML
    BorderPane mainPane;

    public ControleurNav(Carnet carnet, BorderPane mainPane) {
        this.carnet = carnet;
        this.carnet.ajouterObservateur(this);
        this.mainPane = mainPane;
    }

    public void displayPage() throws IOException {
        Page currentPage = carnet.getPageCourante();
        FXMLLoader loader = new FXMLLoader();

        if (currentPage.estAccueil()) {
            loader.setLocation(getClass().getResource("/fxml/PageAccueil.fxml"));
            loader.setControllerFactory(iC -> new ControleurPageAccueil(carnet));
        } else if (currentPage.estTextPhoto()) {
            loader.setLocation(getClass().getResource("/fxml/PageTextPhoto.fxml"));
            loader.setControllerFactory(iC -> new ControleurPageTextPhoto(carnet));
        } else if (currentPage.estModeVignette()){
            loader.setLocation(getClass().getResource("/fxml/ModeVignette.fxml"));
            loader.setControllerFactory(iC -> new ControleurModeVignette(carnet));
        } else if (currentPage.estModeAjouterPage()){
            loader.setLocation(getClass().getResource("/fxml/PageAddPage.fxml"));
            loader.setControllerFactory(iC -> new ControleurModeAjouterPage(carnet));
        }

        Node newPage = loader.load();
        mainPane.setCenter(newPage);
    }

    @Override
    public void reagir() {
        try {
            displayPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}