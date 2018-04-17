package com.example.shobhraj.prefmenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout back;
    Button set;
    TextView uname,nw_operator;
    boolean color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        back=findViewById(R.id.lay);
        set=findViewById(R.id.settings);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(i);
            }
        });
        colorchange();

        nw_operator=findViewById(R.id.operators);
        uname=findViewById(R.id.username);
        SharedPreferences prf= PreferenceManager.getDefaultSharedPreferences(this);
        uname.setText(prf.getString("username","ghost"));
        nw_operator.setText(prf.getString("service","ghost"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.setting) {
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.about_app)
        {
            Intent i1=new Intent(MainActivity.this,About.class);
            startActivity(i1);
            return true;
        }
        return true;
    }

    public void colorchange()
    {
        SharedPreferences prf=PreferenceManager.getDefaultSharedPreferences(this);
        boolean change =prf.getBoolean("background",false);
        if(change==true)
        {
            back.setBackgroundColor(getResources().getColor(R.color.neon));
        }
    }
}
