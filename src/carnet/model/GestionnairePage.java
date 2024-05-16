package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class GestionnairePage implements Iterable<Page>{
    private ArrayList<Page> pages;

    public GestionnairePage() {
        pages = new ArrayList<>();
    }

    public void ajouterPage(Page ... pages){
        Collections.addAll(this.pages, pages);
    }

    public void supprimerPage(Page ... pages){
        for(Page p : pages){
            this.pages.remove(p);
        }
    }

    public Page getPage(int index) throws PageOutOfRangeException {
        try {
            return pages.get(index);
        } catch (IndexOutOfBoundsException e){
            throw new PageOutOfRangeException("La page demandée n'existe pas");
        }
    }

    public boolean isEmpty(){
        return pages.isEmpty();
    }

    @Override
    public Iterator<Page> iterator() {
        return pages.iterator();
    }

    public int getNombrePages() {
        return pages.size() - 1;
    }

    public void supprimerPage(int numeroPage) {
        FabriqueNumero.getInstance().libererPage();
        pages.remove(numeroPage);
        for (int i = numeroPage; i < pages.size(); i++){
            pages.get(i).setNumero(i);
        }
    }
}
