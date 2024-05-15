package carnet.controleur;

import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageAccueil;
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

        if (currentPage.estPageAccueil()) {
            loader.setLocation(getClass().getResource("/fxml/PageAccueil.fxml"));
            loader.setControllerFactory(iC -> new ControleurPageAccueil(carnet));
        } else if (currentPage.estPageTextPhoto()) {
            loader.setLocation(getClass().getResource("/fxml/PageTextPhoto.fxml"));
            loader.setControllerFactory(iC -> new ControleurPageTextPhoto(carnet));
        }

        Node newPage = loader.load();
        mainPane.setCenter(newPage);
    }

    @Override
    public void reagir() {
        try {
            displayPage();
            System.out.println("Page changed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}