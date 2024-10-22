package io.github.yasirmaulana.mimi_store.service.impl;

import io.github.yasirmaulana.mimi_store.dto.MockApiResponse;
import io.github.yasirmaulana.mimi_store.service.SubscriberService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final WebClient webClient;

    public SubscriberServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://4k38m.wiremockapi.cloud").build();
    }

    @Override
    public MockApiResponse getPinByMsisdn(String msisdn) {
        try {
            return this.webClient.get()
                    .uri("/subscriber/{msisdn}/pin", msisdn)
                    .retrieve()
                    .bodyToMono(MockApiResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            MockApiResponse response = new MockApiResponse();
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                response.setStatus("failed");
                response.setCode("01");
                response.setMessage("Subscriber not found");
            }
            return response;
        }
    }
}
