package com.dhirain.gitrepo.ui.home;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhirain.gitrepo.R;

/**
 * Created by Dhirain Jain on 19-12-2017.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {
    public CardView repo_card;
    public ImageView repo_image;
    public TextView repo_name, repo_description, repo_language;
    public AppCompatTextView repo_score, repo_watch, repo_star;

    public RepoViewHolder(View itemView) {
        super(itemView);
        repo_card = itemView.findViewById(R.id.repo_card);
        repo_image = itemView.findViewById(R.id.repo_image);
        repo_name = itemView.findViewById(R.id.repo_name);
        repo_description = itemView.findViewById(R.id.repo_description);
        repo_language = itemView.findViewById(R.id.repo_language);
        repo_score = itemView.findViewById(R.id.repo_score);
        repo_watch = itemView.findViewById(R.id.repo_watch);
        repo_star = itemView.findViewById(R.id.repo_star);
    }
}
