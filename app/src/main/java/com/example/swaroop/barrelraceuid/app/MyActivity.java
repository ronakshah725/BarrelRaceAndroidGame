package com.example.swaroop.barrelraceuid.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.IOException;

import android.R.color;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyActivity extends ActionBarActivity implements OnClickListener {

    private ImageButton highscore;
    private ImageButton settingsBtn;
    public static DataHandler m_objDataHandler;
    private ImageButton startBtn;
    public static boolean bGameSound = true;
    public static boolean bGameFarm = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        m_objDataHandler = new DataHandler();
        m_objDataHandler.initDataHandler();

        startBtn = (ImageButton) findViewById(R.id.start_button);
        // startBtn.setBackgroundDrawable(R.drawable.ic_start_button);
        startBtn.setOnClickListener(this);

        highscore = (ImageButton) findViewById(R.id.highscore);
        highscore.setOnClickListener(this);

        settingsBtn = (ImageButton) findViewById(R.id.settings_button);
        settingsBtn.setOnClickListener(this);
        Dialog dialog = new Dialog(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout);

        try {
            Drawable d = Drawable.createFromStream(
                    getAssets().open("background.png"), null);
            layout.setBackgroundColor(color.holo_blue_bright);
            layout.setBackgroundDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.barrel_race_home, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.start_button:
                Intent intent = new Intent(this, Game.class);
                startActivity(intent);
                break;

            case R.id.highscore:
                if(MyActivity.m_objDataHandler.getListSize() == 0){
                    Toast.makeText(getApplicationContext(), "No Scores Available!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intentHighScore = new Intent(this, HighScores.class);
                startActivity(intentHighScore);
                break;

            case R.id.settings_button:
                Intent intentSettings = new Intent(this, Settings.class);
                startActivity(intentSettings);
                break;
        }

    }
}
