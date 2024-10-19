package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tanggal", nullable = false)
    private LocalDate tanggal;

    @Column(name = "pelanggan_id", nullable = false)
    private Long pelangganId;

    @Column(name = "produk_id", nullable = false)
    private Long produkId;

    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    @Column(name = "harga_satuan", nullable = false)
    private Long hargaSatuan;

    @Column(name = "total_harga", nullable = false)
    private Long totalHarga;

}
