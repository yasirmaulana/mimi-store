package io.github.yasirmaulana.mimi_store.repository;

import io.github.yasirmaulana.mimi_store.service.impl.TransactionServiceImpl;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        // Optional: Setup method if needed
    }
    @Test
    void testFindTransactionSummary() {
        // Arrange
        String msisdn = "62819123456";

        // Create a mock Tuple
        Tuple tuple1 = mock(Tuple.class);
        when(tuple1.get(0)).thenReturn("2023-11");
        when(tuple1.get(1)).thenReturn(30000L);
        when(tuple1.get(2)).thenReturn(2L);

        Tuple tuple2 = mock(Tuple.class);
        when(tuple2.get(0)).thenReturn("2023-12");
        when(tuple2.get(1)).thenReturn(50000L);
        when(tuple2.get(2)).thenReturn(3L);

        List<Tuple> mockResult = Arrays.asList(tuple1, tuple2);

        // Mock the repository behavior
        when(transactionRepository.findTransactionSummary(msisdn)).thenReturn(mockResult);

        // Act
        List<Tuple> result = transactionRepository.findTransactionSummary(msisdn);

        // Assert
        assertEquals(2, result.size());
        assertEquals("2023-11", result.get(0).get(0));
        assertEquals(30000L, result.get(0).get(1));
        assertEquals(2L, result.get(0).get(2));

        assertEquals("2023-12", result.get(1).get(0));
        assertEquals(50000L, result.get(1).get(1));
        assertEquals(3L, result.get(1).get(2));
    }
}