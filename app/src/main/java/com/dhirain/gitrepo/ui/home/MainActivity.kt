package com.dhirain.gitrepo.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dhirain.gitrepo.R
import com.dhirain.gitrepo.database.StoreHistoryAsync
import com.dhirain.gitrepo.model.FilterModel
import com.dhirain.gitrepo.model.HistoryModel
import com.dhirain.gitrepo.model.RepoModel
import com.dhirain.gitrepo.ui.filter.FilterActivity
import com.dhirain.gitrepo.ui.history.activity.TimeLineActivity
import com.dhirain.gitrepo.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import murgency.customer.ui.base.BaseActivity
import java.util.*

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainActivity";
    lateinit var mainPresenter: MainPresenter
    lateinit var repoAdapter: RepoAdapter
    val FILTER_REQUEST_CODE: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        clickListener()
        initAdapter()
        setupPresenter()
    }

    override fun initUI() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        intProgressbar()
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initAdapter() {
        main_recycler.hasFixedSize()
        val linearlayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearlayoutManager.orientation = LinearLayoutManager.VERTICAL
        main_recycler.layoutManager = linearlayoutManager
        repoAdapter = RepoAdapter(this)
        main_recycler.adapter = repoAdapter
        val mCallback = SwipeTouchHelper(repoAdapter)
        val swipeTouchHelper = ItemTouchHelper(mCallback)
        swipeTouchHelper.attachToRecyclerView(main_recycler)
    }


    override fun clickListener() {
        nav_view.setNavigationItemSelectedListener(this)
        fab.setOnClickListener {
            view ->
            val intent = Intent(this@MainActivity, FilterActivity::class.java)
            this.startActivityForResult(intent, FILTER_REQUEST_CODE)
        }


    }

    override fun setupPresenter() {
        mainPresenter = MainPresenter(this, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var searchString: String? = null
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            searchView.queryHint = "Search Repos"
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.d(TAG, "onQueryTextSubmit: $query");
                    mainPresenter.search(searchString)
                    StoreHistoryAsync(this@MainActivity).execute(HistoryModel(Constants.CONTENT_SEARCH, "searched " + searchString, Calendar.getInstance().getTimeInMillis()))
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    //Give query to presenter
                    searchString = newText
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun updateList(repoModelList: MutableList<out RepoModel>?) {
        repoAdapter.updateList(repoModelList)
        if (repoModelList!!.isNotEmpty()) {
            showListState()
        } else {
            showEmptyState()
        }
    }

    private fun showEmptyState() {
        no_result_found.visibility = View.VISIBLE
        main_recycler.visibility = View.GONE
    }

    private fun showListState() {
        no_result_found.visibility = View.GONE
        main_recycler.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.history) {
            Log.d(TAG, "onNavigationItemSelected: history clicked");
            val intent = Intent(this@MainActivity, TimeLineActivity::class.java)
            startActivity(intent)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILTER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val filterModel = data.getParcelableExtra<FilterModel>(Constants.FILTER_MODEL)
                Log.d(TAG, "onActivityResult: " + filterModel.toString());
                mainPresenter.setFilter(filterModel)
            }
        }
    }
}
