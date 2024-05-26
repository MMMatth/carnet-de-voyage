package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.exceptions.ProblemePage;
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

            loader.setControllerFactory(iC -> {
                try { return new ControleurPageAccueil(carnet);
                } catch (ProblemePage e) { throw new RuntimeException(e); }
            });

        } else if (currentPage.estTextPhoto()) {
            System.out.println("TextPhoto");
            loader.setLocation(getClass().getResource("/fxml/PageTextPhoto.fxml"));

            loader.setControllerFactory(iC -> {
                try { return new ControleurPageTextPhoto(carnet);
                } catch (ProblemePage e) {throw new RuntimeException(e);}
            });


        } else if (currentPage.estTextPhotoMap()) {
            System.out.println("TextPhotoMap");
            loader.setLocation(getClass().getResource("/fxml/PageTextPhotoMap.fxml"));

            loader.setControllerFactory(iC -> {
                try { return new ControleurPageTextPhotoMap(carnet);
                } catch (ProblemePage e) { throw new RuntimeException(e); }
            });

        } else if (currentPage.estModeVignette()) {
            loader.setLocation(getClass().getResource("/fxml/ModeVignette.fxml"));
            loader.setControllerFactory(iC -> new ControleurModeVignette(carnet));
        } else if (currentPage.estModeAjouterPage()) {
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