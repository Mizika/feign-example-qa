package feign.qa.pet.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonDeserialize(builder = Tags.TagsBuilder.class)
public class Tags {
    int id;
    String name;

    @JsonPOJOBuilder(withPrefix="")
    public static class TagsBuilder {
    }
}
