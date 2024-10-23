package io.github.yasirmaulana.mimi_store.web;

import io.github.yasirmaulana.mimi_store.dto.MockApiResponse;
import io.github.yasirmaulana.mimi_store.dto.TransactionSummaryDTO;
import io.github.yasirmaulana.mimi_store.dto.WebResponse;
import io.github.yasirmaulana.mimi_store.service.SubscriberService;
import io.github.yasirmaulana.mimi_store.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransaksiResourceTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private SubscriberService subscriberService;

    @InjectMocks
    private TransaksiResource transaksiResource;

    @Test
    void testFindTransactionSummaryByMsisdn_Success() {
        String msisdn = "62819123456";

        // Mock the response from subscriberService
        MockApiResponse mockApiResponse = new MockApiResponse();
        mockApiResponse.setStatus("ok");
        mockApiResponse.setCode("00");
        mockApiResponse.setMessage("success");
        mockApiResponse.setData("1234");

        // Mock the transaction summary list
        List<TransactionSummaryDTO> mockSummaryList = Arrays.asList(
                new TransactionSummaryDTO("2023-11", 30000L, 2L),
                new TransactionSummaryDTO("2023-12", 50000L, 3L)
        );

        // Stub the service methods
        when(subscriberService.getPinByMsisdn(msisdn)).thenReturn(mockApiResponse);
        when(transactionService.findTransactionSummaryByMsisdn(msisdn)).thenReturn(mockSummaryList);

        // Call the method under test
        WebResponse<List<TransactionSummaryDTO>> response = transaksiResource.findTransactionSummaryByMsisdn(msisdn);

        // Verify the response
        assertEquals("ok", response.getStatus());
        assertEquals("00", response.getCode());
        assertEquals("success", response.getMessage());
        assertEquals(2, response.getData().size());
    }

    @Test
    void testFindTransactionSummaryByMsisdn_NotFound() {
        String msisdn = "62819123456";

        // Mock the response from subscriberService
        MockApiResponse mockApiResponse = new MockApiResponse();
        mockApiResponse.setStatus("failed");
        mockApiResponse.setCode("01");
        mockApiResponse.setMessage("Subscriber not found");
        mockApiResponse.setData(null);

        // Mock an empty result from transactionService
        List<TransactionSummaryDTO> emptySummaryList = Collections.emptyList();

        // Stub the service methods
        when(subscriberService.getPinByMsisdn(msisdn)).thenReturn(mockApiResponse);
        when(transactionService.findTransactionSummaryByMsisdn(msisdn)).thenReturn(emptySummaryList);

        // Call the method under test
        WebResponse<List<TransactionSummaryDTO>> response = transaksiResource.findTransactionSummaryByMsisdn(msisdn);

        // Verify the response
        assertEquals("failed", response.getStatus());
        assertEquals("01", response.getCode());
        assertEquals("subscriber not found", response.getMessage());
        assertNull(response.getData()); // Data should not be returned if subscriber not found
    }
}
