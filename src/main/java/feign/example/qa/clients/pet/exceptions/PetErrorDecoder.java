package feign.example.qa.clients.pet.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.example.qa.clients.pet.exceptions.models.ExceptionMessagePet;

import java.io.IOException;
import java.io.InputStream;

public class PetErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessagePet message;
        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper
//                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(bodyIs, ExceptionMessagePet.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 400:
                return new BadRequestExceptionPet(
                        message.getMessage() != null ? message.getMessage() : "Bad request",
                        message.getCode() != null ? message.getCode() : "1");
            case 404:
                return new NotFoundExceptionPet(
                        message.getMessage() != null ? message.getMessage() : "Not found",
                        message.getCode() != null ? message.getCode() : "1");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
