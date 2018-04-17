package zoolueapps.cc360;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static zoolueapps.cc360.AppProvider.CONTENT_AUTHORITY;
import static zoolueapps.cc360.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by curti on 9/13/2017.
 */

public class PeopleContract{

    static final String TABLE_NAME = "Contacts";

    //Contacts fields
    //static in this case just means imbedded in this class. Not the classic sense of static
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String FIELD_NAME = "fullName";
        public static final String FIELD_MOBILE_NUMBER = "mobilePhoneNumber";
        public static final String FIELD_LOCATION = "location";
        public static final String FIELD_TIME = "time";
        public static final String FIELD_DATE = "date";
        public static final String FIELD_CONFIRMATION_TEXT = "confirmationText";
        public static final String FIELD_TOPIC_OF_DISCUSSION = "topicOfDiscussion";
        public static final String FIELD_REMINDER = "reminder";
        public static final String FIELD_OTHER_PHONE = "otherPhone";
        public static final String FIELD_OTHER_NAME = "otherName";
        public static final String FIELD_HOME_ADDRESS = "homeAddress";
        public static final String FIELD_BUSINESS_NAME = "businessName";
        public static final String FIELD_TITLE = "title";
        public static final String FIELD_BUSINESS_ADDRESS = "businessAddress";
        public static final String FIELD_EMAIL1 = "email1";
        public static final String FIELD_EMAIL2 = "emeil2";
        public static final String FIELD_BIRTHDAY = "birthday";
        public static final String FIELD_NOTES = "notes";
        public static final int DATABASE_VERSION = 1;

        private Columns() {
            //private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the Contacts table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    static Uri buildContactsUri(long contactsID) {
        return ContentUris.withAppendedId(CONTENT_URI, contactsID);
    }

    static long getContactsId(Uri uri) {return ContentUris.parseId(uri);
    }

}
