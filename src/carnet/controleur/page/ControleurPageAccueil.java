package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.model.Carnet;
import carnet.model.PageAccueil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

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
        if (page.estAccueil()){
            String titre = this.titre.getText();
            String auteur = this.auteur.getText();
            String[] participants = this.participants.getText().split("\n");
            LocalDate dateDebut = this.dateDebut.getValue();
            LocalDate dateFin = this.dateFin.getValue();

            page.setData(titre, auteur, dateDebut, dateFin, participants);
        }

        page.setModeEdition(false);

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

            update();

        }
    }


}