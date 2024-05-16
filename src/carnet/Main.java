package carnet;

import carnet.controleur.ControleurNav;
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
        carnet.addPage(new PageAccueil());
        carnet.addPage(new PageTextPhoto());
        carnet.addPage(new PageTextPhoto());
        carnet.addPage(new PageTextPhoto());


//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));
//        loader.setControllerFactory(iC -> new ControleurPageAccueil(carnet, (PageAccueil) carnet.getPageCourante()));
//        Parent root = loader.load();

        BorderPane MainPane = new BorderPane();
        MainPane.getStylesheets().add(getClass().getResource("/styles/nonedition.css").toExternalForm());
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
