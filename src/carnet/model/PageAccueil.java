package carnet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * model pour la page accueil
 */
public class PageAccueil extends PageContenu{

    private  String titre; // titre de la page
    private  String auteur; // auteur de la page
    private ArrayList<String> participants; // liste des participants au carnet
    private  LocalDate dateDebut; // date de debut du carnet
    private  LocalDate dateFin;  // date de fin du carnet

    public PageAccueil(String titre, String auteur, LocalDate dateDebut, LocalDate dateFin, String ... participants){
        super();
        this.titre = titre;
        this.auteur = auteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.participants = new ArrayList<>();
        Collections.addAll(this.participants, participants);
    }

    public PageAccueil(){
        super();
        this.titre = "TITRE";
        this.auteur = "Auteur";
        this.dateDebut = null;
        this.dateFin = null;
        this.participants = new ArrayList<>();
        Collections.addAll( this.participants, "Participant 1", "Participant 2", "Participant 3");
    }


    public String getParticipants() {
        StringBuilder sb = new StringBuilder();
        for(String participant : participants){
            sb.append(participant).append("\n");
        }
        return sb.toString();
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    @Override
    public boolean estAccueil(){
        return true;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"type\": \"accueil\",\n");
        json.append("  \"titre\": \"").append(titre).append("\",\n");
        json.append("  \"auteur\": \"").append(auteur).append("\",\n");
        json.append("  \"dateDebut\": \"").append(dateDebut).append("\",\n");
        json.append("  \"dateFin\": \"").append(dateFin).append("\",\n");
        json.append("  \"participants\": [\n");
        for (String participant : participants){
            json.append("    \"").append(participant).append("\",\n");
        }
        json.deleteCharAt(json.length() - 2);
        json.append("  ]\n");
        json.append("}");
        return json.toString();
    }

    /**
     * setter pour toutes les données
     * @param titre titre de la page
     * @param auteur auteur de la page
     * @param dateDebut la date de debut
     * @param dateFin  la date de fin
     * @param participants les participants
     */
    public void setData(String titre, String auteur, LocalDate dateDebut, LocalDate dateFin, String ... participants){
        this.titre = titre;
        this.auteur = auteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.participants = new ArrayList<>();
        Collections.addAll(this.participants, participants);
    }
}
