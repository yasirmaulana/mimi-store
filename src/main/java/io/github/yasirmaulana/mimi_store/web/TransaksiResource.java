package io.github.yasirmaulana.mimi_store.web;

import io.github.yasirmaulana.mimi_store.dto.*;
import io.github.yasirmaulana.mimi_store.service.SubscriberService;
import io.github.yasirmaulana.mimi_store.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("xl")
public class TransaksiResource {

    private final TransactionService transactionService;
    private SubscriberService subscriberService;

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

    @GetMapping("/api/transactionbymsisdn/{msisdn}")
    public WebResponse<List<TransaksiListResponseDTO>> findTransactionByMsisdn(@PathVariable String msisdn) {
        List<TransaksiListResponseDTO> hasil = transactionService.findTransactionByMsisdn(msisdn);
        if (hasil.isEmpty()) {
            return WebResponse.<List<TransaksiListResponseDTO>>builder().status("failed").code("01").message("subscriber not found").build();
        }
        return WebResponse.<List<TransaksiListResponseDTO>>builder().status("ok").code("00").message("success").data(hasil).build();
    }


    @GetMapping("/api/transactionsummarybymsisdn/{msisdn}")
    public WebResponse<List<TransactionSummaryDTO>> findTransactionSummaryByMsisdn(@PathVariable String msisdn) {
        subscriberService.getPinByMsisdn(msisdn);

        List<TransactionSummaryDTO> hasil = transactionService.findTransactionSummaryByMsisdn(msisdn);

        if (hasil.isEmpty()) {
            return WebResponse.<List<TransactionSummaryDTO>>builder().status("failed").code("01").message("subscriber not found").build();
        }
        return WebResponse.<List<TransactionSummaryDTO>>builder().status("ok").code("00").message("success").data(hasil).build();
    }
}
