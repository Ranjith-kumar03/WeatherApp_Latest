package tk.onlinesilkstore.weatherapp_latest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class BackGround_Task extends AsyncTask<String, Weather_Default,Void>{
    Context mContext;
    RecyclerView recyclerView;
    ProgressBar progressBar1;
    Recycler_Adapter recycler_adapter;
    ArrayList<Weather_Default> weatherDefaults = new ArrayList<Weather_Default>();
    static int k=0;


    public BackGround_Task(Context mContext)
    {
        this.mContext=mContext;
        Log.d( "BackGround_Task: ","single constructor called");

         k=1;

    }

 public BackGround_Task(Context mContext, ProgressBar progressBar1, RecyclerView recyclerView)
    {
    this.mContext=mContext;
      this.progressBar1=progressBar1;
     this.recyclerView=recyclerView;
     k=2;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(k==1)
        {
            Log.d( "BackGround_Task: ","singe constructor so skipped stupids");
        }

        if(k==2){
       recycler_adapter=new Recycler_Adapter(weatherDefaults,mContext);
       recyclerView.setAdapter(recycler_adapter);
       progressBar1.setVisibility(View.VISIBLE);}
    }

     @Override
    protected Void doInBackground(String... strings) {

        DB_Operations db_operations=new DB_Operations(mContext);
        SQLiteDatabase db;
        Cursor cursor;
        String Operation=strings[0].toString();
        if(Operation.equals("Adding"))
        {
            Log.d( "BackGround_Task: ","Iam inside ADDING");
            String country__name= strings[1].toString();;

            String country__temperature=strings[2].toString();;
             String icon__=strings[3].toString();
            db=db_operations.getWritableDatabase();
            db_operations.Add_Data_To_Table(db,country__name,country__temperature,icon__);

        } else if (Operation.equals("display"))
        {
            Log.d( "BackGround_Task: ","Iam inside DISPALY");
         db=db_operations.getReadableDatabase();
          cursor=db_operations.Display_Database(db);
            String m_Country_name;
             String m_Temperature;
              String m_Icon;
              while(cursor.moveToNext())
              {
                  Log.d( "BackGround_Task: ","values of cursor"+cursor.getString(cursor.getColumnIndex("Country_Name")));
                  Log.d( "BackGround_Task: ","values of cursor"+cursor.getString(cursor.getColumnIndex("Temperature")));
                  Log.d( "BackGround_Task: ","values of cursor"+cursor.getColumnIndex(String.valueOf(2)));
                  m_Country_name=cursor.getString(cursor.getColumnIndex("Country_Name"));
                  Log.d( "BackGround_Task: ","Iam inside DISPALY"+m_Country_name);
                  m_Temperature=cursor.getString(cursor.getColumnIndex("Temperature"));
                   m_Icon=cursor.getString(cursor.getColumnIndex("Icon"));

                  publishProgress(new Weather_Default(m_Country_name,m_Temperature,m_Icon));

                  try {
                      Thread.sleep(5000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }

            cursor.close();
            db_operations.close();
        }



        return null;
    }


   @Override
    protected void onProgressUpdate(Weather_Default... values) {
       if(k==2) {
           weatherDefaults.add(values[0]);

           recycler_adapter.notifyDataSetChanged();
       }
    }
@Override
protected void onPostExecute(Void aVoid) {
        if(k==2) {
            progressBar1.setVisibility(View.GONE);
        }
}
}
