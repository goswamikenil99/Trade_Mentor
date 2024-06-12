package com.example.trade_mentor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import soup.neumorphism.NeumorphButton;


public class home extends AppCompatActivity {
    String name;
    String code;
    double lp;
    double ch ;
    double chp;
    private RecyclerView recyclerView;
    private StockInfoAdapter adapter;
    private List<StockInfo> stockInfoList;
    NeumorphButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NeumorphButton learning_content=findViewById(R.id.learning_content);
        NeumorphButton profile=findViewById(R.id.profile);
        NeumorphButton buy_sell=findViewById(R.id.buy_sell);
        NeumorphButton deposit=findViewById(R.id.deposit);
        NeumorphButton withdrawal=findViewById(R.id.withdrawal);
        home=findViewById(R.id.home);
        home.setBackgroundColor(Color.BLACK);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchDataFromAPI();
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit.setShapeType(1);
                withdrawal.setShapeType(0);
            }
        });
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposit.setShapeType(0);
                withdrawal.setShapeType(1);
            }
        });
        learning_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(getApplicationContext(), leraning_content.class);
                startActivity(intent);
            }
        });
        buy_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new  Intent(getApplicationContext(), stock_details.class);
                i.putExtra("bank_name",name);
                i.putExtra("lp",lp);
                i.putExtra("chp",chp);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new  Intent(getApplicationContext(), edit_profile.class);
                startActivity(intent);
            }
        });
    }
    private void fetchDataFromAPI() {
        String url = "https://nse-stock-market-india.p.rapidapi.com/symbols?index=NIFTY%20BANK";
        String apiKey = "80e1ef4276mshc2af6f012964c4bp1d8cd4jsn9c4e4300256e";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            stockInfoList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                code = jsonObject.getString("code");
                                lp = jsonObject.getDouble("lp");
                                ch = jsonObject.getDouble("ch");
                                chp = jsonObject.getDouble("chp");
                                StockInfo stockInfo = new StockInfo(name, code, lp, ch, chp);
                                stockInfoList.add(stockInfo);
                            }
                            adapter = new StockInfoAdapter(stockInfoList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(home.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "Error fetching data from API: " + error.getMessage());
                        Toast.makeText(home.this, "Error fetching data from API", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-RapidAPI-Key", "80e1ef4276mshc2af6f012964c4bp1d8cd4jsn9c4e4300256e");
                headers.put("X-RapidAPI-Host", "nse-stock-market-india.p.rapidapi.com");
                return headers;
            }
        };

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


}