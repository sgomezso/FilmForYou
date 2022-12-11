package es.unex.giiis.asee.proyecto.filmforyou.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MostrarMovieActivity;

public class SearchMovieResultsAdapter extends RecyclerView.Adapter<SearchMovieResultsAdapter.MyViewHolder> {
    private List<Movie> mDataset;
    private Movie movieSelected = new Movie();
    private final Repository mRepository = new Repository();

    public interface OnListInteractionListener{
        public void onListInteraction(String url);

        boolean onQueryTextSubmit(String s);

        boolean onQueryTextChange(String s);
    }

    public OnListInteractionListener mListener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView;
        public ImageView mImageView;
        public View mView;

        public Movie mItem;

        public MyViewHolder(View v) {
            super(v);
            mView=v;
            mTitleView = v.findViewById(R.id.movieTitle);
            mImageView = v.findViewById(R.id.idImagen);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchMovieResultsAdapter(List<Movie> myDataset, OnListInteractionListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    @Override
    public SearchMovieResultsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        // Create new views (invoked by the layout manager)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_search, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem = mDataset.get(position);
        holder.mTitleView.setText(mDataset.get(position).getTitle());
        Picasso.get().load(mDataset.get(position).getImage()).into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().networkIO().execute(() -> mRepository.getMovieDetail(holder.mItem.getFullTitle(), new Repository.RepositoryListener() {
                    @Override
                    public void onTopMoviesResponse(LiveData<List<Movie>> top250movies) {}
                    @Override
                    public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {}

                    @Override
                    public void onSearchResultsExpresionResponse(LiveData<List<Movie>> resultsSearch) {

                    }

                    @Override
                    public void onMovieDetailResponse(MovieDetail movieDetail) {
                        movieSelected.setTitle(movieDetail.getTitle());
                        movieSelected.setFullTitle(movieDetail.getFullTitle());
                        movieSelected.setRank("");
                        movieSelected.setImDbRating(movieDetail.getImdbRating());
                        movieSelected.setYear(movieDetail.getYear());
                        movieSelected.setMovieId(movieDetail.getId());
                        movieSelected.setImage(movieDetail.getImage());
                        movieSelected.setCrew(movieDetail.getStars());
                        movieSelected.setImDbRatingCount(movieDetail.getImdbRatingVotes());
//                        movieSelected.setDirectors(movieDetail.getDirectors());

                        Intent intent = new Intent(v.getContext(), MostrarMovieActivity.class);
                        intent.putExtra("Movie", (Serializable) movieSelected);
                        v.getContext().startActivity(intent);
                    }
                }));
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void swap(List<Movie> dataset){
        mDataset = dataset;
        notifyDataSetChanged();
    }
    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }
}
