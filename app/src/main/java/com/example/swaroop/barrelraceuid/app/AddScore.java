package com.example.swaroop.barrelraceuid.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddScore extends ActionBarActivity {


    //oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    //oncreate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.savecancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_cancel: {
                Intent intent = new Intent(this, Game.class);
                startActivity(intent);
            }
            break;
            case R.id.action_save: {
                save();
                Intent intent = new Intent(this, Game.class);
                startActivity(intent);
            }
            break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    //save - saving the contact in different add and edit mode
    public void save(){
        //Get the user input from the edit text field
        EditText editTextFirstName = (EditText) findViewById(R.id.firstName);

        //Typecast it to string type for usage in side file and data handler
        String l_strFirstName = editTextFirstName.getText().toString();

        //Check if the first name is filled else finish
        if (l_strFirstName.isEmpty()) {
            Log.e("AddContact Activity", "First Name Empty");
            Toast.makeText(getApplicationContext(), "Unnamed - Score discarded!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        //Create the form fill object so that it can updated with the latest contact
        HighScoreDetails l_objHighScoreDetails = new HighScoreDetails();
        l_objHighScoreDetails.setName(l_strFirstName);
        l_objHighScoreDetails.setScore(Game.finalTimer);
        l_objHighScoreDetails.setScoreMillis(Game.lFinalTimeinMillis);

        //Update the data in the data handler
        MyActivity.m_objDataHandler.addScore(l_objHighScoreDetails);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_score, container, false);
            ((TextView) rootView.findViewById(R.id.scoreView)).setText("Race Time :: " + Game.finalTimer);
            return rootView;
        }
    }
}
