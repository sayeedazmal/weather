package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {
    public final static String QUERY_FOR_CITY_ID =  "https://www.metaweather.com/api/location/search/?query=";
    Context context;
    String cityId;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VollyResposeListener{
        void onError(String message);
        void onResponse(String cityId);
    }

    public void getCityId(String cityName,VollyResposeListener vollyResposeListener) {
        String url = QUERY_FOR_CITY_ID + cityName;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityId = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityId = cityInfo.getString("woeid");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vollyResposeListener.onResponse(cityId);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vollyResposeListener.onError("SomeThing Wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
//        return cityId;
    }
}
