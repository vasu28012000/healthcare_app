package com.example.practo_healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class userinterface extends AppCompatActivity {
    RadioGroup rgp;
    RadioButton radioButton;
    Button button;
    Button b1;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_userinterface);
        rgp = findViewById(R.id.radiogrp);

        button = findViewById(R.id.select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int i = rgp.getCheckedRadioButtonId();
                radioButton=findViewById(i);
                switch (radioButton.getId()) {
                    case R.id.doctors:
                        e1 = findViewById(R.id.special);
                        e1.setVisibility(View.VISIBLE);
                        b1 = findViewById(R.id.specialbutton);
                        b1.setVisibility(View.VISIBLE);
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String s1 = e1.getText().toString();
                                Intent intent = new Intent(userinterface.this, mainuser.class);
                                intent.putExtra("str", s1);
                                startActivity(intent);

                            }
                        });
                        break;
                    case R.id.PHARMACY:
                        Intent intent = new Intent(userinterface.this, pharmacy.class);
                        startActivity(intent);

                        break;
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        int id=menu.getItemId();
        switch(id)
        {
            case R.id.feedback:Intent i=new Intent(userinterface.this,feedback.class);
                                startActivity(i);


        }
        return super.onOptionsItemSelected(menu);
    }

}