package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cwagt.taskapp345.R;


/**
 * Created by cwagt on 15/07/2018.
 */

public class AddUser extends AppCompatActivity {

    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.add_user);

        //Button code
        final Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent addUserIntent = new Intent(context, UserHome.class);
                startActivity(addUserIntent);
            }
        });
    }

}
