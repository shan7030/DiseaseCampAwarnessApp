package com.example.shantanu.odishaayushman;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class RequestInfo {
    public String Address;
    public Double Latitude;
    public Double Longitude;
    public String Disease;
    public String uid;
    public String checked;

    RequestInfo()
    {
            this.checked="No";
    }
}
public class AddRequest extends AppCompatActivity {

    TextView t1,t2;
    FirebaseAuth firebaseAuth;
    int PLACE_PICKER_REQUEST = 1;
    LatLng latlong;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        firebaseAuth=FirebaseAuth.getInstance();
        t1=(TextView)findViewById(R.id.address);
        t1.setVisibility(View.GONE);

    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AddRequest.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }
            case R.id.profileMenu:
                startActivity(new Intent(AddRequest.this, ProfileActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openmap(View view)
    {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try
        {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);

        }
        catch(GooglePlayServicesRepairableException e)
        {
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {





        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);

                latlong= place.getLatLng();
                address= (String) place.getAddress();
                t1.setText("Address Selected :"  +place.getAddress());
                t1.setVisibility(View.VISIBLE);
            }

        }









    }

    public void submitrequest(View view)
    {
            RequestInfo ri=new RequestInfo();
            ri.Address= address;
            ri.Disease=((EditText)findViewById(R.id.campname)).getText().toString();
            ri.Latitude=latlong.latitude;
            ri.Longitude=latlong.longitude;
            ri.uid=firebaseAuth.getUid();
            ri.checked="No";
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Request");
        databaseReference.child(formattedDate).setValue(ri);
        Toast.makeText(AddRequest.this, "Successfully Added Request!", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,SecondActivity.class);
        startActivity(i);
    }
}
