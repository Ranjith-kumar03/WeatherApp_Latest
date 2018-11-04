package tk.onlinesilkstore.weatherapp_latest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main_weather_fragment extends Fragment {


    public Main_weather_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main_weather, container, false);

        Toolbar mToolbar;
        final TextView mTemperature, mDescription, mDate_time,mDeg_far,mMain,mCountry,mState;
        final EditText mPlace_Selected;
        final ImageView imageView;
        Button btn1;
        final String[] url = new String[1];
           final RequestQueue requestQueue=Volley.newRequestQueue(getContext());
            mTemperature=view.findViewById(R.id.temperature_disp);
            mDate_time=view.findViewById(R.id.date_time_disp);
            mMain=view.findViewById(R.id.main_dis);
            //mDescription=findViewById(R.id.weather_desc_disp);
            mDeg_far=view.findViewById(R.id.deg_far_disp);
            mCountry=view.findViewById(R.id.country_disp);
            mState=view.findViewById(R.id.state_display);
            imageView=view.findViewById(R.id.weather_image_disp);
            btn1=view.findViewById(R.id.btn1);
            mPlace_Selected=view.findViewById(R.id.enter_place_display);
            mToolbar=view.findViewById(R.id.main_toolbar);
             btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String place= mPlace_Selected.getText().toString();
                    url[0] ="http://api.openweathermap.org/data/2.5/weather?q="+place+"&APPID=d8c0218251bf5b2ba540f458f4f352e9";


                    final JsonObjectRequest jsonObject_request=new JsonObjectRequest(Request.Method.POST, url[0], null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                // Toast.makeText(getApplicationContext(),"new Place---"+place,Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(),"new Place---"+url,Toast.LENGTH_LONG).show();
                                JSONObject jsonObject= response.getJSONObject("main");
                                JSONObject jsonObject1= response.getJSONObject("sys");
                                String Name=((String) response.get("name"));
                                JSONArray jsonArray=response.getJSONArray("weather");
                                JSONObject first_object=jsonArray.getJSONObject(0);

                                mTemperature.setText(Integer.toString(jsonObject.getInt("temp")));
                                mMain.setText((String)first_object.get("main"));
                                //mDescription.setText((String)first_object.get("description"));
                                String mIcon=first_object.getString("icon");
                                mState.setText(Name);
                                mCountry.setText((String)jsonObject1.get("country"));
                                String Date= Date_Time();
                                mDate_time.setText(Date);
                                String url_pic="http://openweathermap.org/img/w/"+mIcon+".png";
                                Picasso.get()
                                        .load(url_pic).into(imageView);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(),"Error happend Sorry for that please wait while we work on it",Toast.LENGTH_LONG).show();
                        }
                    });


                    requestQueue.add(jsonObject_request);

                }
            });
        return  view;

        }

        private String Date_Time() {
            Calendar calendar= Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
            String date=sdf.format(calendar.getTime());
            return  date;

        }



    }


