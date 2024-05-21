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

    @FXML
    protected Button vignette;
    @FXML
    protected Button valider;

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
        if (modeEdition){
            vignette.setDisable(true);
            vignette.setVisible(false);
            valider.setDisable(false);
            valider.setVisible(true);
        }else {
            vignette.setDisable(false);
            vignette.setVisible(true);
            valider.setDisable(true);
            valider.setVisible(false);
        }
    }

    @Override
    protected abstract void save();

    @FXML
    public void clickOnSave(){
        save();
        carnet.notifierObservateurs();
    }

    public abstract void toggleModeEdition();
}
