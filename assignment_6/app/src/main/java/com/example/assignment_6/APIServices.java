package com.example.assignment_6;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {
    @GET("api/users")
    Call<UserResponse> getUsers(@Query("per_page") int page);

    @GET("api/users/{id}")
    Call<UserResponse> getUserById(@Path("id") int userId);
}
