package com.dhirain.gitrepo.ui.repoDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhirain.gitrepo.R;
import com.dhirain.gitrepo.model.ContributorModel;
import com.dhirain.gitrepo.ui.userDetail.UserDetailAcitivity;
import com.dhirain.gitrepo.utils.Constants;
import com.dhirain.gitrepo.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhirain Jain on 14-09-2017.
 */

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder> {
    private static final String TAG = "RepoAdapter";
    private List<ContributorModel> contributorModels;
    private Context context;
    private int lastPosition;

    public ContributorAdapter(Context context) {
        this.context = context;
    }


    public void updateList(List<ContributorModel> newRepo) {
        contributorModels = new ArrayList<>();
        this.contributorModels = newRepo;
        notifyDataSetChanged();
    }

    @Override
    public ContributorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contributor_item, parent, false);
        return new ContributorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContributorViewHolder holder, int position) {
        if (contributorModels != null) {
            showCurrentItem(holder, contributorModels.get(position));
            //setAnimation(holder.parent,position);
        }
    }

    private void showCurrentItem(final ContributorViewHolder holder, final ContributorModel contributorModel) {
        holder.contributor_name.setText(contributorModel.getLogin());
        ImageUtils.setCircularImage(context, contributorModel.getAvatarUrl(), holder.contributor_image, R.drawable.user);
        holder.contributor_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetailAcitivity.class);
                intent.putExtra(Constants.USER_DETAIL,contributorModel);
                Activity activity = (Activity) context;
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        new Pair(holder.contributor_image, UserDetailAcitivity.IMAGE_TRANSITION_NAME));
                ActivityCompat.startActivity(activity, intent, options.toBundle());
            }
        });
    }


    @Override
    public int getItemCount() {
        if (contributorModels == null)
            return 0;
        else {
            return contributorModels.size();
        }
    }
    public class ContributorViewHolder extends RecyclerView.ViewHolder {
        public CardView contributor_card;
        public ImageView contributor_image;
        public TextView contributor_name;

        public ContributorViewHolder(View itemView) {
            super(itemView);
            contributor_card = itemView.findViewById(R.id.contributor_card);
            contributor_image = itemView.findViewById(R.id.contributor_image);
            contributor_name = itemView.findViewById(R.id.contributor_name);
           /* repo_description = itemView.findViewById(R.id.repo_description);
            repo_language = itemView.findViewById(R.id.repo_language);
            repo_score = itemView.findViewById(R.id.repo_score);
            repo_watch = itemView.findViewById(R.id.repo_watch);
            repo_star = itemView.findViewById(R.id.repo_star);*/
        }
    }
}
