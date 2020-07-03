package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pharmacy extends AppCompatActivity {
    Button button;
    EditText e1;
    EditText e2;
    UserDB d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pharmacy);
        button=findViewById(R.id.order);
        e1=findViewById(R.id.meds);
        e2=findViewById(R.id.quantity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   d = new UserDB(getApplicationContext());
                   d.open();
                   int i=Integer.parseInt(e2.getText().toString());
                   if (d.checkstock(e1.getText().toString(),i)) {
                       Toast.makeText(pharmacy.this, "ORDER PLACED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                       TextView t = findViewById(R.id.toast);
                       t.setVisibility(View.VISIBLE);
                   } else {
                       TextView t = findViewById(R.id.toast);
                       t.setText("OUT OF STOCK!!!!!");
                       t.setVisibility(View.VISIBLE);
                   }
                   d.close();
               }  catch(Exception e)
               {
                   Toast.makeText(pharmacy.this,"NOT FOUND IN STOCKS!!!",Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}
