package com.rceroorkee.indoriya.gagan.rce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText id,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=(EditText)findViewById(R.id.id);
        pass=(EditText)findViewById(R.id.pass);

    }
    public void doLogin(View view){
        String s1=id.getText().toString();
        String s2=id.getText().toString();


                    if(s1.equals("id")){

                            Intent intent=new Intent(Login.this,NewNotice.class);
                        startActivity(intent);
                        }else {
                            Toast.makeText(this, "you entered Wron id and passwords", Toast.LENGTH_LONG).show();
                        }

                }
    public void doRegister(View view){
        Toast.makeText(this,"sorry ,will start working after final release",Toast.LENGTH_LONG).show();
    }



    }

