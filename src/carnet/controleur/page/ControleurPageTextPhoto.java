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
import java.io.InputStream;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ControleurPageTextPhoto extends ControleurPageContenu implements Observateur {

    private PageTextPhoto page;

    @FXML
    protected TextArea contenu;
    @FXML
    protected ImageView img;
    @FXML
    protected DatePicker date;
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
        if (carnet.getPageCourante().estTextPhoto()) {
            System.out.println(img.getImage().getUrl());
            try {
                URI path = URI.create(img.getImage().getUrl());
                page.setData(contenu.getText(), date.getValue(), path);
            } catch (Exception e) {
                page.setData(contenu.getText(), date.getValue(), null);
            }
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

    protected void initTextPhoto(String defaultImg, PageTextPhoto page){
        this.modeEdition = page.getModeEdition();

        updatePageContenu();

        contenu.setText(page.getContenu());

        InputStream stream;
        if (page.getImgPath() == null) {
            stream = getClass().getResourceAsStream(defaultImg);
        } else {
            try {
                stream = page.getImgPath().toURL().openStream();
            } catch (Exception e) {
                stream = getClass().getResourceAsStream(defaultImg);
            }
        }
        if (stream != null) {
            Image image = new Image(stream);
            img.setImage(image);
        }


        date.setValue(page.getDate());
        date.setDisable(!modeEdition);
    }

    @Override
    public void reagir(){

        if (carnet.getPageCourante().estTextPhoto() && !carnet.getPageCourante().estTextPhotoMap()) {
            this.page = (PageTextPhoto) carnet.getPageCourante();

            initTextPhoto("/image/page/imgBaseGrande.png", page);

        }
    }
}
