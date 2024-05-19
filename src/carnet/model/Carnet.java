package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

import java.util.Iterator;

public class Carnet extends SujetObservateur implements Iterable<Page> {
    private Page pageCourante;
    private final GestionnairePage gestionnairePage;

    public Carnet() {
        gestionnairePage = new GestionnairePage();
        gestionnairePage.ajouterPage(new ModeVignette());
        gestionnairePage.ajouterPage(new ModeAjouterPage());
    }

    public void addPage(Page page) {
        if (gestionnairePage.getNombrePagesContenu() == 0) {
            pageCourante = page;
        }
        gestionnairePage.ajouterPage(page);
    }

    public void modeVignette() throws PageOutOfRangeException {
       pageCourante = gestionnairePage.getPage(-1);
    }

    public void modeAjouterPage() throws PageOutOfRangeException {
        pageCourante = gestionnairePage.getPage(0);
    }

    public Page getPageCourante() {
        return pageCourante;
    }

    public int getNombrePagesContenu() {
        return gestionnairePage.getNombrePagesContenu();
    }

    public void pageSuivante() throws PageOutOfRangeException {
        int numPage = pageCourante.getNumero() + 1;
        pageCourante = gestionnairePage.getPage(numPage);
    }

    public void pagePrecedente() throws PageOutOfRangeException {
        int numPage = pageCourante.getNumero() - 1;
        pageCourante = gestionnairePage.getPage(numPage);
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
            pageCourante = gestionnairePage.getPage(numeroPage);
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean pageCouranteEstLaPremiere() {
        return pageCourante.getNumero() == 1;
    }

    public boolean pageCouranteEstLaDerniere() {
        return pageCourante.getNumero() == gestionnairePage.getNombrePagesContenu();
    }
}
