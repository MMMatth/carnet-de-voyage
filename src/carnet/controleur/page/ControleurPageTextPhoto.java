package carnet.controleur.page;

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

public class ControleurPageTextPhoto extends ControleurPageContenu{

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
    @FXML
    private Button flecheDroite;


    public ControleurPageTextPhoto(Carnet carnet) {
        super(carnet, (PageTextPhoto) carnet.getPageCourante());
        this.page = (PageTextPhoto) carnet.getPageCourante();
    }

     @FXML
    void initialize() {
        contenu.setText(page.getContenu());
        File imgFile = new File(page.getImgPath());
        if (imgFile.exists()) {
            applyImage(imgFile);
        }
        date.setValue(page.getDate());
        numeroPage.setText(page.getNumero() + "/" + (carnet.getNombrePagesContenu()));

        if (carnet.pageCouranteEstLaDerniere()) {
            flecheDroite.setDisable(true);
            flecheDroite.setVisible(false);
        }

        toggleModeEdition();

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
    }

    @FXML
    public void toggleModeEdition() {
        String css = modeEdition ? "/styles/edition.css" : "/styles/nonedition.css";

        applyStylesheet(contenu, modeEdition, css);
        applyStylesheet(date, modeEdition, css);
        filePicker.setDisable(!modeEdition);
    }

}
