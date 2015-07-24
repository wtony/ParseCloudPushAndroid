package com.parse.starter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.Parse;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ComponentsFragment extends ListFragment {

//    private ParseQueryAdapter<Component> componentAdapter
    public ComponentsFragment(){

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
//        ComponentAdapter<Component> adapter = new ComponentAdapter<Component>(inflater.getContext(),
//                android.R.layout.simple_list_item_1);
//        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
