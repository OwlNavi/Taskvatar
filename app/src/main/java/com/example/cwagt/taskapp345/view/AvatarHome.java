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


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.AvatarEditer;
import com.example.cwagt.taskapp345.helper.BodyPartsAdapter;
import com.example.cwagt.taskapp345.helper.CategoriesAdapter;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.RecyclerItemClickListener;
import com.example.cwagt.taskapp345.object.Enums;
import com.example.cwagt.taskapp345.object.Task;
import java.util.ArrayList;
import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class AvatarHome extends AppCompatActivity {
    private List<String> categoriesList;
    private RecyclerView categoryRecyclerView;
    private RecyclerView bodyPartsRecyclerView;
    private AvatarEditer editer;
    private View avatarFragment;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_home);
        context = getApplicationContext();

        //Toolbar  on the top of the screen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //find the avatar fragment
        avatarFragment = findViewById(R.id.avatar_fragment);
        editer = new AvatarEditer(avatarFragment);

        //great the list of categories
        categoriesList = getCategories();

        //populate categories
        categoryRecyclerView = findViewById(R.id.categoriesRecyclerView);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, categoriesList); //categories list
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryRecyclerView.setAdapter(categoriesAdapter);

        //populate body parts
        ArrayList<String> bodyPartsList = getCategoryItems(0); //default is zero

        //create the bodyparts recyclerview
        bodyPartsRecyclerView = findViewById(R.id.bodyPartsRecyclerView);
        BodyPartsAdapter bodyPartsAdapter = new BodyPartsAdapter(this, bodyPartsList); //body part list list
        RecyclerView.LayoutManager bodyPartsLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,false);
        bodyPartsRecyclerView.setLayoutManager(bodyPartsLayoutManager);
        bodyPartsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bodyPartsRecyclerView.setAdapter(bodyPartsAdapter);

        //define the behavior of the two recycler view on click events
        categoryRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                categoryRecyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //display selected category debug
                Log.d("CategoriesAdapter", "Clicked on: " + position);

                //remember the selected list
                SharedPreferences preferences = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("currentCategory", position);
                editor.apply();

                //change the bodyPartsRecyclerView list
                int category = position;
                ArrayList<String> currentBodyPartList = getCategoryItems(category);
                setBodyPartList(bodyPartsRecyclerView, currentBodyPartList);
            }

            @Override
            public void onLongClick(View view, int position) {
                onClick(view, position);
            }
        }));

        //Define the behaviour of the body parts list when clicked
        bodyPartsRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                bodyPartsRecyclerView, new RecyclerItemClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //make sure they have completed a task before changing body parts
                //get current user
                SharedPreferences preferences = getDefaultSharedPreferences(context);
                Long userID = preferences.getLong("currentUser", 0);
                final List<Task> taskList = DatabaseHelper.readAllTasks(context, userID);
                //Check the number of compelted tasks and update tasksCompleted
                int completed = 0;
                if(taskList.size() > 0) {
                    for (Task task : taskList) {
                        if (task.getStatus() == Enums.Status.COMPLETED) {
                            completed++;
                        }
                    }
                }
                if(completed < 0){
                    //NOT ENOUGH TASKS COMPLETED
                    //show the user a message to let them know they must complete tasks first
                    AlertDialog.Builder builder = new AlertDialog.Builder(AvatarHome.this);
                    builder.setMessage("You must complete a task before changing your avatar!")
                            .setTitle("Task Check");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                } else {
                    //get current list and item selected
                    Long categoryPreference = preferences.getLong("currentCategory", 0);//default zero
                    int currentCategory = categoryPreference.intValue();
                    ArrayList<String> items = getCategoryItems(currentCategory);
                    String itemSelected = items.get(position);
                    Log.d("AvatarHome", "Selected: " + itemSelected);

                    editer.setImage(currentCategory, itemSelected);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                onClick(view, position);
            }
        }));

    }

    /**
     * This method updates the bodyPartsRecyclerView after the user clicks on a category,
     * allowing them to change what items are displayed in the list
     * @param bodyPartsRecyclerView the recycler view to change the items of
     * @param bodyPartsList a list of strings to populate the view with
     */
    private void setBodyPartList(RecyclerView bodyPartsRecyclerView, ArrayList<String> bodyPartsList){
        BodyPartsAdapter bodyPartsAdapter = new BodyPartsAdapter(this, bodyPartsList);
        bodyPartsRecyclerView.setAdapter(bodyPartsAdapter);
    }

    /**
     * Returns a list of items in the category with the given ID
     * DUMMY METHOD should be replaced so these values are read from the database
     * instead of being hardcoded here
     * @param categoryID the id of the list
     * @return a list of strings to display
     */
    private ArrayList<String> getCategoryItems(int categoryID){
        ArrayList<String> result = new ArrayList<String>();

        switch (categoryID){
            case 0: //HEAD
                result.add("Crown");
                result.add("Pirate");
                break;
            case 1: //LEFT ARM
            case 2: //RIGHT ARM
                result.add("Strong");
                result.add("Robot");
                result.add("Cartoon");
                break;
            case 3:
            case 4:
                result.add("Strong");
                result.add("Robot");
                result.add("Cartoon");
                break;
            case 5:
                result.add("Surprised");
                break;
            case 6:
                result.add("Beach");
                result.add("Desert");
                result.add("Jungle");
                result.add("White");
                break;
            default:
                result.add("Head 1");
                result.add("Head 2");
                result.add("Head 3");
                result.add("Head 4");
                result.add("Head 5");
                result.add("Head 6");
                break;
        }
        return result;
    }

    /**
     * Creates a list of categories to display on the screen
     * DUMMY METHOD should get categories from database
     * @return a list of categories
     */
    public ArrayList<String> getCategories(){
        ArrayList<String> result = new ArrayList<>();
        result.add("HEAD");
        result.add("LEFT ARM");
        result.add("RIGHT ARM");
        result.add("LEFT LEG");
        result.add("RIGHT LEG");
        result.add("TORSO");
        result.add("BACKGROUND");
        return result;
    }
    /**
     * Populates the options dropdown menu in the top right of the activity
     * @param menu the menu to display
     * @return true when created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Code that handles what happens when you click on one of the menu items
     * @param item the menu item clicked on
     * @return boolean clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Check if user clicked on the refresh button
            case R.id.menu_refresh:
                //go back to main activity
                Intent refreshIntent = new Intent(this, MainActivity.class);
                finish();
                startActivity(refreshIntent);
                break;

            //Got to select/change user activity
            case R.id.menu_user:
                Intent userIntent = new Intent(this, UserHome.class);
                finish();
                startActivity(userIntent);
                break;

            //Got to avatar screen
            case R.id.menu_avatar:
                Intent avatarIntent = new Intent(this, AvatarHome.class);
                finish();
                startActivity(avatarIntent);
                break;

            //Go to edit task screen
            case R.id.menu_tasks:
                Intent editTaskIntent = new Intent(this, EditTask.class);
                finish();
                startActivity(editTaskIntent);
                break;
                //Go to main Activity
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}


