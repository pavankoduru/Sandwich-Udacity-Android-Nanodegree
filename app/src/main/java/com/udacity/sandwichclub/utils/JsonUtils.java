package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {
        List<String> alsoString,ingredientsStrings;
        Sandwich sandwichObject ;


        if(json!=null)
        {

            try {
                alsoString=new ArrayList<>();
                ingredientsStrings=new ArrayList<>();

                JSONObject rootObject=new JSONObject(json);
                JSONObject names=rootObject.getJSONObject("name");
                String mainName=names.getString("mainName");
                JSONArray alsoknownasArray=names.getJSONArray("alsoKnownAs");
                for(int index=0;index<alsoknownasArray.length();index++)
                {
                    alsoString.add(alsoknownasArray.getString(index));
                }
                String placeoforigin=rootObject.getString("placeOfOrigin");
                String description=rootObject.getString("description");
                String imgurl=rootObject.getString("image");
                JSONArray ingredientsArray=rootObject.getJSONArray("ingredients");
                for(int index2=0;index2<ingredientsArray.length();index2++)
                {
                    ingredientsStrings.add(ingredientsArray.getString(index2));
                }


               sandwichObject=new Sandwich(mainName,alsoString,placeoforigin,description,imgurl,ingredientsStrings);
                return sandwichObject;

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
        return null;
    }
}
