package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

public class Carnet extends SujetObservateur {
    private Page pageCourante;
    private final GestionnairePage gestionnairePage;

    public Carnet() {
        gestionnairePage = new GestionnairePage();
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
