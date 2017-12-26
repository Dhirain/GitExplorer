package com.dhirain.gitrepo.ui.userDetail;

import com.dhirain.gitrepo.model.RepoModel;

import java.util.List;

/**
 * Created by Dhirain Jain on 25-12-2017.
 */

public interface UserDetailView {
    void showProgress();

    void hideProgress();

    void updateList(List<RepoModel> repoModels);

}
