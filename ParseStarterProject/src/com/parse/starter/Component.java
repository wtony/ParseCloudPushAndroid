package com.parse.starter;

/**
 * Created by Tony on 15-07-21.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Component")
public class Component extends ParseObject {

    public String getName(){
        return getString("name");
    }

    public String getStatus(){
        return getString("status");
    }

    public String getComponentCreatedAt(){
        return getString("created_at");
    }

    public static ParseQuery<Component> getQuery(){
        return ParseQuery.getQuery(Component.class);
    }
}
