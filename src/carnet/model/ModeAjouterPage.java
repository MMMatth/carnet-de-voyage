package carnet.model;

/**
 * model pour la page ajouter page
 */
public class ModeAjouterPage extends Page{

    public ModeAjouterPage(){
        super();
    }

    @Override
    public boolean estModeAjouterPage(){
        return true;
    }

    @Override
    public String toJson() {
        return "";
    }
}
