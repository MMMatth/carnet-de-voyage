package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public void saveJson(String path) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        for (Page page : this) {
            if (page.estUnePageContenu()) {
                json.append(page.toJson());
                json.append(",\n");
            }
        }
        json.deleteCharAt(json.length() - 2);
        json.append("]");
        System.out.println(json);

        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
