package com.example.swaroop.barrelraceuid.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class    HighScores extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.high_scores, menu);
        return true;
    }

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private HighScoreAdapter mHighScoreAdapter;
        public PlaceholderFragment() {

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.v("MyActivity","Creating View");
            //Initialize the data handler to read the contact list from the file

            View rootView = inflater.inflate(R.layout.high_scores, container, false);


            //Get the contact list
            List<HighScoreDetails> l_lstobjHighScoreDetails;
            List<HighScoreDetails> l_lstobjHighScoreDetailsToBeShown;
            l_lstobjHighScoreDetails = new ArrayList<HighScoreDetails>();
            l_lstobjHighScoreDetailsToBeShown = new ArrayList<HighScoreDetails>();
            l_lstobjHighScoreDetails = MyActivity.m_objDataHandler.readScoreList();

            //String to fill the list model
            String l_strData = null;

            //Local objec to get the vlue
            HighScoreDetails l_objHighScoreDetails;

            List<String> lststrContactList = new ArrayList<String>();

            int iHighScoreList;
            if(l_lstobjHighScoreDetails.size() > 10){
                System.out.println("HighScore :: Size of List(>10) :: " + l_lstobjHighScoreDetails.size());
                iHighScoreList = 10;
            }
            else
            {
                System.out.println("HighScore :: Size of List(<=10) :: " + l_lstobjHighScoreDetails.size());
                iHighScoreList = l_lstobjHighScoreDetails.size();
            }
            System.out.println("HighScore :: List Size to be shown :: " + iHighScoreList);

            //Iterate the list of form field to get the name and phone number
            for(int l_iPos = 0 ; l_iPos < iHighScoreList; l_iPos++ ){

                //Get the object from the list
                l_objHighScoreDetails = l_lstobjHighScoreDetails.get(l_iPos);
                l_lstobjHighScoreDetailsToBeShown.add(l_objHighScoreDetails);

                //check if the object is null
                if(null == l_objHighScoreDetails){
                    Log.v("MyActivity", "Form Field object in the member is null");
                }

                //Construct the string data Name and Phone Number
                l_strData = l_objHighScoreDetails.getName()
                        + "\t\t" +
                        l_objHighScoreDetails.getScore();

                Log.v("MyActivity",l_strData);

                lststrContactList.add(l_strData);
                //set the data as null for next iteration or exit
                l_strData = null;
                l_objHighScoreDetails = null;
            }

            //Initialize the Array Adapter
            /*mContactListAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.contact_list,
                    R.id.contact_list_textview,
                    lststrContactList);*/

            mHighScoreAdapter = new HighScoreAdapter(getActivity(),
                    R.layout.highscorelist,
                    l_lstobjHighScoreDetailsToBeShown);

            //Get a reference to the list view and set the adapter to it.
            ListView listView = (ListView) rootView.findViewById(R.id.listViewHighScoreList);
            //listView.setAdapter(mContactListAdapter);
            listView.setAdapter(mHighScoreAdapter);

            return rootView;
        }
    }
}
