package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.widget.Toolbar;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ParseStarterProjectActivity extends AppCompatActivity {
	/** Called when the activity is first created. */
	Toolbar toolbar;

		ViewPager pager;
		ViewPagerAdapter pagerAdapter;
		SlidingTabLayout tabs;
		String titles[] = {"AYYLMAO"};
		int numTabs = 1;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		pagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), titles, numTabs);

		// Assigning viewPager view and setting adapter
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);

		// Assigning the Sliding Tab Layout View
		tabs = (SlidingTabLayout) findViewById(R.id.tabs);
		tabs.setDistributeEvenly(true); // Distribute the tab width evenly

		// Setting Custom Color for the Scroll bar indicator of the Tab View
		tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.TabScrollColor);
			}
		});

		// Setting the ViewPager For the SlidingTabsLayout
		tabs.setViewPager(pager);



		ParseAnalytics.trackAppOpenedInBackground(getIntent());

		ParseQuery<Component> queryComponent = ParseQuery.getQuery("Component");
		ParseQuery<Incident> queryIncident = ParseQuery.getQuery("Incident");

		queryComponent.findInBackground(new FindCallback<Component>() {
			@Override
			public void done(List<Component> componentList, ParseException e) {
				if (e == null){
					Log.d("AYYLMAO", componentList.toString());

				}
			}
		});

		queryIncident.findInBackground(new FindCallback<Incident>() {
			@Override
			public void done(List<Incident> incidentList, ParseException e) {
				if (e == null){
					Log.d("AYYLMAO", incidentList.toString());
				}
			}
		});
	}
}
