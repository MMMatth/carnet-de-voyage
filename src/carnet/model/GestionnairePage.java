package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;

import java.util.*;

/**
 * gestionnaire de page
 */
public class GestionnairePage implements Iterable<Page>{
    private final ArrayList<Page> pages;

    public GestionnairePage() {
        pages = new ArrayList<>();
    }

    /**
     * fonction qui permet d'ajouter une ou plusieurs pages
     * @param pages les pages a ajouter
     */
    public void ajouterPage(Page ... pages){
        this.pages.addAll(Arrays.asList(pages));
    }


    /**
     * fonction qui permet de supprimer une ou plusieurs pages
     * @param pages les pages a supprimer
     */
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

    public boolean estVide(){
        return pages.isEmpty();
    }

    @Override
    public Iterator<Page> iterator() {
        return pages.iterator();
    }

    /**
     * fonction qui permet de recuperer le nombre de pages
     * @return le nombre de pages
     */
    public int getNombrePages() {
        return pages.size() ;
    }

    /**
     *  fonction qui permet de recuperer le nombre de pages de contenu
     * @return le nombre de pages de contenu
     */
    public int getNombrePagesContenu() {
        return pages.size() - 2; // - 2 car on a une page mode vignette et une page mode ajouter page
    }

    /**
     * fonction qui permet de supprimer une page et de renuméroter les pages
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

    /**
     * fonction qui permet de deplacer une page avant une autre
     * @param numeroPage le numero de la page a deplacer
     */
    public void deplacerAvant(int numeroPage) {
        if (numeroPage == 1) { // on ne peut pas deplacer la page 1
            return;
        }

        // on recupere l'indice de la page a deplacer et de la page precedente
        int indicePageADeplacer = getIndicePage(numeroPage);
        int indicePagePrecedente = getIndicePage(numeroPage - 1);

        // on recupere les pages
        Page pageADeplacer = pages.get(indicePageADeplacer);
        Page pagePrecedente = pages.get(indicePagePrecedente);

        // on echange les numeros
        pageADeplacer.setNumero(numeroPage - 1);
        pagePrecedente.setNumero(numeroPage);

        // on echange les pages
        pages.set(indicePagePrecedente, pageADeplacer);
        pages.set(indicePageADeplacer, pagePrecedente);

    }

    public void deplacerApres(int numeroPage){
        if (numeroPage == this.getNombrePagesContenu()) { // on ne peut pas deplacer la page 1
            return;
        }

        // on recupere l'indice de la page a deplacer et de la page suivante
        int indicePageADeplacer = getIndicePage(numeroPage);
        int indicePageSuivante = getIndicePage(numeroPage + 1);

        // on recupere les pages
        Page pageADeplacer = pages.get(indicePageADeplacer);
        Page pageSuivante = pages.get(indicePageSuivante);

        // on echange les numeros
        pageADeplacer.setNumero(numeroPage + 1);
        pageSuivante.setNumero(numeroPage);

        // on echange les pages
        pages.set(indicePageSuivante, pageADeplacer);
        pages.set(indicePageADeplacer, pageSuivante);
    }
}
