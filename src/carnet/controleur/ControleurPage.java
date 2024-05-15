package carnet.controleur;

import carnet.model.Carnet;
import carnet.model.Page;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;

public abstract class ControleurPage {

    protected Carnet carnet;

    protected boolean isEditable;

    public ControleurPage(Carnet carnet, boolean editable) {
        this.carnet = carnet;
        this.isEditable = editable;
    }

    /**
     * methode qui permet de save ce qu'il y'a sur la vue dans le model
     */
    protected abstract void save();

    /**
     * methode qui permet de passer ou non en mode edition
     */
    @FXML
    public abstract void toggleModeEdition();

    public void pageSuivante() {
        try {
            carnet.pageSuivante();
            carnet.notifierObservateurs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pagePrecedente() {
        try {
            carnet.pagePrecedente();
            carnet.notifierObservateurs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void applyStylesheet(DatePicker control, boolean isEditable, String css){
        control.setEditable(isEditable);
        control.setDisable(!isEditable);
        control.getStylesheets().clear();
        control.getStylesheets().add(css);
    }

    protected void applyStylesheet(TextInputControl control, boolean isEditable, String css){
        control.setEditable(isEditable);
        control.getStylesheets().clear();
        control.getStylesheets().add(css);
    }




}
