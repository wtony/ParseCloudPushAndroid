package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class ParseStarterProjectActivity extends AppCompatActivity {
	/** Called when the activity is first created. */
	Toolbar toolbar;

		ViewPager pager;
		ViewPagerAdapter pagerAdapter;
		SlidingTabLayout tabs;
		String titles[] = {"Components", "Incidents"};
		int numTabs = 2;



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

		//loadFromParse();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		if (id==R.id.info){
			startActivity(new Intent(this, AboutActivity.class ));
		}

		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onResume(){
		super.onResume();

	}
}
