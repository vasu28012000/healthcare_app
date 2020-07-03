package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class userlogin extends AppCompatActivity {

    EditText etUsername,etPassword;
    UserDB database;
    User d;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_userlogin);
        try{
        database=new UserDB(getApplicationContext());
        etUsername = findViewById(R.id.usered1);
        etPassword= findViewById(R.id.usered2);
        b = findViewById(R.id.userbt2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().trim().isEmpty())
                {
                    etUsername.setError("Required");
                }
                if (etPassword.getText().toString().trim().isEmpty())
                {
                    etPassword.setError("Required");
                }
                if (!etUsername.getText().toString().trim().isEmpty() && !etPassword.getText().toString().trim().isEmpty())
                {
                    String name = etUsername.getText().toString();
                    String pass = etPassword.getText().toString();


                        d=new User(getApplicationContext());
                        d.open();
                        String s56=d.getData(name);

                        database.open();
                        int result = database.getData(name,pass);
                        database.close();
                        if (result == 0)
                        {
                            Toast.makeText(userlogin.this, "Username or password is incorrect !! \n Please try again...", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent intent=new Intent(userlogin.this,userinterface.class);
                            Toast.makeText(userlogin.this,s56,Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }

                        etUsername.setText("");
                        etPassword.setText("");

                }
            }
        });

        }
        catch(Exception e)
        {
            Toast.makeText(this, "ENTER CORRECT DETAILS!!!", Toast.LENGTH_SHORT).show();
        }

    }
}