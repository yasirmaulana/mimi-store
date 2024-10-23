package io.github.yasirmaulana.mimi_store.service.impl;

import io.github.yasirmaulana.mimi_store.dto.TransactionSummaryDTO;
import io.github.yasirmaulana.mimi_store.repository.TransactionRepository;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testFindSummaryByMsisdn() {
        String msisdn = "62819123456";

        // Mock data for tuples
        Tuple tuple1 = mock(Tuple.class);
        when(tuple1.get("yearmonth", String.class)).thenReturn("2023-11");
        when(tuple1.get("totalAmount", Long.class)).thenReturn(30000L);
        when(tuple1.get("totalTransaction", Long.class)).thenReturn(2L);

        Tuple tuple2 = mock(Tuple.class);
        when(tuple2.get("yearmonth", String.class)).thenReturn("2023-12");
        when(tuple2.get("totalAmount", Long.class)).thenReturn(50000L);
        when(tuple2.get("totalTransaction", Long.class)).thenReturn(3L);

        List<Tuple> mockTuples = Arrays.asList(tuple1, tuple2);

        // Stub the repository
        when(transactionRepository.findTransactionSummary(msisdn)).thenReturn(mockTuples);

        // Call the method under test
        List<TransactionSummaryDTO> result = transactionService.findTransactionSummaryByMsisdn(msisdn);

        // Assertions
        assertEquals(2, result.size());

        // Verify first DTO
        assertEquals("2023-11", result.get(0).getYearmonth());
        assertEquals(30000L, result.get(0).getTotalAmount());
        assertEquals(2L, result.get(0).getTotalTransaction());

        // Verify second DTO
        assertEquals("2023-12", result.get(1).getYearmonth());
        assertEquals(50000L, result.get(1).getTotalAmount());
        assertEquals(3L, result.get(1).getTotalTransaction());

        // Verify interaction with the repository
        verify(transactionRepository, times(1)).findTransactionSummary(msisdn);
    }
}
