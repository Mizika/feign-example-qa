package example.testcases;

import com.github.javafaker.Faker;
import example.utils.TestBase;
import feign.qa.pet.client.PetClient;
import feign.qa.pet.models.PetModel;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Test")
@Feature("Feign example")
@Severity(SeverityLevel.NORMAL)
@DisplayName("Example with check status code")
public class WorkWithResponseEntityTestCase extends TestBase {

    @Autowired
    PetClient petClient;
    @Autowired
    Faker faker;

    @Test
    @Story("Feign")
    @Description("Example with response entity")
    void workWithStatusCode() {
        step("Поиск питомцев по статусу");
        var response = petClient.findByStatus("available");
        assertThat("Проверка, status code", response.getStatusCode().is2xxSuccessful());

        var listPet = response.getBody();
        assertThat("Проверка массива, количество больше 0", listPet, hasSize(greaterThan(0)));
        assertThat("Проверка массива, каждая сущность имеет статус available",
                listPet.stream().map(PetModel::getStatus).collect(Collectors.toList()),
                everyItem(equalTo("available")));
    }
}

