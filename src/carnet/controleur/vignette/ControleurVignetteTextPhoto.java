package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class ControleurVignetteTextPhoto extends ControleurVignetteContenu {
    private PageTextPhoto page;

    @FXML
    private Label date;
    @FXML
    private Label contenu;
    @FXML
    private ImageView img;

    public ControleurVignetteTextPhoto(PageTextPhoto page, Carnet carnet){
        super(carnet, page);
        this.page = page;
        this.carnet = carnet;
    }

    @FXML
    public void initialize(){
        if (page.getDate() != null) {
            date.setText(page.getDate().toString());
        } else {
            date.setText("JJ/MM/AAAA");
        }
        contenu.setText(page.getContenu());
        File imgFile;
        try {
            imgFile = new File(page.getImgPath());
        } catch (Exception e) {
            imgFile = new File("/image/page/imgBaseGrande.png");
        }
        if (imgFile.exists()) {
            applyImage(imgFile);
        }else {
            page.setImgPath("/image/page/imgBaseGrande.png");
        }
    }
    private void applyImage(File selectedFile) {
        page.setImgPath(selectedFile.getAbsolutePath());
        Image image = new Image(selectedFile.toURI().toString());
        img.setImage(image);
    }



}



