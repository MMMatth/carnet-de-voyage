package carnet.outils;

public class FabriqueNumero {

    private static FabriqueNumero instance;

    private int numeroPage;

    private FabriqueNumero(){
        numeroPage = -1;
    } // -1 car les deux premieres pages sont des pages speciales

    public int getNumeroPage(){
        return numeroPage++;
    }

    public void reset(){
        numeroPage = 1;
    }

    public void supprimerPage(){
        numeroPage--;
    }

    public static FabriqueNumero getInstance(){
        if(instance == null){
            instance = new FabriqueNumero();
        }
        return instance;
    }

    public void libererPage() {
        numeroPage--;
    }
}
