package example.testcases;

import com.github.javafaker.Faker;
import example.utils.TestBase;
import feign.qa.pet.client.PetClient;
import feign.qa.pet.helpers.PetHelper;
import feign.qa.pet.models.PetModel;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("Test")
@Feature("Feign example")
@Severity(SeverityLevel.NORMAL)
@DisplayName("Example with passed test")
public class CreateAndDeletePetTestCase extends TestBase {
    @Autowired
    PetClient petClient;
    @Autowired
    PetHelper petHelper;
    @Autowired
    Faker faker;

    PetModel addNewPet;

    @BeforeEach
    void setup() {
        step("Добавление питомца");
        addNewPet = petClient.addNewPet(petHelper.createPet());
        assertAll(
                () -> assertThat("Проверка id у добавленного питомца",
                        addNewPet.getId(), notNullValue()),
                () -> assertThat("Проверка status у добавленного питомца",
                        addNewPet.getStatus(), equalTo("available"))
        );
    }

    @AfterEach
    void tearDown() {
        step("Удаление добавленного питомца");
        petClient.deleteById(addNewPet.getId());
    }

    @Test
    @Story("Feign")
    @Description("Example with passed test")
    void createAndDeletePet() {
        step("Поиск добавленного питомца по id");
        var findAddPet = petClient.findById(addNewPet.getId());
        petHelper.asserAddPet(addNewPet, findAddPet);
    }
}
