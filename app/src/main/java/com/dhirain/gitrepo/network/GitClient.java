package com.dhirain.gitrepo.network;

import com.dhirain.gitrepo.model.ContributorModel;
import com.dhirain.gitrepo.model.RepoModel;
import com.dhirain.gitrepo.model.RepoNetworkResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Dhirain Jain on 14-09-2017.
 */

public interface GitClient {

    @GET("/search/repositories")
    Observable<RepoNetworkResponse> getRepo(
            @Query("q") String search,
            @Query("per_page") int per_page,
            @QueryMap Map<String,String> options
            );

    @GET
    Call<List<ContributorModel>> getContributor(@Url String link);

    @GET
    Call<List<RepoModel>> getUserRepo(@Url String link);
}
