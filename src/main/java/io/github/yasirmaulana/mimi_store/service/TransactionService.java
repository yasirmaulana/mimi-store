package io.github.yasirmaulana.mimi_store.service;

import io.github.yasirmaulana.mimi_store.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.mimi_store.dto.TransactionSummaryDTO;
import io.github.yasirmaulana.mimi_store.dto.TransaksiListResponseDTO;

import java.util.List;

public interface TransactionService {

    ResultPageResponseDTO<TransaksiListResponseDTO> findTransaksiList(Integer page, Integer limit, String sortBy, String direction, Long pelangganId);

    List<TransaksiListResponseDTO> findTransactionByMsisdn(String msisdn);
    List<TransactionSummaryDTO> findTransactionSummaryByMsisdn(String msisdn);
}
