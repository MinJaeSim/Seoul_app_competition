package com.example.mj975.woder_woman.fragment;

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

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.adpater.ImageViewPageAdapter;

public class MainFragment extends Fragment {

    private static final String URL = "http://www.seoul.go.kr/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ViewPager viewPager = v.findViewById(R.id.viewPager);

        ImageViewPageAdapter adapter = new ImageViewPageAdapter(getLayoutInflater(), new String[]{"", "", ""});
        viewPager.setAdapter(adapter);

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


        TabLayout tabLayout = v.findViewById(R.id.tab_dots);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}
