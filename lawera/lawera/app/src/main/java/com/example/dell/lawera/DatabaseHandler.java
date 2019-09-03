package com.example.dell.lawera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "items.db";
    public static final String TABLE_NAME = "items_table";
    public static final String CUSTOM_TABLE = "CUSTOM_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "IMAGE";
    public static final String COL_5 = "WORD";
    public static final String COL_6 = "CHOICE_ONE_TEXT";
    public static final String COL_7 = "CHOICE_ONE_IMAGE";
    public static final String COL_8 = "CHOICE_TWO_TEXT";
    public static final String COL_9 = "CHOICE_TWO_IMAGE";
    public static final String COL_10 = "VOICE";
    public static final String COL_11 = "LEVEL";
    public static final String COL_12 = "CHOICE_THREE_TEXT";
    public static final String COL_13 = "CHOICE_THREE_IMAGE";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CATEGORY TEXT,IMAGE INTEGER,WORD TEXT,CHOICE_ONE_TEXT TEXT,CHOICE_ONE_IMAGE INTEGER,CHOICE_TWO_TEXT TEXT, CHOICE_TWO_IMAGE INTEGER,VOICE INTEGER,LEVEL INTEGER,CHOICE_THREE_TEXT TEXT,CHOICE_THREE_IMAGE INTEGER)");


