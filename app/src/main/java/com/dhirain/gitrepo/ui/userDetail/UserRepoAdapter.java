package com.dhirain.gitrepo.ui.userDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhirain.gitrepo.R;
import com.dhirain.gitrepo.model.RepoModel;
import com.dhirain.gitrepo.ui.repoDetail.RepoDetailActivity;
import com.dhirain.gitrepo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhirain Jain on 14-09-2017.
 */

public class UserRepoAdapter extends RecyclerView.Adapter<UserRepoAdapter.UserRepoHolder> {
    private static final String TAG = "RepoAdapter";
    private List<RepoModel> repoModels;
    private Context context;
    public UserRepoAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<RepoModel> newRepo) {
        repoModels = new ArrayList<>();
        this.repoModels = newRepo;
        notifyDataSetChanged();
    }

    @Override
    public UserRepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_repo_item, parent, false);
        return new UserRepoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserRepoHolder holder, int position) {
        if (repoModels != null) {
            showCurrentItem(holder, repoModels.get(position));
        }
    }

    private void showCurrentItem(final UserRepoHolder holder, final RepoModel repoModel) {
        holder.user_repo_name.setText(repoModel.getName());
        holder.user_repo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RepoDetailActivity.class);
                intent.putExtra(Constants.REPO_DETAIL, repoModel);
                context.startActivity(intent);
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

    public class UserRepoHolder extends RecyclerView.ViewHolder {
        public CardView user_repo_card;
        public TextView user_repo_name;

        public UserRepoHolder(View itemView) {
            super(itemView);
            user_repo_card = itemView.findViewById(R.id.user_repo_card);
            user_repo_name = itemView.findViewById(R.id.user_repo_name);

        }
    }
}
