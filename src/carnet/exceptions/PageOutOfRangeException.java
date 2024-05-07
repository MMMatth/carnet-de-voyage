package carnet.exceptions;

/**
 * Exception lancée lorsqu'une page est demandée et que celle-ci n'existe pas.
 */
public class PageOutOfRangeException extends CarnetExceptions{
    public PageOutOfRangeException(String message){
        super("Page hors de la plage de pages du carnet: " + message);
    }
}
