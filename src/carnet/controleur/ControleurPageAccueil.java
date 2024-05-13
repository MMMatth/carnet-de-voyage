package carnet.controleur;

import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageAccueil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleurPageAccueil {

    private Carnet carnet; // le model

    private boolean isEditable = false; // Nouvelle variable

    @FXML
    private TextField titre;
    @FXML
    private TextField auteur;
    @FXML
    private TextArea participants;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;

    public ControleurPageAccueil(Carnet carnet) {
        this.carnet = carnet;
    }

    @FXML
    void toggleModeEdition(){
        isEditable = !isEditable; // Inverser la valeur de isEditable

        titre.setEditable(isEditable);
        auteur.setEditable(isEditable);
        participants.setEditable(isEditable);
        dateDebut.setEditable(isEditable);
        dateFin.setEditable(isEditable);

        dateDebut.setDisable(!isEditable);
        dateFin.setDisable(!isEditable);

        // Changer le style
        if (isEditable) {
            applyStylesheet("/styles/PageAccueil/edition.css");
        } else {
            applyStylesheet("/styles/PageAccueil/accueil.css");
        }
    }

    private void applyStylesheet(String css){
        titre.getStylesheets().clear();
        auteur.getStylesheets().clear();
        participants.getStylesheets().clear();
        dateDebut.getStylesheets().clear();
        dateFin.getStylesheets().clear();

        titre.getStylesheets().add(css);
        auteur.getStylesheets().add(css);
        participants.getStylesheets().add(css);
        dateDebut.getStylesheets().add(css);
        dateFin.getStylesheets().add(css);
    }
}