package carnet.model;

import carnet.outils.FabriqueNumero;

public class Page {

    private int numero; // numero de la page unique

    public Page(){
        numero = FabriqueNumero.getInstance().getNumeroPage();
    }

    public boolean estAccueil(){
        return false;
    }

    public boolean estTextPhoto(){
        return false;
    }

    public boolean estModeVignette(){
        return false;
    }

    public boolean estModeAjouterPage(){
        return false;
    }

    public int getNumero() {
        return numero;
    }


    public void setNumero(int i) {
        numero = i;
    }
}
