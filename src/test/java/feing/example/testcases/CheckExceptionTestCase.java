package feing.example.testcases;

import com.github.javafaker.Faker;
import feign.example.qa.clients.pet.PetClient;
import feign.example.qa.clients.pet.exceptions.BadRequestExceptionPet;
import feing.example.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Test")
@Feature("Feign example")
@Severity(SeverityLevel.NORMAL)
@DisplayName("Example with check exception")
public class CheckExceptionTestCase extends TestBase {

    @Autowired
    PetClient petClient;
    @Autowired
    Faker faker;

    @Test
    @Story("Feign")
    @Description("Example with error decoder")
    void checkException() {
        var status = faker.color().name();

        step("Проверка ошибки при некорректном статусе");
        var checkExceptionWithInvalidStatus =
                Assertions.assertThrows(BadRequestExceptionPet.class, () -> {
            petClient.findByStatus(status);
        }, "Check incorrect status");

        assertThat("Проверка, кода ошибки","400",
                equalTo(checkExceptionWithInvalidStatus.getCode()));
        assertThat("Проверка, текста ошибки",
                "Input error: query parameter `status value `"+status+"` is not in the allowable values `[available, pending, sold]`",
                equalTo(checkExceptionWithInvalidStatus.getMessage()));
    }
}

