package com.hfad.starbuzz;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;


    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }


    @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        updateMyDatabase(db, oldVersion, newVersion);
    }


    private static void insertDrink(SQLiteDatabase db, String name, String description, int resourceid) {

        ContentValues dv = new ContentValues();
        dv.put("NAME", name);
        dv.put("DESCRIPTION",description);
        dv.put("IMAGE_RESOURCE_ID",resourceid);
        db.insert("DRINK",null,dv);

    }

  private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(oldVersion < 1) {

        db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DESCRIPTION TEXT,"
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte);
        insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam", R.drawable.cappuccino);
        insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter);

    }
    if(oldVersion < 2) {
        db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVOURITE NUMERIC;");
    }

    }
}
