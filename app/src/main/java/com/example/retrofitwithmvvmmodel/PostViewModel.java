package com.example.retrofitwithmvvmmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PostViewModel extends ViewModel {

    private MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
    private PostRepository postRepository;

    public PostViewModel() {
        postRepository = new PostRepository();
        fetchPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return postsLiveData;
    }

    private void fetchPosts() {
        postRepository.getPosts(new PostRepository.PostRepositoryCallback() {
            @Override
            public void onPostsReceived(List<Post> posts) {
                postsLiveData.postValue(posts);
            }

            @Override
            public void onPostsFailed(String errorMessage) {
                // Handle API call failure, if needed
            }
        });
    }
}
