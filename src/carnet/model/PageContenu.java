package carnet.model;

/**
 * model pour les pages de contenu
 */
public abstract class PageContenu extends Page{

    private boolean modeEdition; // pour savoir si on est en mode edition ou non

    public PageContenu() {
        super();
        this.modeEdition = false;
    }

    public boolean estUnePageContenu() {
        return true;
    }

    public void setModeEdition(boolean modeEdition) {
        this.modeEdition = modeEdition;
    }

    public boolean getModeEdition() {
        return modeEdition;
    }


}
