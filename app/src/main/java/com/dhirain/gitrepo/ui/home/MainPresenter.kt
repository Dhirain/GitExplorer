package com.dhirain.gitrepo.ui.home

import android.content.Context
import android.util.Log
import com.dhirain.gitrepo.manager.RepoManager
import com.dhirain.gitrepo.model.FilterModel
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.utils.GenericCallback

/**
 * Created by Dhirain Jain on 23-12-2017.
 */
class MainPresenter(val context: Context, val mainView: MainView) {
    private val TAG = "MainPresenter";
    var searchString:String? = "test"
    var filterHash:HashMap<String,String> = HashMap()
    init {
        getDatafromNetwork();
    }

    fun getDatafromNetwork() {
        mainView.showProgress()
        if(searchString== null || searchString.equals(""))
            searchString = "test"

        RepoManager.getRepos(searchString!!, filterHash, object : GenericCallback<List<RepoModel>> {
            override fun onRequestSuccess(objectToReturn: List<RepoModel>?) {
                Log.d(TAG, "onRequestSuccess: " + objectToReturn.toString())
                mainView.updateList(objectToReturn)
                mainView.hideProgress()
            }
            override fun onRequestFailure(error: Throwable?, errorMessage: String?) {
                Log.d(TAG, "onRequestFailure: $errorMessage");
                mainView.hideProgress()
                error?.printStackTrace()
            }
        });
    }

    fun search(searchString: String?) {
        if(searchString!=null) {
            mainView.showProgress()
            this.searchString = searchString
            getDatafromNetwork()
        }
    }

    fun setFilter(filterModel: FilterModel) {

        if(filterModel.sort_by!=null){
            filterHash.put("sort",filterModel.sort_by)
        }
        if(filterModel.order_by!=null){
            filterHash.put("order",filterModel.order_by)
        }
        if(filterModel.language!=null && !filterModel.language.equals("")){
            searchString = searchString + "+language:" + filterModel.language
        }
        getDatafromNetwork()

    }

}