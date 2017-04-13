package com.example.butt.newbar;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Dua_1 extends AppCompatActivity {
    TextView dua_namee,arbic_duaa,eng_dua11,eng_dua22,urdu1,urdu2;
    String stuff;
    ToggleButton tg;
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
    public MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_1);
        dua_namee = (TextView)findViewById(R.id.dua_name);
        arbic_duaa = (TextView)findViewById(R.id.arabic_dua);

        eng_dua22 = (TextView)findViewById(R.id.eng_2);
        urdu1 = (TextView)findViewById(R.id.urdu_1);
        tg=(ToggleButton)findViewById(R.id.dua_toggle);

        Bundle bundle = getIntent().getExtras();

        tg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (tg.isChecked()&&stuff.equals(names[0])) {
                    mp = MediaPlayer.create(Dua_1.this, R.raw.azan);
                    mp.start();
                    Toast.makeText(Dua_1.this, "Playing", Toast.LENGTH_SHORT).show();
                }
               else if (tg.isChecked()&&stuff.equals(names[8])) {
                    mp = MediaPlayer.create(Dua_1.this, R.raw.bait_ul_hlajanykidua);
                    mp.start();
                    Toast.makeText(Dua_1.this, "Playing", Toast.LENGTH_SHORT).show();
                }


                else {
                    mp.stop();
                   // mp.release();
                }
            }

        });
        //Extract the data…
       stuff = bundle.getString("stuff");
        if (stuff.equals(names[0])){
            dua_namee.setText(stuff);
            arbic_duaa.setText("بِسْمِ اللَّهِ وَعَلَى بَرَكَةِ اللَّهِ");
           // eng_dua11.setText("");
            eng_dua22.setText("In the name of Allah and with the blessings of Allah I begin (eating).");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
             urdu1.setText("-میں نے الله کے نام کے ساتھ اور الله کی برکت پر کھانا شروع کیا");
            //urdu2.setText("الله کی برکت پر کھانا شروع کیا");
        Toast.makeText(Dua_1.this, stuff, Toast.LENGTH_SHORT).show();
    }else if (stuff.equals(names[1])){

            dua_namee.setText(stuff);
            arbic_duaa.setText("الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنَا وَسَقَانَا وَجَعَلَنَا مِنَ الْمُسْلِمِينَ");
            eng_dua22.setText("Thanks to Allah Azzawajal who fed us and made us among Muslims");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-اللہ عزوجل کا شکر ہے جس نے ہمیں کھلایا پلایا اور مسلمانوں میں سے بنایا۔");

        }
        else if (stuff.equals(names[2])){

            dua_namee.setText(stuff);
            arbic_duaa.setText("اللَّهُمَّ بِاسْمِكَ أَمُوتُ وَأَحْيَا");
            eng_dua22.setText("O Allah (Almighty) I live and die in your name.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-الٰہی عزوجل میں تیرے نام پر مرتا ہوں اور جیتا ہوں۔");

        }
        else if (stuff.equals(names[3])){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,5,0,0);
            eng_dua22.setLayoutParams(params);
            dua_namee.setText(stuff);
            arbic_duaa.setText("الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ");
            eng_dua22.setText("All Praise onto Allah (Almighty) Who granted us life after death (Sleep) and we are return to him.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-تمام تعریفیں اللہ عزوجل کے لئے جس نے ہمیں موت (نیند) کے بعد حیات (بیداری) عطا فرمائی اور ہمیں اسی طرف لوٹنا ہے");

        }
        else if (stuff.equals(names[4])){

            dua_namee.setText(stuff);
            arbic_duaa.setText("بِسْمِ اللَّهِ وَلْحَمْدُ لِلَّهِ");
            eng_dua22.setText("Allah Azzawajal in the name of the most affectionate, the merciful.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("اللہ عزوجل کے نام سے شروع جو بڑا مہربان اور نہایت رحم والا ہے۔");

        }
        else if (stuff.equals(names[5])){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,7,0,0);
            eng_dua22.setLayoutParams(params);
            dua_namee.setText(stuff);
            arbic_duaa.setText("اللَّهُمَّ بَكَ أَصُولُ وَبِكَ أَجُولُ وَبِكَ أَسِيرُ");
            eng_dua22.setText("O Allah Almighty I attack with your help and proceed with your help and return with your help.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-یا الٰہی عزوجل میں تیری ہی مدد سے حملہ کرتا ہوں اور تیری ہی مدد سے چلتا ہوں اور تیری ہی مدد سے پھرتا ہوں");

        }
        else if (stuff.equals(names[6])){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,7,0,0);
            eng_dua22.setLayoutParams(params);
            dua_namee.setText(stuff);
            arbic_duaa.setText("إِنِّي أَسْأَلُكَ خَيْرَ الْمَوْلِجِ وَخَيْرَ الْمَخْرَجِ بِسْمِ اللَّهِ وَلِجْنَا وَعَلَى اللَّهِ رَبِّنَا تَوَكَّلْنَا اللَّهُمَّ");
            eng_dua22.setText("O Allah Almighty I seek the goodness of coming in and going out, from you. Allah Almighty in the name of we come in and Allah Almighty is the name " +
                    "of we go out and we trusted Allah Almighty Who is our Lord.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-اے اللہ عزوجل میں تجھ سے اندر آنے اور باہر جانے کی بھلائی طلب کرتا ہوں اللہ عزوجل کے نام سے باہر نکلے اور اللہ عزوجل پر جو ہمارا رب ہے ہم نے بھروسہ کیا");

        }
        else if (stuff.equals(names[7])){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,16,0,0);
            urdu1.setLayoutParams(params);

            dua_namee.setText(stuff);
            arbic_duaa.setText("بِسْمِ اللَّهِ تَوَكَّلْتُ عَلَى اللَّهِ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ");
            eng_dua22.setText("\n" +
                    "In the name of Allah Almighty (I comeout of my house) I trust Allah Almighty, there is no capability of saving oneself from" +
                    " sins and neither is there capability to do good deeds but from Allah Almighty.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("اللہ عزوجل کے نام سے (گھر سے نکلتا ہوں) میں نے اللہ عزوجل پر بھروسہ کیا اللہ عزوجل کے بغیر نہ طاقت ہے (گناہوں سے بچنےکی) اور نہ وقت ہے (نیکیاں کرنے کی)");

        }
        else if (stuff.equals(names[8])){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,3,0,0);
            eng_dua22.setLayoutParams(params);
            dua_namee.setText(stuff);
            arbic_duaa.setText("\n" +
                    "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبْثِ وَالْخَبَائِثِ");
            eng_dua22.setText("O Allah (Almighty) I seek your refuge from impure Jinnat (male and female)");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("-اے اللہ عزوجل میں ناپاک جنوں (نرو مادہ) سے تیری پناہ مانگتا ہوں۔");

        }
        else if (stuff.equals(names[9])){

            dua_namee.setText(stuff);
            arbic_duaa.setText("الْحَمْدُ لِلَّهِ الَّذِي أَذْهَبَ عَنِّي الْأَذَى وَعَافَانِي");
            eng_dua22.setText("Thanks to Allah Almighty Who removed pain from me and granted me relief.");
            urdu1.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
            urdu1.setText("اللہ عزوجل کا شکر ہے جس نے مجھ سے اذیت دور کی اور مجھے عافیت دی۔");

        }


    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.pause();
            if (isFinishing()) {
                mp.stop();
                mp.release();
            }
        }
    }



    }

