package carnet.exceptions;

public class LoadNotWork extends CarnetExceptions{
    public LoadNotWork(String message) {
        super("Le chargement du carnet n'a pas fonctionné : " + message);
    }
}
