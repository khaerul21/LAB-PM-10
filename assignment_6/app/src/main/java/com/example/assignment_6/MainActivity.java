package com.example.assignment_6;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progress1, progress2;
    private Button more_button, reload_button;
    private LinearLayout lostconnect;
    private TextView notification_lostconnect;
    private UserAdapter userAdapter;
    private int per_page = 6;

    Handler handler = new Handler(Looper.getMainLooper());

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress1 = findViewById(R.id.progress1);
        progress2 = findViewById(R.id.progress2);
        more_button = findViewById(R.id.more_button);
        reload_button = findViewById(R.id.reload_button);
        lostconnect = findViewById(R.id.lostconnect);
        notification_lostconnect = findViewById(R.id.notification_lostconnect);

        recyclerView = findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        lostconnect.setVisibility(View.GONE);
        progress1.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        more_button.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);

        loadUsers();

        progress2.setVisibility(View.GONE);
        more_button.setOnClickListener(view -> {
            more_button.setVisibility(View.GONE);
            progress2.setVisibility(View.VISIBLE);
            per_page += 6;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 1000);
            loadUsers();

        });
    }

    public void loadUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIServices apiService = retrofit.create(APIServices.class);
        Call<UserResponse> call = apiService.getUsers(per_page);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    more_button.setVisibility(View.VISIBLE);
                    progress1.setVisibility(View.GONE);
                    progress2.setVisibility(View.GONE);
                    UserResponse userResponse = response.body();
                    List<User> userList = userResponse.getData();
                    userAdapter = new UserAdapter(MainActivity.this, userList);
                    recyclerView.setAdapter(userAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progress1.setVisibility(View.GONE);
                lostconnect.setVisibility(View.VISIBLE);
                more_button.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                more_button.setVisibility(View.GONE);
                progress2.setVisibility(View.GONE);
                reload_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lostconnect.setVisibility(View.GONE);
                        progress1.setVisibility(View.VISIBLE);
                        loadUsers();
                    }
                });
            }
        });
    }

}