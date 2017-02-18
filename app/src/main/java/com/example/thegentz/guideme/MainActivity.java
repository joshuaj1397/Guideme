package com.example.thegentz.guideme;
import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu.OnMenuItemClickListener;


class MyCalendar {
    public String name;
    public String id;
    public MyCalendar(String _name, String _id) {
        name = _name;
        id = _id;
    }
    @Override
    public String toString() {
        return name;
    }
}

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);

        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Instantiating PopupMenu class */
                PopupMenu popup = new PopupMenu(getBaseContext(), v);

                /** Adding menu items to the popumenu */
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());

                /** Defining menu item click listener for the popup menu */
                popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_event:
                                if (false) {
                                    startCalendarList();
                                }
                                else {
                                    startEvents();
                                }
                                return true;
                            case R.id.menu_map:
                                // start a map activity
                                return true;
                            case R.id.menu_about:
                                startAbout();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                /** Showing the popup menu */
                popup.show();

            }
        };

        btn.setOnClickListener(listener);
    }

    private MyCalendar m_calendars[];
    private void getCalendars() {

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void startAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void startEvents() {
        Intent intent = new Intent(MainActivity.this, EventsActivity.class);
        startActivity(intent);
    }
    public void startCalendarList() {
        Intent intent = new Intent(MainActivity.this, CalendarListActivity.class);
        startActivity(intent);
    }
}

