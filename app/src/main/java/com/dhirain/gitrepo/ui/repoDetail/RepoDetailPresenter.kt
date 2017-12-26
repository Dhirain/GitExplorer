package com.dhirain.gitrepo.ui.repoDetail

import android.content.Context
import android.util.Log
import com.dhirain.gitrepo.model.ContributorModel
import com.dhirain.gitrepo.network.GitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Dhirain Jain on 23-12-2017.
 */
class RepoDetailPresenter(val context: Context, val detailView: RepoDetailView,val contributorLink: String) {
    private val TAG = "RepoDetailPresenter";
    init {
        detailView.showProgress()
        getDatafromNetwork();
    }

    fun getDatafromNetwork() {
        val contributors = GitService.instance().getContributor(contributorLink)
        contributors.enqueue(object : Callback<List<ContributorModel>> {
            override fun onResponse(call: Call<List<ContributorModel>>, response: Response<List<ContributorModel>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    detailView.updateList(response.body())
                    detailView.hideProgress()
                }
            }

            override fun onFailure(call: Call<List<ContributorModel>>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                detailView.hideProgress()
                t.printStackTrace()
            }
        })
    }

}
