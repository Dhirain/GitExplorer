package com.dhirain.gitrepo.ui.repoDetail;

import com.dhirain.gitrepo.model.ContributorModel;

import java.util.List;

/**
 * Created by Dhirain Jain on 25-12-2017.
 */

public interface RepoDetailView {
    void updateList(List<ContributorModel> totalContributorList);

    void showProgress();

    void hideProgress();
}
