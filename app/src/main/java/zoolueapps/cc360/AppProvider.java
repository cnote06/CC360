package zoolueapps.cc360;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by curti on 9/21/2017. Test for github. Test II
 * Provider for the CC360 app. This is the only class that knows about {@link CC360AppDatabase}
 */

public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";

    private CC360AppDatabase mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final String CONTENT_AUTHORITY = "zoolueapps.CC360.provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY); /* Public so it can be used by other apps*/

    private static final int CONTACTS = 100;
    private static final int CONTACTS_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // eg. content://com.zoolueapps.cc360.contacts.provider/Contacts
        matcher.addURI(CONTENT_AUTHORITY, PeopleContract.TABLE_NAME, CONTACTS);
        //eg. content://com.zoolueapps. cc360.contacts.provider/Contacts/3
        matcher.addURI(CONTENT_AUTHORITY, PeopleContract.TABLE_NAME + "/#", CONTACTS_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = CC360AppDatabase.getInstance(getContext()); //This singleton pattern prevents us from creating more than one db
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query: called with URI " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "query: match is " + match);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match) {
            case CONTACTS:
                queryBuilder.setTables(PeopleContract.TABLE_NAME);
                break;

            case CONTACTS_ID:
                queryBuilder.setTables(PeopleContract.TABLE_NAME);
                long contactsId = PeopleContract.getContactsId(uri);
                queryBuilder.appendWhere(PeopleContract.Columns._ID + " = " + contactsId);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }


        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CONTACTS:
                return PeopleContract.CONTENT_TYPE;

            case CONTACTS_ID:
                return PeopleContract.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "Entering insert, called with uri:" + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;

        Uri returnUri;
        long recordId;

        switch (match) {
            case CONTACTS:
                db = mOpenHelper.getWritableDatabase();
                recordId = db.insert(PeopleContract.TABLE_NAME, null, values);
                if (recordId >= 0) {
                    returnUri = PeopleContract.buildContactsUri(recordId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        Log.d(TAG, "Exiting insert, returning " + returnUri);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update called with uri" + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case CONTACTS:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(PeopleContract.TABLE_NAME, values, selection, selectionArgs);
                break;

            case CONTACTS_ID:
                db = mOpenHelper.getWritableDatabase();
                long contactsId = PeopleContract.getContactsId(uri);
                selectionCriteria = PeopleContract.Columns._ID + " = " + contactsId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(PeopleContract.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case CONTACTS:
                db = mOpenHelper.getWritableDatabase();
                db = db.update(PeopleContract.TABLE_NAME, values, selection, String[] selectionArgs);
                break;

            case CONTACTS_ID:
                db = mOpenHelper.getWritableDatabase();
                long contactsID = PeopleContract.getContactsId(uri);
                selectionCriteria = PeopleContract.Columns._ID + " = " + contactsID;

                if ((selection != null) && (selection.length() >= 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }

                count = db.update(PeopleContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("unknown uri: " + uri);
        }
        Log.d(TAG, "Exiting update, returning " + count);
        return count;

    }

}