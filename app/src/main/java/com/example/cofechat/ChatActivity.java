package com.example.cofechat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private EditText edMail, edSecondName,edName;
    private Button btn_read, btn_save;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        edMail = findViewById(R.id.edMail);
        edSecondName = findViewById(R.id.edSecondName);
        edName = findViewById(R.id.edName);
        btn_read = findViewById(R.id.btn_read);
        btn_save = findViewById(R.id.btn_save);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        btn_read.setOnClickListener(v->{
            String id = mDataBase.getKey();
            String name = edName.getText().toString();
            String sec_name = edSecondName.getText().toString();
            String email = edMail.getText().toString();
            User newUser = new User(id, name, sec_name, email);
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sec_name) && !TextUtils.isEmpty(email)) {
                mDataBase.push().setValue(newUser);
                Toast.makeText(this, "Сохранено!", Toast.LENGTH_LONG).show();
                Log.d("MyTag", "mDataBase" + mDataBase);
            }else {
                Toast.makeText(this, "Не заполнено!", Toast.LENGTH_LONG).show();
            }
        });


        btn_save.setOnClickListener(v->{
            Intent intent = new Intent(ChatActivity.this, ReadActivity.class);//ReadActivity
            startActivity(intent);
            //finish();
        });
    }
}
