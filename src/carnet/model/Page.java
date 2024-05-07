package carnet.model;

import carnet.outils.FabriqueNumero;

public class Page {
    private int numero; // numero de la page unique

    public Page(){
        numero = FabriqueNumero.getInstance().getNumeroPage();
    }

    public boolean estPageAccueil(){
        return false;
    }

    public boolean estPageContenu(){
        return false;
    }

    public int getNumero() {
        return numero;
    }
}
