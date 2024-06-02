package carnet.model;

import carnet.exceptions.PageOutOfRangeException;

import java.util.Iterator;

/**
 * modèle du carnet
 */
public class Carnet extends SujetObservateur implements Iterable<Page> {
    private Page pageCourante; // page courante
    private final GestionnairePage gestionnairePage; // gestionnaire de page

    public Carnet() {
        // on initialise le carnet avec deux pages , le mode vignette et le mode ajouter page
        gestionnairePage = new GestionnairePage();
        this.addPage(new ModeVignette());
        this.addPage(new ModeAjouterPage());

    }


    public void addPage(Page page) {
        // si le carnet est vide on definit la page courante comme la page
        if (gestionnairePage.getNombrePages() == 0) {
            pageCourante = page;
        }
        gestionnairePage.ajouterPage(page);
    }

    /**
     * fonction pour ajouter une page de contenu
     * @param page page de contenu
     */
    public void addPageContenu(PageContenu page) {
        this.addPage(page);
        // quand on ajoute une page de contenu on passe en mode edition
        page.setModeEdition(true);
    }

    /**
     * fonction passer au mode vignette
     * @throws PageOutOfRangeException dans le cas ou la page n'existe pas
     */
    public void modeVignette() throws PageOutOfRangeException {
        if (!pageCourante.estModeVignette()) {
            pageCourante = gestionnairePage.getPage(-1);
            this.notifierObservateurs();
        }
    }

    /**
     * fonction passer au mode ajouter page
     * @throws PageOutOfRangeException dans le cas ou la page n'existe pas
     */
    public void modeAjouterPage() throws PageOutOfRangeException {
        if (!pageCourante.estModeAjouterPage()) {
            pageCourante = gestionnairePage.getPage(0);
            this.notifierObservateurs();
        }
    }

    /**
     * fonction pour passer à la page suivante
     * @throws PageOutOfRangeException dans le cas ou la page suivante n'existe pas
     */
    public void pageSuivante() throws PageOutOfRangeException {
        int numPage = pageCourante.getNumero() + 1;
        pageCourante = gestionnairePage.getPage(numPage);
        this.notifierObservateurs();
    }

    /**
     * fonction pour passer à la page précédente
     * @throws PageOutOfRangeException dans le cas ou la page précédente n'existe pas
     */
    public void pagePrecedente() throws PageOutOfRangeException {
        int numPage = pageCourante.getNumero() - 1;
        pageCourante = gestionnairePage.getPage(numPage);
        this.notifierObservateurs();
    }

    /**
     * fonction pour supprimer une page
     * @param numeroPage
     */
    public void supprimerPage(int numeroPage) {
        gestionnairePage.supprimerPage(numeroPage);
        this.notifierObservateurs();
    }


    @Override
    public Iterator<Page> iterator() {
        return gestionnairePage.iterator();
    }

    /**
     * fonction pour sauvegarder le carnet
     * @param numeroPage le numero de la page pas l'indice de la liste
     */
    public void moveTo(int numeroPage) {
        try {
            pageCourante = gestionnairePage.getPage(numeroPage);
            this.notifierObservateurs();
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fonction pour sauvegarder le carnet
     * @param numero le numero de la page pas l'indice de la liste
     */
    public void deplacerAvant(int numero) {
        gestionnairePage.deplacerAvant(numero);
        this.notifierObservateurs();
    }

    /**
     * fonction pour sauvegarder le carnet
     * @param numero le numero de la page pas l'indice de la liste
     */
    public void deplacerApres(int numero) {
        gestionnairePage.deplacerApres(numero);
        this.notifierObservateurs();
    }

    /**
     * fonction pour voir si la page courante (contenue) est la première
     * @return vrai si la page courante est la première
     */
    public boolean pageCouranteEstLaPremiere() {
        return pageCourante.getNumero() == 1; // 1 car la page -1 et 0 sont mode vignette et ajouter page
    }

    /**
     * fonction pour voir si la page courante (contenue) est la dernière
     * @return vrai si la page courante est la dernière
     */
    public boolean pageCouranteEstLaDerniere() {
        return pageCourante.getNumero() == gestionnairePage.getNombrePagesContenu();
    }

    public Page getPageCourante() {
        return pageCourante;
    }

    public int getNombrePagesContenu() {
        return gestionnairePage.getNombrePagesContenu();
    }

    public int getNombrePages() {
        return gestionnairePage.getNombrePages();
    }

    public GestionnairePage getGestionnairePage() {
        return gestionnairePage;
    }
}
