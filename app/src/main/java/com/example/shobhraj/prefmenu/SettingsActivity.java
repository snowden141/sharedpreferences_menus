package com.example.shobhraj.prefmenu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    ConstraintLayout lay2;
    boolean back_color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FragmentManager fmr=getFragmentManager();
        FragmentTransaction ftr=fmr.beginTransaction();

        lay2=findViewById(R.id.back2);

        SharedPreferences prf= PreferenceManager.getDefaultSharedPreferences(this);
        back_color=prf.getBoolean("background",false);

        if(back_color==true)
        {
            lay2.setBackgroundColor(getResources().getColor(R.color.neon));
            Toast.makeText(SettingsActivity.this,"restart the app",Toast.LENGTH_LONG).show();
        }

        SettingsFrag sfrag=new SettingsFrag();
        ftr.add(android.R.id.content,sfrag,"Settins Fragment");
        ftr.commit();
    }

    public static class SettingsFrag extends PreferenceFragment
    {
        @Override
        public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_preferences);
    }
    }
}
