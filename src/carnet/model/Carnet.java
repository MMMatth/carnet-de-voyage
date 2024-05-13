package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

public class Carnet extends SujetObservateur {
    private Page pageCourante;
    private final GestionnairePage gestionnairePage;

    public Carnet() {
        gestionnairePage = new GestionnairePage();
    }

    public void addPage(Page page) {
        if (gestionnairePage.isEmpty()) {
            pageCourante = page;
        }
        gestionnairePage.ajouterPage(page);
    }

    public Page getPageCourante() {
        return pageCourante;
    }

    public void pageSuivante() throws PageOutOfRangeException {
        int index = pageCourante.getNumero() + 1;
        pageCourante = gestionnairePage.getPage(index);
    }

    public void pagePrecedente() throws PageOutOfRangeException {
        int index = pageCourante.getNumero() - 1;
        pageCourante = gestionnairePage.getPage(index);
    }

}
