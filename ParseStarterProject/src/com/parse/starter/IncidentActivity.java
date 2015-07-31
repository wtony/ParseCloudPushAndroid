package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


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

        TextView status = (TextView)findViewById(R.id.incidentStatus);
        status.setText(intent.getStringExtra("status"));

        TextView url = (TextView)findViewById(R.id.incidentUrl);
        url.setClickable(true);
        url.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='" + intent.getStringExtra("url") + "'>Link</a>";
        url.setText(Html.fromHtml(text));

    //    TextView createdAt = (TextView)findViewById(R.id.incidentCreatedAt);
       // createdAt.setText(HelperMethods.datify(intent.getStringExtra("created_at")));

        TextView updatedAt = (TextView)findViewById(R.id.incidentUpdatedAt);
        updatedAt.setText(HelperMethods.datify(intent.getStringExtra("updated_at")));



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
