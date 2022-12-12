package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieList;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Search;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImdbApiEndPoint {
    //Key access for the API
    String apiKey= "k_r5m0mrhk";

    //Get top 250 ImdbApiEndPoint
    @GET("Top250Movies/" + apiKey)
    Call<MovieList> getTopMovies();

    //Get details of selected Movie
    @GET("Title/" + apiKey + "/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") String id);

    //Get the ImdbApiEndPoint results of the search
    @GET("SearchMovie/" + apiKey + "/{title}")
    Call<MovieList> getSearchResults(@Path("title") String id);

    @GET("FavoritesMovies/" + apiKey + "/{idFav}")
    Call<UserFavoritesMovies> getFavoritesUserMovies(@Path("idFav") String idFav);

    @GET("PendingMovies/" + apiKey + "/{idPend}")
    Call<UserPendingMovies> getPendingUserMovies(@Path("idPend") String idPend);

    //Get the ImdbApiEndPoint results of the search with title, year, director, ..
    @GET("Search/" + apiKey + "/{expresion}")
    Call<Search> getSearchResultsExpresion(@Path("expresion") String id);
}

