package carnet;

import carnet.controleur.page.ControleurNav;
import carnet.exceptions.SaveNotWork;
import carnet.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Carnet carnet = new Carnet();
        try {
            carnet.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BorderPane MainPane = new BorderPane();
        MainPane.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        MainPane.setPrefSize(800, 600);
        stage.setOnCloseRequest(e -> {
            try {
                carnet.save();
            } catch (SaveNotWork ex) {
                throw new RuntimeException(ex);
            }
        });

        ControleurNav controleurNav = new ControleurNav(carnet, MainPane);
        controleurNav.displayPage();

        stage.setTitle("Carnet de notes");
        stage.setScene(new Scene(MainPane, 800, 600));
        stage.show();

    }
}
