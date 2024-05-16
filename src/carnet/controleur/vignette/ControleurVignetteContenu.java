package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ControleurVignetteContenu {
    protected Carnet carnet;
    protected PageContenu page;

    @FXML
    Button bouton;


    public ControleurVignetteContenu(Carnet carnet, PageContenu page){
        this.carnet = carnet;
        this.page = page;
    }

    @FXML
    public void initialize(){
        bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            afficherPage();
        });
    }

    @FXML
    public void afficherPage() {
        carnet.moveTo(page.getNumero());
        carnet.notifierObservateurs();
    }

    @FXML
    public void modeEdition(){
        page.setModeEdition(true);
        carnet.moveTo(page.getNumero());
        carnet.notifierObservateurs();
    }



    @FXML
    public void menuContextuel(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Supprimer");
        item1.setOnAction(e -> {
            System.out.println("Supprimer");
            carnet.supprimerPage(page.getNumero());
            carnet.notifierObservateurs();
        });
        MenuItem item2 = new MenuItem("Modifier");
        item2.setOnAction(e -> {
            System.out.println("Modifier");
            modeEdition();
        });
        MenuItem item3 = new MenuItem("Aller à la page");
        item3.setOnAction(e -> {
            System.out.println("Aller à la page");
            afficherPage();
        });

        contextMenu.getItems().addAll(item1, item2, item3);
        contextMenu.show(bouton, event.getScreenX(), event.getScreenY());
        contextMenu.setAutoHide(true);

    }

    @FXML
    public void onClick(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            menuContextuel(event);
        } else {
            afficherPage();
        }
    }
}
