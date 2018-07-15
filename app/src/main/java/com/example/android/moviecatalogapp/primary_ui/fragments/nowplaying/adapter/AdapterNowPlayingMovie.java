package com.example.android.moviecatalogapp.primary_ui.fragments.nowplaying.adapter;

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
import com.example.android.moviecatalogapp.model.movie.nowplaying.ResultNowPlaying;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 10/10/2017.
 */

public class AdapterNowPlayingMovie extends RecyclerView.Adapter<AdapterNowPlayingMovie.ViewHolderNowPlayingMovie>{
    private final ViewGroup nullParent = null;
    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ResultNowPlaying> resultNowPlayingList;
    private ListenerViewHolderNowPlayingMovie listenerViewHolderNowPlayingMovie;

    public AdapterNowPlayingMovie(Context context, List<ResultNowPlaying> resultNowPlayingList,
                                ListenerViewHolderNowPlayingMovie listenerViewHolderNowPlayingMovie){
        this.context = context;
        this.resultNowPlayingList = resultNowPlayingList;
        this.listenerViewHolderNowPlayingMovie = listenerViewHolderNowPlayingMovie;
    }


    @Override
    public ViewHolderNowPlayingMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, nullParent);
        return new ViewHolderNowPlayingMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderNowPlayingMovie holder, int position) {
        ResultNowPlaying resultNowPlaying = resultNowPlayingList.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + resultNowPlaying.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imgViewPosterItemNowPlaying);
        holder.txtViewTitleMovieNowPlaying.setText(resultNowPlaying.getTitle());
        holder.txtViewDescriptionItemMovieNowPlaying.setText(resultNowPlaying.getOverview());
        holder.txtViewDateReleaseItemMovieNowPlaying.setText(resultNowPlaying.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return resultNowPlayingList.size();
    }

    class ViewHolderNowPlayingMovie extends RecyclerView.ViewHolder{
        @BindView(R.id.img_view_poster_item_now_playing_movie)
        ImageView imgViewPosterItemNowPlaying;

        @BindView(R.id.tv_title_item_movie_now_playing)
        TextView txtViewTitleMovieNowPlaying;

        @BindView(R.id.tv_description_item_movie_now_playing)
        TextView txtViewDescriptionItemMovieNowPlaying;

        @BindView(R.id.tv_date_release_item_movie_now_playing)
        TextView txtViewDateReleaseItemMovieNowPlaying;

        ViewHolderNowPlayingMovie(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.btn_detail_item_now_playing_movie,
                R.id.btn_share_item_now_playing_movie
        })
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn_detail_item_now_playing_movie:
                    listenerViewHolderNowPlayingMovie.onItemClickDetail(resultNowPlayingList.get(getAdapterPosition()));
                    break;
                case R.id.btn_share_item_now_playing_movie:
                    listenerViewHolderNowPlayingMovie.onItemClickShare(resultNowPlayingList.get(getAdapterPosition()));
                    break;
            }
        }

    }

    public interface ListenerViewHolderNowPlayingMovie {

        void onItemClickDetail(ResultNowPlaying resultNowPlaying);

        void onItemClickShare(ResultNowPlaying resultNowPlaying);

    }
}
