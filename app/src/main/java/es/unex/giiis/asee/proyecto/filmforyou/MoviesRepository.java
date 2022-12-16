package es.unex.giiis.asee.proyecto.filmforyou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.ImdbApiEndPoint;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.MovieDAO;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private ImdbApiEndPoint topImdbApiEndPointInterface = new Retrofit.Builder().baseUrl("https://imdb-api.com/en/API/").addConverterFactory(GsonConverterFactory.create()).build().create(ImdbApiEndPoint.class);
    private final MovieDAO movieDAO;
    private final RepositoryNetworkDataSource mRepositoryNetwork;
    private static MoviesRepository sInstance;

    public void getTopMovies(MoviesRepositoryListener callback) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = movieDAO.getCurrentsMovies();
                if (movies.size() > 0) {
                    callback.onMoviesResult(movies);
                } else {
                    getRemoteMovies(callback);
                }
            }
        });
    }

    private void getRemoteMovies(MoviesRepositoryListener callback) {
        mRepositoryNetwork.getTopMovies(new MoviesRepositoryListener() {
            @Override
            public void onMoviesResult(List<Movie> movies) {
                movieDAO.insertMovies(movies);
                callback.onMoviesResult(movies);
            }

            @Override
            public void onMovieFailure() {
                callback.onMovieFailure();
            }
        });
    }

    public void getSearchResultsExpresion(String expression, MoviesRepositoryListener callback) {
        mRepositoryNetwork.getSearchResultsExpresion(expression, new MoviesRepositoryListener() {
            @Override
            public void onMoviesResult(List<Movie> movies) {
                callback.onMoviesResult(movies);
            }

            @Override
            public void onMovieFailure() {
                callback.onMovieFailure();
            }
        });
    }

    public void removeMovies() {
        movieDAO.deleteMovies();
    }

//    public LiveData<List<Movie>> getCurrentTopMovies() {
//        return movieDAO.getCurrentsMovies();
//    }

    public interface RepositoryListener2 {
        void onTopMoviesResponse(List<Movie> top250movies);

        void onSearchResultsExpresionResponse(List<Movie> resultsSearch);

        void onSearchResultsExpresionResponse(LiveData<List<Movie>> resultsSearch);

        public void onMovieDetailResponse(MovieDetail movieDetail);
    }

    private MoviesRepository(MovieDAO mDAO, RepositoryNetworkDataSource mRepositooryNetwork) {
        this.movieDAO = mDAO;
        this.mRepositoryNetwork = mRepositooryNetwork;
        //movieDAO.getTop250Movies();
    }

    public synchronized static MoviesRepository getInstance(MovieDAO mDAO, RepositoryNetworkDataSource mRepositooryNetwork) {
        if (sInstance == null) {
            sInstance = new MoviesRepository(mDAO, mRepositooryNetwork);
        }
        return sInstance;
    }

    public void updateMovie(Movie movie) {
            movieDAO.updateMovie(movie);
    }
}
