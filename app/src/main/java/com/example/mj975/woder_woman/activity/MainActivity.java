package com.example.mj975.woder_woman.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.fragment.CleanMapFragment;
import com.example.mj975.woder_woman.fragment.MainFragment;
import com.example.mj975.woder_woman.fragment.PersonFragment;
import com.example.mj975.woder_woman.fragment.ReportFragment;
import com.example.mj975.woder_woman.fragment.ServiceFragment;

public class MainActivity extends BaseActivity {

    private FragmentManager fm;
    private BottomNavigationView bottomNavigation;
    private long pressedTime;
    private Fragment fragment;

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            Toast.makeText(getBaseContext(), "위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
                    + "\n고도 : " + altitude + "\n정확도 : " + accuracy, Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        if (f == null) {
            f = new MainFragment();
            fm.beginTransaction().add(R.id.fragment_container, f).commit();
        }

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new MainFragment();
                        break;
                    case R.id.action_clean:
                        fragment = new CleanMapFragment();
                        break;
                    case R.id.action_report:
                        fragment = new ReportFragment();
                        break;
                    case R.id.action_relieved:
                        fragment = new ServiceFragment();
                        break;
                    case R.id.action_person:
                        fragment = new PersonFragment();
                        break;
                }

                fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });

//        if (Build.VERSION.SDK_INT >= 23 &&
//                ContextCompat.checkSelfPermission(
//                        this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(
//                        this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//        }

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);

//        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
            return;
        }
        if (pressedTime == 0) {
            Toast.makeText(MainActivity.this, getString(R.string.string_close_warning), Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if (seconds > 2000) {
                Toast.makeText(MainActivity.this, getString(R.string.string_close_warning), Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
            }
        }
    }
}
