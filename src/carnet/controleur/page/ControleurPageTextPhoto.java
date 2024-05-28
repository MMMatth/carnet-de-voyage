package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.exceptions.ProblemePage;
import carnet.exceptions.SaveNotWork;
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

public class ControleurPageTextPhoto extends ControleurPageContenu implements Observateur {

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

    private void applyImage(File selectedFile) {
        page.setImgPath(selectedFile.getAbsolutePath());
        Image image = new Image(selectedFile.toURI().toString());
        img.setImage(image);
    }

    protected void save(){
        page.setContenu(contenu.getText());
        page.setDate(date.getValue());
        page.setModeEdition(false);

        carnet.notifierObservateurs();
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


        }
        super.reagir();

    }
}
