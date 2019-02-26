package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays of images for the onboard slides
    int[] slide_images = {
            R.drawable.onboard_1,
            R.drawable.onboard_2,
            R.drawable.onboard_3
    };

    int[] slide_headings = {
            R.string.onboard1Header,
            R.string.onboard2Header,
            R.string.onboard3Header
    };

    int[] slide_description = {
            R.string.onboard1Body,
            R.string.onboard2Body,
            R.string.onboard3Body
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboard_slider, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        TextView viewHeader = (TextView) view.findViewById(R.id.textViewHeader);
        TextView viewBody = (TextView) view.findViewById(R.id.textViewBody);

        imageView.setImageResource(slide_images[position]);
        viewHeader.setText(slide_headings[position]);
        viewBody.setText(slide_description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
