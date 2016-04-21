package cr.ac.itcr.gpstrackerapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mendez Soto on 4/20/2016.
 */
public class GPSTracking extends Service implements LocationListener {
    private final Context context;

    //Booleans for verificatinos
    private boolean gpsEnabled = false;
    private boolean networkEnabled = false;
    private boolean getLocation = false;

    //Latitude and longitude variables
    private double latitude;
    private double longitude;

    //Our location variable
    private Location myLocation;

    // The minimum distance to change Updates in meters
    private static final long DISTANCE_BEFORE_CHANGES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long TIME_BETWEEN_UPDATES = 1000 * 60 * 1; // 1 minute

    protected LocationManager locationManager;

    public GPSTracking(Context cont) {
        this.context = cont;
        getLocation();
    }

    //Returns the location if possible, else asks for enabling the gps
    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!gpsEnabled && !networkEnabled) {
                getLocation = false;
                Toast.makeText(context, "Cant access to the location right now", Toast.LENGTH_LONG);
            } else {
                getLocation = true;
                //After API 23 you have to check for the permission everytime you are going to use it
                if (ActivityCompat.checkSelfPermission((TrackingActivity)context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //Request the permision
                    showSettingsAlert();
                }
                //1000 * 60 * 1 = 1 second which is minimum time for updates
                if (networkEnabled) {//Network is enambled
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, TIME_BETWEEN_UPDATES, DISTANCE_BEFORE_CHANGES, this);
                    if (locationManager != null) {
                        myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (myLocation != null) {
                            latitude = myLocation.getLatitude();
                            longitude = myLocation.getLongitude();
                        }
                    }
                }
                if(gpsEnabled){
                    if (myLocation == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,TIME_BETWEEN_UPDATES,DISTANCE_BEFORE_CHANGES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (myLocation != null) {
                                latitude = myLocation.getLatitude();
                                longitude = myLocation.getLongitude();
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return myLocation;
    }



        //Get the latitude
        public double getLatitude(){
            if(myLocation != null){
                latitude = myLocation.getLatitude();
            }
            // return latitude
            return latitude;
        }

        //Get longitude
        public double getLongitude(){
            if(myLocation != null){
                longitude = myLocation.getLongitude();
            }

            // return longitude
            return longitude;
        }

    public boolean canGetLocation() {
        return this.getLocation;
    }

    //Alert that settings have not yet been enabled
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("GPS settings");

        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                //Action Location Source Setting tells the user the activity location to be called
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    @Override
    public void onLocationChanged(Location location) {
        //Update my location due to changes
        TrackingActivity.onChange(location);
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
