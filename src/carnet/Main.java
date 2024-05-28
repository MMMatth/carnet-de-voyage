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

        BorderPane MainPane = new BorderPane();
        MainPane.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        MainPane.setPrefSize(800, 600);

        stage.setScene(new Scene(MainPane));
        ControleurNav controleurNav = new ControleurNav(carnet, MainPane, stage);

        stage.setTitle("Carnet de notes");
//        stage.setMinHeight(600);
//        stage.setMinWidth(800);
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
