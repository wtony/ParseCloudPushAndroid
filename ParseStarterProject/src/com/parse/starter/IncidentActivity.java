package com.parse.starter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class IncidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Intent intent = getIntent();

        TextView name = (TextView)findViewById(R.id.incidentName);
        name.setText(intent.getStringExtra("name"));

        TextView url = (TextView)findViewById(R.id.incidentUrl);
        url.setClickable(true);
        url.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + intent.getStringExtra("url") + "'>Link</a>";
        url.setText(Html.fromHtml(text));

        TextView updatedAt = (TextView)findViewById(R.id.incidentUpdatedAt);
        updatedAt.setText(HelperMethods.datify(intent.getStringExtra("updated_at")));

        LinearLayout layout = (LinearLayout) findViewById(R.id.incidentLayout);
        Bundle bundle = intent.getExtras();
        try {
            JSONArray updates = new JSONArray(bundle.getString("updates"));
            for (int i = 0; i < updates.length(); i++){

                TextView status = new TextView(this);
                String statusText = updates.getJSONObject(i).getString("status");
                if (statusText.equals("in_progress")){
                    statusText = "in progress";
                }
                status.setText(statusText);
                status.setTextSize(20);
                status.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                layout.addView(status);

                TextView body = new TextView(getApplicationContext());
                body.setText(updates.getJSONObject(i).getString("body"));
                body.setTextColor(Color.BLACK);
                body.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(body);

                TextView time = new TextView(getApplicationContext());
                String timeText = updates.getJSONObject(i).getString("updated_at");
                body.setTextColor(Color.BLACK);
                time.setText(HelperMethods.datify(timeText));

                time.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(time);
            }
        } catch (JSONException e ){
            e.printStackTrace();
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_component, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
