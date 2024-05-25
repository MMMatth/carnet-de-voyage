package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;

import java.util.*;


public class GestionnairePage implements Iterable<Page>{
    private HashMap<Integer, Page> pages;

    public GestionnairePage() {
        pages = new HashMap<>();
    }
    public void ajouterPage(Page ... pages){
        for(Page p : pages){
            this.pages.put(p.getNumero(), p);
        }
    }


    public void supprimerPage(Page ... pages){
        for(Page p : pages){
            this.pages.remove(p.getNumero());
        }
    }

    public Page getPage(int numPage) throws PageOutOfRangeException {
        Page page = pages.get(numPage);
        if (page == null) {
            throw new PageOutOfRangeException("Page " + numPage + " non trouvée");
        }
        return page;
    }

    public boolean isEmpty(){
        return pages.isEmpty();
    }

    @Override
    public Iterator<Page> iterator() {
        return pages.values().iterator();
    }

    public int getNombrePages() {
        return pages.size() ;
    }

    public int getNombrePagesContenu() {
        return pages.size() - 2;
    }

    public void supprimerPage(int numeroPage) {
        FabriqueNumero.getInstance().libererPage();
        // on supprime la page
        pages.remove(numeroPage);

        List<Integer> keysToUpdate = new ArrayList<>();
        // on enregistre les clés à mettre à jour et on met à jour les numéros des pages
        for (Map.Entry<Integer, Page> entry : pages.entrySet()) {
            if (entry.getKey() > numeroPage) {
                keysToUpdate.add(entry.getKey());
                entry.getValue().setNumero(entry.getValue().getNumero() - 1);
            }
        }

        // on décale les pages d'un cran
        for (Integer key : keysToUpdate) {
            Page page = pages.remove(key);
            pages.put(key - 1, page);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GestionnairePage{\n");
        ArrayList<Integer> keys = new ArrayList<>(pages.keySet());
        ArrayList<Page> values = new ArrayList<>(pages.values());
        for (int i = 0; i < pages.size(); i++) {
            sb.append(keys.get(i)).append(" : ").append(values.get(i)).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
