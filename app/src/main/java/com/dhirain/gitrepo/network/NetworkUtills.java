package com.dhirain.gitrepo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.dhirain.gitrepo.GitRepoApp;

/**
 * Created by Dhirain Jain on 14-09-2017.
 */

public class NetworkUtills {

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) GitRepoApp.singleton().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        boolean isNetworkPresent = netInfo != null && netInfo.isConnectedOrConnecting();
        if (!isNetworkPresent) {
            Toast.makeText(GitRepoApp.singleton().getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        return isNetworkPresent;
    }


}
