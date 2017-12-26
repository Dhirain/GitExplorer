package com.dhirain.gitrepo.ui.history.view;

import com.dhirain.gitrepo.model.HistoryModel;

import java.util.List;

/**
 * Created by Dhirain Jain on 20-12-2017.
 */

public interface TimeLineView {
    void showListState();

    void showEmptyState();

    void updateList(List<HistoryModel> totalNewsList);

    void showProgress();

    void hideProgress();
}
