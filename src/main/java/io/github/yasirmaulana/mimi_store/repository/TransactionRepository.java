package io.github.yasirmaulana.mimi_store.repository;

import io.github.yasirmaulana.mimi_store.domain.Transaction;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT CONCAT(YEAR(t.tanggal),'-',MONTH(t.tanggal)) as yearmonth, SUM(t.totalHarga) as totalAmount, COUNT(t.jumlah) as totalTransaction"
            + " FROM `Transaction` t"
            + " WHERE msisdn = :msisdn"
            + " GROUP BY yearmonth")
    List<Tuple> findTransactionSummary(String msisdn);

    List<Transaction> findByMsisdn(String msisdn);

}
