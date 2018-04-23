package com.example.voldarex.neuralmachinetranslation.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.example.voldarex.neuralmachinetranslation.R;


public class CustomAboutDialogClass extends Dialog {

    public Activity c;

    public CustomAboutDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_about_dialog);

    }


}
