package com.example.assignment_5.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignment_5.Account;
import com.example.assignment_5.DataSource;
import com.example.assignment_5.MainActivity;
import com.example.assignment_5.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostFragment extends Fragment {

    private EditText addcontent;
    private ImageView addimg;
    private Button submitpost;
    private boolean isImageChanged = false;
    private Uri image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewpost = inflater.inflate(R.layout.fragment_post, container, false);
        addcontent = viewpost.findViewById(R.id.content_et);
        addimg = viewpost.findViewById(R.id.image);
        submitpost = viewpost.findViewById(R.id.posting);

        addimg.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        submitpost.setOnClickListener(view -> {
            if (addcontent.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Konten Masih Kosong", Toast.LENGTH_SHORT).show();
            } else if (!isImageChanged) {
                Toast.makeText(getActivity(), "Pilih Gambar Terlebih Dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Account newAccount = new Account("Adnan", "adnandi252", addcontent.getText().toString(), R.drawable.satu, image);
                DataSource.accounts.add(0, newAccount);
//                getParentFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainerView, new HomeFragment())
//                        .commit();

                // Intent to MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                // Pass any necessary data using intent extras
                intent.putExtra("newAccount", newAccount);
                startActivity(intent);

                Toast.makeText(getActivity(), "Post Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.homeFragment);

                // Reset flag after successful post
                isImageChanged = false;
            }
        });

        return viewpost;
    }
    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        image = data.getData();
                        addimg.setImageURI(image);
                        isImageChanged = true;
                    } else {
                        isImageChanged = false;
                    }
                }
            }
    );
}