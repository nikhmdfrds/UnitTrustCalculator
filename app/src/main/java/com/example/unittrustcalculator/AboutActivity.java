package com.example.unittrustcalculator; // PASTIKAN NAMA PAKEJ AWAK SAMA

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Ini code untuk tunjuk butang "Back" (Panah Kiri) kat atas
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About Us");
        }
    }

    // Ini code supaya bila tekan Back, dia balik ke Home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Tutup page ni, balik belakang
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}