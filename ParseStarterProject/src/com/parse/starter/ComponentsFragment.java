package com.parse.starter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

public class ComponentsFragment extends ListFragment {

//    private ParseQueryAdapter<Component> componentAdapter

    private ParseQueryAdapter<Component> componentListAdapter;
    public ComponentsFragment(){
        loadFromParse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ParseQueryAdapter.QueryFactory<Component> factory = new ParseQueryAdapter.QueryFactory<Component>(){
            public ParseQuery<Component> create(){
                ParseQuery<Component> query = Component.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }

        };

//
        componentListAdapter = new ComponentListAdapter(inflater,inflater.getContext(),
                factory);
        setListAdapter(componentListAdapter);


        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void loadFromParse() {
        ParseQuery<Component> componentQuery = Component.getQuery();
        ParseQuery<Incident> incidentQuery = Incident.getQuery();

        componentQuery.findInBackground(new FindCallback<Component>() {
            @Override
            public void done(final List<Component> componentList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Component>) componentList,
                            new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            componentListAdapter.notifyDataSetChanged();
                                            Log.d("Ayylmao" , "Nayylmao");
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
}
