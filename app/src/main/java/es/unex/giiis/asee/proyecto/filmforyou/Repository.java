package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.ImdbApiEndPoint;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieList;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Search;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.MovieDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private ImdbApiEndPoint topImdbApiEndPointInterface = new Retrofit.Builder().baseUrl("https://imdb-api.com/en/API/").addConverterFactory(GsonConverterFactory.create()).build().create(ImdbApiEndPoint.class);
    private static UserDAO userDAO;
    private static MovieDAO movieDAO;
    private static Repository sInstance;
    private final MutableLiveData<List<Movie>> result = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> searchResult = new MutableLiveData<>();

    public interface RepositoryListener {
        void onTopMoviesResponse(LiveData<List<Movie>> top250movies);
        void onSearchResultsExpresionResponse(List<Movie> resultsSearch);

        void onSearchResultsExpresionResponse(LiveData<List<Movie>> resultsSearch);

        public void onMovieDetailResponse (MovieDetail movieDetail);
    }

    public Repository (){

    }

    private Repository(MovieDAO mDAO, UserDAO uDAO) {
        this.movieDAO = mDAO;
        this.userDAO = uDAO;
        //movieDAO.getTop250Movies();
    }

    public synchronized static Repository getInstance(MovieDAO mDAO, UserDAO uDAO) {
        if (sInstance == null) {
            sInstance = new Repository(mDAO, uDAO);
        }
        return sInstance;
    }

     public LiveData<List<Movie>> getTopMovies(){
         Log.i("Iniciando getTopMovies","Iniciando getTopMovies");
         topImdbApiEndPointInterface.getTopMovies().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Get top movies failed");
                }else{
                    if(response.body().getMovies() != null) {
                        result.postValue(response.body().getMovies());
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
        return result;
    }
    public void getMovieDetail(String id, RepositoryListener callback){
        Call<MovieDetail> call = topImdbApiEndPointInterface.getMovieDetail(id);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Get top movies failed");
                }else{
                    if(response.body() != null) {
                        Log.i("Movie", response.body().toString());
                        callback.onMovieDetailResponse(response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
    }
    public void getSearchResults(String title){
        Call<MovieList> call = topImdbApiEndPointInterface.getSearchResults(title);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Search error");
                }else{
                    for(Movie movie : response.body().getMovies())
                        Log.i("Movie", movie.toString());
                }
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
    }

    public LiveData<List<Movie>> getSearchResultsExpresion(String expresion){
        topImdbApiEndPointInterface.getSearchResultsExpresion(expresion).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Search expresion error");
                }else{
                    if(response.body().getResults() != null) {
                        searchResult.postValue(response.body().getResults());
                    }
                }
            }
            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
        return searchResult;
    }
}
