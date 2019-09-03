package com.example.dell.lawera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

//import static com.example.dell.lawera.DatabaseHandler.*;

public class Sentence {
//    Context context;
//    public Sentence(Context context){
//        this.context = context;
//    }
//    DatabaseHandler mydb = new DatabaseHandler(context);
//    public boolean insertWordData(String name,String category,int image,String word,int voice) {
//        SQLiteDatabase db = mydb.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(mydb.COL_2,name);
//        contentValues.put(mydb.COL_3,category);
//        contentValues.put(mydb.COL_4,image);
//        contentValues.put(mydb.COL_5,word);
//        contentValues.put(mydb.COL_10,voice);
//        long result = db.insert(mydb.WORDS_TABLE,null ,contentValues);
//        if(result == -1)
//            return false;
//        else
//            return true;
//    }
//    public void insertWord(){
//        insertWordData("cat","animals",R.drawable.cat,"ድመት",R.raw.demet);
//        insertWordData("cat","animals",R.drawable.dogg,"ውሻ",R.raw.demet);
//
//
//    }
//    public void initWords(){
//        clearData();
//        insertWord();
//    }
//    public Integer clearData () {
//        SQLiteDatabase db = mydb.getWritableDatabase();
//        return db.delete(mydb.WORDS_TABLE,null,null);
//    }
//    public List<Item> getwordbycategory(String selected_category){
//        List<Item> data = new ArrayList<>();
//        SQLiteDatabase db = mydb.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+mydb.WORDS_TABLE+" WHERE CATEGORY = ?",new String[] {selected_category});
//        StringBuffer stringBuffer = new StringBuffer();
//        Item item = null;
//        while (cursor.moveToNext()) {
//            item = new Item();
//            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
//            String category = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY"));
//            int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
//            String word = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
//            int voice = cursor.getInt(cursor.getColumnIndexOrThrow("VOICE"));
//            item.setName(name);
//            item.setCategory(category);
//            item.setImage(image);
//            item.setWord(word);
//            item.setVoice(voice);
//            stringBuffer.append(item);
//            data.add(item);
//        }
//        for (Item mo:data ) {
//
//            Log.i("Hellomo",""+mo.getName());
//        }
//
//        return data;
//    }
}
