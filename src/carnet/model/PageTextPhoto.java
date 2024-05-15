package carnet.model;

import java.time.LocalDate;

public class PageTextPhoto extends PageContenu{
    private String contenu;
    private String imgPath;

    public PageTextPhoto(LocalDate date, String contenu, String imgPath) {
        super(date);
        this.contenu = contenu;
        this.imgPath = imgPath;
    }

    public PageTextPhoto(){
        super(null);
        this.contenu = "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula consectetur, ultrices mauris. Maecenas vitae mattis tellus. Nullam quis imperdiet augue. Vestibulum auctor ornare leo, non suscipit magna interdum eu. Curabitur pellentesque nibh nibh, at maximus ante.";
        this.imgPath = "/image/image_ex.png";
    }

    public String getContenu() {
        return contenu;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean estPageTextPhoto(){
        return true;
    }
}
