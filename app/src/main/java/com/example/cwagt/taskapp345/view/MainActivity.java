package com.example.cwagt.taskapp345.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.helper.TaskAdapter;
import com.example.cwagt.taskapp345.object.Task;
import static com.example.cwagt.taskapp345.helper.DatabaseColumnNames.Task.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    View avatarImage;
    RecyclerView recyclerView;

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getColor(android.R.color.white));
        //toolbar.inflateMenu(R.menu.menu_main);

        avatarImage = findViewById(R.id.avatar);
        avatarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvatarHome.class);
                startActivity(intent);
            }

        });
        //if(!getAvatar(db)) sout("Error getting avatar");

		List<Task> taskList = DatabaseHelper.getTasksFromDatabase(context, TASK_NAME_TEXT + " = ?", new String[]{"Task text"});
		recyclerView = findViewById(R.id.taskList);
		TaskAdapter mAdapter = new TaskAdapter(taskList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
		recyclerView.setAdapter(mAdapter);

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			// Check if user triggered a refresh:
			case R.id.menu_refresh:
				// Signal SwipeRefreshLayout to start the progress indicator
				finish();
				startActivity(getIntent());


				// Start the refresh background task.
				// This method calls setRefreshing(false) when it's finished.


				return true;
		}

        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onDestroy() {
		//mDbHelper.close(); //close database connection
		DatabaseHelper.closeDatabase(this);
		super.onDestroy();
	}

	private void sout(String s) {
		System.out.println(s);
	}

}
