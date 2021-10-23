package com.example.projectcss3211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username,pass;
    private Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = (Button) findViewById(R.id.button_log);
        username = (EditText) findViewById(R.id.username_text);
        pass = (EditText) findViewById(R.id.password_text);
        setLogin();
    }

    private void setLogin(){
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("raphaelx") && pass.getText().toString().equals("123456")){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast toast=Toast.makeText(getApplicationContext(),"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"เข้าสู่ระบบไม่สำเร็จ",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
            }
        });
    }
}