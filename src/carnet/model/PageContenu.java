package carnet.model;

import java.time.LocalDate;

public class PageContenu extends Page{

    private boolean modeEdition;

    public PageContenu() {
        super();
        this.modeEdition = false;
    }


    public void setModeEdition(boolean modeEdition) {
        this.modeEdition = modeEdition;
    }

    public boolean getModeEdition() {
        return modeEdition;
    }

}
