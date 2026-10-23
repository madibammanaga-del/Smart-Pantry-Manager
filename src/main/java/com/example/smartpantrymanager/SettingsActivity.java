package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Spinner spinnerMeasurementUnit;
    private Spinner spinnerExpiryDays;

    private android.content.SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinnerMeasurementUnit =
                findViewById(R.id.spinnerMeasurementUnit);

        spinnerExpiryDays =
                findViewById(R.id.spinnerExpiryDays);

        // Save settings separately from the pantry database.
        preferences = getSharedPreferences(
                "SmartPantrySettings",
                MODE_PRIVATE
        );

        setupMeasurementUnits();
        setupExpiryDays();
    }

    private void setupMeasurementUnits() {

        String[] units = {
                "Grams (g)",
                "Kilograms (kg)",
                "Millilitres (ml)",
                "Litres (L)",
                "Pieces"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        units
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerMeasurementUnit.setAdapter(adapter);

        // Restore the previously selected measurement unit.
        int savedUnit = preferences.getInt(
                "measurement_unit",
                0
        );

        spinnerMeasurementUnit.setSelection(savedUnit);

        spinnerMeasurementUnit.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        preferences.edit()
                                .putInt("measurement_unit", position)
                                .apply();
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {
                    }
                }
        );
    }

    private void setupExpiryDays() {

        String[] expiryOptions = {
                "1 day before expiry",
                "3 days before expiry",
                "5 days before expiry",
                "7 days before expiry"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        expiryOptions
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerExpiryDays.setAdapter(adapter);

        // Restore the previously selected expiry reminder.
        int savedExpiryDays = preferences.getInt(
                "expiry_reminder_days",
                1
        );

        spinnerExpiryDays.setSelection(savedExpiryDays);

        spinnerExpiryDays.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        preferences.edit()
                                .putInt("expiry_reminder_days", position)
                                .apply();
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {
                    }
                }
        );
    }
}
