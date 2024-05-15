package carnet;

import carnet.controleur.ControleurNav;
import carnet.controleur.ControleurPageAccueil;
import carnet.controleur.ControleurPageTextPhoto;
import carnet.model.Page;
import carnet.model.PageAccueil;
import carnet.model.PageContenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import carnet.model.Carnet;

import javax.swing.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Carnet carnet = new Carnet();
        carnet.addPage(new PageAccueil("Titre", "Auteur", null, null, "Participant 1", "Participant 2"));
        carnet.addPage(new PageContenu(null,"Texte", "Photo"));

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));
//        loader.setControllerFactory(iC -> new ControleurPageAccueil(carnet, (PageAccueil) carnet.getPageCourante()));
//        Parent root = loader.load();

        BorderPane MainPane = new BorderPane();
        MainPane.setPrefSize(800, 600);


        ControleurNav controleurNav = new ControleurNav(carnet, MainPane);
        controleurNav.displayPage();


        stage.setTitle("Carnet de notes");
        stage.setScene(new Scene(MainPane, 800, 600));
        stage.show();

//
//        stage.setTitle("Carnet de notes");
//        stage.setScene(new Scene(root, 800, 600));
//        stage.show();



    }
}
