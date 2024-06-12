package com.example.trade_mentor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class learning_description extends AppCompatActivity {
    WebView webview;
    TextView tital,text1,text2,text3;
    ImageView img1,img2,img3;
    String video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_description);
        webview=findViewById(R.id.web_view);
        tital=findViewById(R.id.tital);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        Intent intent = getIntent();
        String module = intent.getStringExtra("module");
        switch (module){
            case "b_ch1":
                tital.setText(R.string.b_ch1);
                text1.setText(R.string.b_ch1_desc);
                text2.setText(R.string.b_ch1_point_market);
                text3.setText(R.string.b_ch1_point_Trading_in_the_Stock_Market);
                img1.setImageResource(R.drawable.b_ch1_img1);
                img2.setImageResource(R.drawable.b_ch1_img2);
                img3.setImageResource(R.drawable.b_ch1_img3);
                video="<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/7S4jfCFkMoE?si=_i12ebnwKmkLjG7w\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            case "b_ch2":
                tital.setText(R.string.b_ch2);
                text1.setText(R.string.b_ch2_desc);
                text2.setText(R.string.b_ch2_point_What_are_Stocks);
                text3.setText(R.string.b_ch2_point_Understanding_the_Stock_Market_Basics);
                img1.setImageResource(R.drawable.b_ch2_img1);
                img2.setImageResource(R.drawable.b_ch2_img2);
                img3.setImageResource(R.drawable.b_ch2_img3);
                video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/p7HKvqRI_Bo?si=s4G-gxJrgxHOepSr\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            case "b_ch3":
                tital.setText(R.string.b_ch3);
                text1.setText(R.string.b_ch3_desc);
                text2.setText(R.string.b_ch3_point_share_types);
                text3.setText(R.string.b_ch3_point_Features_of_owning_Equity);
                img1.setImageResource(R.drawable.b_ch3_img1);
                img2.setImageResource(R.drawable.b_ch3_img2);
                img3.setImageResource(R.drawable.b_ch3_img3);
                video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/HYSsMgJu4YM?si=CBgwKwk3eVgJ11tt\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            case "b_ch4":
                tital.setText(R.string.b_ch4);
                text1.setText(R.string.b_ch4_desc);
                text2.setText(R.string.b_ch4_point_What_are_Candlesticks);
                text3.setText(R.string.b_ch4_point_candlestick_types);
                img1.setImageResource(R.drawable.b_ch4_img1);
                img2.setImageResource(R.drawable.b_ch4_img2);
                img3.setImageResource(R.drawable.b_ch4_img3);
                video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AOz1YPOKvEs?si=tanWfWRAdfA0gfcr\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            case "b_ch5":
                tital.setText(R.string.b_ch5);
                text1.setText(R.string.b_ch5_desc);
                text2.setText(R.string.b_ch5_point_1);
                text3.setText(R.string.b_ch5_point_2);
                img1.setImageResource(R.drawable.b_ch5_img1);
                img2.setImageResource(R.drawable.b_ch5_img2);
                img3.setImageResource(R.drawable.b_ch5_img3);
                video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AOz1YPOKvEs?si=tanWfWRAdfA0gfcr\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            case "b_ch6":
                tital.setText(R.string.b_ch6);
                text1.setText(R.string.b_ch6_desc);
                text2.setText(R.string.b_ch6_point_1);
                text3.setText(null);
                img1.setImageResource(R.drawable.b_ch6_img1);
                img2.setImageResource(R.drawable.b_ch6_img2);
                img3.setImageResource(R.drawable.b_ch6_img3);
                video="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/AOz1YPOKvEs?si=tanWfWRAdfA0gfcr\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                break;
            default:tital.setText("hello kenil");
        }
        if(video!=null){
            play_video();
        }
    }
    void play_video(){
        webview.loadData(video,"text/html","utf-8");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
    }
}