package com.parse.starter;

/**
 * Created by Tony on 15-07-21.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Incident")
public class Incident extends ParseObject {

    public String getName(){
        return getString("name");
    }

    public String getIncidentUpdatedAt(){
        return getString("updated_at");
    }

    public String getUrl(){
        return getString("url");
    }

    public static ParseQuery<Incident> getQuery(){
        return ParseQuery.getQuery(Incident.class);
    }
}
