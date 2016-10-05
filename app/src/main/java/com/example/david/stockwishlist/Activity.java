package com.example.david.stockwishlist;

/**
 * Created by David on 10/4/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RoboGuice.injectMembers(getApplicationContext(), this);
    }
}
