package tk.onlinesilkstore.weatherapp_latest;

public class Weather_Default {

    public String mCountry_name;
    public String mTemperature;
    public String mIcon;

    public Weather_Default(String mCountry_name, String mTemperature, String mIcon) {
        this.mCountry_name = mCountry_name;
        this.mTemperature = mTemperature;
        this.mIcon = mIcon;
    }

    public String getmCountry_name() {
        return mCountry_name;
    }

    public String getmTemperature() {
        return mTemperature;
    }

    public String getmIcon() {
        return mIcon;
    }
}
