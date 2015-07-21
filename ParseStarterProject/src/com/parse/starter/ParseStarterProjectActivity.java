package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ParseStarterProjectActivity extends ActionBarActivity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());

		ParseQuery<ParseObject> queryComponent = ParseQuery.getQuery("Component");
		ParseQuery<ParseObject> queryIncident = ParseQuery.getQuery("Incident");

		queryComponent.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> componentList, ParseException e) {
				if (e == null){
					Log.d("AYYLMAO", componentList.toString());

				}
			}
		});
	}
}
