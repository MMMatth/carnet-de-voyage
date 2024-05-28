package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.exceptions.ProblemePage;
import carnet.exceptions.SaveNotWork;
import carnet.model.Carnet;
import carnet.model.PageAccueil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControleurPageAccueil extends ControleurPageContenu implements Observateur {

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
        super(carnet);
        carnet.ajouterObservateur(this);
    }




    protected void save(){
        page.setTitre(titre.getText());
        page.setAuteur(auteur.getText());
        page.setParticipants(participants.getText());
        page.setDateDebut(dateDebut.getValue());
        page.setDateFin(dateFin.getValue());

        page.setModeEdition(false);

        carnet.notifierObservateurs();
    }

    @FXML
    public void estModeEdition(){
        String css = modeEdition ? "/styles/edition.css" : "/styles/main.css";

        applyStylesheet(titre, modeEdition, css);
        applyStylesheet(auteur, modeEdition, css);
        applyStylesheet(participants, modeEdition, css);
        applyStylesheet(dateDebut, modeEdition, css);
        applyStylesheet(dateFin, modeEdition, css);
    }

    @Override
    public void reagir(){

        if (carnet.getPageCourante().estAccueil()) {
            this.page = (PageAccueil) carnet.getPageCourante();

            this.modeEdition = page.getModeEdition();

            titre.setText(page.getTitre());
            auteur.setText(page.getAuteur());
            participants.setText(page.getParticipants());
            dateDebut.setValue(page.getDateDebut());
            dateFin.setValue(page.getDateFin());

        }
        super.reagir();
    }


}