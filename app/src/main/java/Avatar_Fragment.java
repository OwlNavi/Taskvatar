
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.example.cwagt.taskapp345.R;
import com.example.cwagt.taskapp345.object.Avatar;
import com.example.cwagt.taskapp345.view.AvatarHome;
import com.example.cwagt.taskapp345.view.MainActivity;


import java.util.ArrayList;
import java.util.List;


public class Avatar_Fragment extends Fragment  {
    ImageView base, leftArm, rightArm, leftLeg, rightLeg;
    List<ImageView> body = new ArrayList<>();
    Avatar avatar = new Avatar();
    final int DELAY = 1; //how fast the avatar should move
    private boolean moveAvatar = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avatar_fragment,container,false);

        base = view.findViewById(R.id.base);
        leftArm = view.findViewById(R.id.left_arm);
        rightArm = view.findViewById(R.id.right_arm);
        leftLeg = view.findViewById(R.id.left_leg);
        rightLeg = view.findViewById(R.id.right_leg);

        //build-a-body
        body.add(base);
        body.add(leftArm);
        body.add(rightArm);
        body.add(leftLeg);
        body.add(rightLeg);

        leftArm.setRotation(avatar.getRightArmRotation());
        rightArm.setRotation(avatar.getRightArmRotation());
        leftLeg.setRotation(avatar.getLeftLegRotation());
        rightLeg.setRotation(avatar.getRightLegRotation());
        //runs without a timer by reposting this handler at the end of the runnable
        //see https://stackoverflow.com/questions/4597690/android-timer-how-to
        final Handler timerHandler = new Handler();
        final Runnable timerRunnable = new Runnable() {
            int midX = (int) base.getX();
            //int midY = (int) base.getY();
            final int maxDistance = 75;
            final int maxRotation = 25;
            int direction = 0; //0 right 1 left

            int midRotation = (int) leftArm.getRotation();
            int rotationDirection = 0;
            @Override
            public void run() {

                if(base.getX() > midX + maxDistance) direction = 1; //turn left
                if(base.getX() < midX - maxDistance) direction = 0; //turn right
                if(leftArm.getRotation() > midRotation + maxRotation) rotationDirection = 1;
                if(leftArm.getRotation() < midRotation - maxRotation) rotationDirection = 0;

                //move
                moveAvatar(15,0, direction);

                wave_left_arm(rotationDirection);

                timerHandler.postDelayed(this, DELAY);
            }
        };
        //move avatar
        base.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!moveAvatar) {
                    timerHandler.removeCallbacks(timerRunnable);
                    moveAvatar = true;
                } else {
                    timerHandler.postDelayed(timerRunnable, DELAY);
                }
            }
        });

        ConstraintLayout avatarImage = view.findViewById(R.id.avatar);
        avatarImage.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View v) {

                 Intent intent = new Intent(getActivity(), AvatarHome.class);
                 startActivity(intent);

         }

        });




        return view;
    }
    //roatates the left arm in a certain direction
    public void wave_left_arm(int direction) {
        int armRotation = (int) avatar.getLeftArmRotation();

        if(direction == 0) avatar.setLeftArmRotation(armRotation + 10);
        else avatar.setLeftArmRotation(armRotation - 10);

        leftArm.setRotation(avatar.getLeftArmRotation());
    }
    //moves the avatar by given x and y coords to the right, no direction
    public void moveAvatar(int deltaX, int deltaY){
        for(ImageView limb: body){
            limb.setX(limb.getX() + deltaX);
            limb.setY(limb.getY() + deltaY);
        }
    }
    //moves the avatar by given x and y coords and direction 0 right 1 left
    public void moveAvatar(int deltaX, int deltaY, int direction){
        if(direction == 0){ //move right
            for(ImageView limb: body){
                limb.setX(limb.getX() + deltaX);
                limb.setY(limb.getY() + deltaY);
            }
        } else { //move left
            for(ImageView limb: body){
                limb.setX(limb.getX() - deltaX);
                limb.setY(limb.getY() - deltaY);
            }
        }

    }


}
