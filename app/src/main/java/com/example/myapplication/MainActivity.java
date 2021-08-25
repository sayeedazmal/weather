package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3;
    EditText cityEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        cityEdit=findViewById(R.id.editDataInput);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button1:
                WeatherDataService weatherDataService = new WeatherDataService(this);
               weatherDataService.getCityId(cityEdit.getText().toString(), new WeatherDataService.VollyResposeListener() {
                   @Override
                   public void onError(String message) {
                       Toast.makeText(MainActivity.this,"Error Created",Toast.LENGTH_SHORT).show();
                   }
                   @Override
                   public void onResponse(String cityId) {
                    Toast.makeText(MainActivity.this,"the CityID is="+cityId,Toast.LENGTH_SHORT).show();
                   }
               });

             break;
            case R.id.button2:Toast.makeText(this,"this is cityID button 2",Toast.LENGTH_SHORT).show();  break;
            case R.id.button3:Toast.makeText(this,"this is CityName button 3",Toast.LENGTH_SHORT).show();break;
        }
    }
}