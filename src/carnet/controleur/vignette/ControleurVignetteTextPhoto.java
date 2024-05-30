package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;
import java.net.URI;


public class ControleurVignetteTextPhoto extends ControleurVignetteContenu {
    private PageTextPhoto page;

    @FXML
    private Label date;
    @FXML
    private Label contenu;
    @FXML
    private ImageView img;

    protected String defaultImgPath;

    public ControleurVignetteTextPhoto(PageTextPhoto page, Carnet carnet){
        super(carnet, page);
        this.page = page;
        this.carnet = carnet;
        defaultImgPath = "/image/page/imgBaseGrande.png";
    }

    @FXML
    public void initialize(){
        if (page.getDate() != null) {
            date.setText(page.getDate().toString());
        } else {
            date.setText("JJ/MM/AAAA");
        }
        contenu.setText(page.getContenu());

        InputStream stream;
        if (page.getImgPath() == null) {
            stream = getClass().getResourceAsStream(defaultImgPath);
        } else {
            try {
                stream = page.getImgPath().toURL().openStream();
            } catch (Exception e) {
                stream = getClass().getResourceAsStream(defaultImgPath);
            }
        }
        if (stream != null) {
            Image image = new Image(stream);
            img.setImage(image);
        }
    }




}



