package zoolueapps.cc360;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

 //   CC360AppDatabase appDatabase = CC360AppDatabase.getInstance(this);
 //   final SQLiteDatabase db = appDatabase.getReadableDatabase();

 //   FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
 //   fab.setOnClickListener(new View.OnClickListener()



        public void onClick (View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] projection = { PeopleContract.Columns._ID,
                                PeopleContract.Columns.FIELD_NAME,
                                PeopleContract.Columns.FIELD_MOBILE_NUMBER,
                                PeopleContract.Columns.FIELD_LOCATION,
                                PeopleContract.Columns.FIELD_TIME,
                                PeopleContract.Columns.FIELD_DATE,
                                PeopleContract.Columns.FIELD_CONFIRMATION_TEXT,
                                PeopleContract.Columns.FIELD_TOPIC_OF_DISCUSSION,
                                PeopleContract.Columns.FIELD_REMINDER,
                                PeopleContract.Columns.FIELD_OTHER_PHONE,
                                PeopleContract.Columns.FIELD_HOME_ADDRESS,
                                PeopleContract.Columns.FIELD_BUSINESS_NAME,
                                PeopleContract.Columns.FIELD_TITLE,
                                PeopleContract.Columns.FIELD_BUSINESS_ADDRESS,
                                PeopleContract.Columns.FIELD_EMAIL1,
                                PeopleContract.Columns.FIELD_EMAIL2,
                                PeopleContract.Columns.FIELD_BIRTHDAY,
                                PeopleContract.Columns.FIELD_NOTES};

        ContentResolver contentResolver = getContentResolver();

        ContentValues values = new ContentValues();
        values.put(PeopleContract.Columns.FIELD_NAME, "New Name 1");
        values.put(PeopleContract.Columns.FIELD_MOBILE_NUMBER, "New Number 1");
        values.put(PeopleContract.Columns.FIELD_LOCATION, "New Location 1");
        values.put(PeopleContract.Columns.FIELD_TIME, "New Time 1");
        values.put(PeopleContract.Columns.FIELD_DATE, "New Field Date 1");
        values.put(PeopleContract.Columns.FIELD_CONFIRMATION_TEXT, "New Confirmation 1");
        values.put(PeopleContract.Columns.FIELD_TOPIC_OF_DISCUSSION, "New Discussion 1");
        values.put(PeopleContract.Columns.FIELD_REMINDER, "New Reminder 1");
        values.put(PeopleContract.Columns.FIELD_OTHER_PHONE, "New Phone 1");
        values.put(PeopleContract.Columns.FIELD_HOME_ADDRESS, "New Phone 2");
        values.put(PeopleContract.Columns.FIELD_BUSINESS_NAME, "New Business 1");
        values.put(PeopleContract.Columns.FIELD_TITLE, "New Title 1");
        values.put(PeopleContract.Columns.FIELD_BUSINESS_ADDRESS, "New BusinessAddress 1");
        values.put(PeopleContract.Columns.FIELD_EMAIL1, "New Email 1");
        values.put(PeopleContract.Columns.FIELD_EMAIL2, "New Email 2");
        values.put(PeopleContract.Columns.FIELD_BIRTHDAY, "New Birthday 1");
        values.put(PeopleContract.Columns.FIELD_NOTES, "New Note 1");
     //  Uri uri = contentResolver.insert(PeopleContract.CONTENT_URI, values);

        Cursor cursor = contentResolver.query(PeopleContract.CONTENT_URI,
                projection,
                null,
                null,
                PeopleContract.Columns.FIELD_NAME);

        if(cursor != null) {
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while(cursor.moveToNext()) {
                for(int i=0; i<cursor.getColumnCount(); i++) {
                    Log.d(TAG, "oncreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate; ===================");
            }
            cursor.close();
        }

    }}


