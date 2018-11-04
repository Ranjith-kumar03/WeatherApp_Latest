package tk.onlinesilkstore.weatherapp_latest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button bt1,bt2;
    private String url="https://api.openweathermap.org/data/2.5/find?lat=12.2602&lon=77.1461&cnt=20&units=metric&APPID=d8c0218251bf5b2ba540f458f4f352e9";
    private ProgressBar pb1;
    private RecyclerView recyclerView;
    public  Recycler_Adapter recycler_adapter;
    private Toolbar mToolbar;
    private ViewPager mViewpager;
    private TabLayout mTablayout;
    private  Pager_Adapter pager_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        mViewpager=findViewById(R.id.pager);
        mTablayout=findViewById(R.id.tab_layout);
        pager_adapter=new Pager_Adapter(getSupportFragmentManager());
        mViewpager.setAdapter(pager_adapter);
        mTablayout.setupWithViewPager(mViewpager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_template,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.load_images:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {

                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    Log.d("onResponse: ", "we are in request");


                                    JSONArray jsonArray= response.getJSONArray("list");
                                    int count =0;
                                    while(count<jsonArray.length())
                                    {
                                        JSONObject json_object = jsonArray.getJSONObject(count);
                                        JSONObject object_1=json_object.getJSONObject("main");
                                        //JSONObject object_2=json_object.getJSONObject("rain");
                                        // JSONObject object_3=json_object.getJSONObject("clouds");
                                        JSONArray weather=json_object.getJSONArray("weather");
                                        JSONObject weather_1=weather.getJSONObject(0);
                                        String CountryName = json_object.getString("name");
                                        String Temperature=object_1.getString("temp");
                                        //String Rain=object_2.getString("3h");
                                        //String Cloud_cover=object_3.getString("all");
                                        // String Main=weather_1.getString("main");
                                        // String Description=weather_1.getString("description");
                                        String Icon=weather_1.getString("icon");
                                        Toast.makeText(getApplicationContext(), "Uploading Details for you from JSON----" + CountryName, Toast.LENGTH_LONG).show();
                                        BackGround_Task  backGround_task =new BackGround_Task(getApplicationContext());
                                        backGround_task.execute("Adding",CountryName,Temperature, Icon);
                                        count++;
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Error in Uploading Details for you from JSON",Toast.LENGTH_LONG).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getBaseContext(), "Error in Uploading Details for you from Main Request",Toast.LENGTH_LONG).show();
                            }
                        });

                        MySIngleTon.instance_obj(getApplicationContext()).AddRequest(jsonObjectRequest);




                        Toast.makeText(getApplicationContext(),"we clicked you",Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
                return true;
            case R.id.display_images:
                startActivity(new Intent(getApplicationContext(),Activity_Weather_Display.class));
                return true;
            case R.id.exit:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
