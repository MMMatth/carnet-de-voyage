package carnet.controleur;

import carnet.model.Carnet;
import carnet.model.Page;
import carnet.model.PageContenu;

public abstract class ControleurPageContenu extends ControleurPage{

    boolean modeEdition;

    public ControleurPageContenu(Carnet carnet, PageContenu carnetPage){
        super(carnet);
        modeEdition = carnetPage.getModeEdition();
    }

    @Override
    protected abstract void save();

    public abstract void toggleModeEdition();
}
