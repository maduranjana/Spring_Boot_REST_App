package com.encora.test.service;

import com.encora.test.Config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
public class CurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Double> getCurrencyRates(String base) {

        try {
            // Build the API URL using the base
            String url = Constants.THIRD_PARTY_API +"latest?from="+ base;

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

                Map<String, Object> body = response.getBody();
                return (Map<String, Double>) body.get("rates"); //"rates" part from the body

            } else {
                throw new RuntimeException("API response error: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException | HttpServerErrorException ex) {  // Handle 400 - 500

            System.err.println("HTTP error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
        } catch (ResourceAccessException ex) { // Handle server timeouts

            System.err.println("Resource access error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error: " + ex.getMessage());
        }

        return Collections.emptyMap(); // empty
    }
}
