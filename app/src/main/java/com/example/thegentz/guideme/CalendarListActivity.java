package com.example.thegentz.guideme;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.HashSet;
import java.util.Set;


public class CalendarListActivity extends AppCompatActivity {
    public class CalendarContentResolver {
        public final String[] FIELDS = {
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
        };

        public final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");

        ContentResolver contentResolver;
        Set<String> calendars = new HashSet<String>();

        public  CalendarContentResolver(Context ctx) {
            contentResolver = ctx.getContentResolver();
        }

        public Set<String> getCalendars() {
            // Fetch a list of all calendars sync'd with the device and their display names
            Cursor cursor = contentResolver.query(CALENDAR_URI, FIELDS, null, null, null);
            try {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(0);
                        String displayName = cursor.getString(1);
                        // This is actually a better pattern:
                        String color = cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_COLOR));
                        Boolean selected = !cursor.getString(3).equals("0");
                        calendars.add(displayName);
                    }
                }
            } catch (AssertionError ex) {}

            return calendars;
        }
    }
}
