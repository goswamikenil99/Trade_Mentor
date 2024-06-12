package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import soup.neumorphism.NeumorphCardView;

public class leraning_content extends AppCompatActivity {
    NeumorphCardView m1_ch1,m1_ch2,m1_ch3,m1_ch4,m1_ch5,m1_ch6,m1_ch7,m1_ch8,m1_ch9,m1_ch10,m2_ch11,m2_ch12,m2_ch13,m2_ch14,m2_ch15,m2_ch16,m2_ch17,m2_ch18,m2_ch19,m2_ch20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leraning_content);
        Button all_cor=findViewById(R.id.all_cor);
        Button intermidiate=findViewById(R.id.intermidiate);
        Button beginer=findViewById(R.id.beginer);
        Button advance=findViewById(R.id.advance);
         m1_ch1=findViewById(R.id.m1_ch1);
         m1_ch2=findViewById(R.id.m1_ch2);
         m1_ch3=findViewById(R.id.m1_ch3);
         m1_ch4=findViewById(R.id.m1_ch4);
         m1_ch5=findViewById(R.id.m1_ch5);
         m1_ch6=findViewById(R.id.m1_ch6);
         m1_ch7=findViewById(R.id.m1_ch7);
         m1_ch8=findViewById(R.id.m1_ch8);
         m1_ch9=findViewById(R.id.m1_ch9);
         m1_ch10=findViewById(R.id.m1_ch10);
         m2_ch11=findViewById(R.id.m2_ch11);
         m2_ch12=findViewById(R.id.m2_ch12);
         m2_ch13=findViewById(R.id.m2_ch13);
         m2_ch14=findViewById(R.id.m2_ch14);
         m2_ch15=findViewById(R.id.m2_ch15);
         m2_ch16=findViewById(R.id.m2_ch16);
         m2_ch17=findViewById(R.id.m2_ch17);
         m2_ch18=findViewById(R.id.m2_ch18);
         m2_ch19=findViewById(R.id.m2_ch19);
         m2_ch20=findViewById(R.id.m2_ch20);
        all_cor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view);
        beginer.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
        intermidiate.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
        advance.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
        all_cor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_cor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view);
                beginer.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                intermidiate.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                advance.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                all_visiable();
            }
        });
        beginer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_cor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                beginer.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view);
                intermidiate.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                advance.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                beginner();
            }
        });
        intermidiate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_cor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                beginer.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                intermidiate.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view);
                advance.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                intermidiate();
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_cor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                beginer.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                intermidiate.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view2);
                advance.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.learning_view);
                advance();
            }
        });
        m1_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch1");
                startActivity(i);
            }
        });
        m1_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch2");
                startActivity(i);
            }
        });
        m1_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch3");
                startActivity(i);
            }
        });
        m1_ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch4");
                startActivity(i);
            }
        });
        m1_ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch5");
                startActivity(i);
            }
        });
        m1_ch6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), learning_description.class);
                i.putExtra("module","b_ch6");
                startActivity(i);
            }
        });
    }
    void all_visiable(){
        m1_ch1.setVisibility(View.VISIBLE);
        m1_ch2.setVisibility(View.VISIBLE);
        m1_ch3.setVisibility(View.VISIBLE);
        m1_ch4.setVisibility(View.VISIBLE);
        m1_ch5.setVisibility(View.VISIBLE);
        m1_ch6.setVisibility(View.VISIBLE);
        m1_ch7.setVisibility(View.VISIBLE);
        m1_ch8.setVisibility(View.VISIBLE);
        m1_ch9.setVisibility(View.VISIBLE);
        m1_ch10.setVisibility(View.VISIBLE);
        m2_ch11.setVisibility(View.VISIBLE);
        m2_ch12.setVisibility(View.VISIBLE);
        m2_ch13.setVisibility(View.VISIBLE);
        m2_ch14.setVisibility(View.VISIBLE);
        m2_ch15.setVisibility(View.VISIBLE);
        m2_ch16.setVisibility(View.VISIBLE);
        m2_ch17.setVisibility(View.VISIBLE);
        m2_ch18.setVisibility(View.VISIBLE);
        m2_ch19.setVisibility(View.VISIBLE);
        m2_ch20.setVisibility(View.VISIBLE);
    }
    void beginner(){
        m1_ch1.setVisibility(View.VISIBLE);
        m1_ch2.setVisibility(View.VISIBLE);
        m1_ch3.setVisibility(View.VISIBLE);
        m1_ch4.setVisibility(View.VISIBLE);
        m1_ch5.setVisibility(View.VISIBLE);
        m1_ch6.setVisibility(View.VISIBLE);
        m1_ch7.setVisibility(View.GONE);
        m1_ch8.setVisibility(View.GONE);
        m1_ch9.setVisibility(View.GONE);
        m1_ch10.setVisibility(View.GONE);
        m2_ch11.setVisibility(View.GONE);
        m2_ch12.setVisibility(View.GONE);
        m2_ch13.setVisibility(View.GONE);
        m2_ch14.setVisibility(View.GONE);
        m2_ch15.setVisibility(View.GONE);
        m2_ch16.setVisibility(View.GONE);
        m2_ch17.setVisibility(View.GONE);
        m2_ch18.setVisibility(View.GONE);
        m2_ch19.setVisibility(View.GONE);
        m2_ch20.setVisibility(View.GONE);
    }
    void intermidiate(){
        m1_ch1.setVisibility(View.GONE);
        m1_ch2.setVisibility(View.GONE);
        m1_ch3.setVisibility(View.GONE);
        m1_ch4.setVisibility(View.GONE);
        m1_ch5.setVisibility(View.GONE);
        m1_ch6.setVisibility(View.GONE);
        m1_ch7.setVisibility(View.VISIBLE);
        m1_ch8.setVisibility(View.VISIBLE);
        m1_ch9.setVisibility(View.VISIBLE);
        m1_ch10.setVisibility(View.VISIBLE);
        m2_ch11.setVisibility(View.VISIBLE);
        m2_ch12.setVisibility(View.VISIBLE);
        m2_ch13.setVisibility(View.GONE);
        m2_ch14.setVisibility(View.GONE);
        m2_ch15.setVisibility(View.GONE);
        m2_ch16.setVisibility(View.GONE);
        m2_ch17.setVisibility(View.GONE);
        m2_ch18.setVisibility(View.GONE);
        m2_ch19.setVisibility(View.GONE);
        m2_ch20.setVisibility(View.GONE);
    }
    void advance(){
        m1_ch1.setVisibility(View.GONE);
        m1_ch2.setVisibility(View.GONE);
        m1_ch3.setVisibility(View.GONE);
        m1_ch4.setVisibility(View.GONE);
        m1_ch5.setVisibility(View.GONE);
        m1_ch6.setVisibility(View.GONE);
        m1_ch7.setVisibility(View.GONE);
        m1_ch8.setVisibility(View.GONE);
        m1_ch9.setVisibility(View.GONE);
        m1_ch10.setVisibility(View.GONE);
        m2_ch11.setVisibility(View.GONE);
        m2_ch12.setVisibility(View.GONE);
        m2_ch13.setVisibility(View.VISIBLE);
        m2_ch14.setVisibility(View.VISIBLE);
        m2_ch15.setVisibility(View.VISIBLE);
        m2_ch16.setVisibility(View.VISIBLE);
        m2_ch17.setVisibility(View.VISIBLE);
        m2_ch18.setVisibility(View.VISIBLE);
        m2_ch19.setVisibility(View.VISIBLE);
        m2_ch20.setVisibility(View.VISIBLE);
    }
}