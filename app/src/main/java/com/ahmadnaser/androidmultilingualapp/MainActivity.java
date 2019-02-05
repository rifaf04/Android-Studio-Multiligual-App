package com.ahmadnaser.androidmultilingualapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdDisplayListener;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.NativeAdPreferences.NativeAdBitmapSize;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;
import java.util.Locale;

//https://github.com/StartApp-SDK/Documentation/wiki/Android-InApp-Documentation
public class MainActivity extends Activity {

    Spinner spinnerctrl;
    Locale myLocale;
    private StartAppAd startAppAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerctrl = (Spinner) findViewById(R.id.spinner);
        ActivateSpinner();
        startAppAd = new StartAppAd(this);
        StartAppSDK.init(this, "112324504", "212842215", true);

    }

    private void ActivateSpinner() {


        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 1) {

                    Toast.makeText(parent.getContext(),
                            "You have selected Deutsch", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("de");
                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected Frensh", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("fr");
                } else if (pos == 3) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                } else if (pos == 4) {

                    Toast.makeText(parent.getContext(),
                            "You have selected arabic", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("ar");
                    setTheme(R.style.arabic_body_text);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });


    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }


    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);


        super.onRestart();
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();



    }







}
