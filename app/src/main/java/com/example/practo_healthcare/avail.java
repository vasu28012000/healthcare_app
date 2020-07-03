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

public class avail extends AppCompatActivity {
    Button button;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_avail);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e=findViewById(R.id.docnamer);
               try {
                   UserDB d = new UserDB(getApplicationContext());
                   d.open();
                   d.changeavail(e.getText().toString());
                   Toast.makeText(avail.this, "CHANGED!!!", Toast.LENGTH_SHORT).show();
                   d.close();
               }
               catch(Exception e)
               {
                   Toast.makeText(avail.this,"RECORD NOT FOUND",Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}
