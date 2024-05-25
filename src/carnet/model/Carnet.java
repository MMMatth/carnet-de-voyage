package carnet.model;

import carnet.exceptions.LoadNotWork;
import carnet.exceptions.PageOutOfRangeException;
import carnet.exceptions.SaveNotWork;
import carnet.outils.JsonManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        path = getClass().getResource("/").getPath() + "carnet.json";
    }

    public void save() throws SaveNotWork {
        jsonManager.save(path);
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
