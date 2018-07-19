/**
* User interacts with the avatar here.
* Activities include switching cosmetics and starting animations.
*
* Authors: Josh April, Shaun Henderson, Craig Thomas
*/

package com.example.cwagt.taskapp345.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.Task;

import java.util.ArrayList;
import java.util.List;

public class AvatarHome extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_home);


    }

}


