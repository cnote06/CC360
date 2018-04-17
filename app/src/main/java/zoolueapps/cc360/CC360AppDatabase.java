package zoolueapps.cc360;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by curtis lee smith on 9/12/2017.
 *
 * Basic database class for the CC360 Contacts App
 *
 * Only AppProvider should use this class is {@Link AppProvider}.
 *
 */

class CC360AppDatabase extends SQLiteOpenHelper {
    private static final String TAG = "CC360AppDatabase";

    private static final String DATABASE_NAME = "CC360.db";
    private static final String TABLE_NAME = "Contacts";

    private static final String FIELD_NAME = "fullName";
    private static final String FIELD_MOBILE_NUMBER = "mobilePhoneNumber";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_CONFIRMATION_TEXT = "confirmationText";
    private static final String FIELD_TOPIC_OF_DISCUSSION = "topicOfDiscussion";
    private static final String FIELD_REMINDER = "reminder";
    private static final String FIELD_OTHER_PHONE = "otherPhone";
    private static final String FIELD_OTHER_NAME = "otherName";
    private static final String FIELD_HOME_ADDRESS = "homeAddress";
    private static final String FIELD_BUSINESS_NAME = "businessName";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_BUSINESS_ADDRESS = "businessAddress";
    private static final String FIELD_EMAIL1 = "email1";
    private static final String FIELD_EMAIL2 = "emeil2";
    private static final String FIELD_BIRTHDAY = "birthday";
    private static final String FIELD_NOTES = "notes";
    private static final int DATABASE_VERSION = 1;

    //Implement CC360AppDatabase as a singleton prevents instances of the class from being created
    private static CC360AppDatabase instance = null;

    private CC360AppDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "CC360AppDatabase: constructor");
    }

    /**
     * Get an instance of the app's singleton database helper object
     * @param context the content providers context.
     * @return a SQLite database helper object
     */
    static CC360AppDatabase getInstance(Context context){
        if(instance==null){
            Log.d(TAG, "getInstance: creating new instance");
            instance = new CC360AppDatabase(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: starts");
        String sSQL; //Use a string variable to facilitate logging

        sSQL = "CREATE TABLE" + PeopleContract.TABLE_NAME + " ("
                + PeopleContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + PeopleContract.Columns.FIELD_NAME + " TEXT, "
                + PeopleContract.Columns.FIELD_MOBILE_NUMBER + " TEXT, "
                + PeopleContract.Columns.FIELD_LOCATION + " TEXT, "
                + PeopleContract.Columns.FIELD_TIME + " TEXT, "
                + PeopleContract.Columns.FIELD_DATE + " TEXT "
                + PeopleContract.Columns.FIELD_CONFIRMATION_TEXT + " TEXT "
                + PeopleContract.Columns.FIELD_TOPIC_OF_DISCUSSION + " TEXT "
                + PeopleContract.Columns.FIELD_REMINDER + " TEXT "
                + PeopleContract.Columns.FIELD_OTHER_PHONE + " TEXT "
                + PeopleContract.Columns.FIELD_OTHER_NAME + " TEXT "
                + PeopleContract.Columns.FIELD_HOME_ADDRESS + " TEXT "
                + PeopleContract.Columns.FIELD_BUSINESS_NAME + " TEXT "
                + PeopleContract.Columns.FIELD_TITLE + " TEXT "
                + PeopleContract.Columns.FIELD_BUSINESS_ADDRESS + " TEXT "
                + PeopleContract.Columns.FIELD_EMAIL1 + " TEXT "
                + PeopleContract.Columns.FIELD_EMAIL2 + " TEXT "
                + PeopleContract.Columns.FIELD_BIRTHDAY + " TEXT "
                + PeopleContract.Columns.FIELD_NOTES + " TEXT);";
        Log.d(TAG, sSQL);
        db.execSQL(sSQL);

        Log.d(TAG, "onCreate: ends");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: starts");
        switch(oldVersion) {
            case 1:
                //upgrade from version 1
                break;
            default:
                throw new IllegalStateException( "onUpgrade() with unknown newVersion");
        }
        Log.d(TAG, "onUpgrade: ends");
    }
}
