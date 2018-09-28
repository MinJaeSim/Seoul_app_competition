package com.example.mj975.woder_woman.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.adpater.DangerZoneAdapter;
import com.example.mj975.woder_woman.adpater.ImageViewPageAdapter;
import com.example.mj975.woder_woman.data.Event;
import com.example.mj975.woder_woman.data.Toilet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private ArrayList<Event> events;
    private ArrayList<Toilet> toilets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ViewPager viewPager = v.findViewById(R.id.viewPager);

        Toast.makeText(getContext(), FirebaseAuth.getInstance().getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();

        String[] src = {""};

        ImageViewPageAdapter adapter = new ImageViewPageAdapter(getLayoutInflater(), src);
        viewPager.setAdapter(adapter);

        Bundle bundle = getArguments();

        if (bundle != null && bundle.getSerializable("EVENTS") != null) {
            events = (ArrayList<Event>) bundle.getSerializable("EVENTS");

            src = new String[events.size()];
            String[] href = new String[events.size()];
            for (int i = 0; i < events.size(); i++) {
                src[i] = events.get(i).getSrc();
                href[i] = events.get(i).getHref();
            }

            adapter.setResources(src);
            adapter.setOnItemClickListener(position -> {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(href[position]));
                startActivity(browserIntent);
            });
            adapter.notifyDataSetChanged();
        }

        if (bundle != null && bundle.getSerializable("NEAR") != null) {
            System.out.println("TEST");
            toilets = (ArrayList<Toilet>) bundle.getSerializable("NEAR");
            System.out.println(toilets.size());
        }


        RecyclerView dangerRecyclerView = v.findViewById(R.id.danger_view);
        RecyclerView expeditionRecyclerView = v.findViewById(R.id.expedition_view);
        RecyclerView myReportRecyclerView = v.findViewById(R.id.my_report_view);

        LinearLayoutManager layoutManagerDanger = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManagerExpedition = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManagerReport = new LinearLayoutManager(getContext());

        layoutManagerDanger.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerExpedition.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerReport.setOrientation(LinearLayoutManager.HORIZONTAL);

        dangerRecyclerView.setLayoutManager(layoutManagerDanger);
        expeditionRecyclerView.setLayoutManager(layoutManagerExpedition);
        myReportRecyclerView.setLayoutManager(layoutManagerReport);

        DangerZoneAdapter dangerZoneAdapter = new DangerZoneAdapter();
        dangerZoneAdapter.setItems(toilets);
        dangerRecyclerView.setAdapter(dangerZoneAdapter);

        TabLayout tabLayout = v.findViewById(R.id.tab_dots);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}