//        insert();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table " + CUSTOM_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CATEGORY TEXT,IMAGE BLOB,WORD TEXT,VOICE INTEGER)");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CUSTOM_TABLE);
        onCreate(db);
    }

    public void customInsert(){


    }
    public boolean insertCustomData(String name,String category,byte[] image,String word,int voice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,category);
        contentValues.put(COL_4,image);
        contentValues.put(COL_5,word);
        contentValues.put(COL_13,voice);
        long result = db.insert(CUSTOM_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public void insert(){
        insertData("cat","animals",R.drawable.cat,"ድመት","ድመት",R.drawable.cat,"ወፍ",R.drawable.bird,R.raw.demet,1,"ውሻ",R.drawable.dogg);
        insertData("bread","food",R.drawable.bread,"ዳቦ","ፓስታ",R.drawable.pasta,"እንቁላል",R.drawable.egg,R.raw.bread,1,"ዳቦ",R.drawable.bread);
        insertData("shoe","cloths",R.drawable.shoe,"ጫማ","ጫማ",R.drawable.shoe,"ቁምጣ",R.drawable.shorts,R.raw.shoe,1,"ካልሲ",R.drawable.socks);
        insertData("dog","animals",R.drawable.dogg,"ውሻ","ወፍ",R.drawable.bird,"ውሻ",R.drawable.dogg,R.raw.dog, 1,"ድመት",R.drawable.cat);
        insertData("bird","animals",R.drawable.bird,"ወፍ","ውሻ",R.drawable.dogg,"ወፍ",R.drawable.bird,R.raw.bird, 2,"ድመት",R.drawable.cat);
        insertData("hen","animals",R.drawable.hen,"ዶሮ","ውሻ",R.drawable.dogg,"ወፍ",R.drawable.bird,R.raw.hen, 2,"ዶሮ",R.drawable.hen);
        insertData("table","furniture",R.drawable.table,"ጠረጴዛ","ጠረጴዛ",R.drawable.table,"ወንበር",R.drawable.chair,R.raw.table,1,"አልጋ",R.drawable.bed);
        insertData("bed","furniture",R.drawable.bed,"አልጋ","ወንበር",R.drawable.chair,"አልጋ",R.drawable.bed,R.raw.bed,1,"ጠረጴዛ",R.drawable.table);
        insertData("chair","furniture",R.drawable.chair,"ወንበር","ጠረጴዛ",R.drawable.table,"አልጋ",R.drawable.bed,R.raw.chair,1,"ወንበር",R.drawable.chair);
        insertData("red","colors",R.drawable.red,"ቀይ","ቢጫ",R.drawable.yellow,"ቀይ",R.drawable.red,R.raw.red,1,"ጥቁር",R.drawable.black);

        insertData("black","colors",R.drawable.black,"ጥቁር","ጥቁር",R.drawable.black,"ቢጫ",R.drawable.yellow,R.raw.black, 1,"አረንግዋዴ",R.drawable.green);
        insertData("orange","colors",R.drawable.orange,"ብርቱካናማ","ብርቱካናማ",R.drawable.orange,"ቀይ",R.drawable.red,R.raw.orange,1,"ጥቁር",R.drawable.black);
        insertData("blue","colors",R.drawable.blue,"ሰማያዊ","ቀይ",R.drawable.red,"ጥቁር",R.drawable.black,R.raw.blue,2,"ሰማያዊ",R.drawable.blue);
        insertData("green","colors",R.drawable.green,"አረንግዋዴ","አረንግዋዴ",R.drawable.green,"ሰማያዊ",R.drawable.blue,R.raw.green,2,"ቀይ",R.drawable.red);
        insertData("yellow","colors",R.drawable.yellow,"ቢጫ","ቀይ",R.drawable.red,"ቢጫ",R.drawable.yellow,R.raw.yellow,1,"ጥቁር",R.drawable.black);
        insertData("pasta","food",R.drawable.pasta,"ፓስታ","መኮረኒ",R.drawable.macaroni,"ሩዝ",R.drawable.rice,R.raw.pasta,1,"ፓስታ",R.drawable.pasta);
        insertData("fork","furniture",R.drawable.fork,"ሹካ","ሹካ",R.drawable.fork,"ማንኪያ",R.drawable.spoon,R.raw.fork,1,"ጀበና",R.drawable.jebena);
        insertData("shorts","cloths",R.drawable.shorts,"ቁምጣ","ጫማ",R.drawable.shoe,"ቁምጣ",R.drawable.shorts,R.raw.shorts,1,"ካልሲ",R.drawable.socks);
        insertData("socks","cloths",R.drawable.socks,"ካልሲ","ቁምጣ",R.drawable.shorts,"ጫማ",R.drawable.shoe,R.raw.socks,1,"ካልሲ",R.drawable.socks);
//        insertData("shoe","cloths",R.drawable.shoe,"ጫማ","ጫማ",R.drawable.shoe,"ቁምጣ",R.drawable.shorts,R.raw.shoe,1,"ካልሲ",R.drawable.socks);
        insertData("jebena","furniture",R.drawable.jebena,"ጀበና","ሹካ",R.drawable.fork,"ማንኪያ",R.drawable.spoon,R.raw.jebena,1,"ጀበና",R.drawable.jebena);
        insertData("jacket","cloths",R.drawable.jacket,"ጃኬት","ጃኬት",R.drawable.jacket,"ቁምጣ",R.drawable.shorts,R.raw.jacket,1,"ካልሲ",R.drawable.socks);

        insertData("ox","animals",R.drawable.ox,"በሬ","አህያ",R.drawable.donky,"ላም",R.drawable.cow,R.raw.ox,5,"በሬ",R.drawable.ox);
        insertData("stomach","shapes",R.drawable.stomach,"ሆድ","ክንድ",R.drawable.arm,"ሆድ",R.drawable.stomach,R.raw.stomach,5,"ትከሻ",R.drawable.sholder);
        insertData("nose","shapes",R.drawable.nose,"አፍንጫ","እጅ",R.drawable.hand,"አፍንጫ",R.drawable.nose,R.raw.nose,3,"ትከሻ",R.drawable.sholder);
        insertData("rat","animals",R.drawable.rat,"አይጥ","አይጥ",R.drawable.rat,"ድመት",R.drawable.cat,R.raw.rat,3,"ወፍ",R.drawable.bird);

        insertData("lion","animals",R.drawable.lion,"አንበሳ","ነብር",R.drawable.tiger,"አህያ",R.drawable.donky,R.raw.lion,4,"አንበሳ",R.drawable.lion);
        insertData("moseb","furniture",R.drawable.moseb,"መሶብ","ሰሃን",R.drawable.plate,"መሶብ",R.drawable.moseb,R.raw.mesob,5,"ብርጭቆ",R.drawable.glass);
//        insertData("bread","food",R.drawable.bread,"ዳቦ","ፓስታ",R.drawable.pasta,"እንቁላል",R.drawable.egg,R.raw.bread,1,"ዳቦ",R.drawable.bread);
        insertData("egg","food",R.drawable.egg,"እንቁላል","ድንች",R.drawable.potato,"እንቁላል",R.drawable.egg,R.raw.egg,5,"ዳቦ",R.drawable.bread);
        insertData("macaroni","food",R.drawable.macaroni,"መኮረኒ","ፓስታ",R.drawable.pasta,"መኮረኒ",R.drawable.macaroni,R.raw.macoroni,5,"ሩዝ",R.drawable.rice);
        insertData("injera","food",R.drawable.injera,"እንጀራ","እንጀራ",R.drawable.injera,"ዳቦ",R.drawable.bread,R.raw.injera,2,"ሩዝ",R.drawable.rice);




        insertData("eye","shapes",R.drawable.eye,"አይን","አፍንጫ",R.drawable.nose,"ጆሮ",R.drawable.ear,R.raw.eye,1,"አይን",R.drawable.eye);



        insertData("donky","animals",R.drawable.donky,"አህያ","አይጥ",R.drawable.rat,"በሬ",R.drawable.ox,R.raw.donkey,2,"አህያ",R.drawable.donky);
        insertData("leg","shapes",R.drawable.foot,"እግር","እግር",R.drawable.foot,"ጣት",R.drawable.finger,R.raw.foot,2,"አይን",R.drawable.eye);



        insertData("tea","food",R.drawable.tea,"ሻይ","ሻይ",R.drawable.tea,"ወትተ",R.drawable.milk,R.raw.tea,4,"ዳቦ",R.drawable.bread);
        insertData("potato","food",R.drawable.potato,"ድንች","ድንች",R.drawable.potato,"ብርቱካን",R.drawable.orge,R.raw.potato,5,"ፖም",R.drawable.apple);
        insertData("hand","shapes",R.drawable.hand,"እጅ","ክንድ",R.drawable.arm,"እጅ",R.drawable.hand,R.raw.hand,4,"እጅ",R.drawable.hand);
        insertData("tishert","cloths",R.drawable.tishert,"ቲሸርት","ቲሸርት",R.drawable.tishert,"ሱሪ",R.drawable.trouser,R.raw.tshirt,4,"ጃከት",R.drawable.jacket);

        insertData("lemon","food",R.drawable.lemon,"ሎሚ","ፓፓያ",R.drawable.papaya,"ሎሚ",R.drawable.lemon,R.raw.lemon,4,"አቮካዶ",R.drawable.avocado);
        insertData("knee","shapes",R.drawable.knee,"ጉልበት","ጉልበት",R.drawable.knee,"ጣት",R.drawable.finger,R.raw.knee,4,"እግር",R.drawable.foot);

        insertData("apple","food",R.drawable.apple,"ፖም","ብርቱካን",R.drawable.orge,"ቲማቲም",R.drawable.tomatto,R.raw.apple,5,"ፖም",R.drawable.apple);

        insertData("tiger","animals",R.drawable.tiger,"ነብር","አንበሳ",R.drawable.lion,"ፈረስ",R.drawable.horse,R.raw.tiger,5,"ነብር",R.drawable.tiger);

        insertData("cow","animals",R.drawable.cow,"ላም","ላም",R.drawable.cow,"አንበሳ",R.drawable.lion,R.raw.cow,5,"ነብር",R.drawable.tiger);

        insertData("orange","food",R.drawable.orge,"ብርቱካን","እንቁላል",R.drawable.egg,"ብርቱካን",R.drawable.orge,R.raw.burtukan,3,"ድንች",R.drawable.potato);


        insertData("mango","food",R.drawable.mango,"ማንጎ","ቲማቲም",R.drawable.tomato,"ድንች",R.drawable.potato,R.raw.mango,3,"ማንጎ",R.drawable.mango);
        insertData("finger","shapes",R.drawable.finger,"ጣት","እጅ",R.drawable.hand,"ጣት",R.drawable.finger,R.raw.finger,3,"ትከሻ",R.drawable.sholder);
        insertData("milk","food",R.drawable.milk,"ወትተ","እንቁላል",R.drawable.egg,"ወትተ",R.drawable.milk,R.raw.milk,3,"ድንች",R.drawable.potato);
        insertData("trouser","cloths",R.drawable.trouser,"ሱሪ","ሱሪ",R.drawable.trouser,"ጃኬት",R.drawable.jacket,R.raw.trouser,3,"ቲሸርት",R.drawable.tishert);

        insertData("tounge","shapes",R.drawable.tounge,"ምላስ","ጆሮ",R.drawable.ear,"እጅ",R.drawable.hand,R.raw.tounge,3,"ምላስ",R.drawable.tounge);

        insertData("genfo","food",R.drawable.genfo,"ገንፎ","እንጀራ",R.drawable.injera,"ዳቦ",R.drawable.bread,R.raw.genfo,3,"ገንፎ",R.drawable.genfo);
        insertData("fish","animals",R.drawable.fish,"አሳ","ድመት",R.drawable.cat,"አሳ",R.drawable.fish,R.raw.fish,3,"አይጥ",R.drawable.rat);

        insertData("cheek","shapes",R.drawable.arm,"ጉንጭ","አፍንጫ",R.drawable.nose,"ጉንጭ",R.drawable.cheek,R.raw.arm,3,"ትከሻ",R.drawable.shoulder);




        insertData("avocado","food",R.drawable.avocado,"አቮካዶ","ፓፓያ",R.drawable.papaya,"ማንጎ",R.drawable.mango,R.raw.avocado,7,"አቮካዶ",R.drawable.avocado);
        insertData("tomato","food",R.drawable.tomato,"ቲማቲም","ቲማቲም",R.drawable.tomato,"ማንጎ",R.drawable.mango,R.raw.tomato,7,"ብርቱካን",R.drawable.orge);
        insertData("plate","furniture",R.drawable.plate,"ሰሃን","ብርጭቆ",R.drawable.glass,"ሹካ",R.drawable.fork,R.raw.plate,7,"ሰሃን",R.drawable.plate);

        insertData("shoulder","shapes",R.drawable.shoulder,"ትከሻ","ክንድ",R.drawable.arm,"እጅ",R.drawable.hand,R.raw.sholder,7,"ትከሻ",R.drawable.shoulder);
        insertData("glass","furniture",R.drawable.glass,"ብርጭቆ","ማንኪያ",R.drawable.spoon,"ብርጭቆ",R.drawable.glass,R.raw.glass,7,"ሹካ",R.drawable.fork);
        insertData("refregenator","furniture",R.drawable.refregenator,"ፍሪጅ","ቁምሳጥን",R.drawable.cupbord,"ጠረጴዛ",R.drawable.table,R.raw.fridge,7,"ፍሪጅ",R.drawable.refregenator);
        insertData("ear","shapes",R.drawable.ear,"ጆሮ","አፍንጫ",R.drawable.nose,"ጆሮ",R.drawable.ear,R.raw.ear,1,"አይን",R.drawable.eye);

        insertData("rabbit","animals",R.drawable.rabbit,"ጥንቸል","ጥንቸል",R.drawable.rabbit,"አይጥ",R.drawable.rat,R.raw.rabbit,8,"ዶሮ",R.drawable.hen);
        insertData("spoon","furniture",R.drawable.spoon,"ማንኪያ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.spoon,8,"ሰሃን",R.drawable.plate);
        insertData("cupbord","furniture",R.drawable.cupbord,"ቁምሳጥን","ወንበር",R.drawable.chair,"ጠረጴዛ",R.drawable.table,R.raw.cupboard,7,"ቁምሳጥን",R.drawable.cupbord);
        insertData("chin","shapes",R.drawable.chin,"አገጭ","ጉንጭ",R.drawable.cheek,"አገጭ",R.drawable.chin,R.raw.chin,7,"አይን",R.drawable.eye);


//feelings general mine furniture food
        insertData("hot","general",R.drawable.hot,"ሞቀኝ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.imhot,8,"ሰሃን",R.drawable.plate);
        insertData("crying","feelings",R.drawable.crying,"ማልቀስ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.crying,8,"ሰሃን",R.drawable.plate);
        insertData("hungry","general",R.drawable.hungry,"ራበኝ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.hungry,8,"ሰሃን",R.drawable.plate);
        insertData("thinking","general",R.drawable.thinking,"ማሰብ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.thinking,8,"ሰሃን",R.drawable.plate);
        insertData("tresty","general",R.drawable.tresty,"ጠማኝ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.thirsty,8,"ሰሃን",R.drawable.plate);
        insertData("upset","feelings",R.drawable.upset,"ማኩረፍ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.upset,8,"ሰሃን",R.drawable.plate);

        insertData("angry","feelings",R.drawable.angry,"መናደድ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.angry,8,"ሰሃን",R.drawable.plate);
        insertData("laughing","general",R.drawable.laughing,"መሳቅ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.laugh,8,"ሰሃን",R.drawable.plate);
        insertData("happy","feelings",R.drawable.happy,"ደስተኛ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.happy,8,"ሰሃን",R.drawable.plate);

        insertData("pee","general",R.drawable.pee,"መሽናት","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.pee,8,"ሰሃን",R.drawable.plate);
        insertData("cold","general",R.drawable.cold,"በረደኝ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.cold,8,"ሰሃን",R.drawable.plate);
        insertData("scared","feelings",R.drawable.scared,"ፈራሁ","ማንኪያ",R.drawable.spoon,"ሹካ",R.drawable.fork,R.raw.scared,8,"ሰሃን",R.drawable.plate);


    }
    public void initData(){
        clearData();
        insert();
    }

    public boolean insertData(String name,String category,int image,String word,String choice_one,int choice_one_url,String choice_two,int choice_two_url,int voice,int level,String choice_three,int choice_three_url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,category);
        contentValues.put(COL_4,image);
        contentValues.put(COL_5,word);
        contentValues.put(COL_6,choice_one);
        contentValues.put(COL_7,choice_one_url);
        contentValues.put(COL_8,choice_two);
        contentValues.put(COL_9,choice_two_url);
        contentValues.put(COL_10,voice);
        contentValues.put(COL_11,level);
        contentValues.put(COL_12,choice_three);
        contentValues.put(COL_13,choice_three_url);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public List<Item> getdata(){
        List<Item> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        Item item = null;
        while (cursor.moveToNext()) {
            item = new Item();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String category = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
            String word = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
            String choice_one_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_ONE_TEXT"));
            int choice_one_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_ONE_IMAGE"));
            int choice_two_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_TWO_IMAGE"));
            String choice_two_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_TWO_TEXT"));
            int voice = cursor.getInt(cursor.getColumnIndexOrThrow("VOICE"));
            int level = cursor.getInt(cursor.getColumnIndexOrThrow("LEVEL"));
            int choice_three_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_THREE_IMAGE"));
            String choice_three_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_THREE_TEXT"));
            item.setChoice_three(choice_three_text);
            item.setChoice_three_url(choice_three_image);
            item.setName(name);
            item.setCategory(category);
            item.setChoice_one(choice_one_text);
            item.setChoice_one_url(choice_one_image);
            item.setChoice_two(choice_two_text);
            item.setImage(image);
            item.setChoice_two_url(choice_two_image);
            item.setWord(word);
            item.setVoice(voice);
            item.setLevel(level);


            stringBuffer.append(item);
            // stringBuffer.append(dataModel);
            data.add(item);
        }

        for (Item mo:data ) {

            Log.i("Hellomo",""+mo.getName());
        }

        //

        return data;
    }
    public List<Item> getdatabycategory(String selected_category){
        List<Item> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" WHERE CATEGORY = ?",new String[] {selected_category});
        StringBuffer stringBuffer = new StringBuffer();
        Item item = null;
        while (cursor.moveToNext()) {
            item = new Item();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String category = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
            String word = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
            String choice_one_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_ONE_TEXT"));
            int choice_one_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_ONE_IMAGE"));
            int choice_two_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_TWO_IMAGE"));
            String choice_two_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_TWO_TEXT"));
            int voice = cursor.getInt(cursor.getColumnIndexOrThrow("VOICE"));
            int level = cursor.getInt(cursor.getColumnIndexOrThrow("LEVEL"));
            int choice_three_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_THREE_IMAGE"));
            String choice_three_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_THREE_TEXT"));
            item.setChoice_three(choice_three_text);
            item.setChoice_three_url(choice_three_image);
            item.setName(name);
            item.setCategory(category);
            item.setChoice_one(choice_one_text);
            item.setChoice_one_url(choice_one_image);
            item.setChoice_two(choice_two_text);
            item.setImage(image);
            item.setChoice_two_url(choice_two_image);
            item.setWord(word);
            item.setVoice(voice);
            item.setLevel(level);

            stringBuffer.append(item);
            // stringBuffer.append(dataModel);
            data.add(item);
        }

        for (Item mo:data ) {

            Log.i("Hellomo",""+mo.getName());
        }

        //

        return data;
    }
    public Cursor getdatabyname(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE NAME = ?",new String[] {name});
        return res;
    }
    //    public List<Item> getdatabyname(String s){
//        List<Item> data = new ArrayList<>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" WHERE NAME = ?",new String[] {s});
//        StringBuffer stringBuffer = new StringBuffer();
//        Item item = null;
//        while (cursor.moveToNext()) {
//            item= new Item();
//            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
//            String category = cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY"));
//            int image = cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE"));
//            String word = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
//            String choice_one_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_ONE_TEXT"));
//            int choice_one_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_ONE_IMAGE"));
//            int choice_two_image = cursor.getInt(cursor.getColumnIndexOrThrow("CHOICE_TWO_IMAGE"));
//            String choice_two_text = cursor.getString(cursor.getColumnIndexOrThrow("CHOICE_TWO_TEXT"));
//            int voice = cursor.getInt(cursor.getColumnIndexOrThrow("VOICE"));
//            item.setName(name);
//            item.setCategory(category);
//            item.setChoice_one(choice_one_text);
//            item.setChoice_one_url(choice_one_image);
//            item.setChoice_two(choice_two_text);
//            item.setImage(image);
//            item.setChoice_two_url(choice_two_image);
//            item.setWord(word);
//            item.setVoice(voice);
//
//            stringBuffer.append(item);
//            // stringBuffer.append(dataModel);
//            data.add(item);
//        }
//
//        for (Item mo:data ) {
//
//            Log.i("Hellomo",""+mo.getName());
//        }
//
//        //
//
//        return data;
//    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getDataByLevel(String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE LEVEL = ? LIMIT 5",new String[] {level});
        return res;
    }
    public Cursor getDataByCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE CATEGORY = ?",new String[] {category});
        return res;
    }
    public Cursor getDataByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE NAME = ?",new String[] {name});
        return res;
    }

    public boolean updateData(String id,String name,String category,int image,String word,String choice_one,int choice_one_url,String choice_two,int choice_two_url,int voice,int level,String choice_three,int choice_three_url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,category);
        contentValues.put(COL_4,image);
        contentValues.put(COL_5,word);
        contentValues.put(COL_6,choice_one);
        contentValues.put(COL_7,choice_one_url);
        contentValues.put(COL_8,choice_two);
        contentValues.put(COL_9,choice_two_url);
        contentValues.put(COL_10,voice);
        contentValues.put(COL_11,level);
        contentValues.put(COL_12,choice_three);
        contentValues.put(COL_13,choice_three_url);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer clearData () {
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("delete * from "+TABLE_NAME,null);
        return db.delete(TABLE_NAME,null,null);
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}

