package tk.onlinesilkstore.weatherapp_latest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySIngleTon {

    private static MySIngleTon mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private MySIngleTon(Context context) {
        mContext = context;
        mRequestQueue = getRequestQue();
    }

    public RequestQueue getRequestQue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public static synchronized MySIngleTon instance_obj(Context context) {
        if (mInstance == null) {
            mInstance = new MySIngleTon(context);
        }
        return mInstance;
    }

    public <T> void AddRequest(Request<T> request) {
        getRequestQue().add(request);

    }
}

