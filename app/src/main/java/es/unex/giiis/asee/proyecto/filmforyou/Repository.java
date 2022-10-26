package es.unex.giiis.asee.proyecto.filmforyou;

import android.util.Log;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.ImdbApiEndPoint;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private ImdbApiEndPoint topImdbApiEndPointInterface = new Retrofit.Builder().baseUrl("https://imdb-api.com/en/API/").addConverterFactory(GsonConverterFactory.create()).build().create(ImdbApiEndPoint.class);
    public interface RepositoryListener {
        public void onTopMoviesResponse (List<Movie> top250movies);
        public  void onMovieDetailResponse (MovieDetail movieDetail);
    }

    public Repository (){

    }

    public void getTopMovies(RepositoryListener callback){
        Call<MovieList> call = topImdbApiEndPointInterface.getTopMovies();
        Log.i("Iniciando getTopMovies","Iniciando getTopMovies");
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Get top movies failed");
                }else{
                    for (Movie movie : response.body().getMovies())
                        Log.i("Movie", movie.getFullTitle());
                    callback.onTopMoviesResponse(response.body().getMovies());
                }
            }
            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
    }
    public void getMovieDetail(String id){
        Call<MovieDetail> call = topImdbApiEndPointInterface.getMovieDetail(id);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", "Get top movies failed");
                }else{
                    Log.i("Movie", response.body().toString());
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
}
