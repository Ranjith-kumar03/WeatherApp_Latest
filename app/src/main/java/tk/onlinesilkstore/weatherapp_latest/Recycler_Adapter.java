package tk.onlinesilkstore.weatherapp_latest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.View_Holder> {
public String url="";
public ArrayList<Weather_Default> weather_defaults=new ArrayList<Weather_Default>();
public Context context;

    public Recycler_Adapter(ArrayList<Weather_Default> weather_defaults, Context context) {
        this.weather_defaults = weather_defaults;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int posistion) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_template,parent,false);
        View_Holder view_holder= new View_Holder(view);

        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int posistion) {
     Weather_Default weatherDefault= weather_defaults.get(posistion);
     holder.tv1.setText(weatherDefault.getmCountry_name());
        holder.tv2.setText(weatherDefault.getmTemperature());
        String m_icon=weatherDefault.getmIcon().toString();
        Log.d( "onBindViewHolder: ","current Icon is---"+m_icon);
        url="http://openweathermap.org/img/w/"+m_icon+".png";
        Picasso.get().load(url).into(holder.iv1);
    }

    @Override
    public int getItemCount() {
        return weather_defaults.size();
    }

    public  class View_Holder extends RecyclerView.ViewHolder{
        public TextView tv1,tv2,tv3;
        public ImageView iv1;

        public View_Holder(@NonNull View view) {
            super(view);
            tv1=view.findViewById(R.id.country_name);
            tv2=view.findViewById(R.id.temperature);
           // tv3=view.findViewById(R.id.country_name);
            iv1=view.findViewById(R.id.image_display);

        }
    }
}
