package io.github.yasirmaulana.mimi_store.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import io.github.yasirmaulana.mimi_store.dto.MockApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriberServiceImplTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    void testGetPinByMsisdn_Success() {
        // Arrange
        String msisdn = "62819123456";
        MockApiResponse expectedResponse = new MockApiResponse();
        expectedResponse.setStatus("ok");
        expectedResponse.setCode("00");
        expectedResponse.setMessage("success");
        expectedResponse.setData("1234");

        // Mocking the WebClient behavior
        when(webClientBuilder.baseUrl("https://4k38m.wiremockapi.cloud")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(webClient);
        when(webClient.uri("/subscriber/{msisdn}/pin", msisdn)).thenReturn(webClient);
        when(webClient.retrieve()).thenReturn(webClient);
        when(webClient.bodyToMono(MockApiResponse.class)).thenReturn(Mono.just(expectedResponse));

        // Act
        MockApiResponse actualResponse = subscriberService.getPinByMsisdn(msisdn);

        // Assert
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getCode(), actualResponse.getCode());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getData(), actualResponse.getData());
    }

    @Test
    void testGetPinByMsisdn_NotFound() {
        // Arrange
        String msisdn = "628191234566"; // Incorrect msisdn for testing 404
        WebClientResponseException exception =
                WebClientResponseException.create(404, "Not Found", null, null, null);

        // Mocking the WebClient behavior to throw an exception
        when(webClientBuilder.baseUrl("https://4k38m.wiremockapi.cloud")).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(webClient);
        when(webClient.uri("/subscriber/{msisdn}/pin", msisdn)).thenReturn(webClient);
        when(webClient.retrieve()).thenThrow(exception);

        // Act
        MockApiResponse actualResponse = subscriberService.getPinByMsisdn(msisdn);

        // Assert
        assertEquals("failed", actualResponse.getStatus());
        assertEquals("01", actualResponse.getCode());
        assertEquals("Subscriber not found", actualResponse.getMessage());
        assertEquals(null, actualResponse.getData()); // data should be null
    }
}
