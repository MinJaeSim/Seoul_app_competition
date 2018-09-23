package com.example.mj975.woder_woman.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
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
