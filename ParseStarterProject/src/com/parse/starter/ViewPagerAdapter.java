package com.parse.starter;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.SimpleFormatter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by Alda on 2015-06-11.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    String titles[];
    int numTabs;
    ParseStarterProjectActivity context;

    Fragment componentsFragment, incidentsFragment;

    public ViewPagerAdapter(Context context, FragmentManager fm, String mTitles[], int mNumTabs) {
        super(fm);
        this.titles = mTitles;
        this.numTabs = mNumTabs;
        this.context = (ParseStarterProjectActivity) context;
    }

    @Override
    public Fragment getItem(int position) {
        // Switching between fragments
        if (position == 0){
            componentsFragment = Fragment.instantiate(this.context, "com.parse.starter.BlankFragment");
            return componentsFragment;

//        }else if (position == 1) {
//            incidentsFragment = Fragment.instantiate(this.context, "com.utoronto.techknowfile.android.FavouritesEventList");
//            return incidentsFragment;
        }else{
            return null;
        }
    }
//    public void updateTitles(){
//        if (eventsFragment != null){
//            ((EventList)eventsFragment).updateTitles();
//        }
//        if (favouritesFragment != null){
//            ((FavouritesEventList)favouritesFragment).updateTitles();
//        }
//    }
    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public String getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return this.numTabs;
    }


}
