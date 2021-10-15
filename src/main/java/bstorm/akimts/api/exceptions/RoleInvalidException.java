package bstorm.akimts.api.exceptions;

public class RoleInvalidException extends RuntimeException {

    public RoleInvalidException() {
        super("Les roles d'un utilisateurs devrait être soit USER ou ADMIN");
    }
}
