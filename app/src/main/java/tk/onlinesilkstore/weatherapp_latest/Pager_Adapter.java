package tk.onlinesilkstore.weatherapp_latest;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pager_Adapter extends FragmentPagerAdapter {


    public Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
             Main_weather_fragment main_weather_fragment=new Main_weather_fragment();
              return  main_weather_fragment;
            case 1:
                Last_Search_fragment last_search_fragment=new Last_Search_fragment();
                return  last_search_fragment;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                 return"Current Weather";
            case 1:
                return"Previous Weather";

        }
        return super.getPageTitle(position);
    }
}
