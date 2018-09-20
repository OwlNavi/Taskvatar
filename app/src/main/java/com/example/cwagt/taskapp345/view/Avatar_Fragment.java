package com.example.cwagt.taskapp345.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.helper.DatabaseHelper;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.object.User;

import java.util.HashMap;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Reusable fragment used to display the Avatar in Main Activity and AvatarHome
 */

public class Avatar_Fragment extends Fragment {
	private boolean avatarInit = false;
	//Image Views used to display the Avatar
    private ImageView base;
    private ImageView hat;
    private ImageView leftArm;
    private ImageView rightArm;
    private ImageView leftLeg;
    private ImageView rightLeg;
    private ConstraintLayout background; //should this be an ImageView?

	@Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public Avatar_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.avatar_fragment, container, false);
        avatarInit = initAvatar(view);
        openAvatarHome(view);
        return view;
    }

    private void showMessage(String message){
		//show the user a message to let them know they must complete validation
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMessage(message)
				.setTitle("Missing body part");
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// User clicked OK button

			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

    @Override
    public void onResume() {
        super.onResume();
        if(avatarInit) {
            //Log.e("AVATAR TAG","AVATAR IS SET");
			Log.d("Avatar_Fragment","Loading avatar from database");

            //get avatar from database
			Context context = this.getContext();
			SharedPreferences preferences = getDefaultSharedPreferences(context);
			Long userID = preferences.getLong("currentUser", 0);
			User thisUser = DatabaseHelper.readUser(context, userID);

			/*debug stuff*/
			Log.d("AvatarFragment", "Current user: " + userID);

			//replace current avatar with loaded avatar
			//View view = findViewById(android.R.id.content);
			Avatar avatar = thisUser.getAvatar();
			HashMap<String, Integer> bodyParts = avatar.getBodyParts();

			String message = "";
			try{
				setBase(bodyParts.get("base"));
       		} catch (Exception e) {
				Log.e("error","base");
				message += "A base!\n";
			}

			try{
				setHat(bodyParts.get("hat"));
			} catch(Exception e) {
				Log.e("error","hat");
				message += "A hat!\n";
				if(getActivity() instanceof MainActivity) {
					Intent avatarIntent = new Intent(getActivity(), AvatarHome.class);
					startActivity(avatarIntent);
				}
			}

			try{
				setLeftArm(bodyParts.get("leftArm"));
			} catch (Exception e) {
				Log.e("error","left arm");
				message += "A left arm!\n";
			}

			try{
				setRightArm(bodyParts.get("rightArm"));
			} catch (Exception e) {
				Log.e("error","right arm");
				message += "A right arm!\n";			}

			try{
				setLeftLeg(bodyParts.get("leftLeg"));
			} catch (Exception e) {
				Log.e("error","left leg");
				message += "A left leg!\n";			}

			try{
				setRightLeg(bodyParts.get("rightLeg"));
			} catch (Exception e) {
				Log.e("error","right leg");
				message += "A right leg!\n";			}

			try {
				setBackground(bodyParts.get("background"));
			} catch (Exception e) {
				Log.e("error","background");
				message += "A background!\n";
			}

			if(!message.equals("")){
				//errors found
				showMessage("Avatar is missing: \n" + message);
				message = "";
			}

			//now for rotations
			float leftArmRotation = avatar.getLeftArmRotation();
			float rightArmRotation = avatar.getRightArmRotation();
			float leftLegRotation = avatar.getLeftLegRotation();
			float rightLegRotation = avatar.getRightLegRotation();

			leftArm.setRotation(leftArmRotation);
			rightArm.setRotation(rightArmRotation);
			leftLeg.setRotation(leftLegRotation);
			rightLeg.setRotation(rightLegRotation);

		} else {
            Log.e("AVATAR TAG","AVATAR IS NOT SET");

        }

    }

    /**
     * Initiate Avatar --> allows changes to ImageViews
     *
     * */
    private boolean initAvatar(View view) {
        this.base = view.findViewById(R.id.base);
        this.hat = view.findViewById(R.id.hat);
        this.leftArm = view.findViewById(R.id.left_arm);
        this.leftLeg = view.findViewById(R.id.left_leg);
        this.rightLeg = view.findViewById(R.id.right_leg);
        this.rightArm = view.findViewById(R.id.right_arm);
        this.background = view.findViewById(R.id.avatar_container);
        //rotations are 0 degrees
        return true;
    }

    /**
     * Set ImageViews to IDs passed by AvatarEditor when click on item within RecyclerView
     *
     * @param id the ID of the item
     */
	public void setBase(int id) { base.setImageResource(id); }
	public void setHat(int id) { hat.setImageResource(id); }
	public void setLeftArm(int id) { leftArm.setImageResource(id); }
	public void setRightArm(int id) { rightArm.setImageResource(id); }
	public void setLeftLeg(int id) { leftLeg.setImageResource(id); }
    public void setRightLeg(int id) { rightLeg.setImageResource(id); }
    public void setBackground(int id){ background.setBackgroundResource(id); }

    /**
     * Creates and returns a new instance of Avatar Fragment
     * @return avatar fragment instance
     */
    public static Avatar_Fragment newInstance() {
        return new Avatar_Fragment();
    }

    /**
     * onClickListener for clicking the avatar
     * Takes a view, returns void
     * */
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

                }

            }
        });
    }


}
