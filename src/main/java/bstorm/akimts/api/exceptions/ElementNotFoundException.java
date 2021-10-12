package bstorm.akimts.api.exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
        super("L'élément recherché n'a pas été trouvé");
    }
}
