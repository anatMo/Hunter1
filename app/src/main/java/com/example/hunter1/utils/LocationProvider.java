package com.example.hunter1.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationProvider {

    private Context context;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    private LocationProvider(Context context) {
        this.context = context;
    }

    private static LocationProvider me;

    public static LocationProvider getMe() {
        return me;
    }

//    public LocationProvider setCallBack_gpsLocationReturned(CallBack_GPSLocationReturned callBack_gpsLocationReturned) {
//        this.callBack_gpsLocationReturned = callBack_gpsLocationReturned;
//        return this;
//    }

    public static LocationProvider initHelper(Context context) {
        if (me == null) {
            me = new LocationProvider(context);
        }
        return me;
    }

    public void fetchLastLocation(Activity activity) {
        Log.d("location","HI1");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            Log.d("location","HI2");
            return;
        }
        Log.d("location","HI3");
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.d("location","HI4");
                if(location != null){
                    Log.d("location","HI5");
                    currentLocation = location;
                    Toast.makeText(context, currentLocation.getLatitude()+""+currentLocation.getLongitude(), Toast .LENGTH_SHORT).show();
                }
            }
        });
    }

}
