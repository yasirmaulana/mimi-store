package io.github.yasirmaulana.mimi_store.service;

import io.github.yasirmaulana.mimi_store.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.mimi_store.dto.TransaksiListResponseDTO;

public interface TransactionService {

    ResultPageResponseDTO<TransaksiListResponseDTO> findTransaksiList(Integer page, Integer limit, String sortBy, String direction, Long pelangganId);

}
