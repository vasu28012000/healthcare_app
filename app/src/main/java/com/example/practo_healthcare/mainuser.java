package com.example.practo_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class mainuser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    ArrayList<String> list_doc,final_doc;
    Button b1,b2,b3;
    ArrayAdapter aa;
    UserDB d;
    TextView t;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mainuser);
        Bundle extra=getIntent().getExtras();
        final String s1=extra.getString("str");
        Toast.makeText(mainuser.this,s1,Toast.LENGTH_LONG).show();
        b1=findViewById(R.id.b);
                      spinner=findViewById(R.id.spin);
                      try {
                          d = new UserDB(getApplicationContext());
                          list_doc = new ArrayList<String>();
                            d.open();
                          list_doc = d.docdata(s1);
                          d.close();
                          b2 = findViewById(R.id.a1);
                          b3 = findViewById(R.id.a2);
                          t = findViewById(R.id.booking);
                          spinner.setOnItemSelectedListener(this);
                          aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list_doc);
                          aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          spinner.setAdapter(aa);
                          b1.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  d.open();
                                  if (d.docavail(aa.getItem(i).toString())) {

                                      t.setText("APPOINTMENT AVAILABLE");
                                      t.setVisibility(View.VISIBLE);
                                      b2.setVisibility(View.VISIBLE);
                                      b3.setVisibility(View.VISIBLE);
                                      TextView y=findViewById(R.id.tt);
                                      String s44=d.retclinic(aa.getItem(i).toString());
                                      y.setText("CLINIC: "+s44);
                                      y.setVisibility(View.VISIBLE);
                                  }
                                  else
                                  {
                                      t.setText("BOOKING NOT AVAILABLE");
                                      t.setVisibility(View.VISIBLE);
                                  }
                                  d.close();
                              }

                          });
                                  b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      d.open();
                                     if(d.docavail(aa.getItem(i).toString())) {
                                         d.changeavail(aa.getItem(i).toString());
                                         Toast.makeText(mainuser.this, "BOOKING DONE", Toast.LENGTH_SHORT).show();
                                         t.setText("BOOKING DONE");
                                     }
                                     else{
                                         Toast.makeText(mainuser.this, "BOOKING FAILED", Toast.LENGTH_SHORT).show();
                                         t.setText("BOOKING FAILED");
                                     }

                                      d.close();
                                  }
                              });
                                      b3.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      d.open();
                                      String h = d.retcontact(aa.getItem(i).toString());
                                      d.close();
                                      Intent intent = new Intent(Intent.ACTION_DIAL);
                                      intent.setData(Uri.parse("tel:"+h));
                                      startActivity(intent);
                                  }
                              });


                      }  catch(Exception e)
                      {
                          Toast.makeText(mainuser.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                      }




    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        i=position;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    Toast.makeText(mainuser.this,"NOTHING SELECTED!!",Toast.LENGTH_SHORT).show();
    }
}
