package com.example.unittrustcalculator; // PASTIKAN NAMA PACKAGE SAMA DENGAN APP AWAK

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare semua variable
    EditText etAmount, etRate, etMonths;
    TextView tvResult, tvMonthlyResult; // Ada dua result sekarang
    Button btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sambungkan Java dengan ID dalam XML
        etAmount = findViewById(R.id.etInvestedAmount);
        etRate = findViewById(R.id.etDividendRate);
        etMonths = findViewById(R.id.etMonths);

        // Output Views
        tvMonthlyResult = findViewById(R.id.tvMonthlyResult); // Output 1
        tvResult = findViewById(R.id.tvResult);               // Output 2

        // Buttons
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        // Logic bila tekan butang Calculate
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDividend();
            }
        });

        // Logic bila tekan butang Reset (Kosongkan semua)
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etRate.setText("");
                etMonths.setText("");

                // Reset kedua-dua text result
                tvMonthlyResult.setText("Monthly Dividend: RM 0.00");
                tvResult.setText("Total Dividend: RM 0.00");

                // Naikkan keyboard/cursor ke atas
                etAmount.requestFocus();
            }
        });
    }

    private void calculateDividend() {
        String sAmount = etAmount.getText().toString();
        String sRate = etRate.getText().toString();
        String sMonths = etMonths.getText().toString();

        // Check kalau user tak isi semua kotak
        if (sAmount.isEmpty() || sRate.isEmpty() || sMonths.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(sAmount);
        double rate = Double.parseDouble(sRate);
        int months = Integer.parseInt(sMonths);

        // Validation: Bulan tak boleh lebih 12
        if (months > 12) {
            etMonths.setError("Max 12 months only!");
            return;
        }

        // --- FORMULA PENGIRAAN ---
        // Rujuk Assignment: Monthly Dividend = (Rate / 12) x Invested Fund
        // Rate kena bahagi 100 dulu sebab user masukkan persen (e.g. 5)

        double monthlyDividend = (rate / 100 / 12) * amount;

        // Rujuk Assignment: Total Dividend = Monthly Dividend x Number of Months
        double totalDividend = monthlyDividend * months;


        // --- PAPAR KEPUTUSAN ---
        // Format kepada 2 tempat perpuluhan (%.2f)

        // Output 1
        tvMonthlyResult.setText(String.format("Monthly Dividend: RM %.2f", monthlyDividend));

        // Output 2
        tvResult.setText(String.format("Total Dividend: RM %.2f", totalDividend));
    }

    // --- SETUP MENU TITIK TIGA (ACTION BAR) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            // Pergi ke page About
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}