package com.example.android.moviecatalogapp.primary_ui.fragments.favorite.AdapterFavoriteMovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.moviecatalogapp.BuildConfig;
import com.example.android.moviecatalogapp.R;
import com.example.android.moviecatalogapp.model.movie.detail.DetailMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 11/5/2017.
 */

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.ViewHolderItemFavoriteMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<DetailMovie> detailMovieList;
    private ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie;

    public AdapterFavoriteMovie(Context context, List<DetailMovie> detailMovieList,
                                ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie){
        this.context = context;
        this.detailMovieList = detailMovieList;
        this.listenerAdapterFavoriteMovie =listenerAdapterFavoriteMovie;
    }
    @Override
    public ViewHolderItemFavoriteMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_movie,null);
        return new ViewHolderItemFavoriteMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItemFavoriteMovie holder, int position) {
        DetailMovie detailMovie = detailMovieList.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imgViewPosterItemFavoriteMovie);
        holder.txtViewTitleMovieFavoriteMovie.setText(detailMovie.getTitle());
        holder.txtViewDescriptionFavoriteMovie.setText(detailMovie.getOverview());
        holder.txtViewDateReleaseFavoriteMovie.setText(detailMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return detailMovieList.size();
    }

    class ViewHolderItemFavoriteMovie extends RecyclerView.ViewHolder{
        @BindView(R.id.img_view_poster_item_favorite_movie)
        ImageView imgViewPosterItemFavoriteMovie;
        @BindView(R.id.tv_title_item_movie_favorite)
        TextView txtViewTitleMovieFavoriteMovie;
        @BindView(R.id.tv_description_item_movie_favorite)
        TextView txtViewDescriptionFavoriteMovie;
        @BindView(R.id.tv_date_release_item_movie_favorite)
        TextView txtViewDateReleaseFavoriteMovie;

        ViewHolderItemFavoriteMovie(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.btn_detail_item_favorite_movie,
                R.id.btn_share_item_favorite_movie
        })
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn_detail_item_favorite_movie:
                    listenerAdapterFavoriteMovie
                            .onItemClickDetail(detailMovieList.get(getAdapterPosition()));
                    break;
                case R.id.btn_share_item_favorite_movie:
                    listenerAdapterFavoriteMovie
                            .onItemClickShare(detailMovieList.get(getAdapterPosition()));
                    break;
            }
        }
    }

    public interface ListenerAdapterFavoriteMovie{

        void onItemClickDetail(DetailMovie detailMovie);

        void onItemClickShare(DetailMovie detailMovie);
    }
}
