package io.github.yasirmaulana.mimi_store.web;

import io.github.yasirmaulana.mimi_store.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.mimi_store.dto.TransaksiListResponseDTO;
import io.github.yasirmaulana.mimi_store.dto.WebResponse;
import io.github.yasirmaulana.mimi_store.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TransaksiResource {

    private final TransactionService transactionService;

    @GetMapping("/transaksi-list")
    public ResponseEntity<ResultPageResponseDTO<TransaksiListResponseDTO>> findTransaksiList(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "pelangganId") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "pelangganId", required = false) Long pelangganId
    ) {
        return ResponseEntity.ok().body(transactionService.findTransaksiList(pages, limit, sortBy, direction, pelangganId));
    }

    @GetMapping(path = "/api/transaction")
    public WebResponse<ResultPageResponseDTO<TransaksiListResponseDTO>> findTransaction(
            @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "pelangganId") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "pelangganId", required = false) Long pelangganId
    ) {
        ResultPageResponseDTO<TransaksiListResponseDTO> result = transactionService.findTransaksiList(pages, limit, sortBy, direction, pelangganId);
        return WebResponse.<ResultPageResponseDTO<TransaksiListResponseDTO>>builder().status("ok").code("00").message("success").data(result).build();
    }
}
