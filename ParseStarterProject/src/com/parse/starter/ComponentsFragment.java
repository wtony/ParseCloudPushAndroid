package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

public class ComponentsFragment extends Fragment {

//    private ParseQueryAdapter<Component> componentAdapter

    private ParseQueryAdapter<Component> componentListAdapter;
    private SwipeRefreshLayout swipeContainer;
    public ComponentsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_components, container, false);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipe_component);

        ListView list_view = (ListView) view.findViewById(R.id.components_list);

        ParseQueryAdapter.QueryFactory<Component> factory = new ParseQueryAdapter.QueryFactory<Component>(){
            public ParseQuery<Component> create(){
                ParseQuery<Component> query = Component.getQuery();
                query.orderByDescending("created_at");
                query.fromLocalDatastore();
                return query;
            }

        };

//
        componentListAdapter = new ComponentListAdapter(inflater,inflater.getContext(),
                factory);
       // setListAdapter(componentListAdapter);
        list_view.setAdapter(componentListAdapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFromParse();

            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;

    }

    private void loadFromParse() {
        ParseQuery<Component> componentQuery = Component.getQuery();

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
                                            componentListAdapter.loadObjects();
                                            if(swipeContainer!=null){
                                                swipeContainer.setRefreshing(false);
                                            }
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Component item = componentListAdapter.getItem(position);

        Intent intent = new Intent(getActivity(), ComponentActivity.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("status", item.getStatus());
        //intent.putExtra("url", item.getUrl());
        intent.putExtra("created_at", item.getComponentCreatedAt());
        intent.putExtra("updated_at", item.getComponentUpdatedAt());
        startActivity(intent);
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadFromParse();
    }
}
