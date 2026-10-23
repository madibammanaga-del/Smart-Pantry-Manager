package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.Database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAddIngredient;
    private Button btnSuggestedRecipes;

    private TextView txtTotalIngredients;
    private LinearLayout ingredientList;
    private LinearLayout pantryEmptyCard;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });

        databaseHelper = new DatabaseHelper(this);

        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnSuggestedRecipes = findViewById(R.id.btnSuggestedRecipes);

        txtTotalIngredients = findViewById(R.id.txtTotalIngredients);
        ingredientList = findViewById(R.id.ingredientList);
        pantryEmptyCard = findViewById(R.id.pantryEmptyCard);

        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddEditIngredientActivity.class
            );
            startActivity(intent);
        });

        btnSuggestedRecipes.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadIngredients();
    }

    private void loadIngredients() {

        ArrayList<String[]> ingredients =
                databaseHelper.getAllIngredients();

        int count = ingredients.size();

        txtTotalIngredients.setText(
                count + (count == 1 ? " item" : " items")
        );

        ingredientList.removeAllViews();

        if (count == 0) {
            pantryEmptyCard.setVisibility(View.VISIBLE);
            ingredientList.setVisibility(View.GONE);
            return;
        }

        pantryEmptyCard.setVisibility(View.GONE);
        ingredientList.setVisibility(View.VISIBLE);

        for (String[] ingredient : ingredients) {

            String name = ingredient[1];
            String quantity = ingredient[2];
            String unit = ingredient[3];
            String expiry = ingredient[4];

            int ingredientId = Integer.parseInt(ingredient[0]);

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(40, 32, 40, 32);
            card.setBackgroundColor(
                    android.graphics.Color.WHITE
            );

            LinearLayout.LayoutParams cardParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

            cardParams.bottomMargin = 20;
            card.setLayoutParams(cardParams);

            TextView nameText = new TextView(this);
            nameText.setText(name);
            nameText.setTextSize(18);
            nameText.setTextColor(
                    android.graphics.Color.rgb(24, 59, 42)
            );
            nameText.setTypeface(null, android.graphics.Typeface.BOLD);

            TextView detailsText = new TextView(this);
            detailsText.setText(
                    quantity + " " + unit
            );
            detailsText.setTextSize(15);
            detailsText.setTextColor(
                    android.graphics.Color.rgb(104, 117, 108)
            );

            card.addView(nameText);
            card.addView(detailsText);

            if (expiry != null && !expiry.trim().isEmpty()) {

                TextView expiryText = new TextView(this);
                expiryText.setText("Expiry: " + expiry);
                expiryText.setTextSize(14);
                expiryText.setTextColor(
                        android.graphics.Color.rgb(104, 117, 108)
                );

                card.addView(expiryText);
            }

            card.setOnClickListener(v -> {

                Intent intent = new Intent(
                        MainActivity.this,
                        AddEditIngredientActivity.class
                );

                intent.putExtra(
                        AddEditIngredientActivity.EXTRA_INGREDIENT_ID,
                        ingredientId
                );

                intent.putExtra(
                        AddEditIngredientActivity.EXTRA_INGREDIENT_NAME,
                        name
                );

                intent.putExtra(
                        AddEditIngredientActivity.EXTRA_INGREDIENT_QUANTITY,
                        quantity
                );

                intent.putExtra(
                        AddEditIngredientActivity.EXTRA_INGREDIENT_UNIT,
                        unit
                );

                intent.putExtra(
                        AddEditIngredientActivity.EXTRA_INGREDIENT_EXPIRY,
                        expiry
                );

                startActivity(intent);
            });

            ingredientList.addView(card);
        }
    }

    @Override
    protected void onDestroy() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }

        super.onDestroy();
    }
}
