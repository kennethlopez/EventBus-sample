package com.example.kennethlopez.eventbussample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class Fragment2 extends Fragment {

    private TextView mName;
    private ImageView mImage;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment2, container, false);
        mName = (TextView) view.findViewById(R.id.name);
        mImage = (ImageView) view.findViewById(R.id.photo);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(UserEvent event) {
        User user = event.getUser();

        Picasso.with(getContext())
                .load(user.avatarUrl)
                .fit()
                .into(mImage);

        mName.setText(user.getName());

        EventBus.getDefault().removeStickyEvent(User.class);
    }
}