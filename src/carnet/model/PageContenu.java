package carnet.model;

import java.time.LocalDate;

public class PageContenu extends Page{

    private String contenu;
    private String imgPath;
    private LocalDate dateCreation;

    public PageContenu(LocalDate dateCreation, String contenu, String imgPath) {
        super();
        this.dateCreation = dateCreation;
        this.contenu = contenu;
        this.imgPath = imgPath;
    }

    @Override
    public boolean estPageContenu(){
        return true;
    }
}
