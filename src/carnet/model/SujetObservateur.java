package carnet.model;

import carnet.controleur.Observateur;

import java.util.ArrayList;

public class SujetObservateur {
    private final ArrayList<Observateur> observateurs;

    public SujetObservateur() {
        observateurs = new ArrayList<>();
    }

    public void supprimerObservateur(Observateur v) {
        observateurs.remove(v);
    }

    public void ajouterObservateur(Observateur v) {
        observateurs.add(v);
    }

    public void notifierObservateurs() {
        ArrayList<Observateur> copie = new ArrayList<>(observateurs);
        System.out.println("Notifying " + copie.size() + " observers");
        for (Observateur o : copie) {
            o.reagir();
        }
    }
}
