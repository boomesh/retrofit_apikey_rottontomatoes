package com.boojoo.sumeshjohn.retrofit_apikey_rottontomatoes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.boojoo.sumeshjohn.retrofit_apikey_rottontomatoes.Models.RottenTomatoesMovieInfo;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;


public class MainActivity extends ActionBarActivity implements Callback<RottenTomatoesMovieInfo> {

    private static final String RT_API_KEY_PARAM = "apiKey";
    private static final String RT_API_KEY = "<<specify api key>>";
    private static final String RT_API_URL = "http://api.rottentomatoes.com/api/public/v1.0/";

    @Override
    public void success(RottenTomatoesMovieInfo rottenTomatoesMovieInfo, Response response) {
        Log.i(MainActivity.class.getCanonicalName(), "Response = "+response.getStatus());
        Log.i(MainActivity.class.getCanonicalName(), "\n== ROTTEN TOMATOES MOVIE INFO ==\n"+rottenTomatoesMovieInfo.toString());
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(MainActivity.class.getCanonicalName(), error.getLocalizedMessage());
    }

    public interface RottenTomatoesApi {
        @GET("/movies/{id}.json")
        void moviesInfo(@Path("id") String id, Callback<RottenTomatoesMovieInfo> cb);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam(RT_API_KEY_PARAM, RT_API_KEY);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(RT_API_URL).setRequestInterceptor(requestInterceptor).build();

        RottenTomatoesApi rottenTomatoesApi = restAdapter.create(RottenTomatoesApi.class);
        //looking up Toy Story 3
        rottenTomatoesApi.moviesInfo("770672122", this);
    }
}
