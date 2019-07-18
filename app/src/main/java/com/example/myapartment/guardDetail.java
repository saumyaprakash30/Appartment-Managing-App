package com.example.myapartment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class guardDetail extends AppCompatActivity {
    TextView tvName,tvNumber,tvGovId,tvNote,tvGId,tvGNote,tvPrevGuard;
    Button btnCall,btnMoreDetail;

    MyDBHandler mydb = new MyDBHandler(guardDetail.this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_detail);

        tvName = findViewById(R.id.tvGuardName);
        tvNumber = findViewById(R.id.tvGuardNumber);
        tvGovId = findViewById(R.id.tvGuardGovId);
        tvGId = findViewById(R.id.tvGId);
        tvGNote = findViewById(R.id.tvGNote);
        tvNote = findViewById(R.id.tvGuardNote);
        tvPrevGuard = findViewById(R.id.tvPrevGuard);

        btnCall = findViewById(R.id.btnGuardCall);
        btnMoreDetail = findViewById(R.id.btnGuardMoreDetail);

        tvGovId.setVisibility(View.GONE);
        tvNote.setVisibility(View.GONE);
        tvGId.setVisibility(View.GONE);
        tvGNote.setVisibility(View.GONE);

        tvPrevGuard.setMovementMethod(new ScrollingMovementMethod());

        tvPrevGuard.setVisibility(View.GONE);

        guard g1 = new guard();
        g1 = mydb.getCurrentGuard();

        tvName.setText(g1.getGuardName());
        tvNumber.setText(g1.getGuardNumber());
        tvGovId.setText(g1.getGuardGovId());
        tvNote.setText(g1.getGuardNote());



        btnMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnMoreDetail.getText().toString().trim().equals("more detail"))
                {
                    tvNote.setVisibility(View.VISIBLE);
                    tvGovId.setVisibility(View.VISIBLE);

                    tvGId.setVisibility(View.VISIBLE);
                    tvGNote.setVisibility(View.VISIBLE);
                    btnMoreDetail.setText("Less Detail");
                }
                else
                {
                    tvNote.setVisibility(View.GONE);
                    tvGovId.setVisibility(View.GONE);

                    tvGId.setVisibility(View.GONE);
                    tvGNote.setVisibility(View.GONE);
                    btnMoreDetail.setText("more detail");

                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(guardDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(guardDetail.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvNumber.getText().toString()));
                    startActivity(intent);


                }
            }
        });


    }

    @Override
    protected void onPostResume() {
       guard g1 = new guard();
        g1 = mydb.getCurrentGuard();

        tvName.setText(g1.getGuardName());
        tvNumber.setText(g1.getGuardNumber());
        tvGovId.setText(g1.getGuardGovId());
        tvNote.setText(g1.getGuardNote());

        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guard_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId())
        {
            case R.id.guardMenuAdd:
                startActivity(new Intent(guardDetail.this,com.example.myapartment.guardAdd.class));
                break;
            case R.id.guardMenuPrevDetail:
                tvPrevGuard.setVisibility(View.VISIBLE);
                ArrayList<guard> prevGuards = mydb.getAllGuards();

                String data= "";

                for(int i=0;i<prevGuards.size();i++)
                {
                    data += "Name" + " : " +prevGuards.get(i).getGuardName() +"\nMob." +" : " + prevGuards.get(i).getGuardNumber() +
                            "\nId: " + prevGuards.get(i).getGuardGovId()+
                            "\nother: " + prevGuards.get(i).getGuardNote()+
                            "\n---------------------------------\n";
                }

                tvPrevGuard.setText(data);
        }
        
        return super.onOptionsItemSelected(item);
    }
}
