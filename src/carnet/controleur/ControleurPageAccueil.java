package carnet.controleur;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageAccueil;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleurPageAccueil {

    private Carnet carnet; // le model

    private PageAccueil page; // la page courante

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
        this.page = (PageAccueil) carnet.getPageCourante();

    }

    @FXML
    void initialize() {
        PageAccueil page = (PageAccueil) carnet.getPageCourante();
        titre.setText(page.getTitre());
        auteur.setText(page.getAuteur());
        participants.setText(page.getParticipants());
        dateDebut.setValue(page.getDateDebut());
        dateFin.setValue(page.getDateFin());
    }

    private void save(){
        page.setTitre(titre.getText());
        page.setAuteur(auteur.getText());
        page.setParticipants(participants.getText());
        page.setDateDebut(dateDebut.getValue());
        page.setDateFin(dateFin.getValue());
    }

    @FXML
    void toggleModeEdition(){
        if (isEditable){ // dans le cas ou on est en mode edition on sauvegarde les modifications
            save();
        }

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

    @FXML
    void pageSuivante(){
        try {
            carnet.pageSuivante();
            carnet.notifierObservateurs();
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }
}