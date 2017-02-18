package com.example.kennethlopez.eventbussample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    public static final int POSITION_TAB1 = 0;
    public static final int POSITION_TAB2 = 1;

    private TabLayout mTabLayout;
    private TabLayout.Tab mTab1;
    private TabLayout.Tab mTab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mTab1 = mTabLayout.newTab();
        mTab2 = mTabLayout.newTab();
        mTab1.setText("Tab 1");
        mTab2.setText("Tab 2");

        mTabLayout.addTab(mTab1);
        mTabLayout.addTab(mTab2);

        mTabLayout.addOnTabSelectedListener(this);

        changeFragment(new Fragment1());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            default:
            case POSITION_TAB1:
                changeFragment(new Fragment1());
                break;
            case POSITION_TAB2:
                changeFragment(new Fragment2());
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}