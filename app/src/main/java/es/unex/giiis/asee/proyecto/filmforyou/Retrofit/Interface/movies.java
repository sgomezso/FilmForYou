package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.movieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.searchMovieResults;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.topMoviesObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface movies {
    //Key access for the API
    String apiKey= "k_r5m0mrhk";

    //Get top 250 movies
    @GET("Top250Movies/" + apiKey)
    Call<topMoviesObject> getTopMovies();

    //Get details of selected movie
    @GET("Title/" + apiKey + "/{id}")
    Call<movieDetail> getMovieDetail(@Path("id") String id);

    //Get the movies results of the search
    @GET("SearchMovie/" + apiKey + "/{title}")
    Call<searchMovieResults> getSearchResults(@Path("title") String id);
}

