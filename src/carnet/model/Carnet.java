package carnet.model;

import carnet.exceptions.LoadNotWork;
import carnet.exceptions.PageOutOfRangeException;
import carnet.exceptions.SaveNotWork;
import carnet.outils.JsonManager;
import java.util.Iterator;

public class Carnet extends SujetObservateur implements Iterable<Page> {
    private Page pageCourante;
    private final GestionnairePage gestionnairePage;
    private final JsonManager jsonManager;
    private String path;

    public Carnet() {
        gestionnairePage = new GestionnairePage();
        this.addPage(new ModeVignette());
        this.addPage(new ModeAjouterPage());

        jsonManager = new JsonManager(gestionnairePage);
        if (getClass().getResource("/") == null) {
            path = "carnet.json";
        } else {
            path = getClass().getResource("/").getPath() + "carnet.json";
        }
    }

    public void save() throws SaveNotWork {
        jsonManager.save(path);
        this.notifierObservateurs();
    }

    public void load() throws LoadNotWork {
        jsonManager.load(path);
    }

    public void addPage(Page page) {
        if (gestionnairePage.getNombrePages() == 0) {
            pageCourante = page;
        }
        gestionnairePage.ajouterPage(page);
    }

    public void addPageContenu(PageContenu page) {
        this.addPage(page);
        page.setModeEdition(true);
    }

    public void modeVignette() throws PageOutOfRangeException {
        if (!pageCourante.estModeVignette()) {
            pageCourante = gestionnairePage.getPage(-1);
            this.notifierObservateurs();
        }
    }

    public void modeAjouterPage() throws PageOutOfRangeException {
        if (!pageCourante.estModeAjouterPage()) {
            pageCourante = gestionnairePage.getPage(0);
            this.notifierObservateurs();
        }
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
        this.notifierObservateurs();
    }

    public void pagePrecedente() throws PageOutOfRangeException {
        int numPage = pageCourante.getNumero() - 1;
        pageCourante = gestionnairePage.getPage(numPage);
        this.notifierObservateurs();
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
            this.notifierObservateurs();
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


    public int getNombrePages() {
        return gestionnairePage.getNombrePages();
    }

    public void deplacerAvant(int numero) {
        gestionnairePage.deplacerAvant(numero);
    }

    public void deplacerApres(int numero) {
        gestionnairePage.deplacerApres(numero);
    }
}
