package carnet.model;

/**
 * model pour la page mode vignette
 */
public class ModeVignette extends Page{

    public ModeVignette(){
        super();
    }

    @Override
    public boolean estModeVignette(){
        return true;
    }

    @Override
    public String toJson() {
        return "";
    }
}
