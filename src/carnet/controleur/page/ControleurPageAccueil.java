package carnet.controleur.page;

import carnet.model.Carnet;
import carnet.model.PageAccueil;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControleurPageAccueil extends ControleurPageContenu{

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
        super(carnet, (PageContenu) carnet.getPageCourante());
        this.page = (PageAccueil) carnet.getPageCourante();
    }

    @FXML
    public void initialize() {
        super.initialize();

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

        page.setModeEdition(false);
    }

    @FXML
    public void toggleModeEdition(){
        String css = modeEdition ? "/styles/edition.css" : "/styles/nonedition.css";

        applyStylesheet(titre, modeEdition, css);
        applyStylesheet(auteur, modeEdition, css);
        applyStylesheet(participants, modeEdition, css);
        applyStylesheet(dateDebut, modeEdition, css);
        applyStylesheet(dateFin, modeEdition, css);
    }


}