package com.alkasaur.happywalker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alkasaur.happywalker.adapters.WalkAdapter;
import com.alkasaur.happywalker.model.Entry;
import com.alkasaur.happywalker.service.LocationService;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private WalkAdapter adapter;
    private FloatingActionButton addBtn;
    private boolean isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        addTestEntries();
    }

    private void addTestEntries() {
        for (int i = 0; i < 10; i++) {
            Entry e = new Entry();
            e.setTime(System.currentTimeMillis());
            e.setDistanceM(i + 1000);
            adapter.add(e);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initUI() {
        listView = (ListView) findViewById(android.R.id.list);
        listView.setEmptyView(findViewById(android.R.id.empty));
        adapter = new WalkAdapter(this);
        listView.setAdapter(adapter);
        addBtn = (FloatingActionButton) findViewById(R.id.add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isActive) {
                    Intent locationServiceIntent = new Intent(MainActivity.this, LocationService.class);
                    startService(locationServiceIntent);
                    isActive = true;
                    addBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    Intent locationServiceIntent = new Intent(MainActivity.this, LocationService.class);
                    stopService(locationServiceIntent);
                    isActive = false;
                }
            }
        });
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

        return super.onOptionsItemSelected(item);
    }
}
