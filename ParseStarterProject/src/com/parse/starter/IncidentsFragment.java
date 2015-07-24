package com.parse.starter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

public class IncidentsFragment extends ListFragment {


    private ParseQueryAdapter<Incident> incidentListAdapter;

    public IncidentsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ParseQueryAdapter.QueryFactory<Incident> factory = new ParseQueryAdapter.QueryFactory<Incident>(){
            public ParseQuery<Incident> create(){
                ParseQuery<Incident> query = Incident.getQuery();
                query.orderByDescending("updated_at");
                query.fromLocalDatastore();
                return query;
            }

        };
//
//        IncidentAdapter<Incident> adapter = new IncidentAdapter<Incident>(inflater.getContext(),
//                android.R.layout.simple_list_item_1);
//        setListAdapter(adapter);

        incidentListAdapter = new IncidentListAdapter(inflater,inflater.getContext(),
                factory);
        setListAdapter(incidentListAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    private void loadFromParse() {
        ParseQuery<Incident> incidentQuery = Incident.getQuery();

        incidentQuery.findInBackground(new FindCallback<Incident>() {
            @Override
            public void done(final List<Incident> incidentList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Incident>) incidentList,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            incidentListAdapter.notifyDataSetChanged();
                                            incidentListAdapter.loadObjects();
                                            Log.d("Ayylmao", "Nayylmao");
                                        } else {
                                            Log.i("Component", "Error:(");
                                        }
                                    }
                                }
                            });
                } else {
                    Log.i("ComponentMore", "Error;-;");
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        loadFromParse();
    }
}
