package com.example.retrofitdemo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.model.GitHubRepoModel;

import java.util.ArrayList;
import java.util.List;

public class RetrofitAdaptor extends RecyclerView.Adapter<RetrofitAdaptor.RetrofitViewHolder> {

    private List<GitHubRepoModel> repos;
    private OnRetrofitClickedListener listener;

    public RetrofitAdaptor(List<GitHubRepoModel> repos, OnRetrofitClickedListener listener) {
        this.repos = repos;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RetrofitAdaptor.RetrofitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_retrofit,parent,false);
        return new RetrofitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitAdaptor.RetrofitViewHolder holder, int position) {

        GitHubRepoModel repoModel = repos.get(position);

        holder.tvName.setText(repoModel.getName());
        holder.tvId.setText(""+repoModel.getId());
        holder.tvOwnerLogin.setText(repoModel.getOwner().getLogin());
        holder.bind(repoModel, listener);

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RetrofitViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvId;
        public TextView tvOwnerLogin;

        public RetrofitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
            tvOwnerLogin = itemView.findViewById(R.id.tv_owner_login);
        }

        public void bind(final GitHubRepoModel repoModel, final OnRetrofitClickedListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(repoModel);
                }
            });
        }

   }

    public interface OnRetrofitClickedListener {

        void onItemClicked(GitHubRepoModel repoModel);

    }

}
