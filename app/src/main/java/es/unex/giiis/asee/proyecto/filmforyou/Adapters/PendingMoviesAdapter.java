package es.unex.giiis.asee.proyecto.filmforyou.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class PendingMoviesAdapter extends RecyclerView.Adapter<PendingMoviesAdapter.MyViewHolder>{

    private List<UserPendingMovies> mDataset;
    private ItemClickListener onItemClickListener;

    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    public interface OnListInteractionListener {
        public void onListInteraction(String url);
    }

    public interface ItemClickListener {
        void onItemClick(Movie movie);
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public OnListInteractionListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView;
        public TextView mYearView;
        public ImageView mImageView;
        public TextView mRanking;
        public View mView;

        public Movie mItem;

        public MyViewHolder(View v) {
            super(v);
            mView = v;
            mTitleView = v.findViewById(R.id.movieTitle);
            mYearView = v.findViewById((R.id.idYear));
            mImageView = v.findViewById(R.id.idImagen);
            mRanking = v.findViewById(R.id.idRanking);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PendingMoviesAdapter(List<UserPendingMovies> myDataset, OnListInteractionListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

//    public MoviesAdapter(List<UserFavoritesMovies> myDataset, OnListInteractionListener listener) {
//        mFavDataset = myDataset;
//        mListener = listener;
//    }

    @Override
    public PendingMoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        // Create new views (invoked by the layout manager)
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_view, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem = mDataset.get(position);
        holder.mTitleView.setText(mDataset.get(position).getTitle());
        holder.mYearView.setText(mDataset.get(position).getYear());
        holder.mRanking.setText(mDataset.get(position).getRank());
        holder.mRanking.setTextColor(Color.YELLOW);
        Picasso.get().load(mDataset.get(position).getImage()).into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListInteraction(holder.mItem.getSvnUrl());
                }

                 */
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void swap(List<UserPendingMovies> dataset) {
        mDataset = dataset;
        notifyDataSetChanged();
    }
}
