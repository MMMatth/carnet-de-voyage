package carnet.model;

import carnet.exceptions.PageOutOfRangeException;
import carnet.outils.FabriqueNumero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarnetTest {
    Carnet carnet;

    @BeforeEach
    void setUp() {
        carnet = new Carnet();
    }

    @AfterEach
    void tearDown(){
        carnet = null;
        FabriqueNumero.getInstance().reset();
    }

    @Test
    void testConstructor(){
        assertEquals(0, carnet.getNombrePagesContenu());
        assertEquals(2, carnet.getNombrePages());
    }

    @Test
    void testAddPage(){
        carnet.addPage(new PageAccueil());
        assertEquals(1, carnet.getNombrePagesContenu());
        carnet.addPage(new PageTextPhoto());
        assertEquals(2, carnet.getNombrePagesContenu());
        carnet.addPage(new PageTextPhotoMap());
        assertEquals(3, carnet.getNombrePagesContenu());
    }

    @Test
    void testModeVignette() {
        try {
            carnet.modeVignette();
            assertTrue(carnet.getPageCourante().estModeVignette());
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testModeAjouterPage() {
        try {
            carnet.modeAjouterPage();
            assertTrue(carnet.getPageCourante().estModeAjouterPage());
        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPageSuivante(){
        carnet.addPage(new PageAccueil());
        carnet.addPage(new PageTextPhoto());

        try {
            carnet.pageSuivante();
            assertEquals(0, carnet.getPageCourante().getNumero());
            carnet.pageSuivante();
            assertEquals(1, carnet.getPageCourante().getNumero());
            carnet.pageSuivante();
            assertEquals(2, carnet.getPageCourante().getNumero());

            assertThrows(PageOutOfRangeException.class, () -> carnet.pageSuivante());

        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testPagePrecedente(){
        carnet.addPage(new PageAccueil());
        carnet.addPage(new PageTextPhoto());

        try {
            carnet.pageSuivante(); // 0
            carnet.pageSuivante(); // 1
            carnet.pageSuivante(); // 2

            carnet.pagePrecedente(); // 1
            assertEquals(1, carnet.getPageCourante().getNumero());
            carnet.pagePrecedente(); // 0
            assertEquals(0, carnet.getPageCourante().getNumero());
            carnet.pagePrecedente(); // -1
            assertEquals(-1, carnet.getPageCourante().getNumero()   );

            assertThrows(PageOutOfRangeException.class, () -> carnet.pagePrecedente());

        } catch (PageOutOfRangeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSupprimerPage(){
        carnet.addPage(new PageAccueil());
        carnet.addPage(new PageTextPhoto());
        carnet.addPage(new PageTextPhotoMap());


        carnet.supprimerPage(1);
        assertEquals(2, carnet.getNombrePagesContenu());
        int i = -1;
        // on vérifie que les pages ont bien été décalées et que la page 1 a bien été supprimée
        for (Page page : carnet) {
            assertEquals(i, page.getNumero());
            assertFalse(page.estAccueil());
            i++;
        }
    }

}