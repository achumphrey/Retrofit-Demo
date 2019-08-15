package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofitdemo.model.GitHubRepoModel;
import com.example.retrofitdemo.network.GitHubClient;
import com.example.retrofitdemo.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private List<GitHubRepoModel> repos;
    private RecyclerView recyclerView;
    private RetrofitAdaptor retrofitAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_list);


        GitHubClient gitHubClient = RetrofitInstance.getRetrofitInstance().create(GitHubClient.class);

        Call<List<GitHubRepoModel>> call = gitHubClient.getRepose("achumphrey");

        call.enqueue(new Callback<List<GitHubRepoModel>>() {
            @Override
            public void onResponse(Call<List<GitHubRepoModel>> call, Response<List<GitHubRepoModel>> response) {

                repos = response.body();

                retrofitAdaptor = new RetrofitAdaptor(repos, new RetrofitAdaptor.OnRetrofitClickedListener() {
                    @Override
                    public void onItemClicked(GitHubRepoModel repoModel) {

                    }
                });

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(retrofitAdaptor);


                for (int i = 0; i < repos.size(); i++){
                    Log.i("MainActivity","onResponse: " + repos.get(i).getFullName());
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepoModel>> call, Throwable t) {

            }
        });


    }

}
