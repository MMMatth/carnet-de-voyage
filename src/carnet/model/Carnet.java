package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

import java.util.Iterator;

public class Carnet extends SujetObservateur implements Iterable<Page> {
    private Page pageCourante;
    private final GestionnairePage gestionnairePage;

    public Carnet() {

        gestionnairePage = new GestionnairePage();
        ModeVignette modeVignette = new ModeVignette();
        gestionnairePage.ajouterPage(modeVignette);
    }

    public void addPage(Page page) {
        if (gestionnairePage.getNombrePages() == 0){
            pageCourante = page;
        }
        gestionnairePage.ajouterPage(page);
    }

    public void modeVignette() throws PageOutOfRangeException {
       pageCourante = gestionnairePage.getPage(0);
    }

    public Page getPageCourante() {
        return pageCourante;
    }

    public int getNombrePages() {
        return gestionnairePage.getNombrePages();
    }

    public void pageSuivante() throws PageOutOfRangeException {
        int index = pageCourante.getNumero();
        pageCourante = gestionnairePage.getPage(index + 1);
    }

    public void pagePrecedente() throws PageOutOfRangeException {
        int index = pageCourante.getNumero() - 1;
        pageCourante = gestionnairePage.getPage(index);
    }

    public boolean pageCouranteEstLaDerniere() {
        return pageCourante.getNumero() == gestionnairePage.getNombrePages();
    }

    public void supprimerPage(int numeroPage) {
        gestionnairePage.supprimerPage(numeroPage);
    }

    @Override
    public Iterator<Page> iterator() {
        return gestionnairePage.iterator();
    }

    public void moveTo(int numeroPage) {
        try {
            pageCourante = gestionnairePage.getPage(numeroPage );
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }
}
