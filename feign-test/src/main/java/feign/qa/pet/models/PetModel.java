package feign.qa.pet.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonDeserialize(builder = PetModel.PetModelBuilder.class)
public class PetModel {
    int id;
    Category category;
    String name;
    List<String> photoUrls;
    List<Tags> tags;
    String status;

    @JsonPOJOBuilder(withPrefix="")
    public static class PetModelBuilder {
    }
}
