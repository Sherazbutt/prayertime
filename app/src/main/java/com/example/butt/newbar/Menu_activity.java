package com.example.butt.newbar;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Menu_activity extends AppCompatActivity {
    TextView time,datee;
    private ListView listView;
    private String names[] = {
            "Prayer Time",
            "Qibla Direction",
            "Masnoon Dua"
    };


    private Integer imageid[] = {
            R.drawable.prayer_main,
            R.drawable.qibla_main,
            R.drawable.dua_main,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);

        CustomList customList = new CustomList(this, names, imageid);
        time = (TextView) findViewById(R.id.time_main);
        datee = (TextView) findViewById(R.id.date_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(customList);


        SimpleDateFormat format = new SimpleDateFormat("d");
        String date = format.format(new Date());

        if (date.endsWith("1") && !date.endsWith("11"))
            format = new SimpleDateFormat("EE MMM d'st', yyyy");
        else if (date.endsWith("2") && !date.endsWith("12"))
            format = new SimpleDateFormat("EE MMM d'nd', yyyy");
        else if (date.endsWith("3") && !date.endsWith("13"))
            format = new SimpleDateFormat("EE MMM d'rd', yyyy");
        else
            format = new SimpleDateFormat("EE MMM d'th', yyyy");
        String yourDate = format.format(new Date());
        datee.setText(yourDate);


        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat dateee = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
        dateee.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));

        String localTime = dateee.format(currentLocalTime);
        time.setText(localTime);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"You Clicked "+names[i],Toast.LENGTH_SHORT).show();
                String cities = String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(Menu_activity.this, cities, Toast.LENGTH_SHORT).show();
                Fragment fragment=null;
                if (i==0) {
                    Intent openstaringpoint = new Intent(Menu_activity.this, MainActivity.class);
                    startActivity(openstaringpoint);
                    // fragment= new prayerfragment();
                }//getSupportFragmentManager().beginTransaction().replace(R.id.root_view,fragment).commit();

                if (i==2) {

                    // fragment= new prayerfragment();
                }
                    }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}