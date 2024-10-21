package io.github.yasirmaulana.mimi_store.repository;

import io.github.yasirmaulana.mimi_store.domain.Transaction;
import io.github.yasirmaulana.mimi_store.dto.TransactionSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT MONTH(t.tanggal) as yearmonth, SUM(t.totalHarga) as totalAmount, SUM(t.jumlah) as totalTransaction"
            + "FROM Transaction t"
            + "GROUP BY MONTH(t.tanggal)")
    List<TransactionSummaryDTO> findTransactionSummary();


}
