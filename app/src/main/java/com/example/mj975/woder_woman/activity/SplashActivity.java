package com.example.mj975.woder_woman.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.mj975.woder_woman.data.Event;
import com.example.mj975.woder_woman.data.Toilet;
import com.example.mj975.woder_woman.fragment.ReportFragment;
import com.example.mj975.woder_woman.service.DatabaseClient;
import com.example.mj975.woder_woman.util.GPSUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private Intent i;

    private float longitude;
    private float latitude;

    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = (float) location.getLongitude(); //경도
            latitude = (float) location.getLatitude();   //위도
            System.out.println("TEST");
            new BackgroundSplashTask().execute();
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = new Intent(SplashActivity.this, MainActivity.class);
        GPSUtil.ENABLE_GPS_INFO(this, locationListener);
    }

    private class BackgroundSplashTask extends AsyncTask<Void, Void, Void> {

        private ArrayList<Event> events;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                events = DatabaseClient.getInstance().getAllEvents().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            new NearToiletTask().execute();

            i.putExtra("EVENTS", events);
        }
    }

    private class NearToiletTask extends AsyncTask<Void, Void, Void> {
        private ArrayList<Toilet> nearToilets;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                // 37.4495053 lng=127.129636
                System.out.println(latitude);
                System.out.println(longitude);
                nearToilets = DatabaseClient.getInstance().getNearToilets(latitude, longitude).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            i.putExtra("NEAR", nearToilets);
            GPSUtil.DISABLE_GPS(locationListener);
            startActivity(i);
            finish();
        }
    }

}
