package carnet.outils;

public class FabriqueNumero {

    private static FabriqueNumero instance;

    private int numeroPage;

    private FabriqueNumero(){
        numeroPage = 0;
    }

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
