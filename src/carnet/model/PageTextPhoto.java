package carnet.model;

import java.time.LocalDate;

public class PageTextPhoto extends PageContenu{
    private String contenu;
    private String imgPath;
    private LocalDate date;


    public PageTextPhoto(LocalDate date, String contenu, String imgPath) {
        this.contenu = contenu;
        this.imgPath = imgPath;
        this.date = date;
    }

    public PageTextPhoto(){
        this.contenu = "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula consectetur, ultrices mauris. Maecenas vitae mattis tellus. Nullam quis imperdiet augue. Vestibulum auctor ornare leo, non suscipit magna interdum eu. Curabitur pellentesque nibh nibh, at maximus ante.";
        this.imgPath = "/image/page/imgBaseGrande.png";
        this.date = LocalDate.now();
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
    public boolean estTextPhoto(){
        return true;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"type\": \"textPhoto\",\n");
        sb.append("  \"date\": \"").append(date).append("\",\n");
        sb.append("  \"contenu\": \"").append(contenu).append("\",\n");
        sb.append("  \"imgPath\": \"").append(imgPath).append("\"\n");
        sb.append("}");
        return sb.toString();
    }
}
