package feign.example.qa.clients.pet.exceptions.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExceptionMessagePet {
    private String message;
    private String code;
}
