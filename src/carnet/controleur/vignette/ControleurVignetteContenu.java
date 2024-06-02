package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
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
        bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> afficherPage());
    }

    @FXML
    public void menuContextuel(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Supprimer");
        item1.setOnAction(e -> supprimerPage());

        MenuItem item2 = new MenuItem("Modifier");
        item2.setOnAction(e -> modeEdition());

        MenuItem item3 = new MenuItem("Aller à la page");
        item3.setOnAction(e -> afficherPage());

        MenuItem item4 = new MenuItem("Déplacer avant");
        item4.setOnAction(e -> deplacerAvant());

        MenuItem item5 = new MenuItem("Déplacer après");
        item5.setOnAction(e -> deplacerApres());

        contextMenu.getItems().addAll(item1, item2, item3, item4, item5);
        contextMenu.show(bouton, event.getScreenX(), event.getScreenY());
        contextMenu.setAutoHide(true);

    }

    private void afficherPage() {
        carnet.moveTo(page.getNumero());
    }

    private void modeEdition(){
        page.setModeEdition(true);
        carnet.moveTo(page.getNumero());
    }

    private void supprimerPage() {
        carnet.supprimerPage(page.getNumero());
    }

    private void deplacerAvant() {
        carnet.deplacerAvant(page.getNumero());
    }

    private void deplacerApres() {
        carnet.deplacerApres(page.getNumero());
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
