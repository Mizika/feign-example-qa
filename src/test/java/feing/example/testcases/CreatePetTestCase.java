package feing.example.testcases;

import com.github.javafaker.Faker;
import feign.example.qa.clients.pet.PetClient;
import feign.example.qa.model.Category;
import feign.example.qa.model.PetModel;
import feign.example.qa.model.Tags;
import feing.example.utils.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CreatePetTestCase extends TestBase {
    @Autowired
    PetClient petClient;
    @Autowired
    Faker faker;


    @Test
    void createPet() {
        step("Добавление питомца");
        var pet = PetModel.builder()
                .id(new Random().ints(10, 1000).findFirst().getAsInt())
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
                .status("available")
                .build();
        var addNewPet = petClient.addNewPet(pet);
        assertAll(
                () -> assertThat("Проверка id у добавленного питомца",
                        addNewPet.getId(), notNullValue()),
                () -> assertThat("Проверка status у добавленного питомца",
                        addNewPet.getStatus(), equalTo("available"))
        );

        step("Поиск добавленного питомца по id");
        var findAddPet = petClient.findById(addNewPet.getId());
        assertAll(
                () -> assertThat("Проверка id у найденного питомца",
                        findAddPet.getId(), equalTo(addNewPet.getId())),
                () -> assertThat("Проверка category у найденного питомца",
                        findAddPet.getCategory(), notNullValue()),
                () -> assertThat("Проверка category id у найденного питомца",
                        findAddPet.getCategory().getId(), equalTo(addNewPet.getCategory().getId())),
                () -> assertThat("Проверка category name у найденного питомца",
                        findAddPet.getCategory().getName(), equalTo(addNewPet.getCategory().getName())),
                () -> assertThat("Проверка name у найденного питомца",
                        findAddPet.getName(), equalTo(addNewPet.getName())),
                () -> assertThat("Проверка photoUrls у найденного питомца",
                        findAddPet.getPhotoUrls(), hasSize(1)),
                () -> assertThat("Проверка tags у найденного питомца",
                        findAddPet.getTags(), notNullValue()),
                () -> assertThat("Проверка tags id у найденного питомца",
                        findAddPet.getTags().get(0).getId(), equalTo(addNewPet.getTags().get(0).getId())),
                () -> assertThat("Проверка tags name у найденного питомца",
                        findAddPet.getTags().get(0).getName(), equalTo(addNewPet.getTags().get(0).getName())),
                () -> assertThat("Проверка status у найденного питомца",
                        findAddPet.getTags().get(0).getName(), equalTo(addNewPet.getTags().get(0).getName()))
        );
    }
}
