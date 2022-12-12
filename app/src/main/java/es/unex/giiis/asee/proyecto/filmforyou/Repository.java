package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.ImdbApiEndPoint;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieList;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Search;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
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
    private final MovieDAO movieDAO;
    private final RepositoryNetworkDataSource mRepositooryNetwork;
    private static Repository sInstance;
    private final MutableLiveData<List<Movie>> result = new MutableLiveData<>();
    private LiveData<List<Movie>> searchResult = new MutableLiveData<>();

    public void getTopMovies() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.deleteMovies();
                mRepositooryNetwork.getTopMovies();
            }
        });
    }

    public LiveData<List<Movie>>  getSearchResultsExpresion(String expression) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                searchResult = mRepositooryNetwork.getSearchResultsExpresion(expression);
            }
        });
        return  searchResult;
    }

    public LiveData<List<Movie>> getCurrentTopMovies() {
        return movieDAO.getCurrentsMovies();
    }

    public interface RepositoryListener {
        void onTopMoviesResponse(LiveData<List<Movie>> top250movies);
        void onSearchResultsExpresionResponse(List<Movie> resultsSearch);
        void onSearchResultsExpresionResponse(LiveData<List<Movie>> resultsSearch);

        public void onMovieDetailResponse (MovieDetail movieDetail);
    }

    private Repository(MovieDAO mDAO,RepositoryNetworkDataSource mRepositooryNetwork) {
        this.movieDAO = mDAO;
        this.mRepositooryNetwork= mRepositooryNetwork;
        //movieDAO.getTop250Movies();
    }

    public synchronized static Repository getInstance(MovieDAO mDAO,RepositoryNetworkDataSource mRepositooryNetwork) {
        if (sInstance == null) {
            sInstance = new Repository(mDAO,mRepositooryNetwork);
        }
        return sInstance;
    }

}
