package carnet.controleur;

import carnet.model.Carnet;
import carnet.model.PageContenu;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ControleurPageTextPhoto implements Observateur{
    private Carnet carnet;

    private PageTextPhoto page;

    @FXML
    private TextArea contenu;
    @FXML
    private ImageView img;
    @FXML
    private DatePicker date;
    @FXML
    private Label numeroPage;
    @FXML
    private Button filePicker;

    public ControleurPageTextPhoto(Carnet carnet) {
        this.carnet = carnet;
        this.page = (PageTextPhoto) carnet.getPageCourante();
    }

     @FXML
    void initialize() {
        contenu.setText(page.getContenu());
        File imgFile = new File(page.getImgPath());
        if (imgFile.exists()) {
            Image image = new Image(imgFile.toURI().toString());
            img.setImage(image);
        } else {
            // handle the situation when the image file does not exist
            // for example, set a default image or show an error message
        }
        date.setValue(page.getDate());
        numeroPage.setText(page.getNumero() + "/" + carnet.getNombrePages());
    }

    @FXML
    void openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(filePicker.getScene().getWindow());
        if (selectedFile != null) {
            page.setImgPath(selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            img.setImage(image);
            carnet.notifierObservateurs();
        }
    }

    @FXML
    void toggleModeEdition() {

    }

    @FXML
    void pageSuivante() {
        try {
            carnet.pageSuivante();
            carnet.notifierObservateurs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pagePrecedente() {
        try {
            carnet.pagePrecedente();
            carnet.notifierObservateurs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reagir() {

    }
}
