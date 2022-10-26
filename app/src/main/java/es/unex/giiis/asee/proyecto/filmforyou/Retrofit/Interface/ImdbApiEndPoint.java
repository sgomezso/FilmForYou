package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImdbApiEndPoint {
    //Key access for the API
    String apiKey= "k_r5m0mrhk";

    //Get top 250 ImdbApiEndPoint
    @GET("Top250Movies/" + apiKey)
    Call<List<Movie>> getTopMovies();

    //Get details of selected Movie
    @GET("Title/" + apiKey + "/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") String id);

    //Get the ImdbApiEndPoint results of the search
    @GET("SearchMovie/" + apiKey + "/{title}")
    Call<List<Movie>> getSearchResults(@Path("title") String id);
}

