package com.dhirain.gitrepo.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhirain.gitrepo.R;
import com.dhirain.gitrepo.model.RepoModel;
import com.dhirain.gitrepo.ui.repoDetail.RepoDetailActivity;
import com.dhirain.gitrepo.utils.Constants;
import com.dhirain.gitrepo.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dhirain Jain on 14-09-2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private static final String TAG = "RepoAdapter";
    private List<RepoModel> repoModels;
    private Context context;
    private int lastPosition;

    public RepoAdapter(Context context) {
        this.context = context;
    }


    public void updateList(List<RepoModel> newRepo) {
        repoModels = new ArrayList<>();
        this.repoModels = newRepo;
        this.lastPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        if (repoModels != null) {
            showCurrentItem(holder, repoModels.get(position));
            //setAnimation(holder.parent,position);
        }
    }

    private void showCurrentItem(final RepoViewHolder holder, final RepoModel repoModel) {
        holder.repo_description.setText(repoModel.getDescription());
        holder.repo_name.setText(repoModel.getName());
        holder.repo_language.setText("Language: "+ repoModel.getLanguage());
        holder.repo_score.setText(repoModel.getScore());
        holder.repo_watch.setText(Integer.toString(repoModel.getWatchers()));
        holder.repo_star.setText(Integer.toString(repoModel.getStargazersCount()));
        Log.d(TAG, "showCurrentItem: "+repoModel.getOnwer().getAvatar_url());
        ImageUtils.setImage(context, repoModel.getOnwer().getAvatar_url(), holder.repo_image, R.drawable.user);
        holder.repo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra(Constants.REPO_DETAIL,repoModel);
                Activity activity = (Activity) context;
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        new Pair(holder.repo_image, RepoDetailActivity.IMAGE_TRANSITION_NAME));
                ActivityCompat.startActivity(activity, intent, options.toBundle());
            }
        });
    }


    @Override
    public int getItemCount() {
        if (repoModels == null)
            return 0;
        else {
            return repoModels.size();
        }
    }


    public void swap(int adapterPosition, int adapterPosition1) {
        Collections.swap(repoModels, adapterPosition, adapterPosition1);
        notifyItemMoved(adapterPosition, adapterPosition1);
    }

    public void remove(int adapterPosition) {
        repoModels.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

}
