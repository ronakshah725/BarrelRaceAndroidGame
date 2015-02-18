package com.example.swaroop.barrelraceuid.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Settings extends ActionBarActivity {

    protected static final String SETTINGS = null;
    static int ball_color = Color.BLUE;
    static int barrel_color = Color.RED;
    static int field_color = Color.WHITE;

    static int game_bg = 1;

    static final int[] color_items_values= new int[]{Color.BLUE,Color.RED,Color.BLACK};
    static final int[] field_color_items_values= new int[]{Color.WHITE,Color.GRAY,Color.YELLOW};
    static final int[] barrel_color_items_values= new int[]{Color.RED,Color.BLUE,Color.BLACK};
    static final int[] levels= new int[]{8,10,20,50};


    static final int[] game_bg_items_values = new int[]{1,0};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Setting file to store selected values
        SharedPreferences settings = getSharedPreferences(SETTINGS, 0);




        //Game BG Menu
        final Spinner game_bg_dd = (Spinner) findViewById(R.id.game_bg_settings);
        String[] game_bg_setting = new String[]{"Green","Yellow"};
        ArrayAdapter<String> adapterbg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,game_bg_setting);
        game_bg_dd.setAdapter(adapterbg);
        game_bg_dd.setSelection(settings.getInt("game_bg", 0));

       /* //Ball color selection drop down
        final Spinner ball_color_dd = (Spinner) findViewById(R.id.ball_color_spinner);
        String[] color_items = new String[]{"Blue","Red","Black"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,color_items);
        ball_color_dd.setAdapter(adapter);
        ball_color_dd.setSelection(settings.getInt("ball_color", 0));

        //Barrel color selection drop down
        final Spinner barrel_color_dd = (Spinner) findViewById(R.id.barrel_color_spinner);
        String[] barrel_color_items = new String[]{"Red","Blue","Black"};
        ArrayAdapter<String> barrel_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,barrel_color_items);
        barrel_color_dd.setAdapter(barrel_adapter);
        barrel_color_dd.setSelection(settings.getInt("barrel_color", 0));

        //Field/Background color selection drop down
        final Spinner field_color_dd = (Spinner) findViewById(R.id.field_color_spinner);
        String[] field_color_items = new String[]{"White","Gray","Yellow"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,field_color_items);
        field_color_dd.setAdapter(adapter1);
        field_color_dd.setSelection(settings.getInt("field_color", 0));

        //Difficulty level selection drop down
        final Spinner level_dd = (Spinner) findViewById(R.id.level_spinner);
        String[] level = new String[]{"Normal","Medium","Hard","Crazy"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,level);
        level_dd.setAdapter(adapter2);*/


        Button okBtn = (Button) findViewById(R.id.ok_button);
        okBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View V) {


                /*ball_color = color_items_values[ball_color_dd.getSelectedItemPosition()];
                barrel_color = barrel_color_items_values[barrel_color_dd.getSelectedItemPosition()];
                field_color = field_color_items_values[field_color_dd.getSelectedItemPosition()];
                */



                game_bg = game_bg_items_values[game_bg_dd.getSelectedItemPosition()];

                if(game_bg == 0)
                {
                    MyActivity.bGameFarm = false;
                }
                else
                {
                    MyActivity.bGameFarm = true;
                }

                //Store selected value in Settings file so that it can be used while next time loading the app
                SharedPreferences settings = getSharedPreferences(SETTINGS, 0);
                SharedPreferences.Editor editor = settings.edit();

                /*editor.putInt("ball_color", ball_color_dd.getSelectedItemPosition());
                editor.putInt("barrel_color", barrel_color_dd.getSelectedItemPosition());
                editor.putInt("field_color", field_color_dd.getSelectedItemPosition());*/

                editor.putInt("game_bg", game_bg_dd.getSelectedItemPosition());


                // Commit the edits!
                editor.commit();
                finish();
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.cancel_button);
        cancelBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View V) {
                finish();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }




    public void onClick(View v) {

    }
}