package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        TextView name = (TextView)findViewById(R.id.componentName);
        name.setText(intent.getStringExtra("name"));

        TextView status = (TextView)findViewById(R.id.componentStatus);
        status.setText(intent.getStringExtra("status"));
//
//        TextView url = (TextView)findViewById(R.id.componentUrl);
//        String text = intent.getStringExtra("url");
//        url.setText("<a href='" + text + "'>Link</a>");
//        url.setClickable(true);
//        url.setMovementMethod(LinkMovementMethod.getInstance());

        TextView createdAt = (TextView)findViewById(R.id.componentCreatedAt);
        createdAt.setText(HelperMethods.datify(intent.getStringExtra("created_at")));

        TextView updatedAt = (TextView)findViewById(R.id.componentUpdatedAt);
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
