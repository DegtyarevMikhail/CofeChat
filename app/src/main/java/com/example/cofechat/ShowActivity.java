package com.example.cofechat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSecondName, tvEmail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }
    private void init() {
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvEmail = findViewById(R.id.tvEmail);
    }
    private void getIntentMain() {
        Intent intent = getIntent();
        if (intent !=null){
            tvName.setText(intent.getStringExtra(Constant.USER_NAME));
            tvSecondName.setText(intent.getStringExtra(Constant.USER_SEC_NAME));
            tvName.setText(intent.getStringExtra(Constant.USER_EMAIL));
        }
    }
}
