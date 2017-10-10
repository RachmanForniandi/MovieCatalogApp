package com.example.android.moviecatalogapp.primary_ui.fragments.search.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.model.movie.search.ResultSearchMovie;

import java.util.List;

/**
 * Created by Lenovo on 9/20/2017.
 */

public class AdapterSearchMovie extends RecyclerView.Adapter<AdapterSearchMovie.ViewHolderSearchMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ResultSearchMovie> listResultSearchMovies;
    private ListenerAdapterSearchMovie listenerAdapterSearchMovie;

    public AdapterSearchMovie(Context context, List<ResultSearchMovie> listResultSearchMovies, ListenerAdapterSearchMovie listenerAdapterSearchMovie) {
        this.context = context;
        this.listResultSearchMovies = listResultSearchMovies;
        this.listenerAdapterSearchMovie = listenerAdapterSearchMovie;
    }


    @Override
    public ViewHolderSearchMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_search, null);
        return new ViewHolderSearchMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSearchMovie holder, int position) {
        ResultSearchMovie resultSearchMovie = listResultSearchMovies.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + resultSearchMovie.getPosterPath())
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_black_24dp))
                .error(ContextCompat.getDrawable(context, R.drawable.ic_broken_image_black_24dp))
                .into(holder.imgViewPosterViewHolderSearchMovie);
        holder.txtViewTitleMovieViewHolderMovieSearch.setText(resultSearchMovie.getTitle());
        holder.txtViewDescriptionMovieViewHolderMovieSearch.setText(resultSearchMovie.getOverview());
        holder.txtViewReleaseDateViewHolderMovieSearch.setText(resultSearchMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return listResultSearchMovies.size();
    }

    public void refreshData(List<ResultSearchMovie> listResultSearchMovies) {
        this.listResultSearchMovies = listResultSearchMovies;
        notifyDataSetChanged();
    }


    class ViewHolderSearchMovie extends RecyclerView.ViewHolder{

        private ImageView imgViewPosterViewHolderSearchMovie;
        private TextView txtViewTitleMovieViewHolderMovieSearch;
        private TextView txtViewDescriptionMovieViewHolderMovieSearch;
        private TextView txtViewReleaseDateViewHolderMovieSearch;

        ViewHolderSearchMovie(View itemView) {
            super(itemView);
            initViews(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerAdapterSearchMovie.onClick(listResultSearchMovies.get(getAdapterPosition()));
                }
            });
        }
        private void initViews(View itemView) {
            imgViewPosterViewHolderSearchMovie = (ImageView) itemView.findViewById(R.id.img_view_poster_item_search_movie);
            txtViewTitleMovieViewHolderMovieSearch = (TextView) itemView.findViewById(R.id.tv_title_item_movie_search);
            txtViewDescriptionMovieViewHolderMovieSearch = (TextView) itemView.findViewById(R.id.tv_description_item_movie_search);
            txtViewReleaseDateViewHolderMovieSearch = (TextView) itemView.findViewById(R.id.tv_date_release_item_movie_search);
        }
    }
        public interface ListenerAdapterSearchMovie{
            void onClick(ResultSearchMovie resultSearchMovie);
        }
    }








