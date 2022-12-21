package feign.qa.pet.client.exceptions.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExceptionMessagePet {
    private String message;
    private String code;
}
