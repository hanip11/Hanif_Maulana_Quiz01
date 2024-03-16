package com.example.quiz01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvKodeBarang, tvNamaBarang, tvHargaBarang, tvTotalHarga,
            tvHargaDiskon, tvMember, tvJumlahBayar, detailettipemember, detailetnama;

    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailettipemember = findViewById(R.id.detailettipemember);
        detailetnama = findViewById(R.id.detailetnama);
        tvKodeBarang = findViewById(R.id.tv_kode_barang);
        tvNamaBarang = findViewById(R.id.tv_nama_barang);
        tvHargaBarang = findViewById(R.id.tv_harga_barang);
        tvTotalHarga = findViewById(R.id.tv_total_harga);
        tvHargaDiskon = findViewById(R.id.tv_harga_diskon);
        tvMember = findViewById(R.id.tv_member);
        tvJumlahBayar = findViewById(R.id.tv_jumlah_bayar);
        btnShare = findViewById(R.id.btn_share);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("NAMA");
        String tipemember = intent.getStringExtra("TIPE_MEMBER");
        String kodeBarang = intent.getStringExtra("KODE_BARANG");
        String namaBarang = getNamaBarang(kodeBarang); // Mendapatkan nama barang berdasarkan kode barang
        double hargaBarang = intent.getDoubleExtra("HARGA_BARANG", 0.0);
        double totalHarga = intent.getDoubleExtra("TOTAL_HARGA", 0.0);
        double hargaDiskon = intent.getDoubleExtra("HARGA_DISKON", 0.0);
        String member = intent.getStringExtra("MEMBER");
        double jumlahBayar = intent.getDoubleExtra("JUMLAH_BAYAR", 0.0);

        tvKodeBarang.setText("Kode Barang: " + kodeBarang);
        detailetnama.setText("Selamat datang "  + nama);
        detailettipemember.setText("Tipe Member " + tipemember + member);
        tvNamaBarang.setText("Nama Barang: " + namaBarang);
        tvHargaBarang.setText("Harga Barang: Rp " + String.format("%,.2f", hargaBarang)); // Menampilkan harga barang
        tvTotalHarga.setText("Total Harga: Rp " + String.format("%,.2f", totalHarga));
        tvHargaDiskon.setText("Harga Diskon: Rp " + String.format("%,.2f", hargaDiskon));
        tvMember.setText("Member: " );
        double diskonMember = intent.getDoubleExtra("DISKON", 0.0);
        double diskonHarga = diskonMember * totalHarga;
        tvMember.setText("Diskon Member: Rp " + String.format("%,.0f", diskonHarga));
        tvJumlahBayar.setText("Jumlah Bayar: Rp " + String.format("%,.2f", jumlahBayar));


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareText = "Kode Barang: " + kodeBarang + "\n" +
                        "Nama Barang: " + namaBarang + "\n" +
                        "Jumlah Bayar: Rp " + String.format("%,.2f", jumlahBayar);


                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "Bagikan informasi melalui"));
            }
        });
    }
    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "o17":
                return "Oppo A17";
            case "AA5":
                return "Acer Aspire 5";
            case "MP3":
                return "Macbook Pro M3";
            default:
                return "";
        }
    }
}
