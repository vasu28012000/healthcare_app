package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class adminlogin extends AppCompatActivity {

    EditText e1,e2;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           requestWindowFeature(Window.FEATURE_NO_TITLE);
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adminlogin);
        e1=findViewById(R.id.admined1);
        e2=findViewById(R.id.admined2);
        Button b123=findViewById(R.id.adminlog);
        b123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String [] adminusers={"VASU","SHASHANK"};
                String adminpasscode="meetthedevil";
                if(s1.equalsIgnoreCase(adminusers[0])||s1.equalsIgnoreCase(adminusers[1])) {
                    if (s2.equalsIgnoreCase(adminpasscode))
                    {
                        Toast.makeText(adminlogin.this, "SUCCESS!!!", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(adminlogin.this,adminsection.class);
                      intent.putExtra("STR",s1);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(adminlogin.this, "FAILED!!!", Toast.LENGTH_LONG).show();
                        e1.setText("");
                        e2.setText("");
                      }
                }
                else {
                    Toast.makeText(adminlogin.this, "FAILED!!!", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                }

            }
        });
    }
}
