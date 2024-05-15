package carnet.controleur;

import carnet.exceptions.PageOutOfRangeException;
import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageAccueil;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControleurPageAccueil extends ControleurPage{

    private PageAccueil page;

    // les champs de la vue
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
        super(carnet, false);
        this.page = (PageAccueil) carnet.getPageCourante();
    }

    @FXML
    void initialize() {
        titre.setText(page.getTitre());
        auteur.setText(page.getAuteur());
        participants.setText(page.getParticipants());
        dateDebut.setValue(page.getDateDebut());
        dateFin.setValue(page.getDateFin());
    }

    protected void save(){
        page.setTitre(titre.getText());
        page.setAuteur(auteur.getText());
        page.setParticipants(participants.getText());
        page.setDateDebut(dateDebut.getValue());
        page.setDateFin(dateFin.getValue());
    }

    @FXML
    public void toggleModeEdition(){
        if (isEditable){ // dans le cas ou on est en mode edition on sauvegarde les modifications
            save();
        }

        isEditable = !isEditable; // Inverser la valeur de isEditable

        // Changer le style
        String css = isEditable ? "/styles/edition.css" : "/styles/nonedition.css";

        applyStylesheet(titre, isEditable, css);
        applyStylesheet(auteur, isEditable, css);
        applyStylesheet(participants, isEditable, css);
        applyStylesheet(dateDebut, isEditable, css);
        applyStylesheet(dateFin, isEditable, css);
    }


}