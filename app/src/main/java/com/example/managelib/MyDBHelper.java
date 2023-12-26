package com.example.managelib;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.inputmethodservice.Keyboard;
import android.text.format.DateUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ContactDB";
    private static final int DATABASE_VERSION=4;
    private static final String TABLE_CONTACT="contacts";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME="name";
    public static final String KEY_PHONE="phone_no";
    public static final String KEY_SLOT="slot";
    public static final String KEY_AADHAR="aadhar";
    private static final String KEY_STD ="starting_date" ;
    private static final String KEY_EDD="ending_date";


    public ArrayList<ContactModel> arrContacts=new ArrayList<>();
    public ArrayList<ContactModel> membersLeftFiveDaysOrLess = new ArrayList<>();

    public ArrayList<ContactModel> liveMemberAccessList=new ArrayList<>();

    public ArrayList<ContactModel> expireMemberAccessList=new ArrayList<>();


    Context context;
    public MyDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_CONTACT + " ( " +
                KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_PHONE + " TEXT, " +
                KEY_SLOT + " TEXT, " +
                KEY_AADHAR + " TEXT, " +
                KEY_STD + " TEXT, " +
                KEY_EDD + " TEXT " +
                ")";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACT);
        onCreate(sqLiteDatabase);
    }

    public void addContact(String name, String phone, String slot, String aadhar, String std, String edd){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(KEY_ID,id);
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone);
        values.put(KEY_SLOT,slot);
        values.put(KEY_AADHAR,aadhar);
        values.put(KEY_STD,std);
        values.put(KEY_EDD,edd);
        sqLiteDatabase.insert(TABLE_CONTACT,null,values);
    }

    @SuppressLint("Range")
    public ArrayList<ContactModel> fetchContacts(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_CONTACT, null);
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + KEY_ID + ", " + KEY_NAME + ", " + KEY_PHONE + ", " + KEY_SLOT + ", " + KEY_AADHAR + " FROM " + TABLE_CONTACT, null);

        arrContacts.clear();

        while (cursor.moveToNext()){
            ContactModel contactModel=new ContactModel();
            contactModel.id=cursor.getInt(0);
            contactModel.name=cursor.getString(1);
            contactModel.phone=cursor.getString(2);
            contactModel.slot=cursor.getString(3);
            contactModel.aadhar=cursor.getString(4);
            contactModel.std=cursor.getString(5);
            contactModel.edd=cursor.getString(6);
            arrContacts.add(contactModel);
        }
        cursor.close();
        return arrContacts;
    }
    
    public ArrayList<ContactModel> getMembersLeftFiveDaysOrLess() {

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_CONTACT, null);
        long currentDateMillis = System.currentTimeMillis();

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.id = cursor.getInt(0);
            contactModel.name = cursor.getString(1);
            contactModel.phone = cursor.getString(2);
            contactModel.slot = cursor.getString(3);
            contactModel.aadhar = cursor.getString(4);
            contactModel.std = cursor.getString(5);
            contactModel.edd = cursor.getString(6);

            // Parse the date from the database
            SimpleDateFormat dateFormat = null;
            //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            //}
            try {
                Date eddDate = dateFormat.parse(contactModel.edd);

                // Calculate the difference in days
                long differenceInDays = (eddDate.getTime() - currentDateMillis) / (24 * 60 * 60 * 1000);

                // Check if the member has left 5 or less days from completing one month
                if (differenceInDays <= 5) {
                    membersLeftFiveDaysOrLess.add(contactModel);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        cursor.close();
        return membersLeftFiveDaysOrLess;
    }

    public ArrayList<ContactModel> getLiveAccessArrayList(){


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Assuming today's date
        long currentDateMillis = System.currentTimeMillis();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.id = cursor.getInt(0);
            contactModel.name = cursor.getString(1);
            contactModel.phone = cursor.getString(2);
            contactModel.slot = cursor.getString(3);
            contactModel.aadhar = cursor.getString(4);
            contactModel.std = cursor.getString(5);
            contactModel.edd = cursor.getString(6);

            // Parse the date from the database

            //}
            SimpleDateFormat format;
            format= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

            try {

                Date eddDate = format.parse(contactModel.edd);
                Date stdDate=format.parse(contactModel.std);

                long differenceInDays2=(currentDateMillis-stdDate.getTime())/ (24 * 60 * 60 * 1000);

                long differenceInDays = (eddDate.getTime() - currentDateMillis) / (24 * 60 * 60 * 1000);

                if (differenceInDays2<=30 || differenceInDays2>0){
                    liveMemberAccessList.add(contactModel);

                }

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }

        cursor.close();
        return liveMemberAccessList;
    }


    public ArrayList<ContactModel> getExpireAccessList(){


        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        // Assuming today's date
        long currentDateMillis = System.currentTimeMillis();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.id = cursor.getInt(0);
            contactModel.name = cursor.getString(1);
            contactModel.phone = cursor.getString(2);
            contactModel.slot = cursor.getString(3);
            contactModel.aadhar = cursor.getString(4);
            contactModel.std = cursor.getString(5);
            contactModel.edd = cursor.getString(6);

            // Parse the date from the database

            //}
            SimpleDateFormat format;
            format= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

            try {

                Date eddDate = format.parse(contactModel.edd);
                Date stdDate=format.parse(contactModel.std);

                long differenceInDays = (eddDate.getTime() - currentDateMillis) / (24 * 60 * 60 * 1000);

                if (differenceInDays<=0){
                    expireMemberAccessList.add(contactModel);
                }

            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }

        cursor.close();
        return expireMemberAccessList;
    }




    public void deleteContact(int contactId) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String whereClause = KEY_ID + "=?";
        String[] whereArgs = {String.valueOf(contactId)};
        sqLiteDatabase.delete(TABLE_CONTACT, whereClause, whereArgs);
        sqLiteDatabase.close();
    }

}
