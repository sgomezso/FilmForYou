package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.top250movies;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.topMoviesObject;
import retrofit2.Call;
import retrofit2.http.GET;



public interface topMovies {
    //Key access for the API
    String apiKey= "k_r5m0mrhk";

    //Get top 250 movies
    @GET("Top250Movies/" + apiKey)
    Call<topMoviesObject> getTopMovies();
}
