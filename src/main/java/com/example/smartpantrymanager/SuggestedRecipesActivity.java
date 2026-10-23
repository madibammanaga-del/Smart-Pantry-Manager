package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.Database.DatabaseHelper;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private LinearLayout recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        databaseHelper = new DatabaseHelper(this);
        recipeList = findViewById(R.id.recipeList);

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        recipeList.removeAllViews();

        ArrayList<String[]> ingredients =
                databaseHelper.getAllIngredients();

        if (ingredients.isEmpty()) {
            TextView emptyText = new TextView(this);

            emptyText.setText(
                    "Your pantry is empty. Add ingredients to see recipe suggestions."
            );
            emptyText.setTextSize(16);
            emptyText.setPadding(8, 16, 8, 16);

            recipeList.addView(emptyText);
            return;
        }

        // Built-in recipes and their required ingredients.
        String[][] recipes = {
                {"Vegetable Omelette", "eggs", "onion", "tomato"},
                {"Tomato Pasta", "tomato", "pasta", "onion"},
                {"Potato Salad", "potato", "eggs", "onion"},
                {"Rice and Beans", "rice", "beans", "tomato"},
                {"Simple Fried Rice", "rice", "eggs", "onion"}
        };

        boolean foundRecipe = false;

        for (String[] recipe : recipes) {

            String recipeName = recipe[0];

            ArrayList<String> available = new ArrayList<>();
            ArrayList<String> missing = new ArrayList<>();

            // Check every required ingredient against the pantry.
            for (int i = 1; i < recipe.length; i++) {

                String requiredIngredient = recipe[i];
                boolean found = false;

                for (String[] ingredient : ingredients) {

                    // Database order: id, name, quantity, unit, expiry.
                    String pantryName = ingredient[1].trim();

                    if (pantryName.equalsIgnoreCase(requiredIngredient)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    available.add(requiredIngredient);
                } else {
                    missing.add(requiredIngredient);
                }
            }

            // Show a recipe if at least one required ingredient is available.
            if (!available.isEmpty()) {

                foundRecipe = true;

                int totalRequired = recipe.length - 1;
                int matchPercent =
                        (available.size() * 100) / totalRequired;

                // Create the recipe card.
                LinearLayout card = new LinearLayout(this);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setPadding(24, 24, 24, 24);
                card.setBackgroundColor(
                        Color.rgb(240, 248, 240)
                );

                // Recipe name.
                TextView nameText = new TextView(this);
                nameText.setText(recipeName);
                nameText.setTextSize(20);
                nameText.setTextColor(
                        Color.rgb(35, 90, 45)
                );
                nameText.setTypeface(null, Typeface.BOLD);

                // Percentage of required ingredients available.
                TextView matchText = new TextView(this);
                matchText.setText(
                        "Ingredient match: " + matchPercent + "%"
                );
                matchText.setTextSize(15);
                matchText.setPadding(0, 8, 0, 0);

                // Ingredients already in the pantry.
                TextView availableText = new TextView(this);
                availableText.setText(
                        "You have: " + String.join(", ", available)
                );
                availableText.setTextSize(15);
                availableText.setPadding(0, 12, 0, 0);

                // Ingredients still needed.
                TextView missingText = new TextView(this);

                if (missing.isEmpty()) {
                    missingText.setText(
                            "You need: Nothing from this recipe list!"
                    );
                } else {
                    missingText.setText(
                            "You need: " + String.join(", ", missing)
                    );
                }

                missingText.setTextSize(15);
                missingText.setPadding(0, 8, 0, 0);

                // Add all information to the card.
                card.addView(nameText);
                card.addView(matchText);
                card.addView(availableText);
                card.addView(missingText);

                card.setOnClickListener(v -> {
                    Intent intent = new Intent(
                            SuggestedRecipesActivity.this,
                            RecipeDetailActivity.class
                    );

                    intent.putExtra("recipe_name", recipeName);

                    intent.putExtra(
                            "avaiilable_ingredients",
                            String.join(", ", available)
                    );

                    intent.putExtra(
                            "missing_ingredients",
                            String.join(", ", missing)
                    );

                    startActivity(intent);
                });

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                params.setMargins(0, 0, 0, 16);

                recipeList.addView(card, params);
            }
        }

        // Message if none of the recipes have matching ingredients.
        if (!foundRecipe) {

            TextView noRecipesText = new TextView(this);

            noRecipesText.setText(
                    "No matching recipes found yet. Try adding eggs, rice, "
                            + "tomato, onion, pasta, beans or potato."
            );

            noRecipesText.setTextSize(16);
            noRecipesText.setPadding(8, 16, 8, 16);

            recipeList.addView(noRecipesText);
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

