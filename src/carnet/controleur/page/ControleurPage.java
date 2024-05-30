package carnet.controleur.page;

import carnet.model.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;

public abstract class ControleurPage {

    protected Carnet carnet;


    public ControleurPage(Carnet carnet) {
        this.carnet = carnet;
    }

    /**
     * methode qui permet de save ce qu'il y'a sur la vue dans le model
     */
    protected abstract void save();


    @FXML
    public void pageSuivante() {
        try {
            carnet.pageSuivante();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pagePrecedente() {
        try {
            carnet.pagePrecedente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modeVignette() {
        try {
            carnet.modeVignette();
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
