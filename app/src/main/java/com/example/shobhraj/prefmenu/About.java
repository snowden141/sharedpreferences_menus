package com.example.shobhraj.prefmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class About extends AppCompatActivity {

    private ActionMode mactionMode;
    Button but1,but2,but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        but1=findViewById(R.id.context);
        registerForContextMenu(but1);

        but2=findViewById(R.id.button1);
        but3=findViewById(R.id.button3);

        but2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mactionMode!=null)
                    return false;

                mactionMode=startActionMode(action_callback);
                 return true;
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(About.this,but3);
                popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(About.this,"" + menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private ActionMode.Callback action_callback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.action,menu);
            actionMode.setTitle("action mode");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Integer id=menuItem.getItemId();
            if(id==R.id.share_me) {
                Toast.makeText(About.this, "you shared the app", Toast.LENGTH_LONG).show();
                return true;
            }
            if(id==R.id.send_me) {
                Toast.makeText(About.this, "mail sent", Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mactionMode=null;
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item1:
                Intent i=new Intent(About.this,SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.item2:
                Toast.makeText(About.this,"you commented here",Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(About.this,"you liked this app",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
