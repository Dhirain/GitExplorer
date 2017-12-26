package com.dhirain.gitrepo.ui.userDetail

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.dhirain.gitrepo.R
import com.dhirain.gitrepo.database.StoreHistoryAsync
import com.dhirain.gitrepo.model.ContributorModel
import com.dhirain.gitrepo.model.HistoryModel
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.ui.repoDetail.RepoDetailActivity
import com.dhirain.gitrepo.utils.Constants
import com.dhirain.gitrepo.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_user_detail_acitivity.*
import kotlinx.android.synthetic.main.content_user_detail_acitivity.*
import murgency.customer.ui.base.BaseActivity
import java.util.*



class UserDetailAcitivity : BaseActivity() , UserDetailView{

    companion object {
        const val IMAGE_TRANSITION_NAME:String = "IMAGE_TRANSITION_NAME";
    }

    private val TAG = "RepoDetailActivity";
    lateinit var contributorModel : ContributorModel
    lateinit var userDetailPresenter : UserDetailPresenter
    lateinit var userRepoAdapter: UserRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_acitivity)
        setSupportActionBar(toolbar)
        getIntentData()
        initUI()
        clickListener()
        intiAdapter()
        setupPresenter()
        ViewCompat.setTransitionName(user_image, RepoDetailActivity.IMAGE_TRANSITION_NAME)
        saveHistory()
    }
    private fun getIntentData() {
        contributorModel = intent.extras!!.get(Constants.USER_DETAIL) as ContributorModel

    }

    override fun initUI() {
        setTitleWithBackPress(contributorModel.login)
        ImageUtils.setImage(this,contributorModel.avatarUrl,user_image,R.drawable.user)
        intProgressbar()
    }

    override fun setTitleWithBackPress(title: String) {
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setTitle(title)
            mActionBar.setDisplayHomeAsUpEnabled(true) //to activate back pressed on home button press
            mActionBar.setDisplayShowHomeEnabled(false) //
        }
    }

    override fun clickListener() {
    }

    override fun setupPresenter() {
        userDetailPresenter = UserDetailPresenter(this,this, contributorModel.reposUrl)
    }

    private fun intiAdapter() {
        user_repo_recycler.hasFixedSize()
        val linearlayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearlayoutManager.orientation = LinearLayoutManager.VERTICAL
        user_repo_recycler.layoutManager = linearlayoutManager
        userRepoAdapter = UserRepoAdapter(this)
        user_repo_recycler.adapter = userRepoAdapter
    }


    override fun updateList(repoModels: MutableList<RepoModel>?) {
        userRepoAdapter.updateList(repoModels)
        if (repoModels!!.isNotEmpty()) {
            //showListState()
        } else {
            //showEmptyState()
        }
    }

    private fun saveHistory(){
        StoreHistoryAsync(this).execute(HistoryModel(Constants.CONTENT_CONTRIBUTOR,"Checked User "+contributorModel.login, Calendar.getInstance().getTimeInMillis()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
