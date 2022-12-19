package feing.example.utils;

import feign.example.qa.app.AppConfig;
import io.qameta.allure.Step;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = AppConfig.class)
public class TestBase {

    @Step("{info}")
    public void step(String info) {
        System.out.println("======================");
        System.out.println(info);
        System.out.println("======================");
    }
}
