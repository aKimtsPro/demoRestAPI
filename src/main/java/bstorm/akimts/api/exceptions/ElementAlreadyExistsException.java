package bstorm.akimts.api.exceptions;

public class ElementAlreadyExistsException extends RuntimeException{

    public ElementAlreadyExistsException() {
        super("L'element existe deja");
    }
}
