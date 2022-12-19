package feign.example.qa.clients.pet;

import feign.example.qa.clients.pet.config.PetFeignConfiguration;
import feign.example.qa.model.PetModel;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(
        value="petClient",
        url="${test.url}",
        configuration = {PetFeignConfiguration.class}
)
public interface PetClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "/pet/findByStatus?status={status}",
            headers = "Accept=application/json")
    @ExceptionHandler(FeignException.class)
    List<PetModel> findByStatus(@PathVariable("status") String status);
}