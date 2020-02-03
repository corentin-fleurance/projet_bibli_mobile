package com.example.monlivre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class ListItem extends AppCompatActivity {


    private ImageButton logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        logoutButton = (ImageButton) findViewById(R.id.loggout);
    }
}
