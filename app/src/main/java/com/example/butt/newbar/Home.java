package com.example.butt.newbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        click=(ImageView)findViewById(R.id.start);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(Home.this, Menu_activity.class);
                startActivity(openstaringpoint);
                finish();
            }
        });
    }
}
