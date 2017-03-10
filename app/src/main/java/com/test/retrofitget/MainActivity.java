package com.test.retrofitget;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.retrofitget.Model.Model;
import com.test.retrofitget.Service.Service;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    private final String BASE_URL = "http://ruslancode.net23.net/";
    private Button btnGetAsync, btnGetSync;
    private TextView subjectInfo, infoInfo;
    private String version = "1.9";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RestAdapter restAdapter;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectInfo = (TextView) findViewById(R.id.subjectInfo);
        infoInfo = (TextView) findViewById(R.id.infoInfo);

        radioGroup=(RadioGroup)findViewById(R.id.choice);

        restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).build();
        service = restAdapter.create(Service.class);

        btnGetSync = (Button) findViewById(R.id.btnGetSync);
        btnGetSync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRequestSync();
            }
        });
        btnGetAsync = (Button) findViewById(R.id.btnGetAsync);
        btnGetAsync.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRequestAsync();
            }
        });
    }

    private void getRequestSync(){
        final Handler handler = new Handler();
        new Thread(new Runnable(){
            @Override
            public void run() {
                final Model model = service.getSync(version, getTest());

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateUi(model.getSubject(), model.getInfo());
                    }
                });
            }
        }).start();
    }

    private void getRequestAsync(){
        service.getAsync(version, getTest(), new Callback<Model>() {
            @Override
            public void success(Model model, Response response) {
                updateUi(model.getSubject(), model.getInfo());
                Log.i("Result", response.toString() );
            }
            @Override
            public void failure(RetrofitError error) {
                String errorPlaceHolder = "No Info Obtained";
                updateUi(errorPlaceHolder, errorPlaceHolder);
                Log.i("Result", error.getMessage() );
            }
        });
    }

    private String getTest(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);
        return radioButton.getTag().toString();
    }

    private void updateUi(String subject, String info){
        subjectInfo.setText(subject);
        infoInfo.setText(info);
    }
}
