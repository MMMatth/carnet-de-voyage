package carnet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PageAccueil extends Page{
    private final String titre;
    private final String auteur;
    private ArrayList<String> participants;
    private final Date dateDebut;
    private final Date dateFin;

    public PageAccueil(String titre, String auteur, Date dateDebut, Date dateFin, String ... participants){
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
}
