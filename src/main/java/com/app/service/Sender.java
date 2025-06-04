package com.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
@RequiredArgsConstructor
public class Sender {

    private static final String SMS_API_BASE_URL = "http://10.81.1.175:1401/send";
    private static final String SMS_API_USERNAME = "sdapp";
    private static final String SMS_API_PASSWORD = "SDapp123";

    private final RestClient restClient = RestClient.builder().build();

    public static String formatMsisdn(String msisdn) {
        if (msisdn == null || msisdn.trim().length() < 9) {
            log.info("Invalid MSISDN format: {}", msisdn);
            return null;
        }
        String cleaned = msisdn.replaceAll("\\s+", "").replaceFirst("^\\+", "");
        String lastNine = cleaned.substring(cleaned.length() - 9);
        return "233" + lastNine;
    }

    public boolean sendSms(String msisdn, String message) {
        String formattedMsisdn = formatMsisdn(msisdn);
        if (formattedMsisdn == null) {
            log.warn("Invalid MSISDN input for sending SMS: {}", msisdn);
            return false;
        }

        try {
            String encodedUser = URLEncoder.encode(SMS_API_USERNAME, StandardCharsets.UTF_8);
            String encodedPass = URLEncoder.encode(SMS_API_PASSWORD, StandardCharsets.UTF_8);

            String uri = String.format("%s?username=%s&password=%s&to=%s&content=%s",
                    SMS_API_BASE_URL, encodedUser, encodedPass, formattedMsisdn, message);

            String responseBody = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(String.class);

            log.info("Message sent: {}\nSMS sent to: {}\nResponse: {}\n", message, formattedMsisdn, responseBody);

            return true;

        } catch (Exception e) {
            log.error("Failed to send SMS to {} with message '{}': {}", msisdn, message, e.getMessage());
            return false;
        }

    }



}
