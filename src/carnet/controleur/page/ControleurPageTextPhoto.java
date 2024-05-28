package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.model.Carnet;
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
import java.net.URI;

public class ControleurPageTextPhoto extends ControleurPageContenu implements Observateur {

    private PageTextPhoto page;

    @FXML
    protected TextArea contenu;
    @FXML
    protected ImageView img;
    @FXML
    protected DatePicker date;
    @FXML
    private Label numeroPage;
    @FXML
    private Button filePicker;



    public ControleurPageTextPhoto(Carnet carnet) {
        super(carnet);
        carnet.ajouterObservateur(this);
    }

    @FXML
    void openFileChooser(){
        FileChooser fileChooser = createFileChooser();
        File selectedFile = fileChooser.showOpenDialog(filePicker.getScene().getWindow());
        if (selectedFile != null) {
            applyImage(selectedFile);
        }
    }

    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        return fileChooser;
    }

    protected void applyImage(File selectedFile) {
        Image image = new Image(selectedFile.toURI().toString());
        img.setImage(image);
    }

    protected void save(){
        if (page.estTextPhoto()) {
            URI uri = URI.create(img.getImage().getUrl());
            String path = uri.getPath();
            page.setData(contenu.getText(), date.getValue(), path);
        }

        page.setModeEdition(false);

    }


    @FXML
    public void estModeEdition() {
        String css = modeEdition ? "/styles/edition.css" : "/styles/main.css";

        applyStylesheet(contenu, modeEdition, css);
        applyStylesheet(date, modeEdition, css);
        filePicker.setDisable(!modeEdition);
    }

    @Override
    public void reagir(){

        if (carnet.getPageCourante().estTextPhoto() && !carnet.getPageCourante().estTextPhotoMap()) {

            this.page = (PageTextPhoto) carnet.getPageCourante();
            this.modeEdition = page.getModeEdition();
            contenu.setText(page.getContenu());

            File imgFile = new File(page.getImgPath());
            if (imgFile.exists()) {
                applyImage(imgFile);
            }


            date.setValue(page.getDate());
            date.setDisable(!modeEdition);

            update();
        }
    }
}
