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
            holder.componentStatus = (TextView) view.findViewById(R.id.status);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        TextView componentName = holder.componentName;
        componentName.setText(component.getName());
        TextView componentUpdatedAt = holder.componentUpdatedAt;
        componentUpdatedAt.setText(HelperMethods.datify(component.getComponentCreatedAt()));
        TextView componentStatus = holder.componentStatus;
        String status = component.getStatus();
        componentStatus.setText(status);
        if (status.equals("operational")){
            componentStatus.setTextColor(getContext().getResources().getColor(R.color.Operational));
        }else{
            componentStatus.setTextColor(getContext().getResources().getColor(R.color.NotOperational));

        }
        
        return view;
    }

    private class ViewHolder{
        TextView componentName;
        TextView componentUpdatedAt;
        TextView componentStatus;
    }

}
