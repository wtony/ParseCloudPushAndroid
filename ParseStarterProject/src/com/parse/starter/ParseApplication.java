package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends android.app.Application {

  @Override
  public void onCreate() {
    super.onCreate();

    Log.d("NOO", "no 8(");

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here

    ParseObject.registerSubclass(Component.class);
    ParseObject.registerSubclass(Incident.class);

    Parse.initialize(this, "H4rSxAvHsYiMGb5E0PAZTVGbtM2c8vz2IbWFw1Uj", "aj3Fv8vrfSJOIr5uxkcY0LIKLIZqj3voMyOjbpBx");
    ParseInstallation.getCurrentInstallation().saveInBackground();

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
