package com.dhirain.gitrepo.ui.filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.dhirain.gitrepo.R
import com.dhirain.gitrepo.model.FilterModel
import com.dhirain.gitrepo.utils.Constants
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private val TAG = "FilterActivity";

    var sort_by : String? = null
    var order_by : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        clickListner()
    }

    private fun clickListner() {
        stars_radiobutton.setOnClickListener {
            view -> sort_by = "stars"
        }

        forks_radiobutton.setOnClickListener {
            view -> sort_by = "forks"
        }

        updated_radiobutton.setOnClickListener {
            view -> sort_by = "updated"
        }

        asc_radiobutton.setOnClickListener {
            view -> order_by = "asc"
        }

        desc_radiobutton.setOnClickListener {
            view -> sort_by = "desc"
        }

        filter_apply.setOnClickListener {
            view ->

            if(sort_by==null && order_by==null && filter_language.text.toString().equals("")){
                Toast.makeText(this,"Please select some Filter",Toast.LENGTH_SHORT).show()
            }
            else {
                val filterModel = FilterModel(sort_by, order_by, filter_language.text.toString())
                Log.d(TAG, "clickListner: " + filterModel.toString());
                val intent = Intent()
                intent.putExtra(Constants.FILTER_MODEL, filterModel)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        filter_cancel.setOnClickListener {
            onBackPressed()
        }
    }
}
