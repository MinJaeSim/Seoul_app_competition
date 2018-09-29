package com.example.mj975.woder_woman.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.data.SafeHouse;
import com.example.mj975.woder_woman.service.DatabaseClient;

import java.io.IOException;
import java.util.List;

public class ServiceScoutFragment extends Fragment {

    private List<SafeHouse> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_scout, container, false);

        return v;
    }

    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                list = DatabaseClient.getInstance().getAllSafeHouse().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
