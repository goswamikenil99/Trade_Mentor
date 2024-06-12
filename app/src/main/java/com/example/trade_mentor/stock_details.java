package com.example.trade_mentor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import soup.neumorphism.NeumorphButton;


public class stock_details extends AppCompatActivity {
    int stock_count=0,flg=0;
    private RecyclerView recyclerView;
    private StockAdapter adapter;
    private List<StockItem> stockItems;
    dbhelper helper;
    Date d1;
    String date,stock_price,number_of_stock,total_amount,chp_d;
    double lp_k,chp_k,current_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stockItems = new ArrayList<>();
        NeumorphButton buy=findViewById(R.id.buy);
        NeumorphButton sell=findViewById(R.id.sell);
        NeumorphButton history=findViewById(R.id.history);
        NeumorphButton plus=findViewById(R.id.plus);
        NeumorphButton minus=findViewById(R.id.minus);
        TextView bank_name=findViewById(R.id.bank_name);
        TextView lp=findViewById(R.id.lp);
        TextView chp=findViewById(R.id.chp);
        TextView stock_number=findViewById(R.id.stock_number);
        Intent intent=getIntent();
        String name=intent.getStringExtra("bank_name");
        bank_name.setText(name);
        lp_k=intent.getDoubleExtra("lp",33.33);
        chp_k=intent.getDoubleExtra("chp",33.33);
        lp.setText("₹"+lp_k);
        chp.setText(" "+chp_k+"%");
        helper=new dbhelper(getApplicationContext());
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertrecycleview();
                history.setEnabled(false);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock_count+=1;
                stock_number.setText(""+stock_count);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stock_count<=1) return;
                stock_count-=1;
                stock_number.setText(""+stock_count);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper=new dbhelper(getApplicationContext());
                int flg=0;
                 d1 = new Date();
                 date="" + d1;
                 stock_price=" "+lp_k;
                 chp_d=" "+chp_k;
                 number_of_stock=" "+stock_count;
                 total_amount=" "+(stock_count*lp_k);
                 edit_profile pref=new edit_profile();
                 pref.make_pref(getApplicationContext());
                 double current_mony=Double.parseDouble(pref.getValue());
                 current_amount=current_mony-(stock_count*lp_k);
                 Toast.makeText(getApplicationContext()," "+current_amount,Toast.LENGTH_SHORT).show();
                 pref.saveValue(" "+current_amount);
                ArrayList<stock_info_model> model=helper.fatch_stock_info();
                for(int i=0;i<model.size();i++){
                    if(model.get(i).bank_name.equals(name)){
                        flg=1;
                    }
                }
                if(flg==0){
                    helper.insertStock(name,stock_price,chp_d,number_of_stock,total_amount,date);
                    Toast.makeText(getApplicationContext(),"Stock Inserted Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    helper.updateStock(name,stock_price,chp_d,number_of_stock,total_amount,date);
                    Toast.makeText(getApplicationContext(),"update",Toast.LENGTH_LONG).show();
                }
                StockItem stockInfo = new StockItem(name,stock_price,chp_d,number_of_stock,total_amount,date);
                stockItems.add(stockInfo);
                adapter = new StockAdapter(stockItems);
                recyclerView.setAdapter(adapter);
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper=new dbhelper(getApplicationContext());
                int flg=0;
                d1 = new Date();
                date="" + d1;
                stock_price=" "+lp_k;
                chp_d=" "+chp_k;
                number_of_stock=" "+stock_count;
                total_amount=" "+(stock_count*lp_k);
                edit_profile pref=new edit_profile();
                pref.make_pref(getApplicationContext());
                double current_mony=Double.parseDouble(pref.getValue());
                current_amount=current_mony+(stock_count*lp_k);
                Toast.makeText(getApplicationContext()," "+current_amount,Toast.LENGTH_SHORT).show();
                pref.saveValue(" "+current_amount);
                ArrayList<stock_info_model> model=helper.fatch_stock_info();
                for(int i=0;i<model.size();i++){
                    if(model.get(i).bank_name.equals(name)){
                        flg=1;
                    }
                }
                if(flg==0){
                    helper.insertStock(name,stock_price,chp_d,number_of_stock,total_amount,date);
                    Toast.makeText(getApplicationContext(),"Stock Inserted Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    helper.updateStock(name,stock_price,chp_d,number_of_stock,total_amount,date);
                    Toast.makeText(getApplicationContext(),"update",Toast.LENGTH_LONG).show();
                }
                StockItem stockInfo = new StockItem(name,stock_price,chp_d,number_of_stock,total_amount,date);
                stockItems.add(stockInfo);
                adapter = new StockAdapter(stockItems);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    void add_data(String banak_name,String stock_price,String chp,String number_of_stock,String total_amount,String date){
        helper=new dbhelper(getApplicationContext());
        helper.insertStock(banak_name,stock_price,chp,number_of_stock,total_amount,date);
        Toast.makeText(getApplicationContext(),"Stock Inserted Successfully",Toast.LENGTH_SHORT).show();
    }
    void insertrecycleview(){
        try{
            helper=new dbhelper(getApplicationContext());
            ArrayList<stock_info_model> model=helper.fatch_stock_info();
            for(int i=0;i<model.size();i++){
                Toast.makeText(stock_details.this, model.get(i).bank_name+" "+model.get(i).stock_price+" "+model.get(i).chp, Toast.LENGTH_LONG).show();
                StockItem stockInfo = new StockItem(model.get(i).bank_name,model.get(i).stock_price,model.get(i).chp,model.get(i).number_of_stock,model.get(i).total_amount,model.get(i).date);
                stockItems.add(stockInfo);
            }
            adapter = new StockAdapter(stockItems);
            recyclerView.setAdapter(adapter);
        }
        catch (NullPointerException e){
            Toast.makeText(stock_details.this,"null",Toast.LENGTH_LONG).show();
        }
    }
}