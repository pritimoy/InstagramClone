package com.example.instagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfilHobbies,
            edtProfileSport;

    private Button btnProfieUpdate;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtUserName);
        edtProfileBio = view.findViewById(R.id.edtUserBio);
        edtProfileProfession = view.findViewById(R.id.edtUserProfession);
        edtProfilHobbies = view.findViewById(R.id.edtUserHobbis);
        edtProfileSport = view.findViewById(R.id.edtUserSpots);
        btnProfieUpdate = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }
        else {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }

        if (parseUser.get("profileBio") == null){
            edtProfileBio.setText("");
        }
        else {
            edtProfileBio.setText(parseUser.get("profileBio").toString());
        }

        if (parseUser.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }
        else {
            edtProfileProfession.setText(parseUser.get("profileProfession").toString());
        }

        if (parseUser.get("profileHobbies") == null){
            edtProfilHobbies.setText("");
        }
        else {
            edtProfilHobbies.setText(parseUser.get("profileHobbies").toString());
        }
        if (parseUser.get("profileSport") == null){
            edtProfileSport.setText("");
        }
        else {
            edtProfileSport.setText(parseUser.get("profileSport").toString());
        }




        btnProfieUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtProfilHobbies.getText().toString());
                parseUser.put("profileSport", edtProfileSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(),"Info updated",
                                    Toast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
                        }
                        else {

                            FancyToast.makeText(getContext(),"Failed updated",
                                    Toast.LENGTH_LONG, FancyToast.ERROR,false).show();

                        }
                    }
                });
            }
        });


        return view;

    }

}
