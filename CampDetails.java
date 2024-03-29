package com.example.shantanu.odishaayushman;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CampDetails extends AppCompatActivity {

    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_details);
        t1=(TextView)findViewById(R.id.camp);
        t2=(TextView)findViewById(R.id.address);
        t3=(TextView)findViewById(R.id.date);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Camp/"+DateTimePasser.datetime);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CampInfo ci=dataSnapshot.getValue(CampInfo.class);
                t2.setText("Address :"+ci.Address);
                t1.setText("Camp Name :"+ci.name);
                t3.setText("Start Date :"+ci.startdate+"        "+"End Date :"+ci.enddate);
                AddressInformer.address="Camp Name :"+ci.name+"     Address :"+ci.Address;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void seeaddress(View view)
    {
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Camp/"+DateTimePasser.datetime);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CampInfo d=dataSnapshot.getValue(CampInfo.class);
                Double lat=d.Latitute;
                Double longp=d.Longitude;

                String label = "Address to set the campaign!!";
                String uriBegin = "geo:"+lat+","+longp;
                String query = lat+","+longp+"(" + label + ")";
                String encodedQuery = Uri.encode( query  );
                String uriString = uriBegin + "?q=" + encodedQuery;
                Uri uri = Uri.parse( uriString );
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri );
                startActivity( intent );

                Toast.makeText(CampDetails.this, "Going to the address !!", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CampDetails.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
