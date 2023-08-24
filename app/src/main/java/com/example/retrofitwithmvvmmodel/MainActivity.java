package com.example.retrofitwithmvvmmodel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.TextView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPosts;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPosts = findViewById(R.id.textViewPosts);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                displayPosts(posts);
            }
        });
    }

    private void displayPosts(List<Post> posts) {
        StringBuilder sb = new StringBuilder();
        for (Post post : posts) {
            sb.append("Title: ").append(post.getTitle()).append("\n");
            sb.append("Body: ").append(post.getBody()).append("\n\n");
        }
        textViewPosts.setText(sb.toString());
    }
}
