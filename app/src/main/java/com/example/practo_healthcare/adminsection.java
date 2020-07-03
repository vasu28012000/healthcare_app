package com.example.practo_healthcare;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminsection extends AppCompatActivity {
    Button button,b2,y;
    EditText e,e1;
    UserDB d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adminsection);
        try {
            d = new UserDB(getApplicationContext());
            Bundle extra = getIntent().getExtras();
            String s1 = extra.getString("STR");
            Toast.makeText(adminsection.this, "WELCOME " + s1, Toast.LENGTH_LONG).show();
            d.open();
            button = findViewById(R.id.asb1);
            b2 = findViewById(R.id.bgh);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    e = findViewById(R.id.mname);
                    e1 = findViewById(R.id.mquantity);
                    y = findViewById(R.id.mbutton);
                    y.setVisibility(View.VISIBLE);
                    e.setVisibility(View.VISIBLE);
                    e1.setVisibility(View.VISIBLE);
                    y.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            d.change(e.getText().toString(), Integer.parseInt(e1.getText().toString()));
                            d.close();
                            Toast.makeText(adminsection.this, "UPDATED", Toast.LENGTH_LONG).show();
                            e.setVisibility(View.GONE);
                            e1.setVisibility(View.GONE);
                            y.setVisibility(View.GONE);
                        }
                    });

                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(adminsection.this, avail.class);
                    startActivity(intent);
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(adminsection.this,"RECORD NOT FOUND ",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.deleterecord:
                Toast.makeText(adminsection.this,"FEATURE TO BE ADDED LATER....",Toast.LENGTH_LONG).show();
                        break;
            case R.id.addrecord:
                Intent intent=new Intent(adminsection.this,get_info.class);
                startActivity(intent);
                break;
            case R.id.upgrade:
                Toast.makeText(adminsection.this,"FEATURE TO BE ADDED LATER.....",Toast.LENGTH_LONG).show();
                break;
            case R.id.medic:Button v=findViewById(R.id.mbutton);
                e=findViewById(R.id.mname);
                e1=findViewById(R.id.mquantity);
                v.setVisibility(View.VISIBLE);
                e.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        d=new UserDB(getApplicationContext());
                        d.open();
                        d.addmeds(e.getText().toString(),Integer.parseInt(e1.getText().toString()));
                        Toast.makeText(adminsection.this,"ADDED",Toast.LENGTH_LONG).show();
                        e.setVisibility(View.GONE);
                        e1.setVisibility(View.GONE);
                        v.setVisibility(View.GONE);
                        d.close();
                    }
                });

        }
        return super.onOptionsItemSelected(item);
    }
}
