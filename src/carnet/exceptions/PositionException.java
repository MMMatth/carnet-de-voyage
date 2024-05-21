package carnet.exceptions;

public class PositionException extends NumberFormatException{
    public PositionException(String message) {
        super("Problème de position : la longitude et la latitude doivent être des nombres décimaux");
    }
}
