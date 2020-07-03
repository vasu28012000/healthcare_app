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

public class get_info extends AppCompatActivity {
    UserDB data;
    Button button;
    EditText e1,e2,e3,e4,e5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_get_info);
        e1=findViewById(R.id.docname);
        e2=findViewById(R.id.specialization);
        e3=findViewById(R.id.contact);
        e4=findViewById(R.id.clinic);
        e5=findViewById(R.id.avail);
       try {
           data = new UserDB(getApplicationContext());
           data.open();
           button=findViewById(R.id.addbutton);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   data.docinfo(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), e5.getText().toString());
                   Toast.makeText(get_info.this,"Added",Toast.LENGTH_SHORT).show();
                   data.close();
               }
           });

       }
       catch(Exception e)
       {
           Toast.makeText(get_info.this,"RECORD NOT FOUND",Toast.LENGTH_SHORT).show();
       }

    }
}
