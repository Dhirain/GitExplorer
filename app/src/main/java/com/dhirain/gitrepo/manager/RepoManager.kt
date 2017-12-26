package com.dhirain.gitrepo.manager

import com.dhirain.gitrepo.model.RepoNetworkResponse
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.network.GitService
import com.dhirain.gitrepo.utils.Constants
import com.dhirain.gitrepo.utils.GenericCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dhirain Jain on 24-12-2017.
 */
object RepoManager {

    fun getRepos(search: String, map: HashMap<String, String>, genericCallback: GenericCallback<List<RepoModel>>) {

        GitService.instance().getRepo(search, Constants.PAGE_LIMIT, map)
                .map { t: RepoNetworkResponse -> t.items }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data -> genericCallback.onRequestSuccess(data) },
                        { throwable -> genericCallback.onRequestFailure(throwable, "Newtork fail :-(") }

                )

    }
}

