package carnet.model;

import carnet.outils.FabriqueNumero;

/**
 * model pour les pages
 */
public abstract class Page {

    private int numero; // numero de la page unique

    public Page(){
        numero = FabriqueNumero.getInstance().getNumeroPage(); // on attribue un numero de page unique
    }

    public boolean estUnePageContenu() {
        return false;
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

    public boolean estTextPhotoMap() {
        return false;
    }

    public int getNumero() {
        return numero;
    }


    public void setNumero(int i) {
        numero = i;
    }

    @Override
    public String toString() {
        return "Page{" +
                "numero=" + numero +
                '}';
    }

    public abstract String toJson();


}
