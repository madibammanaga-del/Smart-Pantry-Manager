package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView txtRecipeName;
    private TextView txtAvailableIngredients;
    private TextView txtMissingIngredients;
    private TextView txtCookingInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        txtRecipeName = findViewById(R.id.txtRecipeName);
        txtAvailableIngredients =
                findViewById(R.id.txtAvailableIngredients);
        txtMissingIngredients =
                findViewById(R.id.txtMissingIngredients);
        txtCookingInstructions =
                findViewById(R.id.txtCookingInstructions);

        // Get recipe information from SuggestedRecipesActivity.
        String recipeName = getIntent().getStringExtra("recipe_name");

        String availableIngredients =
                getIntent().getStringExtra("available_ingredients");

        String missingIngredients =
                getIntent().getStringExtra("missing_ingredients");

        // Display the recipe name.
        if (recipeName != null) {
            txtRecipeName.setText(recipeName);
        }

        // Display ingredients already in the pantry.
        if (availableIngredients != null
                && !availableIngredients.isEmpty()) {

            txtAvailableIngredients.setText(
                    availableIngredients
            );

        } else {
            txtAvailableIngredients.setText(
                    "No ingredients available."
            );
        }

        // Display ingredients still needed.
        if (missingIngredients != null
                && !missingIngredients.isEmpty()) {

            txtMissingIngredients.setText(
                    missingIngredients
            );

        } else {
            txtMissingIngredients.setText(
                    "You have all the listed ingredients!"
            );
        }

        // Load cooking instructions.
        txtCookingInstructions.setText(
                getCookingInstructions(recipeName)
        );
    }

    private String getCookingInstructions(String recipeName) {

        if (recipeName == null) {
            return "Recipe instructions are unavailable.";
        }

        switch (recipeName) {

            case "Vegetable Omelette":
                return "1. Beat the eggs in a bowl.\n\n"
                        + "2. Chop the onion and tomato.\n\n"
                        + "3. Heat a little oil in a pan and cook "
                        + "the onion until soft.\n\n"
                        + "4. Add the tomato and cook for a few minutes.\n\n"
                        + "5. Pour in the beaten eggs.\n\n"
                        + "6. Cook until the eggs are set, then fold "
                        + "the omelette and serve.";

            case "Tomato Pasta":
                return "1. Boil water in a pot and cook the pasta "
                        + "according to its package instructions.\n\n"
                        + "2. Chop the onion and tomato.\n\n"
                        + "3. Heat a little oil in a pan and cook "
                        + "the onion until soft.\n\n"
                        + "4. Add the tomato and cook until it "
                        + "forms a simple sauce.\n\n"
                        + "5. Drain the pasta and mix it with "
                        + "the tomato sauce.\n\n"
                        + "6. Serve warm.";

            case "Potato Salad":
                return "1. Wash and cut the potatoes.\n\n"
                        + "2. Boil the potatoes until tender, "
                        + "then allow them to cool.\n\n"
                        + "3. If using eggs, boil them until fully cooked, "
                        + "cool them, and peel them.\n\n"
                        + "4. Chop the onion and cooked eggs.\n\n"
                        + "5. Mix the potatoes, eggs, and onion "
                        + "together gently.\n\n"
                        + "6. Add your preferred dressing or seasoning "
                        + "if available, then serve.";

            case "Rice and Beans":
                return "1. Cook the rice according to its "
                        + "package instructions.\n\n"
                        + "2. Chop the onion and tomato.\n\n"
                        + "3. Heat a little oil in a pan and cook "
                        + "the onion until soft.\n\n"
                        + "4. Add the tomato and cook until softened.\n\n"
                        + "5. Add the cooked beans and heat "
                        + "them thoroughly.\n\n"
                        + "6. Serve the beans with the rice.";

            case "Simple Fried Rice":
                return "1. Cook the rice and allow it to cool. "
                        + "Day-old refrigerated rice works well.\n\n"
                        + "2. Beat the eggs in a bowl.\n\n"
                        + "3. Chop the onion.\n\n"
                        + "4. Heat a little oil in a pan and cook "
                        + "the onion until soft.\n\n"
                        + "5. Add the eggs and stir until fully cooked.\n\n"
                        + "6. Add the rice and stir-fry until "
                        + "heated thoroughly.\n\n"
                        + "7. Serve warm.";

            default:
                return "Cooking instructions are not available "
                        + "for this recipe yet.";
        }
    }
}
