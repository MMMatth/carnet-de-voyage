package carnet.exceptions;

public class JsonLoadExeception extends CarnetExceptions{
    public JsonLoadExeception(String message) {
        super("Probleme sur le chargement du carnet : " + message);
    }
}
