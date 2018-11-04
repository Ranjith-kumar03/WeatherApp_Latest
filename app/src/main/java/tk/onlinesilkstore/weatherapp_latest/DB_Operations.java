package tk.onlinesilkstore.weatherapp_latest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_Operations extends SQLiteOpenHelper {
 private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="weather_database.db";
    private static final String CREATE_TABLE="create table "+Weater_Contract.Weather.TABLE_NAME  +"("+Weater_Contract.Weather.COUNTRY_NAME+" text , "+
                                     Weater_Contract.Weather.COUNTRY_TEMPERATURE+" text , "+Weater_Contract.Weather.ICON+" text "+")";



    public DB_Operations(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d( "DB_Operations: ","Created Database");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        Log.d( "DB_Operations: ","Created Table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Add_Data_To_Table(SQLiteDatabase db, String country_name, String temperature, String icon)
    {
        ContentValues cv= new ContentValues();
        cv.put(Weater_Contract.Weather.COUNTRY_NAME,country_name);
        //cv.put(Weater_Contract.Weather_Default.STATE,state_name);
        cv.put(Weater_Contract.Weather.COUNTRY_TEMPERATURE,temperature);
        cv.put(Weater_Contract.Weather.ICON,icon);
        db.insert(Weater_Contract.Weather.TABLE_NAME,null, cv);
        Log.d( "DB_Operations: ","Insert OPerations DOne");
    }
    public Cursor Display_Database(SQLiteDatabase db){
        String Projections[]={"country_name","temperature", "icon"};
        Cursor cursor=db.query(Weater_Contract.Weather.TABLE_NAME,Projections,  null,null,null,null,null);
        return cursor;
    }
}
