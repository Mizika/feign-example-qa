package feign.example.qa.clients.pet.exceptions;

public class NotFoundExceptionPet extends RuntimeException {

    private final String code;

    public String getCode() {
        return code;
    }

    public NotFoundExceptionPet(String message, String cod) {
        super(message);
        code = cod;
    }

}
