package com.example.butt.newbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Butt on 4/9/2017.
 */
public class duafragment extends android.support.v4.app.Fragment {
    private ListView listView;
    private String names[] = {
            "Dua Before Meals",
            "Dua After Eating",
            "Dua Before Sleeping",
            "Dua When Waking Up from Sleep",
            "Dua Before Wudhu (Ablution)",
            "Dua For Starting a Journey",
            "Dua When Entering The Home",
            "Dua When Leaving The House",
            "Dua Before Entering Toilet",
            "Dua After Leaving Toilet"


    };
    public  duafragment(){
        //constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.dua_fragment,container,false);
        prayer_customlist d_customList = new prayer_customlist(getActivity(),names);

        listView = (ListView)view. findViewById(R.id.listView_dua);
        listView.setAdapter(d_customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
                String cities = String.valueOf(adapterView.getItemAtPosition(i));
                //Toast.makeText(getActivity().this, cities, Toast.LENGTH_SHORT).show();


                    Intent ii= new Intent(getActivity(), Dua_1.class);
                    Bundle bundle = new Bundle();
                    //Add your data to bundle
                    bundle.putString("stuff", cities);
                    //Add the bundle to the intent
                    ii.putExtras(bundle);
                    //Fire that second activity
                    startActivity(ii);
                    // fragment= new prayerfragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.root_view,fragment).commit();


            }
        });


        return  view;
    }
}
