package com.example.retrofitwithmvvmmodel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApiService {
    @GET("posts")
    Call<List<Post>> getPosts();
}
