package feign.qa.pet.client;

import feign.qa.pet.client.config.PetFeignConfiguration;
import feign.qa.pet.models.PetModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@FeignClient(
        value="petClient",
        url="${test.url}",
        configuration = {PetFeignConfiguration.class}
)
public interface PetClient {

    /**
     * Find pets by status
     *
     * @param status status pets
     * @return list of pets
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/pet/findByStatus?status={status}",
            headers = "Accept=application/json")
    ResponseEntity<List<PetModel>> findByStatus(@PathVariable("status") String status);

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
     *
     * @param id id pets
     * @return pet model
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "/pet/{id}",
            headers = "Accept=application/json")
    PetModel findById(@PathVariable("id") Long id);

    /**
     * Find pets by id
     *
     * @param id id pets
     */
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/pet/{id}",
            headers = "Accept=application/json")
    ResponseEntity<String> deleteById(@PathVariable("id") Long id);
}