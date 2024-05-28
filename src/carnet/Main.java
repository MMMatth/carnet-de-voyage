package carnet;

import carnet.controleur.page.ControleurNav;
import carnet.exceptions.SaveNotWork;
import carnet.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    Carnet carnet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        carnet = new Carnet();
        try {
            carnet.load();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ControleurNav controleurNav = new ControleurNav(carnet, stage);

        stage.setTitle("Carnet de notes");

        carnet.notifierObservateurs();

        stage.show();

    }

    @Override
    public void stop() {
        try {
            carnet.save();
        } catch (SaveNotWork ex) {
            throw new RuntimeException(ex);
        }
    }
}
