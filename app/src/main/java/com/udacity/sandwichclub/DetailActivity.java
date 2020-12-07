package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {
    TextView alsoKnownAs,placeOfOrigin,desription,ingredients;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAs=findViewById(R.id.also_known_tv);
        placeOfOrigin=findViewById(R.id.origin_tv);
        desription=findViewById(R.id.description_tv);
        ingredients=findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.warning)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        StringBuilder aliasString=new StringBuilder();
        StringBuilder ingreString=new StringBuilder();
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++)
        {
            aliasString.append("\u25BA"+sandwich.getAlsoKnownAs().get(i)+"\n");
        }
        alsoKnownAs.setText(aliasString);
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        desription.setText(sandwich.getDescription());
        for(int i=0;i<sandwich.getIngredients().size();i++)
        {
            ingreString.append("\u25BA"+sandwich.getIngredients().get(i)+"\n");
        }
        ingredients.setText(ingreString);


    }
}
