package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

public class IncidentsFragment extends Fragment {


    private ParseQueryAdapter<Incident> incidentListAdapter;
    private SwipeRefreshLayout swipeContainer;

    public IncidentsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_incidents, container, false);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_incident);


        ListView list_view = (ListView) view.findViewById(R.id.incidents_list);

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
        list_view.setAdapter(incidentListAdapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFromParse();

            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Incident item = incidentListAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), IncidentActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("url", item.getUrl());
                intent.putExtra("updated_at", item.getIncidentUpdatedAt());
                startActivity(intent);
            }
        });

        return view;
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
                                            if (swipeContainer != null) {
                                                swipeContainer.setRefreshing(false);
                                            }
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
