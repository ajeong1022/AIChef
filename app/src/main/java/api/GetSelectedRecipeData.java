package api;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//front end need to check whether the list mRecipe is empty: if it is, show prompt for the users to select from the suggested recipes
public class GetSelectedRecipeData {
    private static int counter;
    private static JSONObject responseObj;

    private static String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/<recipeID>/information";


    //info of ingredients of the currently chosen recipe is shown upon clicking that particular recipes

    public static void callIngredientsListAPI(final List<Recipe> selectedRecipes, RequestQueue queue, final List<String> shoppingList, final String key) {
        counter = -1;
        final Set<String> allIngredients = new HashSet<>();
        shoppingList.clear();
        for(int i=0; i<selectedRecipes.size(); i++) {

            String id = selectedRecipes.get(i).getRecipeID();
            String curURL = url.replaceAll("<recipeID>", id);
            final Recipe curRecipe = selectedRecipes.get(i);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, curURL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
//                            System.out.println("BREAKPOINT 1");
                            responseObj = response;
                            System.out.println(response.toString());
                            JSONArray curIngdList = null;
                            try {
                                curRecipe.setRecipeURL(responseObj.getString("sourceUrl"));
                                System.out.println(curRecipe.getRecipeURL());
                                curIngdList = (JSONArray) responseObj.get("extendedIngredients");
//                            System.out.println("Number of ingredients in the current Recipe: " + curIngdList.length());
                                for (int c = 0; c < curIngdList.length(); c++) {
                                    String newIngredient = (String) curIngdList.getJSONObject(c).get("name");
//                                    System.out.println(newIngredient.getName());
                                    allIngredients.add(newIngredient);
//                                    System.out.println(allIngredients.size());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //check whether it's the last api call
                            counter ++;
//                            if(counter == 0) {
//                                shoppingList.clear();
//                            }
                            if(counter == selectedRecipes.size()-1) {
                                shoppingList.addAll(allIngredients);
//                                System.out.println("ShoppingList Size after reaching the last : " + allIngredients.size());
                            }

                        }

                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error.toString());
                        }
                    }) {

                // Passing some request headers

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    //headers.put("Content-Type", "application/json");
                    headers.put("X-RapidAPI-Key", key);
                    return headers;
                }
            };

            //a request need to be sent for every for-loop
            queue.add(request);
        }

    }
}
