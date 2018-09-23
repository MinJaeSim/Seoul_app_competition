package com.example.mj975.woder_woman.adpater;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mj975.woder_woman.R;

public class ImageViewPageAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private String[] resources;

    public ImageViewPageAdapter(LayoutInflater layoutInflater, String[] strings) {
        this.inflater = layoutInflater;
        this.resources = strings;
    }

    @Override
    public int getCount() {
        return resources.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.viewpager_image_view, null);
        ImageView imageView = view.findViewById(R.id.viewpager_image_view);

        Glide.with(imageView)
                .load(R.drawable.ic_home)
                .apply(new RequestOptions()
                        .centerCrop()
                )
                .into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
