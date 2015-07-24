package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;

/**
 * Created by Tony on 15-07-24.
 */
public class IncidentListAdapter extends ParseQueryAdapter<Incident> {
    public IncidentListAdapter(Context context, ParseQueryAdapter.QueryFactory<Incident> queryFactory){
        super(context,queryFactory);
    }

    @Override
    public View getItemView(Incident incident, View view, ViewGroup parent){
        ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // view = inflater.inflate(R.layo)
            holder = new ViewHolder();

            //setViewstuffs
        }else{
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private class ViewHolder{
        TextView incidentName;
    }

}
