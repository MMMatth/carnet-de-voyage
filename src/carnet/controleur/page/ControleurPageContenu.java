package carnet.controleur.page;

import carnet.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ControleurPageContenu extends ControleurPage  {

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
        modeEdition = false;
        carnet.notifierObservateurs();
    }


    public abstract void estModeEdition();


    public void updatePageContenu() {
        if (carnet.getPageCourante().estUnePageContenu()) {
            estModeEdition();

            if (carnet.pageCouranteEstLaDerniere() && carnet.pageCouranteEstLaPremiere()) {
                setPagePrec(false);
                setPageSuiv(false);
            } else if (carnet.pageCouranteEstLaDerniere()) {
                setPagePrec(true);
                setPageSuiv(false);
            } else if (carnet.pageCouranteEstLaPremiere()) {
                setPagePrec(false);
                setPageSuiv(true);
            } else {
                setPagePrec(true);
                setPageSuiv(true);
            }

            if (modeEdition) {
                setVignette(false);
                setValider(true);
                setPageSuiv(false);
                setPagePrec(false);
            } else {
                setVignette(true);
                setValider(false);
            }
        }

        numeroPage.setText(String.valueOf(carnet.getPageCourante().getNumero()));
    }

    private void setPageSuiv(boolean enable){
        pageSuiv.setDisable(!enable);
        pageSuiv.setVisible(enable);
    }

    private void setPagePrec(boolean enable){
        pagePrec.setDisable(!enable);
        pagePrec.setVisible(enable);
    }

    private void setVignette(boolean enable){
        vignette.setDisable(!enable);
        vignette.setVisible(enable);
    }

    private void setValider(boolean enable){
        valider.setDisable(!enable);
        valider.setVisible(enable);
    }
}
