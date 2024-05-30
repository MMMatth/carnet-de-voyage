package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestionnairePageTest {
    private GestionnairePage gestionnairePage;

    @BeforeEach
    void setUp() {
        gestionnairePage = new GestionnairePage();
    }

    @AfterEach
    void tearDown() {
        FabriqueNumero.getInstance().reset();
    }

    @Test
    void testAjouterPage() {
        Page page = new PageAccueil();
        gestionnairePage.ajouterPage(page);
        assertEquals(1, gestionnairePage.getNombrePages());
    }

    @Test
    void testSupprimerPage() {
        Page page = new PageAccueil();;
        gestionnairePage.ajouterPage(page);
        gestionnairePage.supprimerPage(page);
        assertEquals(0, gestionnairePage.getNombrePages());
    }

    @Test
    void testGetPage() {
        Page page = new PageAccueil();
        gestionnairePage.ajouterPage(page);
        try {
            Page retrievedPage = gestionnairePage.getPage(page.getNumero());
            assertEquals(page, retrievedPage);
        } catch (PageOutOfRangeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(gestionnairePage.estVide());
        Page page = new PageAccueil();
        gestionnairePage.ajouterPage(page);
        assertFalse(gestionnairePage.estVide());
    }

    @Test
    void testGetNombrePages() {
        assertEquals(0, gestionnairePage.getNombrePages());
        Page page = new PageAccueil();
        gestionnairePage.ajouterPage(page);
        assertEquals(1, gestionnairePage.getNombrePages());
    }


    @Test
    void testSupprimerPageByNumero() {
        Page page = new PageAccueil();
        gestionnairePage.ajouterPage(page);
        gestionnairePage.supprimerPage(page.getNumero());
        assertEquals(0, gestionnairePage.getNombrePages());
    }
}