package com.example.dell.lawera;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MeFragment extends Fragment {
    ImageView profilePicture;
    Drawable d;
    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

//        Glide.with(getContext()).load(R.drawable.profile).fitCenter().into(profilePicture);

//        Toast.makeText(getContext(),"no",Toast.LENGTH_SHORT).show();

        LinearLayout edit = rootView.findViewById(R.id.edit_profile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),UserProfile.class);
                startActivity(intent);

            }
        });
//        //------profile picture---------------------
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");
        if (mypath.exists()){

//            loadImageFromStorage(cw.getFilesDir().getAbsolutePath());
            try {
                File f=new File(mypath, "profile.jpg");
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                ImageView profilePicture = rootView.findViewById(R.id.profile_picture_image_view);
                profilePicture.setImageBitmap(b);
                Toast.makeText(getContext(),"yes",Toast.LENGTH_SHORT).show();
                d = new BitmapDrawable(getResources(),b);
                profilePicture = rootView.findViewById(R.id.profile_picture_image_view);

                Glide.with(getContext()).load(d).fitCenter().into(profilePicture);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }else {
            profilePicture = rootView.findViewById(R.id.profile_picture_image_view);
            Glide.with(getContext()).load(R.drawable.profile).fitCenter().into(profilePicture);

//            Glide.with(getContext()).load(R.drawable.profile).fitCenter().into(pprofilePicture);
            Toast.makeText(getContext(),"no",Toast.LENGTH_SHORT).show();
        }
        //---------------end of profile picture part ---------------------


        return rootView;
    }

}
