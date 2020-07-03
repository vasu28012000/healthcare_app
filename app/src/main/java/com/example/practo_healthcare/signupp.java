package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class signupp extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> data= new ArrayList<String>();
    String s4;
    UserDB database;
    User d1;
        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signupp);
        Spinner s=findViewById(R.id.spinner);
        list.add("MISTER");
        list.add("MASTER");
        list.add("MISS");
        list.add("MISTRESS");
        ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(a);

        s.setOnItemSelectedListener(this);
            Button b= findViewById(R.id.sign123);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e1=findViewById(R.id.firstname);
                EditText e2=findViewById(R.id.lastname);
                EditText e3=findViewById(R.id.number);
                EditText e4= findViewById(R.id.pass);
                String []s1 = new String[4];
                s1[0] = e1.getText().toString();
                s1[1] = e2.getText().toString();
                s1[2] = e3.getText().toString();
                s1[3]=e4.getText().toString();
                if (s1[0].length()!=0&& s1[1].length()!=0 && s1[2].length()!=0&&s1[3].length()!=0)
                {
                    if (s1[2].length() != 10)
                    {
                        Toast.makeText(signupp.this, "ENTER CORRECT PHONE NUMBER", Toast.LENGTH_LONG).show();
                        e3.setText("");
                    }
                    else if(s1[3].length()<8)
                    {
                        Toast.makeText(signupp.this,"MINIMUM 8 CHARACTERS FOR PASSWORD",Toast.LENGTH_LONG).show();
                        e4.setText("");
                    }
                    else {
                        data.add(s4);
                        data.add(s1[0]);
                        data.add(s1[1]);
                        data.add(s1[2]);
                        data.add(s1[3]);
                        try{
                            database=new UserDB(getApplicationContext());
                            d1=new User(getApplicationContext());
                            d1.open();
                            database.open();
                            database.createEntry(s1[2].trim(),s1[3].trim());
                            d1.createEntry(s1[0],s1[1],s4,s1[2]);
                            d1.close();
                            database.close();
                            Toast.makeText(signupp.this, "SUCCESS!!!!", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(signupp.this,Main2Activity.class);
                            startActivity(intent);
                            finish();
                        }
                        catch (Exception e) {
                            Toast.makeText(signupp.this, "ERROR", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(signupp.this,"all fields required",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        s4=list.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
