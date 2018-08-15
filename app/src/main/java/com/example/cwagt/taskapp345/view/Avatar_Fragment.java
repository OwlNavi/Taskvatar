package com.example.cwagt.taskapp345.view;

/**
 * Reusable fragment used to display the Avatar in Main Activity and AvatarHome
 */

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cwagt.taskapp345.R;



public class Avatar_Fragment extends Fragment {
    private View view;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avatar_fragment, container, false);
        this.view = view;

        openAvatarHome(view);
        return view;
    }

    //onClickListener for clicking avatar
    private void openAvatarHome(View view) {

        ConstraintLayout fragmentHolder = view.findViewById(R.id.avatar_container);
        fragmentHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //From mainActivity, need to go back to something
                if(getActivity() instanceof MainActivity) {
                    Intent avatarIntent = new Intent(getActivity(), AvatarHome.class);
                    startActivity(avatarIntent);
                } //From AvatarHome, TODO add in animation instead here
                else if (getActivity() instanceof AvatarHome){
                    Intent avatarIntent = new Intent(getActivity(), AvatarHome.class);
                    getActivity().finish();
                    startActivity(avatarIntent);
                }

            }
        });
    }


}
