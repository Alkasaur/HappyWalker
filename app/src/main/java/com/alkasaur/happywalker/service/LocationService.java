package com.alkasaur.happywalker.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

/**
 * TODO: add class header comment!
 */
public class LocationService extends Service implements LocationListener {
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(GPS_PROVIDER, 0, 100, this);
        } else {
            locationManager.requestLocationUpdates(NETWORK_PROVIDER, 0, 100, this);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Finished!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, String.format("lat:%1$.5f ; %2$.5f", location.getLatitude(), location.getLongitude()), Toast.LENGTH_LONG).show();;
    }

    @Override
    public void onStatusChanged(String provider, int i, Bundle bundle) {
        Toast.makeText(this, String.format("%1$s status changed", provider), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, String.format("%1$s enabled", provider), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, String.format("%1$s disabled", provider), Toast.LENGTH_LONG).show();
    }
}
