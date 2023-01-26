package feign.qa.pet.client.config;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static feign.Util.*;

@Slf4j
public class CustomFeignRequestResponseAllureLogging extends Logger {

    private final AllureLifecycle lifecycle = Allure.getLifecycle();

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        lifecycle.startStep(UUID.randomUUID().toString(),
                (new StepResult())
                        .setStatus(Status.PASSED)
                        .setName(String.format("%s: %s", request.httpMethod().name(), request.url())));

        lifecycle.addAttachment("Request", "", "",
                new ByteArrayInputStream(String.format("URL: %s,\nHeaders: %s,\nBody: %s",
                        request.httpMethod().name() + " " + request.url(),
                        request.headers(),
                        ((request.body() != null) ? new String(request.body(), StandardCharsets.UTF_8) : "")
                ).getBytes()));

        log(configKey, "---> %s %s ", request.httpMethod().name(), request.url());
        for (String field : request.headers().keySet()) {
            if (shouldLogRequestHeader(field)) {
                for (String value : valuesOrEmpty(request.headers(), field)) {
                    log(configKey, "%s: %s", field, value);
                }
            }
        }
        if (request.body() != null) {
            if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                String bodyText =
                        request.charset() != null
                                ? new String(request.body(), request.charset())
                                : null;
                log(configKey, "");
                log(configKey, "%s", bodyText != null ? bodyText : "Binary data");
            }
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        int status = response.status();
        byte[] bodyData = Util.toByteArray(response.body().asInputStream());
        int bodyLength = bodyData.length;
        lifecycle.addAttachment("HTTP/1.1 " + response.status() + " " + response.reason(), "", "",
                new ByteArrayInputStream(String.format("Status code: %s, \nBody: %s",
                        response.status(),
                        ((response.body() != null && bodyLength > 0) ? decodeOrDefault(bodyData, UTF_8, "Binary data") : "")
                ).getBytes()));
        lifecycle.stopStep();
        log(configKey, "<--- HTTP/1.1 Status code: %s ", status);
        for (String field : response.headers().keySet()) {
            if (shouldLogResponseHeader(field)) {
                for (String value : valuesOrEmpty(response.headers(), field)) {
                    log(configKey, "%s: %s", field, value);
                }
            }
        }
        if (response.body() != null) {
            if (bodyLength > 0) {
                log(configKey, "");
                log(configKey, "%s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
            }
            log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
            return response.toBuilder().body(bodyData).build();
        }
        return response;
    }


    @Override
    protected void log(String configKey, String format, Object... args) {
        log.debug(format(configKey, format, args));
    }

    protected String format(String configKey, String format, Object... args) {
        return String.format(methodTag(configKey) + format, args);
    }

}

