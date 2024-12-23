package carnet;

import carnet.controleur.page.ControleurNav;
import carnet.exceptions.JsonLoadExeception;
import carnet.exceptions.JsonSaveProblem;
import carnet.model.*;
import carnet.outils.JsonManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {
    private Carnet carnet;
    private JsonManager jsonManager;
    private String path;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        carnet = new Carnet();
        jsonManager = new JsonManager(carnet.getGestionnairePage());

        URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
        Path jarPath = Paths.get(url.toURI()).getParent();
        path = jarPath.resolve("carnet.json").toString();

        try {
            jsonManager.load(path);
        } catch (JsonLoadExeception e) {
            System.out.println(e.getMessage());
        }


        ControleurNav controleurNav = new ControleurNav(carnet, stage);
        controleurNav.initPages();

        stage.setTitle("Carnet de voyages");

        stage.setMinHeight(600);
        stage.setMinWidth(800);

        carnet.notifierObservateurs();

        stage.show();

    }

    @Override
    public void stop() {
        try {
            jsonManager.save(path);
        } catch (JsonSaveProblem ex) {
            System.out.println(ex.getMessage());
        }
    }
}
