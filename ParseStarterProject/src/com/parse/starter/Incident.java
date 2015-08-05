package com.parse.starter;

/**
 * Created by Tony on 15-07-21.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Incident")
public class Incident extends ParseObject {

    private boolean isNew = true;

    public void setOld(){
        isNew = false;
    }

    public Boolean getIfNew(){
        return isNew;
    }

    public String getName(){
        return getString("name");
    }

    public String getIncidentUpdatedAt(){
        return getString("updated_at");
    }

    public String getUrl(){
        return getString("url");
    }

    public String getStatus() {return getString("status");}

    public JSONArray getUpdates(){return getJSONArray("updates");}

    public static ParseQuery<Incident> getQuery(){
        return ParseQuery.getQuery(Incident.class);
    }
}
