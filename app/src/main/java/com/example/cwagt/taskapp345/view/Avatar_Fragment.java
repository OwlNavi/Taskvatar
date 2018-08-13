package com.example.cwagt.taskapp345.view;

/**
 * Reusable fragment used to display the Avatar in Main Activity and AvatarHome
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cwagt.taskapp345.R;



public class Avatar_Fragment extends Fragment {
    private Context context;
    private View view;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avatar_fragment, container, false);
        this.view = view;
        return view;
    }



}
