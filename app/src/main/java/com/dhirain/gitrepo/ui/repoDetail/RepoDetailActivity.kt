package com.dhirain.gitrepo.ui.repoDetail

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.dhirain.gitrepo.R
import com.dhirain.gitrepo.database.StoreHistoryAsync
import com.dhirain.gitrepo.model.ContributorModel
import com.dhirain.gitrepo.model.HistoryModel
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.ui.web.WebActivity
import com.dhirain.gitrepo.utils.Constants
import com.dhirain.gitrepo.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_repo_detail.*
import murgency.customer.ui.base.BaseActivity
import java.util.*

class RepoDetailActivity : BaseActivity(),RepoDetailView {

    companion object {
        const val IMAGE_TRANSITION_NAME:String = "IMAGE_TRANSITION_NAME";
    }

    private val TAG = "RepoDetailActivity";
    lateinit var repoModel : RepoModel
    lateinit var repoDetailPresenter : RepoDetailPresenter
    lateinit var contributorAdapter:ContributorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
        getIntentData()
        initUI()
        clickListener()
        intiAdapter()
        setupPresenter()
        ViewCompat.setTransitionName(repo_detail_image, IMAGE_TRANSITION_NAME)
        saveHistory()
    }

    private fun getIntentData() {
        repoModel = intent.extras!!.get(Constants.REPO_DETAIL) as RepoModel

    }

    override fun initUI() {
        setTitleWithBackPress("Repository Detail")
        ImageUtils.setImage(this,repoModel.onwer.avatar_url,repo_detail_image,R.drawable.user)
        repo_detail_descripition.setText(repoModel.description)
        repo_detail_name.setText(repoModel.fullName)
        repo_detail_language.setText(repoModel.language)
        repo_detail_project_Link.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun clickListener() {
        repo_detail_project_Link.setOnClickListener {
            view ->
            Toast.makeText(this,"opening Link",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,WebActivity::class.java)
            intent.putExtra(Constants.PROJECT_LINK,repoModel.htmlUrl)
            startActivity(intent)
        }
    }

    override fun setupPresenter() {
        Log.d(TAG, "setupPresenter: "+repoModel.toString());
        repoDetailPresenter = RepoDetailPresenter(this,this, repoModel.contributorsUrl)
    }

    private fun intiAdapter() {
        repo_detail_recycle.setHasFixedSize(true)
        contributorAdapter = ContributorAdapter(this)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        repo_detail_recycle.setLayoutManager(manager)
        repo_detail_recycle.setAdapter(contributorAdapter)

    }

    override fun intProgressbar() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setMessage(resources.getString(R.string.loading))
        mProgressDialog.setIndeterminate(true)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
    }

    override fun showProgress() {
        contributor_progress_bar.visibility = View.VISIBLE

    }

    override fun hideProgress() {
        contributor_progress_bar.visibility = View.GONE
    }

    override fun updateList(totalContributorList: MutableList<ContributorModel>?) {
        contributorAdapter.updateList(totalContributorList)
        if (totalContributorList!!.isNotEmpty()) {
            showListState()
        } else {
            showEmptyState()
        }
    }

    private fun showEmptyState() {
        no_result_found.visibility = View.VISIBLE
        repo_detail_recycle.visibility = View.GONE
    }

    private fun showListState() {
        no_result_found.visibility = View.GONE
        repo_detail_recycle.visibility = View.VISIBLE
    }

    private fun saveHistory(){
        StoreHistoryAsync(this).execute(HistoryModel(Constants.CONTENT_REPO,"Checked Repo "+repoModel.name, Calendar.getInstance().getTimeInMillis()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
