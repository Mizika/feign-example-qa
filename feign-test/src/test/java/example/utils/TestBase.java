package example.utils;

import feign.qa.AppConfigFeign;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = AppConfigFeign.class)
public class TestBase {

    @Step("{info}")
    public void step(String info) {
        log.info("======================");
        log.info(info);
        log.info("======================");
    }
}
