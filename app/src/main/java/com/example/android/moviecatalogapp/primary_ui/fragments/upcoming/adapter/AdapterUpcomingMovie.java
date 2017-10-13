package com.example.android.moviecatalogapp.primary_ui.fragments.upcoming.adapter;

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
import com.example.android.moviecatalogapp.model.movie.upcoming.ResultUpcomingMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AdapterUpcomingMovie extends RecyclerView.Adapter<AdapterUpcomingMovie.ViewHolderItemUpcomingMovie>{
    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ResultUpcomingMovie> resultUpcomingMovieList;
    private ListenerAdapterUpcomingMovie listenerAdapterUpcomingMovie;

    public AdapterUpcomingMovie(Context context, List<ResultUpcomingMovie> resultUpcomingMovieList,
                                ListenerAdapterUpcomingMovie listenerAdapterUpcomingMovie){
        this.context = context;
        this.resultUpcomingMovieList = resultUpcomingMovieList;
        this.listenerAdapterUpcomingMovie = listenerAdapterUpcomingMovie;
    }

    @Override
    public ViewHolderItemUpcomingMovie onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_movie, null);
        return new ViewHolderItemUpcomingMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItemUpcomingMovie holder, int position) {
        ResultUpcomingMovie resultUpcomingMovie = resultUpcomingMovieList.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + resultUpcomingMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imgViewPosterItemUpcomingMovie);
        holder.txtViewTitleItemUpcomingMovie.setText(resultUpcomingMovie.getTitle());
        holder.txtViewDescriptionItemUpcomingMovie.setText(resultUpcomingMovie.getOverview());
        holder.txtViewDateReleaseItemUpcomingMovie.setText(resultUpcomingMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return resultUpcomingMovieList.size();
    }

    class ViewHolderItemUpcomingMovie extends RecyclerView.ViewHolder{
        @BindView(R.id.img_view_poster_item_upcoming_movie)
        ImageView imgViewPosterItemUpcomingMovie;

        @BindView(R.id.tv_title_item_movie_upcoming)
        TextView txtViewTitleItemUpcomingMovie;

        @BindView(R.id.tv_description_item_movie_upcoming)
        TextView txtViewDescriptionItemUpcomingMovie;

        @BindView(R.id.tv_date_release_item_movie_upcoming)
        TextView txtViewDateReleaseItemUpcomingMovie;

        public ViewHolderItemUpcomingMovie(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.btn_detail_item_upcoming_movie,
                R.id.btn_share_item_upcoming_movie
        })

        public void onClick(View view){
            switch (view.getId()){
                case R.id.btn_detail_item_upcoming_movie:
                    listenerAdapterUpcomingMovie.onItemClickDetail(resultUpcomingMovieList.get(getAdapterPosition()));
                    break;
                case R.id.btn_share_item_upcoming_movie:
                    listenerAdapterUpcomingMovie.onItemClickShare(resultUpcomingMovieList.get(getAdapterPosition()));
                    break;
            }
        }
    }
    public interface ListenerAdapterUpcomingMovie{
        void onItemClickDetail(ResultUpcomingMovie resultUpcomingMovie);

        void onItemClickShare(ResultUpcomingMovie resultUpcomingMovie);
    }

}
