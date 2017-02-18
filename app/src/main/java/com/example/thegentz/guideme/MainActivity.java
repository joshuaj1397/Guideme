package com.example.thegentz.guideme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;
import android.os.Build;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

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
    private MyCalendar m_calendars[];
    private void getCalendars() {
        String[] l_projection = new String[]{"_id", "displayName"};
        Uri l_calendars;
        if (Build.VERSION.SDK_INT >= 8 ) {
            l_calendars = Uri.parse("content://com.android.calendar/calendars");
        } else {
            l_calendars = Uri.parse("content://calendar/calendars");
        }
        Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, null, null, null);    //all calendars
        //Cursor l_managedCursor = this.managedQuery(l_calendars, l_projection, "selected=1", null, null);   //active calendars
        if (l_managedCursor.moveToFirst()) {
            m_calendars = new MyCalendar[l_managedCursor.getCount()];
            String l_calName;
            String l_calId;
            int l_cnt = 0;
            int l_nameCol = l_managedCursor.getColumnIndex(l_projection[1]);
            int l_idCol = l_managedCursor.getColumnIndex(l_projection[0]);
            do {
                l_calName = l_managedCursor.getString(l_nameCol);
                l_calId = l_managedCursor.getString(l_idCol);
                m_calendars[l_cnt] = new MyCalendar(l_calName, l_calId);
                ++l_cnt;
            } while (l_managedCursor.moveToNext());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_event:
                getCalendars();
                if (m_calendars.length() > 1) {
                    // start a menu view activity
                }
                else {
                    // start a event view activity
                }
                return true;
            case R.id.menu_map:
                // start a map activity
                return true;
            case R.id.menu_about:
                // start an about page activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
