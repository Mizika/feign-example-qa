package feign.example.qa.clients.pet.exceptions;

public class BadRequestExceptionPet extends RuntimeException {
    private final String code;
    public String getCode(){return code;}

    public BadRequestExceptionPet(String message, String cod) {
        super(message);
        code = cod;
    }

}
