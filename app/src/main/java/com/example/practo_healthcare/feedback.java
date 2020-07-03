package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    UserDB usd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);
        b1=findViewById(R.id.bts);
        e1=findViewById(R.id.r);
        e2=findViewById(R.id.f);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    usd=new UserDB(getApplicationContext());
                    usd.open();
                    usd.feed(e1.getText().toString(), e2.getText().toString());
                    usd.close();
                    Toast.makeText(feedback.this, "THANK YOU!", Toast.LENGTH_LONG).show();
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(feedback.this, "CONNECTION ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
