package com.example.swaroop.barrelraceuid.app;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by swaroop on 11/3/2014.
 */

public class HighScoreAdapter extends ArrayAdapter {
    private final Context context;
    private final List<HighScoreDetails> m_lstobjValues;

    public HighScoreAdapter(Context context,int resource,List<HighScoreDetails> p_lstobjValues) {
        super(context, resource, p_lstobjValues);
        this.context = context;
        //this.m_lstobjValues = new ArrayList<FormFields>();
        this.m_lstobjValues = p_lstobjValues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.highscorelist, parent, false);
        TextView nametextView = (TextView) rowView.findViewById(R.id.NameListView);
        TextView phonetextView = (TextView) rowView.findViewById(R.id.ScoreListView);



        String l_strName = m_lstobjValues.get(position).getName();
        nametextView.setText(l_strName);
        phonetextView.setText(m_lstobjValues.get(position).getScore());

        return rowView;
    }
}