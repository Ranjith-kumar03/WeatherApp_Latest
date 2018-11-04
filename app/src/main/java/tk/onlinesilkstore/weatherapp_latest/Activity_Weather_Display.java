package tk.onlinesilkstore.weatherapp_latest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

public class Activity_Weather_Display extends AppCompatActivity {
private ProgressBar pb1;
private RecyclerView recyclerView;
public  Recycler_Adapter recycler_adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__weather__display);
        pb1=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();

        BackGround_Task backGround_task=new BackGround_Task(this,pb1,recyclerView);
        backGround_task.execute("display");
    }

}
