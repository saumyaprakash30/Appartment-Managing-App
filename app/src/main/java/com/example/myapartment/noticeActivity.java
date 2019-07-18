package com.example.myapartment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.CompactDecimalFormat;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class noticeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnMSend,btnMCancel;
    TextView tvM;
    EditText etMBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        this.setTitle("Notice");

        btnMSend = findViewById(R.id.btnMSend);
        btnMCancel = findViewById(R.id.btnMCancel);
        etMBody = findViewById(R.id.etMBody);
        tvM = findViewById(R.id.tvM);

        Spinner snotice = findViewById(R.id.spinnerNotice);
        snotice.setOnItemSelectedListener(this);

        final String[] noticeList = new String[]{"Select any one","Monthly Maintainance","Custom Notice"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,noticeList);

        snotice.setAdapter(adapter);
        btnMSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeActivity.this.finish();
            }
        });
        btnMSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(noticeActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(noticeActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
                }
                else
                {
                    MyDBHandler dbHandler  = new MyDBHandler(noticeActivity.this,null,null,1);
                    ArrayList<flat> f = dbHandler.getFlats();
                    if(f.size()==0)
                    {
                        Toast.makeText(noticeActivity.this, "No flats present !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String n ="";

                        for(int i=0;i<f.size();i++)
                        {
                            n+=f.get(i).getFlatResidentMobile() + ";";
                        }


                        Uri uri = Uri.parse("smsto:"+n);
                        Intent smsIntent = new Intent(Intent.ACTION_VIEW,uri);
                        smsIntent.putExtra("sms_body",etMBody.getText().toString());
                        startActivity(smsIntent);
                    }
                }
            }
        });

        btnMCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeActivity.this.finish();
            }
        });






    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0)
        {
            tvM.setVisibility(View.GONE);
            etMBody.setVisibility(View.GONE);
            btnMSend.setVisibility(View.GONE);
            btnMCancel.setVisibility(View.GONE);
        }
        else if(i==1)
        {
            tvM.setVisibility(View.VISIBLE);
            etMBody.setVisibility(View.VISIBLE);
            btnMSend.setVisibility(View.VISIBLE);
            btnMCancel.setVisibility(View.VISIBLE);
            etMBody.setText("--Kshitish Residency Notice--\n" +
                    "--------------------------------------\n"+
                    "Tomorrow guard will visit your flat to collect maintenance fee. Please keep the required amount ready with you.\n" +
                    "Thank you\n" +
                    "Kr. Satya Prakash"
            );


        }
        else
        {

            tvM.setVisibility(View.VISIBLE);
            etMBody.setVisibility(View.VISIBLE);
            btnMSend.setVisibility(View.VISIBLE);
            btnMCancel.setVisibility(View.VISIBLE);
            etMBody.setText("");



        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
