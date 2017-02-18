package com.example.kennethlopez.eventbussample;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class Fragment1 extends Fragment {

    private EditText mUsername;
    private ProgressDialog progressDialog;
    private TextView mName;
    private TextView mLocation;

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
        View view = inflater.inflate(R.layout.layout_fragment1, container, false);
        mUsername = (EditText) view.findViewById(R.id.username);
        mName = (TextView) view.findViewById(R.id.name);
        mLocation = (TextView) view.findViewById(R.id.location);

        Button button = (Button) view.findViewById(R.id.fetch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(getString(R.string.processing_text));
                progressDialog.show();
                new FetchUserJob(mUsername.getText().toString()).execute();
            }
        });
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(UserEvent event) {
        progressDialog.dismiss();

        User user = event.getUser();
        mName.setText(user.getName());
        mLocation.setText(user.getLocation());
    }
}