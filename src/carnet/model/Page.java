package carnet.model;

import carnet.outils.FabriqueNumero;

public class Page {

    private int numero; // numero de la page unique
    private String FXMLPath; // chemin vers le fichier FXML de la page

    public Page(){
        numero = FabriqueNumero.getInstance().getNumeroPage();
    }

    public boolean estPageAccueil(){
        return false;
    }

    public boolean estPageTextPhoto(){
        return false;
    }

    public int getNumero() {
        return numero;
    }

    public String getFXMLPath() {
        return FXMLPath;
    }

    public void setFXMLPath(String FXMLPath) {
        this.FXMLPath = FXMLPath;
    }

}
