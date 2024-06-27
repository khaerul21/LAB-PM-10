package com.example.assignment_2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("WrongViewCast")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String nama;
    private String username;
    private Uri gambar;

    private ActivityResultLauncher<Intent> openGaleri = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            // Set the selected image to the ImageView
                            ImageView imageView = findViewById(R.id.pilihGambar);
                            gambar = selectedImageUri;
                            imageView.setImageURI(selectedImageUri);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOne = findViewById(R.id.buttonOne);
        buttonOne.setOnClickListener(this);

        ImageView imageView = findViewById(R.id.pilihGambar);
        imageView.setOnClickListener(this);
    }

    @Override







    public void onClick(View view) {
        if (view.getId() == R.id.buttonOne) {
            EditText inputNama = findViewById(R.id.nama);
            nama = inputNama.getText().toString();
            EditText inputUsername = findViewById(R.id.username);
            username = inputUsername.getText().toString();

            if (gambar!=null && !nama.isEmpty() && !username.isEmpty()) {
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                intent.putExtra("gambar", gambar.toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Pilih gambar dan isi semua inputan", Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.pilihGambar) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            openGaleri.launch(Intent.createChooser(intent, "Pilih gambar"));
        }
    }
}