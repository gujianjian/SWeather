package com.example.joy.sweather.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.joy.sweather.R;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
