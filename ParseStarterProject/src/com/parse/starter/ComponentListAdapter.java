package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Tony on 15-07-24.
 */
public class ComponentListAdapter extends ParseQueryAdapter<Component> {

    LayoutInflater inflater;
    public ComponentListAdapter(LayoutInflater inflater, Context context, ParseQueryAdapter.QueryFactory<Component> queryFactory){
        super(context,queryFactory);
        this.inflater = inflater;
    }

    @Override
    public View getItemView(Component component, View view, ViewGroup parent){
        ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.componentName = (TextView) view.findViewById(R.id.name);
            holder.componentUpdatedAt = (TextView) view.findViewById(R.id.updatedAt);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        TextView componentName = holder.componentName;
        componentName.setText(component.getName());
        TextView componentUpdatedAt = holder.componentUpdatedAt;
        componentUpdatedAt.setText(component.getComponentCreatedAt());
        
        return view;
    }

    private class ViewHolder{
        TextView componentName;
        TextView componentUpdatedAt;
    }

}
