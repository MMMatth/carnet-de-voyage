package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class ControleurPageContenu extends ControleurPage implements Observateur {

    protected boolean modeEdition;

    @FXML
    protected Button pageSuiv;
    @FXML
    protected Button pagePrec;

    @FXML
    protected Button vignette;
    @FXML
    protected Button valider;

    @FXML
    protected Label numeroPage;


    public ControleurPageContenu(Carnet carnet){
        super(carnet);
        modeEdition = false;
    }



    @Override
    protected abstract void save();

    @FXML
    public void clickOnSave(){
        save();
    }

    public abstract void estModeEdition();


    @Override
    public void reagir() {
        estModeEdition();

        if (carnet.pageCouranteEstLaDerniere()) {
            pageSuiv.setDisable(true);
            pageSuiv.setVisible(false);
        }
        if (carnet.pageCouranteEstLaPremiere()) {
            pagePrec.setDisable(true);
            pagePrec.setVisible(false);
        }
        if (modeEdition) {
            vignette.setDisable(true);
            vignette.setVisible(false);
            valider.setDisable(false);
            valider.setVisible(true);
        } else {
            vignette.setDisable(false);
            vignette.setVisible(true);
            valider.setDisable(true);
            valider.setVisible(false);
        }

        numeroPage.setText(String.valueOf(carnet.getPageCourante().getNumero()));
    }
}
