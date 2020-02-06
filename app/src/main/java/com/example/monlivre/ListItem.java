package com.example.monlivre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ListItem extends AppCompatActivity {

    LinearLayout item = (LinearLayout) findViewById(R.id.main_item);
    Intent myIntent = new Intent(ListItem.this, Detail.class);
    private ImageButton logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        logoutButton = (ImageButton) findViewById(R.id.loggout);

        item.setOnLongClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListItem.this.startActivity(myIntent);
            }
        });
    }
}
