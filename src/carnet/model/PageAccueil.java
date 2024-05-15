package carnet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PageAccueil extends Page{
    private  String titre;
    private  String auteur;
    private ArrayList<String> participants;
    private  LocalDate dateDebut;
    private  LocalDate dateFin;

    public PageAccueil(String titre, String auteur, LocalDate dateDebut, LocalDate dateFin, String ... participants){
        super();
        this.titre = titre;
        this.auteur = auteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.participants = new ArrayList<>();
        Collections.addAll(this.participants, participants);
    }

    @Override
    public boolean estPageAccueil(){
        return true;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
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

    public void setTitre(String text) {
        this.titre = text;
    }

    public void setAuteur(String text) {
        this.auteur = text;
    }

    public void setParticipants(String text) {
        this.participants.clear();
        String[] parts = text.split("\n");
        Collections.addAll(this.participants, parts);
    }

    public void setDateDebut(LocalDate value) {
        this.dateDebut = value;
    }

    public void setDateFin(LocalDate value) {
        this.dateFin = value;
    }

    public void addParticipant(String participant){
        participants.add(participant);
    }
}
