package com.example.sobhana.memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.sobhana.memorableplaces.MainActivity.arrayAdapter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0&&grantResults[0]== getPackageManager().PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {

                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
                // Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location lastknownLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapOnLocation(lastknownLocation,"your location");


            }
        }
    }

    public  void centerMapOnLocation(Location location, String title)
    {
        LatLng userLocation=new LatLng(location.getLatitude(),location.getLongitude());
       mMap.clear();
        if(title!="your location") {
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,10));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        // Add a marker in Sydney and move the camera

   //     mMap.addMarker(new MarkerOptions().position(Constants.POSITION));
     //   mMap.moveCamera(CameraUpdateFactory.newLatLng());
        Intent intent=getIntent();
        if(intent.getIntExtra(Constants.POSITION,0)==0)
        {
           locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            locationListener=new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centerMapOnLocation(location,"your location");


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

                    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                   // Location lastknownLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    centerMapOnLocation(location,"your location");


                }

                else
                {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        }else
        {
            Location placeLocation=new Location(LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(MainActivity.locations.get(intent.getIntExtra(Constants.POSITION,0)).latitude);
            placeLocation.setLongitude(MainActivity.locations.get(intent.getIntExtra(Constants.POSITION,0)).longitude);
            centerMapOnLocation(placeLocation,MainActivity.places.get(intent.getIntExtra(Constants.POSITION,0)));
            

        }

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        mMap.addMarker(new MarkerOptions().position(latLng).title("your new memorable place"));
        String address = "";
        try {

            List<Address> listadresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (listadresses != null && listadresses.size() > 0) {
                Log.i("placeInfo", listadresses.get(0).toString());
                //address = " \n Address: \n";
                if (listadresses.get(0).getSubThoroughfare() != null) {
                    address += listadresses.get(0).getSubThoroughfare();
                }
                if (listadresses.get(0).getThoroughfare() != null) {
                    address += listadresses.get(0).getThoroughfare() + "\n";
                }
                if (listadresses.get(0).getLocality() != null) {
                    address += listadresses.get(0).getLocality() + "\n";
                }
                if (listadresses.get(0).getPostalCode() != null) {
                    address += listadresses.get(0).getPostalCode() + "\n";
                }
                if (listadresses.get(0).getCountryName() != null) {
                    address += listadresses.get(0).getCountryName() + "\n";
                }
                if (listadresses.get(0).getCountryCode() != null) {
                    address += listadresses.get(0).getCountryCode() + "\n";
                }
                //TextView addressTextview=(TextView)findViewById(R.id.addressview);
                //addressTextview.setText(address);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (address == "") {
            SimpleDateFormat sdf = new SimpleDateFormat("mm:HH  yyyy:MM:dd");
            address = sdf.format(new Date());
        }


        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
        MainActivity.places.add(address);
        MainActivity.locations.add(latLng);
        MainActivity.arrayAdapter.notifyDataSetChanged();
      //MainActivity.arrayAdapter.
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.sobhana.memorableplaces",Context.MODE_PRIVATE);
        try {
            ArrayList<String> latitudes=new ArrayList<>();
            ArrayList<String> longitudes=new ArrayList<>();
            for(LatLng Coordinates:MainActivity.locations)
            {
                latitudes.add(Double.toString(Coordinates.latitude));
                longitudes.add(Double.toString(Coordinates.longitude));
            }
            sharedPreferences.edit().putString("places",objectSerializer.serialize(MainActivity.places)).apply();
           // sharedPreferences.edit().putString("locations",objectSerializer.serialize(MainActivity.locations)).apply();

            sharedPreferences.edit().putString("latitudes",objectSerializer.serialize(latitudes)).apply();
            sharedPreferences.edit().putString("longitudes",objectSerializer.serialize(longitudes)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"location saved",Toast.LENGTH_SHORT).show();



    }}