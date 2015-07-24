package com.parse.starter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class IncidentsFragment extends ListFragment {
    

    public IncidentsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ParseQueryAdapter.QueryFactory<Incident> factory = new ParseQueryAdapter.QueryFactory<Incident>(){
            public ParseQuery<Incident> create(){
                ParseQuery<Incident> query = Incident.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }

        };
//
//        IncidentAdapter<Incident> adapter = new IncidentAdapter<Incident>(inflater.getContext(),
//                android.R.layout.simple_list_item_1);
//        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
