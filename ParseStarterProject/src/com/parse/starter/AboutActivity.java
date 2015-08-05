package com.parse.starter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView aboutText;
    TextView emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        aboutText = (TextView) findViewById(R.id.aboutText);
        aboutText.setText("This System Status App provides an overview of current service status messages and scheduled maintenance for major University of Toronto systems and services."+
                            " You may contact the Joint Operations Group (JOG). \n" +
        "The JOG is staffed Monday to Friday, 7am-9pm.\n\n" + "This App has pull to refresh functions" +
                " as well as push notifications when statuses change for the respective Components and Incidents.");

        emailText = (TextView)findViewById(R.id.emailText);

        emailText.setText(Html.fromHtml("<a href='mailto:jog.help@utoronto.ca'>Email Here</a>"));
        emailText.setClickable(true);
        emailText.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
