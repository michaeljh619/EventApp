package com.eventappucsd.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EnterEventInfoActivity extends FragmentActivity {
    private final String LOG_TAG = EnterEventInfoActivity.class.getSimpleName();
    private EditText editTextDescription, editTextLocation, editTextTime, editTextEventName,
            editTextDate;
    private Button mButton;
    private ContentResolver mContentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_event_info);

        editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        mContentResolver = EnterEventInfoActivity.this.getContentResolver();

        mButton = (Button) findViewById(R.id.addRecord);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    ContentValues values = new ContentValues();
                    values.put(EventsContract.EventsColumns.EVENTS_NAME, editTextEventName.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_DESCRIPTION, editTextDescription.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_LOCATION, editTextLocation.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_DATE, editTextTime.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_TIME, editTextDate.getText().toString());
                    values.put(EventsContract.EventsColumns.EVENTS_NUM_VOTES, "0");
                    Uri returned = mContentResolver.insert(EventsContract.URI_TABLE, values);
                    Log.d(LOG_TAG,"record id returned is " + returned.toString() );
                    Intent intent = new Intent(EnterEventInfoActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter some valid event data.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        private boolean isValid(){
        if(editTextEventName.getText().length() == 0 ||
                editTextDescription.getText().length() ==0 ||
                editTextLocation.getText().toString().length() == 0 ||
                editTextTime.getText().toString().length() == 0 ||
                editTextDate.getText().toString().length() == 0){
            return false;
        }else
            return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(0, intent);
        finish();
    }
}
/**
 * Called when the user clicks the Add Event button.
 * Eventually, this will add the event to the database and check for duplicates.
 * Right now, it just creates the event object.
 * @param view The view that was clicked
 */
//    public void addEventToDatabase(View view) {
//
//        Event currentEvent = new Event();
//
//        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
//        EditText editTextLocation = (EditText) findViewById(R.id.editTextLocation);
//        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
//        EditText editTextEventName = (EditText) findViewById(R.id.editTextEventName);
//        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
//
//        currentEvent.setEventName(editTextEventName.getText().toString());
//        currentEvent.setDescription(editTextDescription.getText().toString());

//        Date date = new Date(editTextDate.getText().toString());
//
//        currentEvent.setDate(date);
//        currentEvent.setLocation(editTextLocation.getText().toString());
//        currentEvent.setTime(editTextTime.getText().toString());
//
//        FakeDB.add(currentEvent);
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("event", currentEvent.getEventName()      + "\n" +
//                                 "@ " + currentEvent.getTime()    + " on " +
//                                 currentEvent.getDate() + "\n" + "Place: " +
//                                 currentEvent.getLocation());
//        setResult(1, intent);
//        finish();
//    }