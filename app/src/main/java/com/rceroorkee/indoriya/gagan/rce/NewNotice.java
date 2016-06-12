package com.rceroorkee.indoriya.gagan.rce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class NewNotice extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextSub;
    private EditText editTextMsg;
//    private EditText editTextSal;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);

        //Initializing views
        editTextSub = (EditText) findViewById(R.id.editTextSubject);
        editTextMsg = (EditText) findViewById(R.id.editTextMsg);
//        editTextSal = (EditText) findViewById(R.id.editTextSalary);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Adding a Notice
    private void addNotice(){

        final String sub = editTextSub.getText().toString().trim();
        final String msg = editTextMsg.getText().toString().trim();
//        final String sal = editTextSal.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NewNotice.this,"Adding...","Wait...",false,false);

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NOTICE_SUB,sub);
                params.put(Config.KEY_NOTICE_MSG,msg);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(NewNotice.this, s, Toast.LENGTH_LONG).show();
            }


        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addNotice();
        }

        if(v == buttonView){
            startActivity(new Intent(this,EditNotice.class));
        }
    }
}