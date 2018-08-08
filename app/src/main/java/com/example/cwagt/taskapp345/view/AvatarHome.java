/**
 * User interacts with the avatar here.
 * Activities include switching cosmetics and starting animations.
 *
 * Authors: Josh April, Shaun Henderson, Craig Thomas
 */

package com.example.cwagt.taskapp345.view;
/**
 * Created by cwagt on 15/07/2018.
 *
 * Activity which holds the Avatar fragment and recycler view
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.BodyPartsAdapter;
import com.example.cwagt.taskapp345.helper.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class AvatarHome extends AppCompatActivity {
    private List<String> categoriesList = new ArrayList<>();
    private RecyclerView categoryRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_home);

        categoriesList.add("HEAD");
        categoriesList.add("ARM"); // dont forget we have left arm, right arm
        categoriesList.add("LEG"); // left leg, right leg
        categoriesList.add("TORSO");
        categoriesList.add("BACKGROUND");


        //populate categories
        categoryRecyclerView = findViewById(R.id.categoriesRecyclerView);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categoriesList); //categories list
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryRecyclerView.setAdapter(categoriesAdapter);

        //populate body parts
        ArrayList<String> bodyPartsList = new ArrayList<>();
        bodyPartsList.add("bodypart1");
        bodyPartsList.add("bodypart2"); // dont forget we have left arm, right arm
        bodyPartsList.add("bodypart3"); // left leg, right leg
        bodyPartsList.add("bodypart4");
        bodyPartsList.add("bodypart5");

        RecyclerView bodyPartsRecyclerView = findViewById(R.id.bodyPartsRecyclerView);
        BodyPartsAdapter bodyPartsAdapter = new BodyPartsAdapter(this, bodyPartsList); //body part list list
        RecyclerView.LayoutManager bodyPartsLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        bodyPartsRecyclerView.setLayoutManager(bodyPartsLayoutManager);
        bodyPartsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bodyPartsRecyclerView.setAdapter(bodyPartsAdapter);
    }
}


