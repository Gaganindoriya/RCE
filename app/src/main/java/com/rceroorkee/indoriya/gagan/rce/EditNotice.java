package com.rceroorkee.indoriya.gagan.rce;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EditNotice extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextSubject;
    private EditText editTextMessage;
//    private EditText editTextSalary;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    private String subject;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notice);

        Intent intent = getIntent();
        Toast.makeText(EditNotice.this,intent.toString(),Toast.LENGTH_LONG).show();
        id = intent.getStringExtra(Config.NOTICE_ID);
        subject = intent.getStringExtra(Config.NOTICE_SUB);
        message = intent.getStringExtra(Config.NOTICE_MSG);


        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
//        editTextSalary = (EditText) findViewById(R.id.editTextSalary);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
//        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        getEmployee();

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);
        editTextSubject.setText(subject);
        editTextMessage.setText(message);


    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditNotice.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_SUB);
            String desg = c.getString(Config.TAG_MSG);
//            String sal = c.getString(Config.TAG_SAL);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){
        final String sub = editTextSubject.getText().toString().trim();
        final String msg = editTextMessage.getText().toString().trim();
//        final String salary = editTextSalary.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditNotice.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditNotice.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(Config.KEY_EMP_ID,id);
                hashMap.put(Config.KEY_NOTICE_SUB,sub);
                hashMap.put(Config.KEY_NOTICE_MSG,msg);
//                hashMap.put(Config.KEY_EMP_SAL,salary);
                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditNotice.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditNotice.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Notice?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(EditNotice.this,NoticeList.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}