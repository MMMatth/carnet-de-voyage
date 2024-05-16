package carnet.controleur.vignette;

import carnet.model.Carnet;
import carnet.model.PageTextPhoto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
        img.setImage(new Image(page.getImgPath()));
    }

}



