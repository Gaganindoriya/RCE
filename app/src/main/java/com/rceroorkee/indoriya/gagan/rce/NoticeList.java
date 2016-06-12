package com.rceroorkee.indoriya.gagan.rce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NoticeList extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        //check connectivity
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        if(connected){
        getJSON();
        }else{
            Toast.makeText(NoticeList.this, "No Internet Connection ,Please Switch on Your Intenet data..", Toast.LENGTH_SHORT).show();
            NoticeList.this.finish();
        }
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String sub = jo.getString(Config.TAG_SUB);

                String msg = jo.getString(Config.TAG_MSG);





                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,"Id="+id);
                employees.put(Config.TAG_SUB,sub);
                employees.put(Config.TAG_MSG,msg);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                NoticeList.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_SUB,Config.TAG_MSG},
                new int[]{R.id.id, R.id.msg,R.id.desg});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(NoticeList.this,"Fetching Data","Wait...",false,true);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,NoticeDetail.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String id1 = map.get(Config.TAG_ID).toString();
        String sub = map.get(Config.TAG_SUB).toString();
        String msg = map.get(Config.TAG_MSG).toString();
        intent.putExtra(Config.NOTICE_ID,id1);
        intent.putExtra(Config.NOTICE_SUB,sub);
        intent.putExtra(Config.NOTICE_MSG,msg);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.refresh:
                Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
                getJSON();
                break;
            }

        return super.onOptionsItemSelected(item);
    }


}