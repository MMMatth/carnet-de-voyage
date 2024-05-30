package carnet.model;

import java.net.URI;
import java.time.LocalDate;

/**
 * model pour la page textPhoto
 */
public class PageTextPhoto extends PageContenu{
    private String contenu; // le texte de la page
    private URI imgPath; // le chemin de l'image
    private LocalDate date; // la date de la page


    public PageTextPhoto(LocalDate date, String contenu, String imgPath) {
        this.contenu = contenu;
        this.imgPath = URI.create(imgPath);
        this.date = date;
    }

    public PageTextPhoto(){
        this.contenu = "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna. Pellentesque sit amet sapien fringilla, mattis ligula consectetur, ultrices mauris. Maecenas vitae mattis tellus. Nullam quis imperdiet augue. Vestibulum auctor ornare leo, non suscipit magna interdum eu. Curabitur pellentesque nibh nibh, at maximus ante.";

        this.imgPath = null;

        this.date = null;
    }

    public String getContenu() {
        return contenu;
    }

    public URI getImgPath() {
        return imgPath;
    }

    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setImgPath(URI imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public boolean estTextPhoto(){
        return true;
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

    /**
     * setter pour toutes les données
     * @param contenu le texte de la page
     * @param date la date de la page
     * @param imgPath le chemin de l'image
     */
    public void setData(String contenu, LocalDate date, URI imgPath){
        this.contenu = contenu;
        this.date = date;
        this.imgPath = imgPath;
    }
}

