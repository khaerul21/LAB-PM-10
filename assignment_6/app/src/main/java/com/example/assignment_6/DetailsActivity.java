package com.example.assignment_6;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
    private ImageView avatar_details;
    private TextView name_details, email_details, losttextdetails;
    private ProgressBar loaddetails;
    private LinearLayout detailayouts, lostdetails;
    private Button reloadDetails;
    Handler handler = new Handler(Looper.getMainLooper());
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        avatar_details = findViewById(R.id.details_avatar);
        name_details = findViewById(R.id.details_name);
        email_details = findViewById(R.id.details_email);
        loaddetails = findViewById(R.id.loadingdetails);
        detailayouts = findViewById(R.id.lineardetails);
        losttextdetails = findViewById(R.id.losttextdetails);
        lostdetails = findViewById(R.id.lostdetails);
        reloadDetails = findViewById(R.id.reload_details);

        lostdetails.setVisibility(View.GONE);
        detailayouts.setVisibility(View.GONE);
        loaddetails.setVisibility(View.VISIBLE);

        showUsers();

    }
    private void showUsers() {
        int userId = getIntent().getIntExtra("id", -1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServices apiService = retrofit.create(APIServices.class);
        Call<UserResponse> call = apiService.getUsers(userId);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                loaddetails.setVisibility(View.GONE);
                detailayouts.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null && userResponse.getData() != null) {
                        List<User> userList = userResponse.getData();
                        User user = getUserById(userList, userId);
                        if (user != null) {
                            Picasso.get().load(user.getAvatar()).into(avatar_details);
                            name_details.setText(user.getFirst_name() + " " + user.getLast_name());
                            email_details.setText(user.getEmail());
                        } else {
                            Toast.makeText(DetailsActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetailsActivity.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailsActivity.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                loaddetails.setVisibility(View.GONE);
                lostdetails.setVisibility(View.VISIBLE);
                detailayouts.setVisibility(View.GONE);
                reloadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loaddetails.setVisibility(View.VISIBLE);
                        lostdetails.setVisibility(View.GONE);
                        showUsers();
                    }
                });
            }
        });
    }
    private User getUserById(List<User> userList, int userId) {
        for (User user : userList) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
}