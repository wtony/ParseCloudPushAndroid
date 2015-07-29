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
    public IncidentListAdapter(LayoutInflater inflater,Context context, ParseQueryAdapter.QueryFactory<Incident> queryFactory){
        super(context,queryFactory);
        super.setObjectsPerPage(10);
    }

    @Override
    public View getItemView(Incident incident, View view, ViewGroup parent){
        ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder();

            holder.incidentName = (TextView) view.findViewById(R.id.name);
            holder.incidentUpdatedAt = (TextView) view.findViewById(R.id.updatedAt);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

            TextView incidentName = holder.incidentName;
            incidentName.setText(incident.getName());
            TextView incidentUpdatedAt = holder.incidentUpdatedAt;
            incidentUpdatedAt.setText(incident.getIncidentUpdatedAt());

        return view;
    }

    private class ViewHolder{
        TextView incidentName;
        TextView incidentUpdatedAt;
    }

}
