package com.zombocom.fiend.calendarquery3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.ID;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long currentTimeMillis = System.currentTimeMillis();


       // Toast.makeText(getBaseContext(), ("Inside quiet zone! Great!"), Toast.LENGTH_LONG).show();



        //In order: Create an event, view an event, and view event by ID







        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2018, 3, 23, 1, 01);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2018, 3, 23, 23, 59);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, "Test Event2")
                .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
        startActivity(intent);





        long startMillis = currentTimeMillis;
        /*


        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(builder.build());
        startActivity(intent);
        */


        //This creates a cursor for going through the Instances calendar, which stores event IDs
        Uri.Builder eventsUriBuilder = CalendarContract.Instances.CONTENT_URI
                .buildUpon();
        //Start millis is the key here. We should track events that are happening at current time (startmillis)
        //and check for a certain amount of time in the future. Say, 15 mins, or maybe 30.
        ContentUris.appendId(eventsUriBuilder, startMillis);
        ContentUris.appendId(eventsUriBuilder, startMillis+100000000);
        Uri eventsUri = eventsUriBuilder.build();
        Cursor cursor = null;
        try{
            cursor = this.getContentResolver().query(eventsUri, INSTANCE_PROJECTION, null, null, CalendarContract.Instances.DTSTART + " ASC");
            // Use the cursor to step through the returned records
            while (cursor.moveToNext())  {
                String title = null;
                long eventID = 0;
                long beginVal = 0;

                // Get the field values
                eventID = cursor.getLong(PROJECTION_ID_INDEX);
                beginVal = cursor.getLong(PROJECTION_BEGIN_INDEX);
                title = cursor.getString(PROJECTION_TITLE_INDEX);

                // Do something with the values.
                Log.i(DEBUG_TAG, "Event:  " + title);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(beginVal);
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Toast.makeText(getBaseContext(), Long.toString(eventID), Toast.LENGTH_LONG).show();
                Log.i(DEBUG_TAG, "Date: " + formatter.format(calendar.getTime()));
            }



        }
        catch (SecurityException e) {
        Toast.makeText(getBaseContext(), "Security Exception, permissions are not found: " + e, Toast.LENGTH_LONG).show();
         } catch (java.lang.NullPointerException e) {
        Toast.makeText(getBaseContext(), "Null Pointer Exception", Toast.LENGTH_LONG).show();
         }








        /*
        while (cursor.moveToNext()!= false && cursor.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cursor.getLong(PROJECTION_ID_INDEX);
            displayName = cursor.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cursor.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cursor.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            Toast.makeText(getBaseContext(), Long.toString(calID), Toast.LENGTH_LONG).show();

            // Do something with the values...


        }
        */





       /*
        //Store event IDs from the Instance_Projection,
        while (cursor.moveToNext()) {
            String title = null;
            long eventID = 0;
            long beginVal = 0;

            // Get the field values
            eventID = cursor.getLong(PROJECTION_ID_INDEX);
            beginVal = cursor.getLong(PROJECTION_BEGIN_INDEX);
            title = cursor.getString(PROJECTION_TITLE_INDEX);

            // Do something with the values.
            Log.i(DEBUG_TAG, "Event:  " + title);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(beginVal);
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Toast.makeText(getBaseContext(), Long.toString(eventID), Toast.LENGTH_LONG).show();
            Log.i(DEBUG_TAG, "Date: " + formatter.format(calendar.getTime()));
        }


        */






        //Print out an event with ID eventID, if it exists


        /*
        long eventID = 303;
        Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(uri);
        startActivity(intent);
        */





        /*
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[] {"hera@example.com", "com.example",
                "hera@example.com"};
        */



        /*
        final int callbackId = 42;

       // checkPermissions(callbackId, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);

        final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 42;

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALENDAR)
                != PERMISSION_GRANTED) {
            // Permission is not granted
        }



        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALENDAR)
                != PERMISSION_GRANTED) {

// Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CALENDAR)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CALENDAR);

                // MY_PERMISSIONS_REQUEST_READ_CALENDAR is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{

            //uri = cur.insert(CalendarContract.Events.CONTENT_URI,values);

        }


        */




        // Run query


// Submit the query and get a Cursor object back.

        /*
        try {
            //cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(), "Security Exception, permissions are not found: " + e, Toast.LENGTH_LONG).show();
        } catch (java.lang.NullPointerException e) {
            Toast.makeText(getBaseContext(), "Null Pointer Exception", Toast.LENGTH_LONG).show();
        }
        */


        /*
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;
        */


            /*
            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            // Do something with the values...
            */








            //Apparently these work now??

        //Requesting permissions for Read Calendar

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }


        int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 0;
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CALENDAR)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CALENDAR);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }






        //Requesting Permissions for Write Calendar

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }


        int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 0;
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_CALENDAR)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_CALENDAR},
                        MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }







        /*
        // Run query
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"hera@example.com", "com.example",
                "hera@example.com"};
// Submit the query and get a Cursor object back.


        try {

            cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(), "Security Exception, permissions are not found: " + e, Toast.LENGTH_LONG).show();
        } catch (java.lang.NullPointerException e) {
            Toast.makeText(getBaseContext(), "Null Pointer Exception", Toast.LENGTH_LONG).show();
        }



    */
    }





    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    // The indices for the projection array above.
    // private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;





    private static final String DEBUG_TAG = "MyActivity";
    public static final String[] INSTANCE_PROJECTION = new String[] {
            CalendarContract.Instances.EVENT_ID,      // 0
            CalendarContract.Instances.BEGIN,         // 1
            CalendarContract.Instances.TITLE          // 2
    };
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_BEGIN_INDEX = 1;
    private static final int PROJECTION_TITLE_INDEX = 2;








}

