package carnet.controleur.page;

import carnet.model.Carnet;
import carnet.model.PageContenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class ControleurPageContenu extends ControleurPage{

    protected boolean modeEdition;

    @FXML
    protected Button pageSuiv;
    @FXML
    protected Button pagePrec;

    public ControleurPageContenu(Carnet carnet, PageContenu carnetPage){
        super(carnet);
        modeEdition = carnetPage.getModeEdition();
    }

    @FXML
    public void initialize(){
        toggleModeEdition();
        if (carnet.pageCouranteEstLaDerniere()){
            pageSuiv.setDisable(true);
            pageSuiv.setVisible(false);
        }
        if (carnet.pageCouranteEstLaPremiere()){
            pagePrec.setDisable(true);
            pagePrec.setVisible(false);
        }
    }

    @Override
    protected abstract void save();

    public abstract void toggleModeEdition();
}
