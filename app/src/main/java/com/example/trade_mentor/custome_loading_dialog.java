package com.example.trade_mentor;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class custome_loading_dialog {
    Activity activity;
    AlertDialog dialog;
    custome_loading_dialog(Activity myactivity){
    activity=myactivity;
    }

    void startloading(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custome_loading_doalog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }

    void endloading(){
        dialog.dismiss();
    }
}
