package com.example.sobhana.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
               startlistening();

               // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
    }
    public void startlistening()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==(PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }
    public void updateLocationInfo(Location location) {
        Log.i("location info", location.toString());
        //TextView hike=(TextView)findViewById(R.id.watchview);
        TextView lat = (TextView) findViewById(R.id.latview);
        TextView lon = (TextView) findViewById(R.id.longview);
        TextView alt = (TextView) findViewById(R.id.altitudeview);
        TextView acc = (TextView) findViewById(R.id.accuracyview);



        lat.setText("latitude: " + location.getLatitude());
        lon.setText("longitude: " + location.getLongitude());
        alt.setText("Altitude: " + location.getAltitude());
        acc.setText("Accuracy:" + location.getAccuracy());
       //address.setText("Address"+location.getAddress());
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            String address="could not find address";
            List<Address> listadresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (listadresses != null && listadresses.size() > 0) {
                Log.i("placeInfo", listadresses.get(0).toString());
                address=" \n Address: \n";
                if(listadresses.get(0).getSubThoroughfare()!=null)
                {
                    address+=listadresses.get(0).getSubThoroughfare();
                }
                if(listadresses.get(0).getThoroughfare()!=null)
                {
                    address+=listadresses.get(0).getThoroughfare()+"\n";
                }
                if(listadresses.get(0).getLocality()!=null)
                {
                    address+=listadresses.get(0).getLocality()+"\n";
                }
                if(listadresses.get(0).getPostalCode()!=null)
                {
                    address+=listadresses.get(0).getPostalCode()+"\n";
                }
                if(listadresses.get(0).getCountryName()!=null)
                {
                    address+=listadresses.get(0).getCountryName()+"\n";
                }
                if(listadresses.get(0).getCountryCode()!=null)
                {
                    address+=listadresses.get(0).getCountryCode()+"\n";
                }
                TextView addressTextview=(TextView)findViewById(R.id.addressview);
                addressTextview.setText(address);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
         locationListener=new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                updateLocationInfo(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if(Build.VERSION.SDK_INT<23)
        {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
        }
        else
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else {
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    updateLocationInfo(location);
                }
            }
        }
        }
    }
