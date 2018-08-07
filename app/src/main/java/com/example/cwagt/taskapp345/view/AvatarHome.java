/**
* User interacts with the avatar here.
* Activities include switching cosmetics and starting animations.
*
* Authors: Josh April, Shaun Henderson, Craig Thomas
*/

package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.BodyPartsAdapter;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.Task;

import java.util.ArrayList;
import java.util.List;

public class AvatarHome extends AppCompatActivity {
    private List<String> body_parts = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_home);

        body_parts.add("HEAD");
        body_parts.add("ARM"); // dont forget we have left arm, right arm
        body_parts.add("LEG"); // left leg, right leg
        body_parts.add("TORSO");


        recyclerView = findViewById(R.id.body_parts);
        BodyPartsAdapter mAdapter = new BodyPartsAdapter(this,body_parts);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManger);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);





    }




}


