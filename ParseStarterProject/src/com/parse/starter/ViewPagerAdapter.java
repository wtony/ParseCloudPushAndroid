package com.parse.starter;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

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
            componentsFragment = Fragment.instantiate(this.context, "com.parse.starter.ComponentsFragment");
            return componentsFragment;

        }else if (position == 1) {
            incidentsFragment = Fragment.instantiate(this.context, "com.parse.starter.IncidentsFragment");
            return incidentsFragment;
        }else{
            return null;
        }
    }
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
