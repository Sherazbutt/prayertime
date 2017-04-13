package com.example.butt.newbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
//import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import android.support.v4.app.Fragment;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener()  {
            //prayerfragment fragmentt=null;
            Fragment fragment=null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_calls) {
                    fragment= new prayerfragment();

                } else if (tabId == R.id.tab_groups) {
                    // The tab with id R.id.tab_groups was selected,
                    // change your content accordingly.

                    fragment= new qiblafragment();
                } else if (tabId == R.id.tab_chats) {
                    // The tab with id R.id.tab_chats was selected,
                    fragment= new duafragment();
                    //textView.setText("Your Chats");
                }getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,fragment).commit();
            }
        });
    }

    }


