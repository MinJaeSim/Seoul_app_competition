package com.example.mj975.woder_woman.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mj975.woder_woman.R;
import com.example.mj975.woder_woman.adpater.ImageViewPageAdapter;

public class MainFragment extends Fragment {

    private static final String URL = "http://www.seoul.go.kr/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);

        ViewPager viewPager = v.findViewById(R.id.viewPager);

        ImageViewPageAdapter adapter = new ImageViewPageAdapter(getLayoutInflater(), new String[]{"","",""});
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = v.findViewById(R.id.tab_dots);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

}
