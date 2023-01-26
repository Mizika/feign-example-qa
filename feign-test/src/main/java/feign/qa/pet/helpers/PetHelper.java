package feign.qa.pet.helpers;

import com.github.javafaker.Faker;
import feign.qa.pet.models.Category;
import feign.qa.pet.models.PetModel;
import feign.qa.pet.models.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@Component
public class PetHelper {
    @Autowired
    Faker faker;

    /**
     * Create pet model
     *
     * @return PetModel
     */
    public PetModel createPet() {
        return PetModel.builder()
                .id(new Random().longs(10, 1000).findFirst().getAsLong())
                .name(faker.name().firstName())
                .category(Category.builder()
                        .id(new Random().ints(10, 1000).findFirst().getAsInt())
                        .name(faker.animal().name())
                        .build())
                .photoUrls(List.of(faker.internet().url()))
                .tags(List.of(Tags.builder()
                        .id(new Random().ints(10, 1000).findFirst().getAsInt())
                        .name(faker.name().firstName())
                        .build()))
                .status("available").build();
    }

    public void asserAddPet(PetModel expected, PetModel actual) {
        assertAll(
                () -> assertThat("Проверка id у найденного питомца",
                        actual.getId(), equalTo(expected.getId())),
                () -> assertThat("Проверка category у найденного питомца",
                        actual.getCategory(), notNullValue()),
                () -> assertThat("Проверка category id у найденного питомца",
                        actual.getCategory().getId(), equalTo(expected.getCategory().getId())),
                () -> assertThat("Проверка category name у найденного питомца",
                        actual.getCategory().getName(), equalTo(expected.getCategory().getName())),
                () -> assertThat("Проверка name у найденного питомца",
                        actual.getName(), equalTo(expected.getName())),
                () -> assertThat("Проверка photoUrls у найденного питомца",
                        actual.getPhotoUrls(), hasSize(1)),
                () -> assertThat("Проверка tags у найденного питомца",
                        actual.getTags(), notNullValue()),
                () -> assertThat("Проверка tags id у найденного питомца",
                        actual.getTags().get(0).getId(), equalTo(expected.getTags().get(0).getId())),
                () -> assertThat("Проверка tags name у найденного питомца",
                        actual.getTags().get(0).getName(), equalTo(expected.getTags().get(0).getName())),
                () -> assertThat("Проверка status у найденного питомца",
                        actual.getTags().get(0).getName(), equalTo(expected.getTags().get(0).getName()))
        );
    }
}
