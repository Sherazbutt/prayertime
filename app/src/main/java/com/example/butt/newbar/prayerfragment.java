package com.example.butt.newbar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by Butt on 4/9/2017.
 */
public class prayerfragment extends Fragment {
    DataBaseHelper dbhelper;
    Cursor cursor;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    ToggleButton toggle,sunrise_toggle,dhur_toggle,asar_toggle,maghrib_toggle,isha_toggle;
    SQLiteDatabase _db;
    ImageView fajr_image;
    SharedPreferences preferences;
    String GetSQliteQuery;
    TextView datee, fajarr, shuoorrq, zoharr, asarr, magribb, ishaa, city, country, time;
    private String TAG = prayerfragment.class.getSimpleName();
    private static String url = "http://muslimsalat.com/daily.json?key=a5b2604126fcfa7ccb1ae08a0b61b023";


    public prayerfragment() {
        //constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.prayerfragment, container, false);
        fajarr = (TextView) view.findViewById(R.id.fajar);
        zoharr = (TextView) view.findViewById(R.id.zohar);
        datee = (TextView) view.findViewById(R.id.date);
        shuoorrq = (TextView) view.findViewById(R.id.Shurooq);
        asarr = (TextView) view.findViewById(R.id.asar);
        magribb = (TextView) view.findViewById(R.id.magrib);
        ishaa = (TextView) view.findViewById(R.id.isha);
        toggle=(ToggleButton)view.findViewById(R.id.patientUtilityButton);
        sunrise_toggle=(ToggleButton)view.findViewById(R.id.sunrise);
        dhur_toggle=(ToggleButton)view.findViewById(R.id.zuhr);
        asar_toggle=(ToggleButton)view.findViewById(R.id.asr);
        maghrib_toggle=(ToggleButton)view.findViewById(R.id.mgrib);
        isha_toggle=(ToggleButton)view.findViewById(R.id.isha_toggle);
        //fajr_image = (ImageView) view.findViewById(R.id.fajar_alarm);
        //  city=(TextView)container.findViewById(R.id.cityy);
        // country=(TextView)container.findViewById(R.id.countryy);
        time = (TextView) view.findViewById(R.id.time_current);
        dbhelper = new DataBaseHelper(getActivity().getApplicationContext());
        Thread myThread = null;
        Runnable myRunnableThread = new CountDownRunner();
        myThread = new Thread(myRunnableThread);
        myThread.start();
        hasConnection();
         preferences = getActivity().getPreferences(getActivity().MODE_PRIVATE);

        boolean tgpref = preferences.getBoolean("tgpref", true);  //default is true
        if (tgpref == true) //if (tgpref) may be enough, not sure
        {
            toggle.setChecked(true);
        }
        else
        {
            toggle.setChecked(false);
        }
        boolean sun = preferences.getBoolean("sun", true);  //default is true
        if (sun == true) //if (tgpref) may be enough, not sure
        {
            sunrise_toggle.setChecked(true);

        }
        else
        {
            sunrise_toggle.setChecked(false);
        }
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
        alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
      // Alarm();
        if(toggle.isChecked()){

           // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_DAY, pendingIntent);

        }
        return view;


    }
        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

        }

    public void Alarm() {
        toggle.setOnClickListener(new View.OnClickListener() {
            long time;
            boolean isPressed = false;
int counter=0;
            @Override
            public void onClick(View v) {


                int id=5;
                if(toggle.isChecked()){

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", true); // value to store
                    editor.commit();
                   //fajr_image.setImageResource(R.drawable.dua);
                    String timee = "9:49:30"; //mm:ss
                    //String val=sec.getText().toString();
                    String[] units = timee.split(":"); //will break the string up into an array
                    int hours = Integer.parseInt(units[0]);
                    int fh = hours + 12;
                    String minutes = (units[1]);//first element
                    String vv = String.valueOf(minutes);
                    String[] unittss = minutes.split(" ");
                    int fminutes = Integer.parseInt(unittss[0]);
                    int fm = fminutes + 0;
                    //sec.setText(fminutes);
                    //int seconds = Integer.parseInt(units[2]); //second element
                    Toast.makeText(getActivity(), "ALARM ON", Toast.LENGTH_SHORT).show();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, fh);
                    calendar.set(Calendar.MINUTE, fm);
                    calendar.set(Calendar.SECOND, 10);
                    Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                    if (System.currentTimeMillis() > time) {
                        Toast.makeText(getActivity(), "Moved to next day", Toast.LENGTH_SHORT).show();
                        if (calendar.AM_PM == 0)
                            time = time + (1000 * 60 * 60 * 12);
                        else
                            time = time + (1000 * 60 * 60 * 24);
                    }
                    //AlarmManager.INTERVAL_DAY
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_DAY, pendingIntent);
                }
                else
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", false); // value to store
                    editor.commit();
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
    public void fajarAlarm(){
        int id=5;
        if(id==5){
        long time;
        String timee = "9:49:30"; //mm:ss
        //String val=sec.getText().toString();
        String[] units = timee.split(":"); //will break the string up into an array
        int hours = Integer.parseInt(units[0]);
        int fh = hours + 12;
        String minutes = (units[1]);//first element
        String vv = String.valueOf(minutes);
        String[] unittss = minutes.split(" ");
        int fminutes = Integer.parseInt(unittss[0]);
        int fm = fminutes + 0;
        //sec.setText(fminutes);
        //int seconds = Integer.parseInt(units[2]); //second element
        Toast.makeText(getActivity(), "ALARM ON", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, fh);
        calendar.set(Calendar.MINUTE, fm);
        calendar.set(Calendar.SECOND, 10);
        Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
        if (System.currentTimeMillis() > time) {
            Toast.makeText(getActivity(), "Moved to next day", Toast.LENGTH_SHORT).show();
            if (calendar.AM_PM == 0)
                time = time + (1000 * 60 * 60 * 12);
            else
                time = time + (1000 * 60 * 60 * 24);
        }
        //AlarmManager.INTERVAL_DAY
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    else
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("tgpref", false); // value to store
        editor.commit();
        alarmManager.cancel(pendingIntent);
        Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
    }

    }

    public void doWork() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    // TextView txtCurrentTime= (TextView)findViewById(R.id.textView5);
                    Date dt = new Date();
                    String state = "am";
                    int value = 12;
                    int new_hours;

                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();


                    int seconds = dt.getSeconds();
                    if (hours > 12) {
                        new_hours = hours - value;
                        state = "pm";
                        String curTime = new_hours + ":" + minutes + " " + state;
                        time.setText(curTime);
                    } else {

                        String curTime = hours + ":" + minutes + ":" + state;
                        time.setText(curTime);
                    }

                } catch (Exception e) {
                }
            }
        });
    }


    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }

    }

    /**
     * Checks if the device has Internet connection.
     *
     * @return <code>true</code> if the phone is connected to the Internet.
     */
    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if ((wifiNetwork != null && wifiNetwork.isConnected())) {

            new GetContacts().execute();
            return true;
        } else if (mobileNetwork != null && mobileNetwork.isConnected()) {

            new GetContacts().execute();
            return true;
        } else if (activeNetwork != null && activeNetwork.isConnected()) {
            new GetContacts().execute();
            return true;

        } else {
            gettime();
            return false;

        }

        // return false;

    }

    public void gettime() {
        GetSQliteQuery = "SELECT * FROM details";
        _db = getActivity().openOrCreateDatabase("Prayers_time.db", Context.MODE_PRIVATE, null);

        cursor = _db.rawQuery(GetSQliteQuery, null);
        if (cursor != null && cursor.moveToFirst())
            if (cursor.moveToFirst()) {
                do {
                    GetSQLiteDatabaseRecords();
                } while (cursor.moveToNext());
            } else {
                //Toast.makeText(prayerfragment.this, "No match", Toast.LENGTH_SHORT).show();
            }

    }

    public void GetSQLiteDatabaseRecords() {
      //  city.setText(cursor.getString(1).toString());
      //  country.setText(cursor.getString(2).toString());
        fajarr.setText(cursor.getString(3).toString());
        shuoorrq.setText(cursor.getString(4).toString());
        zoharr.setText(cursor.getString(5).toString());
        asarr.setText(cursor.getString(6).toString());
        magribb.setText(cursor.getString(7).toString());
        ishaa.setText(cursor.getString(8).toString());

    }


    public class GetContacts extends AsyncTask<String, String, Wrapper> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            // pDialog = new ProgressDialog(MainActivity.this);
            // pDialog.setMessage("Please wait...");
            // pDialog.setCancelable(false);
            //pDialog.show();

        }

        @Override
        protected Wrapper doInBackground(String... params) {

            Wrapper ww = new Wrapper();
            HttpHandler sh;
            sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray items = jsonObj.getJSONArray("items");
                    //JSONObject city=jsonObj.getJSONObject("city");
                    String cityname = jsonObj.getString("city");
                    String countryname = jsonObj.getString("country");

                    ww.cityy = cityname;
                    ww.country = countryname;

                    // looping through All Contacts
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject c = items.getJSONObject(i);


                        String Date = c.getString("date_for");
                        String Fajar = c.getString("fajr");
                        String Shurooq = c.getString("shurooq");
                        String Dhuhr = c.getString("dhuhr");
                        String Asr = c.getString("asr");
                        String Magrib = c.getString("maghrib");
                        String Isha = c.getString("isha");
                        dbhelper.deletesomedata();

                        dbhelper.insertData(cityname, countryname, Fajar, Shurooq, Dhuhr, Asr, Magrib, Isha);
                        //Wrapper ww = new Wrapper();
                        ww.Date = Date;
                        ww.Fajar = Fajar;
                        ww.Shurooq = Shurooq;
                        ww.Dhuhr = Dhuhr;
                        ww.Asr = Asr;
                        ww.Magrib = Magrib;
                        ww.Isha = Isha;

                        return ww;
                        //return Magrib;

                        //Toast.makeText(MainActivity.this, Date.toString(), Toast.LENGTH_SHORT).show();

                        // tmp hash map for single contact
                       /* HashMap<String, String> time = new HashMap<>();

                        // adding each child node to HashMap key => value
                        time.put("date_for", Date);
                        time.put("fajr", Fajar);
                        time.put("shurooq", Shurooq);
                        time.put("dhuhr", Dhuhr);

                        // adding contact to contact list
                        contactList.add(time);*/
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Wrapper result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            //if (pDialog.isShowing())
            //   pDialog.dismiss();

            fajarr.setText(result.Fajar);
            zoharr.setText(result.Dhuhr);
            shuoorrq.setText(result.Shurooq);
            asarr.setText(result.Asr);
            magribb.setText(result.Magrib);
            ishaa.setText(result.Isha);

            toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (toggle.isChecked()){
                        fajarAlarm();
                    }
                    if (toggle.isChecked()==false){
                        fajralarmoff();
                    }
                }
            });

            sunrise_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sunrise_toggle.isChecked()){
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sun", true); // value to store
                        editor.commit();
                        shurooqon();
                    }
                    if (sunrise_toggle.isChecked()==false){
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sun", false); // value to store
                        editor.commit();
                        shurooqoff();
                    }
                }
            });

        }

        public void fajarAlarm(){
            int id=5;
            if(id==5){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("tgpref", true); // value to store
                editor.commit();
                long time;
                String timee="6:27 pm";
               // String timee =fajarr.getText().toString(); //mm:ss
                Toast.makeText(getActivity().getApplicationContext(), timee, Toast.LENGTH_SHORT).show();
                //String val=sec.getText().toString();
                String[] units = timee.split(":"); //will break the string up into an array
                int hours = Integer.parseInt(units[0]);
                int fh = hours + 12;
                String minutes = (units[1]);//first element
                String vv = String.valueOf(minutes);
                String[] unittss = minutes.split(" ");
                int fminutes = Integer.parseInt(unittss[0]);
                int fm = fminutes + 0;
                Toast.makeText(getActivity().getApplicationContext(), minutes, Toast.LENGTH_SHORT).show();
               // Toast.makeText(getActivity().getApplicationContext(), timee, Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getActivity(), fh, Toast.LENGTH_SHORT).show();
               // Toast.makeText(getActivity(), fm, Toast.LENGTH_SHORT).show();
                //sec.setText(fminutes);
                //int seconds = Integer.parseInt(units[2]); //second element
                Toast.makeText(getActivity(), "ALARM ON", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hours);
                calendar.set(Calendar.MINUTE, fminutes);
                calendar.set(Calendar.SECOND, 10);
                Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent_fj = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 144, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    Toast.makeText(getActivity(), "Moved to next day", Toast.LENGTH_SHORT).show();
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                //AlarmManager.INTERVAL_DAY
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time,AlarmManager.INTERVAL_DAY, pendingIntent);
            }
            else
            {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("tgpref", false); // value to store
                editor.commit();
                alarmManager.cancel(pendingIntent);
                Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
            }

        }
        public void fajralarmoff(){

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("tgpref", false); // value to store
            editor.commit();
            Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);

            PendingIntent pendingIntent_fj = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 144, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent_fj);
            Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
        }

        public void shurooqon(){
            int id=5;
            if(id==5){

                long timeee;
                String timee="6:29 pm";
              //  String timee =shuoorrq.getText().toString(); //mm:ss
                Toast.makeText(getActivity().getApplicationContext(), timee, Toast.LENGTH_SHORT).show();
                //String val=sec.getText().toString();
                String[] units = timee.split(":"); //will break the string up into an array
                int hours = Integer.parseInt(units[0]);
                int fh = hours + 12;
                String minutes = (units[1]);//first element
                String vv = String.valueOf(minutes);
                String[] unittss = minutes.split(" ");
                int fminutes = Integer.parseInt(unittss[0]);
                int fm = fminutes + 0;
                Toast.makeText(getActivity().getApplicationContext(), minutes, Toast.LENGTH_SHORT).show();
                // Toast.makeText(getActivity().getApplicationContext(), timee, Toast.LENGTH_SHORT).show();
                //  Toast.makeText(getActivity(), fh, Toast.LENGTH_SHORT).show();
                // Toast.makeText(getActivity(), fm, Toast.LENGTH_SHORT).show();
                //sec.setText(fminutes);
                //int seconds = Integer.parseInt(units[2]); //second element
                Toast.makeText(getActivity(), "ALARM ON", Toast.LENGTH_SHORT).show();
                Calendar calendarr = Calendar.getInstance();
                calendarr.set(Calendar.HOUR_OF_DAY, hours);
                calendarr.set(Calendar.MINUTE, fminutes);
                calendarr.set(Calendar.SECOND, 10);
                Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent_sh_off = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 1050, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                timeee = (calendarr.getTimeInMillis() - (calendarr.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > timeee) {
                    Toast.makeText(getActivity(), "Moved to next day", Toast.LENGTH_SHORT).show();
                    if (calendarr.AM_PM == 0)
                        timeee = timeee + (1000 * 60 * 60 * 12);
                    else
                        timeee = timeee + (1000 * 60 * 60 * 24);
                }
                //AlarmManager.INTERVAL_DAY
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeee,100000, pendingIntent_sh_off);
            }
            else
            {
                Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);

               PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 1050, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.cancel(pendingIntent);
                Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
            }

        }
        public void shurooqoff(){

            Intent intent = new Intent(getActivity().getApplicationContext(), AlarmReceiver.class);

            PendingIntent pendingIntent_sh = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 1050, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent_sh);
            Toast.makeText(getActivity(), "ALARM OFF", Toast.LENGTH_SHORT).show();
        }


    }



}

