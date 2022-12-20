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

    /**
     * Find pets by status
     * @param status status pets
     * @return list of pets
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/pet/findByStatus?status={status}",
            headers = "Accept=application/json")
    List<PetModel> findByStatus(@PathVariable("status") String status);

    /**
     * Add new pet
     * @param petModel filled pet model
     * @return pet model
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/pet",
            headers = "Accept=application/json")
    PetModel addNewPet(PetModel petModel);

    /**
     * Find pets by id
     * @param id status pets
     * @return pet model
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/pet/{id}",
            headers = "Accept=application/json")
    PetModel findById(@PathVariable("id") int id);
}