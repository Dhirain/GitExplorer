package com.dhirain.gitrepo.ui.userDetail

import android.content.Context
import android.util.Log
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.network.GitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Dhirain Jain on 23-12-2017.
 */
class UserDetailPresenter(val context: Context, val detailView: UserDetailView, val userReposLink: String) {
    private val TAG = "RepoDetailPresenter";
    init {
        detailView.showProgress()
        getDatafromNetwork();
    }

    fun getDatafromNetwork() {
        val contributors = GitService.instance().getUserRepo(userReposLink)
        contributors.enqueue(object : Callback<List<RepoModel>> {
            override fun onResponse(call: Call<List<RepoModel>>, response: Response<List<RepoModel>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: " + response.body().toString())
                    detailView.updateList(response.body())
                    detailView.hideProgress()
                }
            }

            override fun onFailure(call: Call<List<RepoModel>>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                detailView.hideProgress()
                t.printStackTrace()
            }
        })
    }

}
