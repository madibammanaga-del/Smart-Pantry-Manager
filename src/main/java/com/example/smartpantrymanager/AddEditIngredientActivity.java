package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.Database.DatabaseHelper;

public class AddEditIngredientActivity extends AppCompatActivity {

    public static final String EXTRA_INGREDIENT_ID = "ingredient_id";
    public static final String EXTRA_INGREDIENT_NAME = "ingredient_name";
    public static final String EXTRA_INGREDIENT_QUANTITY = "ingredient_quantity";
    public static final String EXTRA_INGREDIENT_UNIT = "ingredient_unit";
    public static final String EXTRA_INGREDIENT_EXPIRY = "ingredient_expiry";

    private DatabaseHelper databaseHelper;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient_activity);

        databaseHelper = new DatabaseHelper(this);

        EditText nameInput = findViewById(R.id.editIngredientName);
        EditText quantityInput = findViewById(R.id.editIngredientQuantity);
        EditText unitInput = findViewById(R.id.editIngredientUnit);
        EditText expiryInput = findViewById(R.id.editIngredientExpiry);
        Button saveButton = findViewById(R.id.btnSaveIngredient);

        ingredientId = getIntent().getIntExtra(
                EXTRA_INGREDIENT_ID, -1
        );

        boolean isEditing = ingredientId != -1;

        if (isEditing) {
            nameInput.setText(getIntent().getStringExtra(
                    EXTRA_INGREDIENT_NAME
            ));

            quantityInput.setText(getIntent().getStringExtra(
                    EXTRA_INGREDIENT_QUANTITY
            ));

            unitInput.setText(getIntent().getStringExtra(
                    EXTRA_INGREDIENT_UNIT
            ));

            expiryInput.setText(getIntent().getStringExtra(
                    EXTRA_INGREDIENT_EXPIRY
            ));

            saveButton.setText("Update Ingredient");
            Button deleteButton = findViewById(R.id.btnDeleteIngredient);

            deleteButton.setVisibility(View.VISIBLE);

            deleteButton.setOnClickListener(view -> {

                new AlertDialog.Builder(AddEditIngredientActivity.this)
                        .setTitle("Delete Ingredient")
                        .setMessage("Are you sure you want to delete this ingredient? This cannot be undone.")
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("Delete", (dialog, which) -> {

                            boolean deleted = databaseHelper.deleteIngredient(
                                    ingredientId
                            );

                            if (deleted) {
                                Toast.makeText(
                                        AddEditIngredientActivity.this,
                                        "Ingredient deleted successfully!",
                                        Toast.LENGTH_SHORT
                                ).show();

                                finish();

                            } else {
                                Toast.makeText(
                                        AddEditIngredientActivity.this,
                                        "Could not delete ingredient.",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        })
                        .show();
            });


        }

        saveButton.setOnClickListener(view -> {

            String name = nameInput.getText().toString().trim();
            String quantity = quantityInput.getText().toString().trim();
            String unit = unitInput.getText().toString().trim();
            String expiry = expiryInput.getText().toString().trim();

            if (name.isEmpty()) {
                nameInput.setError("Enter an ingredient name");
                return;
            }

            if (quantity.isEmpty()) {
                quantityInput.setError("Enter a quantity");
                return;
            }

            if (unit.isEmpty()) {
                unitInput.setError("Enter a unit");
                return;
            }

            boolean saved;

            if (isEditing) {
                saved = databaseHelper.updateIngredient(
                        ingredientId, name, quantity, unit, expiry
                );
            } else {
                saved = databaseHelper.insertIngredient(
                        name, quantity, unit, expiry
                );
            }

            if (saved) {
                Toast.makeText(
                        this,
                        isEditing
                                ? "Ingredient updated successfully!"
                                : "Ingredient saved successfully!",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {
                Toast.makeText(
                        this,
                        isEditing
                                ? "Could not update ingredient."
                                : "Could not save ingredient.",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
        super.onDestroy();
    }
}

