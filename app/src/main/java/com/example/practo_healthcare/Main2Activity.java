package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {

    RadioGroup login;
    RadioButton radioButton;
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        login= findViewById(R.id.login);
        b1=(Button) findViewById(R.id.loginbutton);
        b2= (Button) findViewById(R.id.signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int i=login.getCheckedRadioButtonId();
                radioButton = findViewById(i);
                switch(radioButton.getId())
                {
                case R.id.admin:Intent intent= new Intent(Main2Activity.this,adminlogin.class);
                        startActivity(intent);
                        break;
                    case R.id.user:Intent intent1=new Intent(Main2Activity.this,userlogin.class);
                        startActivity(intent1);
                                        break;
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,signupp.class);
                startActivity(intent);
            }
        });

    }
}
