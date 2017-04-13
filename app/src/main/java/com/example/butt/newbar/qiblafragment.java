package com.example.butt.newbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Butt on 4/9/2017.
 */
public class qiblafragment extends Fragment {

    public  qiblafragment(){
        //constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.qibla_fragment,container,false);
        return  view;
    }
}
