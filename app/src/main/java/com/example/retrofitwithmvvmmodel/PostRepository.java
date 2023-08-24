package com.example.retrofitwithmvvmmodel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private JsonPlaceholderApiService apiService;

    public PostRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(JsonPlaceholderApiService.class);
    }

    public void getPosts(final PostRepositoryCallback callback) {
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    if (posts != null) {
                        callback.onPostsReceived(posts);
                    }
                } else {
                    callback.onPostsFailed("Failed to fetch posts");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onPostsFailed("API call failed: " + t.getMessage());
            }
        });
    }

    public interface PostRepositoryCallback {
        void onPostsReceived(List<Post> posts);
        void onPostsFailed(String errorMessage);
    }
}
