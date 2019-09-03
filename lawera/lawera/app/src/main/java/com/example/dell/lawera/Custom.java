package com.example.dell.lawera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Custom {
    public static final String IMAGE_ID = "id";
    public static final String IMAGE = "image";

    private final Context mContext;

    private DatabaseHelper mCustom;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Images.db";
    private static final int DATABASE_VERSION = 1;

    private static final String IMAGES_TABLE = "ImagesTable";
    private static final String NAME = "NAME";
    private static final String TABLE_NAME = "myTable";
    private static final String CREATE_IMAGES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + IMAGES_TABLE + " (" +
                    IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IMAGE + " BLOB NOT NULL, " + NAME + " );";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            //  db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,IMGE INTEGER, OPTION_ONE TEXT, OPTION_TWO TEXT, ANSWER TEXT )");
            db.execSQL(CREATE_IMAGES_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + CREATE_IMAGES_TABLE);
            onCreate(db);
        }
    }


    public Custom(Context ctx) {
        mContext = ctx;
        mCustom = new DatabaseHelper(mContext);
    }

    public Custom open() throws SQLException {
        mDb = mCustom.getWritableDatabase();
        return this;
    }

    public void close() {
        mCustom.close();
    }

    // Insert the image to the Sqlite DB
    public void insertImage(byte[] imageBytes) {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        mDb.insert(IMAGES_TABLE, null, cv);
    }
    public void insertData(byte[] imageBytes,String name) {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        cv.put(NAME,name);
        mDb.insert(IMAGES_TABLE, null, cv);
    }

    //     Get the image from SQLite DB
//     We will just get the last image we just saved for convenience...
    public byte[] retreiveImageFromDB() {
        Cursor cur = mDb.query(false, IMAGES_TABLE, new String[]{IMAGE_ID, IMAGE},
                null, null, null, null,
                IMAGE_ID + " DESC", null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
    public List<CustomItem> getdata(){
        List<CustomItem> data = new ArrayList<>();
        SQLiteDatabase db = mCustom.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+IMAGES_TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        CustomItem customItem = null;
        while (cursor.moveToNext()) {
            customItem = new CustomItem();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
//            String category = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY"));
//            int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
            byte[] image = cursor.getBlob(1);
            customItem.setImage(image);
//            customItem.setChoice_three_url(choice_three_image);
            customItem.setName(name);



            stringBuffer.append(customItem);
            // stringBuffer.append(dataModel);
            data.add(customItem);
        }

        for (CustomItem mo:data ) {

            Log.i("HellomoCustom",""+mo.getName());
        }

        //

        return data;
    }
    public Cursor get() {

        Cursor res = mDb.rawQuery("select * from "+IMAGES_TABLE, null);
        return res;
//        if(c.moveToNext())
//        {
//            byte[] image = c.getBlob(0);
//            Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
////            imageView.setImageBitmap(bmp);
////            Toast.makeText(this,"Done", Toast.LENGTH_SHORT).show();
//        }
    }
    public byte[] retreiveFromDB() {
        Cursor cur = mDb.query(false, IMAGES_TABLE, new String[]{IMAGE_ID, IMAGE},
                null, null, null, null,
                IMAGE_ID + " DESC", null);
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            String name = cur.getString(cur.getColumnIndex(NAME));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }
}
