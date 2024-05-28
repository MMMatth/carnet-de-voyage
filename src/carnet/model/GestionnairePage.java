package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;

import java.util.*;


public class GestionnairePage implements Iterable<Page>{
    private final ArrayList<Page> pages;

    public GestionnairePage() {
        pages = new ArrayList<>();
    }

    public void ajouterPage(Page ... pages){
        this.pages.addAll(Arrays.asList(pages));
    }


    public void supprimerPage(Page ... pages){
        for(Page p : pages){
            this.pages.remove(p.getNumero());
        }
    }

    /**
     * fonction qui permet de recuperer l'indice de la List par rapport au numero de la page
     * @param numeroPage le numero de la page
     * @return l'indice de la page dans la List
     */
    public int getIndicePage(int numeroPage) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getNumero() == numeroPage) {
                return i;
            }
        }
        return -1;
    }

    /**
     * fonction qui permet de recuperer une page par rapport a son numero
     * @param numPage le numero de la page
     * @return la page
     * @throws PageOutOfRangeException si la page n'existe pas
     */
    public Page getPage(int numPage) throws PageOutOfRangeException {
        int indice = getIndicePage(numPage);
        if (indice == -1) {
            throw new PageOutOfRangeException("Page " + numPage + " non trouvée");
        }
        Page page = pages.get(indice);
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
        return pages.iterator();
    }

    public int getNombrePages() {
        return pages.size() ;
    }

    public int getNombrePagesContenu() {
        return pages.size() - 2;
    }

    /**
     * fonction qui permet de supprimer une page
     * @param numeroPage le numero de la page a supprimer
     */
    public void supprimerPage(int numeroPage) {
        FabriqueNumero.getInstance().libererPage();
        // on supprime la page
        int indice = getIndicePage(numeroPage);
        pages.remove(indice);
        // on renumérote les pages
        for (int i = indice; i < pages.size(); i++) {
            pages.get(i).setNumero(pages.get(i).getNumero() - 1);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GestionnairePage{\n");
        for (Page page : pages) {
            sb.append(page.toString()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public void deplacerAvant(int numeroPage) {
        if (numeroPage == 1) {
            return;
        }

        int indicePageADeplacer = getIndicePage(numeroPage);
        int indicePagePrecedente = getIndicePage(numeroPage - 1);

        Page pageADeplacer = pages.get(indicePageADeplacer);
        Page pagePrecedente = pages.get(indicePagePrecedente);

        pageADeplacer.setNumero(numeroPage - 1);
        pagePrecedente.setNumero(numeroPage);

        pages.set(indicePagePrecedente, pageADeplacer);
        pages.set(indicePageADeplacer, pagePrecedente);

    }

    public void deplacerApres(int numeroPage){
        if (numeroPage == this.getNombrePagesContenu()) {
            return;
        }

        int indicePageADeplacer = getIndicePage(numeroPage);
        int indicePageSuivante = getIndicePage(numeroPage + 1);

        Page pageADeplacer = pages.get(indicePageADeplacer);
        Page pageSuivante = pages.get(indicePageSuivante);

        pageADeplacer.setNumero(numeroPage + 1);
        pageSuivante.setNumero(numeroPage);

        pages.set(indicePageSuivante, pageADeplacer);
        pages.set(indicePageADeplacer, pageSuivante);
    }
}
