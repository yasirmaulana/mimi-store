package io.github.yasirmaulana.mimi_store.service.impl;

import io.github.yasirmaulana.mimi_store.domain.Transaction;
import io.github.yasirmaulana.mimi_store.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.mimi_store.dto.TransactionSummaryDTO;
import io.github.yasirmaulana.mimi_store.dto.TransaksiListResponseDTO;
import io.github.yasirmaulana.mimi_store.repository.TransactionRepository;
import io.github.yasirmaulana.mimi_store.service.TransactionService;
import io.github.yasirmaulana.mimi_store.util.PaginationUtil;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public ResultPageResponseDTO<TransaksiListResponseDTO> findTransaksiList(Integer page, Integer limit,
                                                                             String sortBy, String direction, Long pelangganId) {

        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Transaction> pageResult = transactionRepository.findAll(pageable);
        List<TransaksiListResponseDTO> dtos = pageResult.stream().map(p-> {
            TransaksiListResponseDTO dto = new TransaksiListResponseDTO();
            dto.setTanggal(p.getTanggal());
            dto.setPelangganId(p.getPelangganId());
            dto.setProdukId(p.getProdukId());
            dto.setJumlah(p.getJumlah());
            dto.setHargaSatuan(p.getHargaSatuan());
            dto.setTotalHarga(p.getTotalHarga());

            return dto;
        }).toList();

        Long totalElement = pageResult.getTotalElements();
        Long totalPage = (long) pageResult.getTotalPages();
        return PaginationUtil.createResultPageDTO(dtos, totalElement, totalPage);
    }

    @Override
    public List<TransaksiListResponseDTO> findTransactionByMsisdn(String msisdn) {
        return transactionRepository.findByMsisdn(msisdn).stream()
                .map(p -> new TransaksiListResponseDTO(
                        p.getTanggal(),
                        p.getPelangganId(),
                        p.getProdukId(),
                        p.getJumlah(),
                        p.getHargaSatuan(),
                        p.getTotalHarga(),
                        p.getMsisdn()
                )).toList();
    }

    @Override
    public List<TransactionSummaryDTO> findTransactionSummaryByMsisdn(String msisdn) {
        List<Tuple> tuples = transactionRepository.findTransactionSummary(msisdn);
        return convertToDTO(tuples);
    }

    public List<TransactionSummaryDTO> convertToDTO(List<Tuple> tuples) {
        return tuples.stream()
                .map(tuple -> new TransactionSummaryDTO(
                        tuple.get("yearmonth", String.class),
                        tuple.get("totalAmount", Long.class),
                        tuple.get("totalTransaction", Long.class)
                )).toList();
    }
}
